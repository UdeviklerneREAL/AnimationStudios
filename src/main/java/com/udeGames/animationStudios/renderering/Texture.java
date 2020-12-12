package com.udeGames.animationStudios.renderering;

public class Texture {
    private int width, height;
    private transient int id;
    private String fileName, filePath;

    public Texture(int id, int width, int height, String fileName, String filePath) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setId(int id) {
        this.id = id;
    }
}
