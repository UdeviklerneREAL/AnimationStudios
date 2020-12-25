package com.udeGames.animationStudios.apis.imGui.panels;

import com.udeGames.animationStudios.Statics;
import com.udeGames.animationStudios.apis.imGui.ImGuiObject;
import com.udeGames.animationStudios.apis.imGui.ImGuiPanel;
import com.udeGames.animationStudios.renderering.Texture;
import com.udeGames.animationStudios.saving.GSONImplementation;
import imgui.internal.ImGui;

import java.util.ArrayList;

public class VideoTimelinePanel extends ImGuiPanel {

    private static ImGuiObject selectedObject;
    private static final ArrayList<ImGuiObject> videos = new ArrayList<>();

    public VideoTimelinePanel() {
        super("Video Timeline");
    }

    @Override
    public void render() {
        ImGui.beginChild("drag ´n drop target");
        for (ImGuiObject object : videos) {
            if (ImGui.imageButton(object.getTexture().getId(), object.getTexture().getWidth() / 20.0f, object.getTexture().getHeight() / 20.0f)) {
                selectedObject = object;
            }
            ImGui.sameLine();
        }
        ImGui.endChild();
        for (int i = 0; i <= ImportsPanel.getTextureArrayListLength(); i++) {
            if (ImGui.beginDragDropTarget()) {
                Object payload = ImGui.acceptDragDropPayloadObject("image" + i);
                if (payload != null) {
                    videos.add(new ImGuiObject(Texture.getTextureById((int)payload)));
                }
                ImGui.endDragDropTarget();
            }
        }
    }

    @Override
    public void dispose() {
        if (!Statics.getProjectName().equals("")) {
            GSONImplementation.toJson(videos, Statics.getProjectPath() + "/.animationStudios/timeline.json");
        }
    }

    public static ImGuiObject getSelectedObject() {
        if (selectedObject == null) {
            selectedObject = new ImGuiObject();
        }

        return selectedObject;
    }
}
