package com.udeGames.animationStudios.apis.imGui.panels;

import com.udeGames.animationStudios.apis.imGui.components.Transform;

public class InspectorPanel extends ImGuiPanel {

    private final Transform transform = new Transform();

    public InspectorPanel() {
        super("Inspector");
    }

    @Override
    public void render() {
        transform.renderToPanel();
    }
}
