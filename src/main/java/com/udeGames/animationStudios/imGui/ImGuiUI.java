package com.udeGames.animationStudios.imGui;

import com.udeGames.animationStudios.imGui.panels.*;
import com.udeGames.animationStudios.saving.Dialog;
import com.udeGames.animationStudios.saving.SLSImplementation;
import imgui.ImGuiViewport;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGui;
import imgui.internal.flag.ImGuiDockNodeFlags;
import imgui.type.ImBoolean;

import java.util.Arrays;
import java.util.HashMap;

public class ImGuiUI {
    private final HashMap<String, ImBoolean> imBooleanHashMap = new HashMap<>();
    private final HashMap<String, ImGuiPanel> imGuiPanelHashMap = new HashMap<>();

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

        imGuiPanelHashMap.put("videoPlayerPanel", new VideoPlayerPanel());
        imGuiPanelHashMap.put("importsPanel", new ImportsPanel());
        imGuiPanelHashMap.put("inspectorPanel", new InspectorPanel());
        imGuiPanelHashMap.put("videoTimelinePanel", new VideoTimelinePanel());
        imGuiPanelHashMap.put("keyframeAnimationPanel", new KeyframeAnimationPanel());
        imGuiPanelHashMap.put("animatorPanel", new AnimatorPanel());
    }

    public void render() {
        final int dockspaceId = ImGui.getID("space");
        showDockSpace(dockspaceId);
        if (imBooleanHashMap.get("videoPlayer").get()) {
            renderPanel(imGuiPanelHashMap.get("videoPlayerPanel"));
        }
        if (imBooleanHashMap.get("imports").get()) {
            renderPanel(imGuiPanelHashMap.get("importsPanel"));
        }
        if (imBooleanHashMap.get("inspector").get()) {
            renderPanel(imGuiPanelHashMap.get("inspectorPanel"));
        }
        if (imBooleanHashMap.get("videoTimeline").get()) {
            renderPanel(imGuiPanelHashMap.get("videoTimelinePanel"));
        }
        if (imBooleanHashMap.get("keyframeAnimation").get()) {
            renderPanel(imGuiPanelHashMap.get("keyframeAnimationPanel"));
        }
        if (imBooleanHashMap.get("animator").get()) {
            renderPanel(imGuiPanelHashMap.get("animatorPanel"));
        }

        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("File")) {
                if (ImGui.menuItem("New", "Ctrl+N")) {
                    Dialog.openFolderDialog();
                }

                if (ImGui.menuItem("Open...", "Ctrl+O")) {
                    Dialog.openFolderDialog();
                }

                if (ImGui.menuItem("Save...", "Ctrl+S")) {
                    //TODO: save
                }

                if (ImGui.menuItem("Save As...", "Ctrl+Shift+S")) {
                    //TODO: save as
                }

                if (ImGui.menuItem("Import...", "Ctrl+I")) {
                    String[] stringArray = Dialog.openMultipleFileDialog("mp4,mov,flv,avi,png,jpg,jpeg,gif,mp3,wav");
                    ImportsPanel.add(stringArray);
                }
                ImGui.endMenu();
            }
            if (ImGui.beginMenu("Windows")) {
                if (ImGui.beginMenu("Animation")) {
                    if (ImGui.menuItem("Keyframe Animation", "", imBooleanHashMap.get("keyframeAnimation"))) {
                        renderPanel(imGuiPanelHashMap.get("keyframeAnimationPanel"));
                    }

                    if (ImGui.menuItem("Animator", "", imBooleanHashMap.get("animator"))) {
                        renderPanel(imGuiPanelHashMap.get("animatorPanel"));
                    }
                    ImGui.endMenu();
                }
                if (ImGui.beginMenu("Default")) {
                    if (ImGui.menuItem("Video Player", "", imBooleanHashMap.get("videoPlayer"))) {
                        renderPanel(imGuiPanelHashMap.get("videoPlayerPanel"));
                    }

                    if (ImGui.menuItem("Video Timeline", "", imBooleanHashMap.get("videoTimeline"))) {
                        renderPanel(imGuiPanelHashMap.get("videoTimelinePanel"));
                    }

                    if (ImGui.menuItem("Imports", "", imBooleanHashMap.get("imports"))) {
                        renderPanel(imGuiPanelHashMap.get("importsPanel"));
                    }

                    if (ImGui.menuItem("Inspector", "", imBooleanHashMap.get("inspector"))) {
                        renderPanel(imGuiPanelHashMap.get("inspectorPanel"));
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