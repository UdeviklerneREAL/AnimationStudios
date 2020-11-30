package com.udeGames.animationStudios.imGui;

import com.udeGames.animationStudios.Main;
import imgui.*;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.lwjgl.glfw.GLFW;

public class ImGuiLayer {

    private final ImGuiImplGlfw imGuiGLFW = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiG13 = new ImGuiImplGl3();
    private final ImGuiUI imGuiUI = new ImGuiUI();

    public ImGuiLayer() {
        ImGui.createContext();

        final ImGuiIO io = ImGui.getIO();

        io.setIniFilename("imgui.ini");
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);
        io.setConfigViewportsNoTaskBarIcon(true);

        final ImFontAtlas fontAtlas = io.getFonts();
        final ImFontConfig fontConfig = new ImFontConfig();

        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesDefault());

        fontAtlas.addFontFromFileTTF("assets/fonts/LucidaGrande.ttf", 15, fontConfig);

        fontConfig.destroy();

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final ImGuiStyle style = ImGui.getStyle();
            style.setWindowRounding(0.0f);
            style.setColor(ImGuiCol.WindowBg, ImGui.getColorU32(ImGuiCol.WindowBg, 1));
        }

        imGuiGLFW.init(Main.getInstance().getWindow(), true);
        imGuiG13.init("#version 330 core");
    }

    public void render()  {
        imGuiGLFW.newFrame();
        ImGui.newFrame();
        imGuiUI.render();
        ImGui.render();
    }

    public void endFrame() {
        imGuiG13.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindowPtr);
        }
    }

    public void dispose() {
        imGuiUI.dispose();
        imGuiG13.dispose();
        imGuiGLFW.dispose();

        ImGui.destroyContext();
    }
}
