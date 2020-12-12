package com.udeGames.animationStudios.apis.imGui.components;

import imgui.internal.ImGui;

public abstract class ImGuiComponents {

    private final String name;

    protected ImGuiComponents(String name) {
        this.name = name;
    }

    public void renderToPanel() {
        if (ImGui.collapsingHeader(name)) {
            render();
        }
    }

    public abstract void render();
}
