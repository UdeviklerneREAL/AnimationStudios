package com.udeGames.animationStudios.imGui;

import com.udeGames.animationStudios.imGui.panels.*;
import com.udeGames.animationStudios.saving.SLSImplementation;
import imgui.ImGuiViewport;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGui;
import imgui.internal.flag.ImGuiDockNodeFlags;
import imgui.type.ImBoolean;

import java.util.HashMap;

public class ImGuiUI {
    private final HashMap<String, ImBoolean> imBooleanHashMap = new HashMap<>();
    private final VideoPlayerPanel videoPlayerPanel = new VideoPlayerPanel();
    private final ImportsPanel importsPanel = new ImportsPanel();
    private final InspectorPanel inspectorPanel = new InspectorPanel();
    private final VideoTimelinePanel videoTimelinePanel = new VideoTimelinePanel();
    private final AnimatorPanel animatorPanel = new AnimatorPanel();
    private final KeyframeAnimationPanel keyframeAnimationPanel = new KeyframeAnimationPanel();

    public ImGuiUI() {
        ImBoolean current = SLSImplementation.loadImGuiLayout("videoPlayer");
        imBooleanHashMap.put("videoPlayer", current == null ? new ImBoolean(true) : current);
        current = SLSImplementation.loadImGuiLayout("imports");
        imBooleanHashMap.put("imports", current == null ? new ImBoolean(true) : current);
        current = SLSImplementation.loadImGuiLayout("inspector");
        imBooleanHashMap.put("inspector", current == null ? new ImBoolean(true) : current);
        current = SLSImplementation.loadImGuiLayout("videoTimeline");
        imBooleanHashMap.put("videoTimeline", current == null ? new ImBoolean(true) : current);

        current = SLSImplementation.loadImGuiLayout("keyframeAnimation");
        imBooleanHashMap.put("keyframeAnimation", current == null ? new ImBoolean(false) : current);
        current = SLSImplementation.loadImGuiLayout("animator");
        imBooleanHashMap.put("animator", current == null ? new ImBoolean(false) : current);
    }

    public void render() {
        final int dockspaceId = ImGui.getID("space");
        showDockSpace(dockspaceId);
        if (imBooleanHashMap.get("videoPlayer").get()) {
            renderPanel(videoPlayerPanel);
        }
        if (imBooleanHashMap.get("imports").get()) {
            renderPanel(importsPanel);
        }
        if (imBooleanHashMap.get("inspector").get()) {
            renderPanel(inspectorPanel);
        }
        if (imBooleanHashMap.get("videoTimeline").get()) {
            renderPanel(videoTimelinePanel);
        }
        if (imBooleanHashMap.get("keyframeAnimation").get()) {
            renderPanel(keyframeAnimationPanel);
        }
        if (imBooleanHashMap.get("animator").get()) {
            renderPanel(animatorPanel);
        }

        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("File")) {
                if (ImGui.menuItem("New", "Ctrl+N")) {
                    System.out.println("New");
                }

                if (ImGui.menuItem("Open...", "Ctrl+O")) {

                }

                if (ImGui.menuItem("Save...", "Ctrl+S")) {

                }

                if (ImGui.menuItem("Save As...", "Ctrl+Shift+S")) {
                    System.out.println("Save As");
                }
                ImGui.endMenu();
            }
            if (ImGui.beginMenu("Windows")) {
                if (ImGui.beginMenu("Animation")) {
                    if (ImGui.menuItem("Keyframe Animation", "", imBooleanHashMap.get("keyframeAnimation"))) {
                        renderPanel(keyframeAnimationPanel);
                    }

                    if (ImGui.menuItem("Animator", "", imBooleanHashMap.get("animator"))) {
                        renderPanel(animatorPanel);
                    }
                    ImGui.endMenu();
                }
                if (ImGui.beginMenu("Default")) {
                    if (ImGui.menuItem("Video Player", "", imBooleanHashMap.get("videoPlayer"))) {
                        renderPanel(videoPlayerPanel);
                    }

                    if (ImGui.menuItem("Video Timeline", "", imBooleanHashMap.get("videoTimeline"))) {
                        renderPanel(videoTimelinePanel);
                    }

                    if (ImGui.menuItem("Imports", "", imBooleanHashMap.get("imports"))) {
                        renderPanel(importsPanel);
                    }

                    if (ImGui.menuItem("Inspector", "", imBooleanHashMap.get("inspector"))) {
                        renderPanel(inspectorPanel);
                    }
                    ImGui.endMenu();
                }
                ImGui.endMenu();
            }

            ImGui.endMainMenuBar();
        }
    }

    public void dispose() {
        SLSImplementation.saveImGUILayout(imBooleanHashMap);
    }

    private void showDockSpace(final int dockspaceId) {
        final ImGuiViewport mainViewport = ImGui.getMainViewport();
        final int windowFlags = ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize
                | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus | ImGuiWindowFlags.NoBackground;

        ImGui.setNextWindowPos(mainViewport.getWorkPosX(), mainViewport.getWorkPosY());
        ImGui.setNextWindowSize(mainViewport.getWorkSizeX(), mainViewport.getWorkSizeY());
        ImGui.setNextWindowViewport(mainViewport.getID());
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0);

        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0, 0);
        ImGui.begin("Dockspace Demo", windowFlags);
        ImGui.popStyleVar(3);

        ImGui.dockSpace(dockspaceId, 0, 0, ImGuiDockNodeFlags.PassthruCentralNode);
        ImGui.end();
    }

    private void renderPanel(ImGuiPanel imGuiPanel) {
        imGuiPanel.renderToImGui();
    }
}