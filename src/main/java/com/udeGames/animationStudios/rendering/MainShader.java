package com.udeGames.animationStudios.rendering;

import com.udeGames.animationStudios.utils.ShaderUtils;
import org.joml.Matrix4f;

public class MainShader extends Shader {
    private final int[] ides;
    private final int transformLocation;

    public MainShader() {
        ides = ShaderUtils.genShader("assets/shaders/main.shader");
        transformLocation = super.getUniformLocation("transform");
    }

    @Override
    public int[] getShader() {
        return ides;
    }

    public void loadTransform(Matrix4f matrix4f)  {
        ShaderUtils.loadMatrix4f(transformLocation, matrix4f);
    }
}
