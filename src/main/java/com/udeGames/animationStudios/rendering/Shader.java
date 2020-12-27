package com.udeGames.animationStudios.rendering;

import org.lwjgl.opengl.GL20;

/**
 * Copyright 2020
 * @author Kristoffer
 * @since 1.0 Beta
 */
public abstract class Shader {
    public abstract int[] getShader();

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void dispose() {
        stop();
        GL20.glDetachShader(getShader()[2], getShader()[0]);
        GL20.glDetachShader(getShader()[2], getShader()[1]);
        GL20.glDeleteShader(getShader()[0]);
        GL20.glDeleteShader(getShader()[1]);
        GL20.glDeleteProgram(getShader()[2]);
    }

    public void start() {
        GL20.glUseProgram(getShader()[2]);
    }

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(getShader()[2], uniformName);
    }
}
