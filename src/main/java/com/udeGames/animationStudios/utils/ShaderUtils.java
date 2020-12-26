package com.udeGames.animationStudios.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShaderUtils {

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
