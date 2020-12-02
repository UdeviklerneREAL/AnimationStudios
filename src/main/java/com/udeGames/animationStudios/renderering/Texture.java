package com.udeGames.animationStudios.renderering;

public class Texture {
    private final int ID, WIDTH, HEIGHT;
    private final String FILE_NAME;

    protected Texture(int id, int width, int height, String fileName) {
        this.ID = id;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.FILE_NAME = fileName;
    }

    public int getId() {
        return ID;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public String getFileName() {
        return FILE_NAME;
    }
}
