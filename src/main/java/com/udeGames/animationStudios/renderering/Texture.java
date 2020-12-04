package com.udeGames.animationStudios.renderering;

public class Texture {
    private int WIDTH, HEIGHT;
    private transient int ID;
    private String FILE_NAME, FILE_PATH;

    public Texture(int id, int width, int height, String fileName, String filePath) {
        this.ID = id;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.FILE_NAME = fileName;
        this.FILE_PATH = filePath;
    }

    public Texture() {

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

    public String getFilePath() {
        return FILE_PATH;
    }

    public void setId(int ID) {
        this.ID = ID;
    }
}
