package com.udeGames.animationStudios.rendering;

import org.lwjgl.opengl.GL30;

public class Framebuffer {
    private final int fboID;
    private final Texture texture;

    public Framebuffer(int width, int height) {
        fboID = GL30.glGenFramebuffers();
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboID);

        this.texture = new Texture(width, height);
        GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL30.GL_TEXTURE_2D, this.texture.getId(), 0);

        int rboID = GL30.glGenRenderbuffers();
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, rboID);
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL30.GL_DEPTH_COMPONENT32, width, height);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, rboID);

        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
    }

    public void bind() {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fboID);
    }

    public void unbind() {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
    }

    public int getFboID() {
        return fboID;
    }

    public int getTextureId() {
        return texture.getId();
    }
}
