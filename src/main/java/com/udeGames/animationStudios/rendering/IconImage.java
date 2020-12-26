package com.udeGames.animationStudios.rendering;

import java.nio.ByteBuffer;

public class IconImage {
    private final int width, height;
    private final ByteBuffer image;

    public IconImage(ByteBuffer image, int width, int height) {
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getImage() {
        return image;
    }
}
