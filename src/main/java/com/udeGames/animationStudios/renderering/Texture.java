package com.udeGames.animationStudios.renderering;

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class Texture {
    private transient int id;
    private String fileName, filePath;
    private int width, height;
    private static ArrayList<Texture> textures = new ArrayList<>();

    public Texture(int id, int width, int height, String fileName, String filePath) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.fileName = fileName;
        this.filePath = filePath;
        textures.add(this);
    }

    public Texture() {
        textures.add(this);
    }

    public Texture(int width, int height) {
        this.width = width;
        this.height = height;

        this.filePath = "Generated";

        id = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, 0);
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

    public static Texture getTextureById(int id) {
        for (Texture texture : textures) {
            if (texture.getId() == id) {
                System.out.println(texture.getFilePath());
                return texture;
            }
        }

        throw new RuntimeException("Can't find a texture with requested id");
    }
}
