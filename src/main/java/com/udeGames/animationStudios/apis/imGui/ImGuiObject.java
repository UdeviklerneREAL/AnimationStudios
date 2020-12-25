package com.udeGames.animationStudios.apis.imGui;

import com.udeGames.animationStudios.apis.imGui.components.TransformComponent;
import com.udeGames.animationStudios.renderering.Texture;

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

    public <T extends ImGuiComponent> T addComponent(T component) {
        imGuiComponents.add(component);
        return component;
    }

    public ArrayList<ImGuiComponent> getImGuiComponents() {
        return imGuiComponents;
    }
}
