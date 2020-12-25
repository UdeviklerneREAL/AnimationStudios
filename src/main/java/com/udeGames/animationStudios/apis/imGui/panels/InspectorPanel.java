package com.udeGames.animationStudios.apis.imGui.panels;

import com.udeGames.animationStudios.apis.imGui.ImGuiComponent;
import com.udeGames.animationStudios.apis.imGui.ImGuiPanel;
import com.udeGames.animationStudios.apis.imGui.ImGuiUI;
import com.udeGames.animationStudios.apis.imGui.components.TextureComponent;
import com.udeGames.animationStudios.apis.imGui.components.TransformComponent;
import imgui.internal.ImGui;

import java.util.ArrayList;
import java.util.HashMap;

public class InspectorPanel extends ImGuiPanel {

    public InspectorPanel() {
        super("Inspector");
    }

    @Override
    public void render() {
        for (ImGuiComponent imGuiComponent : VideoTimelinePanel.getSelectedObject().getImGuiComponents()) {
            imGuiComponent.renderToPanel(() -> {});
        }

        if (VideoTimelinePanel.getSelectedObject().getTexture() != null) {
            if (ImGui.beginPopupContextWindow()) {
                if (ImGui.menuItem("Add Component..")) {
                    ImGuiUI.setAddComponentBoolean(true);
                }
                ImGui.endPopup();
            }
        }
    }

    public void addComponent(ImGuiComponent imGuiComponent) {
        VideoTimelinePanel.getSelectedObject().getImGuiComponents().add(imGuiComponent);
    }
}
