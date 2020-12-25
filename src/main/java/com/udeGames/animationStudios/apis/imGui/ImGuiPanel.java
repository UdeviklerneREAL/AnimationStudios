package com.udeGames.animationStudios.apis.imGui.panels;

import imgui.internal.ImGui;

public abstract class ImGuiPanel {

    private final String name;

    protected ImGuiPanel(String name) {
        this.name = name;
    }

    public void renderToImGui() {
        ImGui.begin(name);

        render();

        ImGui.end();
    }

    public abstract void render();

    public void onOpenNewProject() {}

    public void dispose() {}
}
