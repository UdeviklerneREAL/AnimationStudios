package com.udeGames.animationStudios.apis.imGui;

import com.udeGames.animationStudios.apis.imGui.components.TransformComponent;
import com.udeGames.animationStudios.apis.imGui.panels.VideoTimelinePanel;
import com.udeGames.animationStudios.rendering.Texture;
import org.joml.Quaternionf;

import java.util.ArrayList;

public class ImGuiObject {
    private Texture texture;
    private final ArrayList<ImGuiComponent> imGuiComponents;

    public ImGuiObject() {
        imGuiComponents = new ArrayList<>();
    }

    public ImGuiObject(Texture texture) {
        this.texture = texture;
        imGuiComponents = new ArrayList<>();
        imGuiComponents.add(new TransformComponent());
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public ImGuiComponent addComponent(ImGuiComponent component) {
        imGuiComponents.add(component);
        return component;
    }

    public ImGuiComponent getComponent(Class<? extends ImGuiComponent> imGuiComponentClass) {
        for (ImGuiComponent imGuiComponent : VideoTimelinePanel.getSelectedObject().getImGuiComponents()) {
            if (imGuiComponent.getClass() == imGuiComponentClass) {
                return imGuiComponent;
            }
        }
        return null;
    }

    public ArrayList<ImGuiComponent> getImGuiComponents() {
        return imGuiComponents;
    }
}
