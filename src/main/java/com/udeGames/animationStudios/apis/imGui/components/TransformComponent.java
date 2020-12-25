package com.udeGames.animationStudios.apis.imGui.components;

import com.udeGames.animationStudios.apis.imGui.ImGuiExtension;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform extends ImGuiComponents {

    private final Vector2f position = new Vector2f(0.0f, 0.0f);
    private final Vector3f rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    private final Vector2f scale = new Vector2f(0.0f, 0.0f);

    public Transform() {
        super("Transform");
    }

    @Override
    public void render() {
        ImGuiExtension.drawVec2("Position", position);
        ImGuiExtension.drawVec3("Rotation", rotation);
        ImGuiExtension.drawVec2("Scale", scale);
    }
}
