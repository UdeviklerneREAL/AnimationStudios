package com.udeGames.animationStudios;

import com.udeGames.animationStudios.ffmpeg.FFMpegImplementation;
import com.udeGames.animationStudios.imGui.ImGuiLayer;
import com.udeGames.animationStudios.renderering.ImageLoader;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWImage.Buffer;
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
        long NULL = 0L;
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
            Buffer buffer = GLFWImage.create(2);
            GLFWImage icon = GLFWImage.create().set(250, 250, ImageLoader.loadIcon("assets/images/logo.png"));
            GLFWImage smallIcon = GLFWImage.create().set(125, 125, ImageLoader.loadIcon("assets/images/smallLogo.png"));
            buffer.put(0, icon);
            buffer.put(1, smallIcon);
            GLFW.glfwSetWindowIcon(window, buffer);
        } catch (IOException e) {
            System.out.println("Can't load image");
        }

        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        GL11.glClearColor(0.11372549019f, 0.11372549019f, 0.11372549019f, 1.0f);

        imGuiLayer = new ImGuiLayer();

        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

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

    public long getWindow() {
        return window;
    }

    public static void main(String[] args) {
        getInstance().run(Main.class.getSimpleName());
    }
}
