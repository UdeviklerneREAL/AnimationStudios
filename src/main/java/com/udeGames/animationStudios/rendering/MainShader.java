package com.udeGames.animationStudios.rendering;

public class MainShader extends Shader {
    private final int[] ides;

    public MainShader() {
        ides = ShaderUtils.genShader("assets/shaders/main.shader");
    }

    @Override
    public int[] getShader() {
        return ides;
    }
}
