package com.udeGames.animationStudios.apis.imGui.components;

import com.udeGames.animationStudios.apis.imGui.ImGuiComponent;
import com.udeGames.animationStudios.apis.imGui.ImGuiExtension;
import org.joml.Vector3f;

public class TransformComponent extends ImGuiComponent {

    private boolean add = true;
    private final Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);
    private final Vector3f rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    private final Vector3f scale = new Vector3f(1.0f, 1.0f, 1.0f);

    public TransformComponent() {
        super("Transform");
    }

    @Override
    public void render() {
        ImGuiExtension.drawVec3("Position", position, 0.0f);
        ImGuiExtension.drawVec3("Rotation", rotation, 0.0f);
        ImGuiExtension.drawVec3("Scale", scale, 1.0f);
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
