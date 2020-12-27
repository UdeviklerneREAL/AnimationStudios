package com.udeGames.animationStudios.utils;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.util.Scanner;

public class ShaderUtils {

    private static final FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    private ShaderUtils() {}

    public static int[] genShader(String path) {
        StringBuilder vertex = new StringBuilder();
        StringBuilder fragment = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            int state = 0;

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#shader")) {
                    if (line.contains("vertex")) {
                        state = 1;
                    } else if (line.contains("fragment")) {
                        state = 2;
                    } else {
                        throw new RuntimeException("Dose not know how to react on " + line);
                    }
                } else {
                    switch (state) {
                        case 1:
                            vertex.append(line).append("\n");
                            break;
                        case 2:
                            fragment.append(line).append("\n");
                            break;
                        case 0:
                            throw new RuntimeException("Missing statement '#shader'");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int vertexId, fragmentId, shaderProgram;

        vertexId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexId, vertex);
        GL20.glCompileShader(vertexId);

        byte success = (byte)GL20.glGetShaderi(vertexId, GL20.GL_COMPILE_STATUS);
        if (success == GL11.GL_FALSE) {
            int length = GL20.glGetShaderi(vertexId, GL20.GL_INFO_LOG_LENGTH);
            throw new ShaderCompileException(GL20.glGetShaderInfoLog(vertexId, length));
        }

        fragmentId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentId, fragment);
        GL20.glCompileShader(fragmentId);

        success = (byte)GL20.glGetShaderi(fragmentId, GL20.GL_COMPILE_STATUS);
        if (success == GL11.GL_FALSE) {
            int length = GL20.glGetShaderi(fragmentId, GL20.GL_INFO_LOG_LENGTH);
            throw new ShaderCompileException(GL20.glGetShaderInfoLog(fragmentId, length));
        }

        shaderProgram = GL20.glCreateProgram();
        GL20.glAttachShader(shaderProgram, vertexId);
        GL20.glAttachShader(shaderProgram, fragmentId);
        GL20.glLinkProgram(shaderProgram);

        success = (byte)GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS);
        if (success == GL11.GL_FALSE) {
            int length = GL20.glGetProgrami(shaderProgram, GL20.GL_INFO_LOG_LENGTH);
            throw new ShaderLinkingException(GL20.glGetProgramInfoLog(shaderProgram, length));
        }

        return new int[] {
                vertexId, fragmentId, shaderProgram
        };
    }

    public static void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    public static void loadVector3f(int location, Vector3f value) {
        GL20.glUniform3f(location, value.x, value.y, value.z);
    }

    public static void loadBoolean(int location, boolean value) {
        GL20.glUniform1f(location, value ? 1 : 0);
    }

    public static void loadMatrix4f(int location, Matrix4f value) {
        value.get(matrixBuffer);
        GL20.glUniformMatrix4fv(location, false, matrixBuffer);
    }

    private static class ShaderLinkingException extends RuntimeException {
        public ShaderLinkingException(String st) {
            super(st);
        }
    }

    private static class ShaderCompileException extends RuntimeException {
        public ShaderCompileException(String st) {
            super(st);
        }
    }
}
