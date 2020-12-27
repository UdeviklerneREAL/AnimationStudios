package com.udeGames.animationStudios;

import com.udeGames.animationStudios.apis.imGui.ImGuiLayer;
import com.udeGames.animationStudios.rendering.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main {

    private long window;
    private Dimension resolution;
    private ImGuiLayer imGuiLayer;
    private MainShader shader;
    private Entity entity;
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
        shader = new MainShader();
        double[] vertices = {-0.5f, 0.5f, 0f, -0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0.5f,  0.5f, 0f};
        short[] indices = {0, 1, 3, 3, 1, 2};
        double[] textureCoordinates = {0, 0, 0, 1, 1, 1, 1, 0};
        Sprite sprite = Sprite.loadSpriteToVAO(vertices, indices, textureCoordinates, "assets/images/logo.png");
        entity = new Entity(sprite, new Vector3f(0, 0, 0), new Quaternionf(), new Vector3f(1, 1, 1));

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
        Sprite.cleanUp();
        Texture.cleanUp();
        shader.dispose();

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
        Renderer.getInstance().clear();
        shader.start();
        Renderer.getInstance().render(entity, shader);
        entity.increaseRotation(new Vector3f(0, 0.01f, 0));
        shader.stop();
    }

    public long getWindow() {
        return window;
    }

    public static void main(String[] args) {
        getInstance().run(Statics.getTitle());
    }
}
