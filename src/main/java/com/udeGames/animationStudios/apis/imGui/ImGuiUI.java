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

    private static ImBoolean addComponentBoolean = new ImBoolean(false);
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

        imGuiPanelHashMap.put("addComponentPanel", new AddComponentPanel((InspectorPanel) imGuiPanelHashMap.get("inspectorPanel")));
    }

    public void render() {
        final int dockSpaceId = ImGui.getID("space");
        showDockSpace(dockSpaceId);
        if (imBooleanHashMap.get("videoPlayer").get()) {
            renderPanel(imGuiPanelHashMap.get("videoPlayerPanel"), imBooleanHashMap.get("videoPlayer"));
        }
        if (imBooleanHashMap.get("imports").get()) {
            renderPanel(imGuiPanelHashMap.get("importsPanel"), imBooleanHashMap.get("imports"));
        }
        if (imBooleanHashMap.get("inspector").get()) {
            renderPanel(imGuiPanelHashMap.get("inspectorPanel"), imBooleanHashMap.get("inspector"));
        }
        if (imBooleanHashMap.get("videoTimeline").get()) {
            renderPanel(imGuiPanelHashMap.get("videoTimelinePanel"), imBooleanHashMap.get("videoTimeline"));
        }
        if (imBooleanHashMap.get("keyframeAnimation").get()) {
            renderPanel(imGuiPanelHashMap.get("keyframeAnimationPanel"), imBooleanHashMap.get("keyframeAnimation"));
        }
        if (imBooleanHashMap.get("animator").get()) {
            renderPanel(imGuiPanelHashMap.get("animatorPanel"), imBooleanHashMap.get("animator"));
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
                        jEasyLayout.setMinimumSize(new Dimension(215, 220));
                        jEasyLayout.toFront();
                        jEasyLayout.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        jEasyLayout.add(new JLabel("Project Name:"), 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, -40, -80));
                        jEasyLayout.add(new JLabel("Project Width:"), 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, -40, -40));
                        jEasyLayout.add(new JLabel("Project Height:"), 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, -40, 0));
                        JTextField _jTextField = new JTextField();
                        JTextField _jTextField1 = new JTextField();
                        JTextField _jTextField2 = new JTextField();
                        JButton _jButton = new JButton("Create");
                        jEasyLayout.add(_jTextField, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, 40, -80));
                        jEasyLayout.add(_jTextField1, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, 40, -40));
                        jEasyLayout.add(_jTextField2, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.CENTER, 40, 0));
                        jEasyLayout.add(_jButton, 100, 30, new JEasyAdvancedLayoutManager(JEasyLayoutManager.BUTTON, 0, -30));
                        _jButton.addActionListener(e -> {
                            if (!_jTextField.getText().equals("")) {
                                File projectFolder = new File(dialog + "/.animationStudios/");

                                if (!projectFolder.exists()) {
                                    projectFolder.mkdirs();
                                }

                                Statics.setProjectPath(dialog);
                                Statics.setProjectName(_jTextField.getText());
                                try {
                                    Statics.setWidth(Integer.parseInt(_jTextField1.getText()));
                                    Statics.setHeight(Integer.parseInt(_jTextField2.getText()));
                                } catch (NumberFormatException exception) {
                                    JOptionPane.showMessageDialog(jEasyLayout, "You can't put a letter in the width or height field!");
                                    _jTextField1.setText("");
                                    _jTextField2.setText("");
                                    return;
                                }
                                SLSMain slsMain =  new SLSMain(projectFolder.getPath(), SLSMain.FileOrDir.CREATEDIR);
                                slsMain.encodeString(Statics.getProjectName(), "projectName", "project.saveLoadSystem");
                                slsMain.encodeLong(Statics.getWidth(), "projectWidth", "project.saveLoadSystem");
                                slsMain.encodeLong(Statics.getHeight(), "projectHeight", "project.saveLoadSystem");

                                jEasyLayout.dispose();

                                onNewScene();
                            }
                        });
                    }
                }

                if (ImGui.menuItem("Open...", "Ctrl+O")) {
                    String dialog = Dialog.openFolderDialog();
                    if (!dialog.equals("")) {
                        SLSMain slsMain = new SLSMain(dialog + "/.animationStudios/", SLSMain.FileOrDir.CREATEDIR);
                        Statics.setProjectPath(dialog);
                        Statics.setProjectName(slsMain.decodeString("projectName", "project.saveLoadSystem"));
                        Statics.setWidth((int) slsMain.decodeLong("projectWidth", "project.saveLoadSystem"));
                        Statics.setHeight((int) slsMain.decodeLong("projectHeight", "project.saveLoadSystem"));
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

        if (addComponentBoolean.get()) {
            renderPanel(imGuiPanelHashMap.get("addComponentPanel"), addComponentBoolean);
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
        windowFlags |= ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove;
        windowFlags |= ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus;

        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0, 0);
        ImGui.begin("Dockspace", imBoolean, windowFlags);
        ImGui.popStyleVar(3);

        ImGui.dockSpace(dockSpaceId, 0, 0, ImGuiDockNodeFlags.PassthruCentralNode);
        ImGui.end();
    }

    private void renderPanel(ImGuiPanel imGuiPanel) {
        imGuiPanel.renderToImGui();
    }

    private void renderPanel(ImGuiPanel imGuiPanel, ImBoolean imBoolean) {
        imGuiPanel.renderToImGui(imBoolean);
    }

    public static void setAddComponentBoolean(boolean addComponentBoolean) {
        ImGuiUI.addComponentBoolean = new ImBoolean(addComponentBoolean);
    }
}