package com.udeGames.animationStudios.apis.imGui;

import imgui.internal.ImGui;
import imgui.type.ImBoolean;

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

    public void renderToImGui(ImBoolean imBoolean) {
        ImGui.begin(name, imBoolean);

        render();

        ImGui.end();
    }

    public abstract void render();

    public void onOpenNewProject() {}

    public void dispose() {}
}
