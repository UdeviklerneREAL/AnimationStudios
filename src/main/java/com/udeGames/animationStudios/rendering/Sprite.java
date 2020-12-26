package com.udeGames.animationStudios.rendering;

import com.udeGames.animationStudios.utils.NewBufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private final static List<Integer> vaos = new ArrayList<>();
    private final static List<Integer> vbos = new ArrayList<>();

    private final int vaoID;
    private final int textureId;
    private final int vertexCount;

    private Sprite(int vaoID, int vertexCount, String texture) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.textureId = ImageLoader.loadImage(texture).getId();
    }

    public static Sprite loadSpriteToVAO(float[] positions, short[] indices, float[] textureCoordinates, String texture) {
        int vaoID = createVAO();
        bindIndices(indices);
        storeData(0, 3, positions);
        storeData(1, 2, textureCoordinates);
        unbindVAO();
        return new Sprite(vaoID, indices.length, texture);
    }

    public static void cleanUp() {
        for (int vao: vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo: vbos) {
            GL30.glDeleteBuffers(vbo);
        }
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getTextureId() {
        return textureId;
    }

    private static int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private static void storeData(int abbrNumber, int size, float[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = NewBufferUtils.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(abbrNumber, size, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    private static void bindIndices(short[] indices) {
        int vboId = GL15.glGenBuffers();
        vbos.add(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,  vboId);
        ShortBuffer buffer = NewBufferUtils.storeDataInShortBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
}
