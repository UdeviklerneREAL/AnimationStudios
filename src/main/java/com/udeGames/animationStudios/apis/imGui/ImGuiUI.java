package com.udeGames.animationStudios.apis.imGui;

import com.udeGames.animationStudios.apis.imGui.panels.*;
import com.udeGames.animationStudios.saving.Dialog;
import com.udeGames.animationStudios.Statics;
import com.udeGames.animationStudios.saving.SLSImplementation;
import com.udeGames.easySwing.JEasyAdvancedLayoutManager;
import com.udeGames.easySwing.JEasyLayout;
import com.udeGames.easySwing.JEasyLayoutManager;
import com.udeGames.saveLoadSystem.SLSMain;
import imgui.ImGuiViewport;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.internal.ImGui;
import imgui.internal.flag.ImGuiDockNodeFlags;
import imgui.type.ImBoolean;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class ImGuiUI {
    private final HashMap<String, ImBoolean> imBooleanHashMap = new HashMap<>();
    private final HashMap<String, ImGuiPanel> imGuiPanelHashMap = new HashMap<>();

    private static final ImBoolean imBoolean = new ImBoolean(true);

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
        final int dockSpaceId = ImGui.getID("space");
        showDockSpace(dockSpaceId);
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
                    String dialog = Dialog.openFolderDialog();
                    if (!dialog.equals("")) {
                        JEasyLayout jEasyLayout = new JEasyLayout("New Project", (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2));
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                            e.printStackTrace();
                        }
                        jEasyLayout.setup();
                        jEasyLayout.setMinimumSize(new Dimension(215, 190));
                        jEasyLayout.toFront();
                        jEasyLayout.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        jEasyLayout.add(new JLabel("Project Name:"), 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, -40, -80));
                        JTextField _jTextField = new JTextField();
                        JButton _jButton = new JButton("Create");
                        jEasyLayout.add(_jTextField, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, 40, -80));
                        jEasyLayout.add(_jButton, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.BUTTON, 0, -30));
                        _jButton.addActionListener(e -> {
                            if (!_jTextField.getText().equals("")) {
                                File projectFolder = new File(dialog + "/.animationStudios/");

                                if (!projectFolder.exists()) {
                                    projectFolder.mkdirs();
                                }

                                Statics.setProjectPath(dialog);
                                Statics.setProjectName(_jTextField.getText());

                                new SLSMain(projectFolder.getPath(), SLSMain.FileOrDir.CREATEDIR).encodeString(Statics.getProjectName(), "projectName", "project.saveLoadSystem");

                                jEasyLayout.dispose();

                                onNewScene();
                            }
                        });
                    }
                }

                if (ImGui.menuItem("Open...", "Ctrl+O")) {
                    String dialog = Dialog.openFolderDialog();
                    if (!dialog.equals("")) {
                        Statics.setProjectPath(dialog);
                        Statics.setProjectName(new SLSMain(dialog + "/.animationStudios/", SLSMain.FileOrDir.CREATEDIR).decodeString("projectName", "project.saveLoadSystem"));
                        onNewScene();
                    }
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

    public void onNewScene() {
        for (ImGuiPanel imGuiPanel : imGuiPanelHashMap.values()) {
            imGuiPanel.onOpenNewProject();
        }
    }

    public void dispose() {
        SLSImplementation.saveImGUILayout(imBooleanHashMap);
        for (ImGuiPanel panel : imGuiPanelHashMap.values()) {
            panel.dispose();
        }
    }

    private void showDockSpace(final int dockSpaceId) {
        final ImGuiViewport mainViewport = ImGui.getMainViewport();
        int windowFlags = ImGuiWindowFlags.MenuBar | ImGuiWindowFlags.NoDocking;

        ImGuiViewport viewport = ImGui.getMainViewport();

        ImGui.setNextWindowPos(viewport.getPosX(), viewport.getPosY());
        ImGui.setNextWindowSize(mainViewport.getWorkSizeX(), mainViewport.getWorkSizeY());
        ImGui.setNextWindowViewport(mainViewport.getID());
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0);
        windowFlags |= ImGuiWindowFlags.NoTitleBar |ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove;
        windowFlags |= ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus;
        windowFlags |= ImGuiWindowFlags.NoBackground;

        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0, 0);
        ImGui.begin("Dockspace Demo", imBoolean, windowFlags);
        ImGui.popStyleVar(3);

        ImGui.dockSpace(dockSpaceId, 0, 0, ImGuiDockNodeFlags.PassthruCentralNode);
        ImGui.end();
    }

    private void renderPanel(ImGuiPanel imGuiPanel) {
        imGuiPanel.renderToImGui();
    }
}