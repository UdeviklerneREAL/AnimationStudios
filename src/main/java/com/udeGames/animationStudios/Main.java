package com.udeGames.animationStudios;

import com.udeGames.animationStudios.apis.imGui.ImGuiLayer;
import com.udeGames.animationStudios.renderering.AnimationFramebuffer;
import com.udeGames.animationStudios.renderering.Icon;
import com.udeGames.animationStudios.renderering.ImageLoader;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {

    private long window;
    private Dimension resolution;
    private ImGuiLayer imGuiLayer;
    private static Main init = null;
    private static final long NULL = 0L;

    private Main() {}

    public static Main getInstance() {
        if (init == null) {
            init = new Main();
        }

        return init;
    }

    public void run(String title) {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        resolution = Toolkit.getDefaultToolkit().getScreenSize();
        window = GLFW.glfwCreateWindow(resolution.width, resolution.height, title, NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwSetWindowSizeCallback(window, (w, width, height) -> {
            resolution = new Dimension(width, height);
            System.out.println("Width: " + width + " Height. " + height);
        });

        GLFW.glfwMakeContextCurrent(window);

        try {
            Icon icon = new Icon();
            icon.addIcon(ImageLoader.loadIcon("assets/images/logo.png"));
            icon.addIcon(ImageLoader.loadIcon("assets/images/smallLogo.png"));
            GLFW.glfwSetWindowIcon(window, icon.getImage());
        } catch (IOException e) {
            System.out.println("Can't load image");
        }

        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        imGuiLayer = new ImGuiLayer();

        double previousTime = GLFW.glfwGetTime();
        int frameCount = 0;

        while (!GLFW.glfwWindowShouldClose(window)) {
            double currentTime = GLFW.glfwGetTime();
            frameCount++;

            if ( currentTime - previousTime >= 1.0 ) {
                Time.setFPS(frameCount);
                frameCount = 0;
                previousTime = currentTime;
            }

            if (AnimationFramebuffer.getFramebuffer() != null) {
                AnimationFramebuffer.getFramebuffer().bind();
                drawToViewport();
                AnimationFramebuffer.getFramebuffer().unbind();
            }

            loop();

            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        imGuiLayer.dispose();

        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        Objects.requireNonNull(GLFW.glfwSetErrorCallback(null)).free();
    }

    public void loop() {
        imGuiLayer.render();
        imGuiLayer.endFrame();
    }

    public void drawToViewport() {
        GL11.glClearColor(0, 0, 0, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public long getWindow() {
        return window;
    }

    public static void main(String[] args) {
        getInstance().run(Statics.getTitle());
    }
}
