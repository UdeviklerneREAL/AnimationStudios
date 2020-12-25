package com.udeGames.animationStudios.apis.imGui;

import com.udeGames.animationStudios.basic.Lambda;
import imgui.internal.ImGui;

public abstract class ImGuiComponent {

    private final String name;

    public ImGuiComponent(String name) {
        this.name = name;
    }

    public void renderToPanel(Lambda lambda) {
        if (ImGui.collapsingHeader(name)) {
            render();
            lambda.method();
        }
    }

    public abstract void render();

    public String getName() {
        return name;
    }
}
