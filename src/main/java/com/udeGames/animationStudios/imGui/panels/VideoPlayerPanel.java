package com.udeGames.animationStudios.imGui.panels;

import imgui.ImGui;
import imgui.type.ImBoolean;

public class VideoPlayerPanel extends ImGuiPanel {

    private final ImBoolean showDemoWindow = new ImBoolean();

    public VideoPlayerPanel() {
        super("Video Player");
    }

    @Override
    public void render() {
        ImGui.checkbox("Show Demo Window", showDemoWindow);
        if (ImGui.button("Play")) {

        }
        if (showDemoWindow.get()) {
            ImGui.showDemoWindow(showDemoWindow);
        }
    }
}
