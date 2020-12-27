package com.udeGames.animationStudios.utils;

import org.joml.*;

public class Maths {
    public static Matrix4f createTransformMatrix(Vector3f position, Quaternionf rotation, Vector3f scale) {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.identity();
        matrix4f.translate(position, matrix4f);
        matrix4f.rotate(rotation, matrix4f);
        matrix4f.scale(scale, matrix4f);
        return matrix4f;
    }
}
