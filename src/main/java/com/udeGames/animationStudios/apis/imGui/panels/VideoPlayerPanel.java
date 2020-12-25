package com.udeGames.animationStudios.apis.imGui.panels;

import com.udeGames.animationStudios.Statics;
import com.udeGames.animationStudios.apis.imGui.ImGuiPanel;
import com.udeGames.animationStudios.renderering.AnimationFramebuffer;
import imgui.ImVec2;
import imgui.internal.ImGui;
import org.joml.Vector2f;

public class VideoPlayerPanel extends ImGuiPanel {

    private boolean firstTime = true;

    public VideoPlayerPanel() {
        super("Video Player");
    }

    @Override
    public void render() {
        if (!Statics.getProjectName().equals("")) {
            if (firstTime) {
                AnimationFramebuffer.init();
                firstTime = false;
            }

            Vector2f windowSize = getLargestSizeForViewport();
            Vector2f windowPos = getCenteredPositionForViewport(windowSize);

            ImGui.setCursorPos(windowPos.x, windowPos.y);

            int textureId = AnimationFramebuffer.getFramebuffer().getTextureId();

            ImGui.image(textureId, windowSize.x, windowSize.y, 0, 1, 1, 0);
        }
    }

    private Vector2f getLargestSizeForViewport() {
        ImVec2 windowSize = new ImVec2();
        imgui.ImGui.getContentRegionAvail(windowSize);
        windowSize.x -= imgui.ImGui.getScrollX();
        windowSize.y -= imgui.ImGui.getScrollY();

        float aspectWidth = windowSize.x;
        float aspectHeight = aspectWidth / ((float)Statics.getWidth() / (float)Statics.getHeight());
        if (aspectHeight > windowSize.y) {
            aspectHeight = windowSize.y;
            aspectWidth = aspectHeight * ((float)Statics.getWidth() / (float)Statics.getHeight());
        }

        return new Vector2f(aspectWidth, aspectHeight);
    }

    private Vector2f getCenteredPositionForViewport(Vector2f aspectSize) {
        ImVec2 windowSize = new ImVec2();
        imgui.ImGui.getContentRegionAvail(windowSize);
        windowSize.x -= imgui.ImGui.getScrollX();
        windowSize.y -= imgui.ImGui.getScrollY();

        float viewportX = (windowSize.x / 2.0f) - (aspectSize.x / 2.0f);
        float viewportY = (windowSize.y / 2.0f) - (aspectSize.y / 2.0f);

        return new Vector2f(viewportX + ImGui.getCursorPosX(), viewportY + ImGui.getCursorPosY());
    }
}
