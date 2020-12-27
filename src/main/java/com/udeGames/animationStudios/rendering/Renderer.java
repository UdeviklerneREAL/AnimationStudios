package com.udeGames.animationStudios.rendering;

import com.udeGames.animationStudios.utils.Maths;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

    private static Renderer instance;

    private Renderer() {

    }

    public static Renderer getInstance() {
        if (instance == null) {
            instance = new Renderer();
        }

        return instance;
    }

    public void clear() {
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void render(Entity entity, MainShader shader) {
        Sprite sprite = entity.getSprite();
        GL30.glBindVertexArray(sprite.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transform = Maths.createTransformMatrix(entity.getPosition(), entity.getRotation(), entity.getScale());
        shader.loadTransform(transform);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, sprite.getTexture().getId());
        GL11.glDrawElements(GL11.GL_TRIANGLES, sprite.getVertexCount(), GL11.GL_UNSIGNED_SHORT, 0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
