package com.udeGames.animationStudios.apis.imGui.panels;

import com.udeGames.animationStudios.apis.imGui.ImGuiComponent;
import com.udeGames.animationStudios.apis.imGui.ImGuiPanel;
import com.udeGames.animationStudios.apis.imGui.ImGuiUI;
import imgui.internal.ImGui;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class AddComponentPanel extends ImGuiPanel {

    private final ArrayList<ImGuiComponent> components = new ArrayList<>();
    private final InspectorPanel inspectorPanel;

    public AddComponentPanel(InspectorPanel inspectorPanel) {
        super("Add Component");
        this.inspectorPanel = inspectorPanel;
        Reflections reflections = new Reflections("com.udeGames.animationStudios.apis.imGui");
        Set<Class<? extends ImGuiComponent>> classes = reflections.getSubTypesOf(ImGuiComponent.class);
        for (Class<? extends ImGuiComponent> extension : classes) {
            try {
                components.add(extension.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void render() {
        for (ImGuiComponent component : components) {
            if (component.getName().equals("Transform")) {
                continue;
            }

            if (ImGui.button(component.getName(), ImGui.getWindowWidth(), ImGui.getWindowWidth() / 20)) {
                inspectorPanel.addComponent(component);
                ImGuiUI.setAddComponentBoolean(false);
            }
        }
    }
}
