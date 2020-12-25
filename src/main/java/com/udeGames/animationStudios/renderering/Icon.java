package com.udeGames.animationStudios.renderering;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWImage.Buffer;

import java.util.ArrayList;

public class Icon {
    private final ArrayList<IconImage> images;

    public Icon() {
        images = new ArrayList<>();
    }

    public void addIcon(IconImage icon) {
        images.add(icon);
    }

    public Buffer getImage() {
        Buffer buffer = GLFWImage.create(images.size());

        int i = 0;
        for (IconImage image : images) {
            GLFWImage icon = GLFWImage.create().set(image.getWidth(), image.getHeight(), image.getImage());
            buffer.put(i, icon);
            i++;
        }

        return buffer;
    }
}
