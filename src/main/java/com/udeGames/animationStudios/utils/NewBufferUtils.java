package com.udeGames.animationStudios.utils;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class NewBufferUtils {
    private NewBufferUtils() {}

    public static int[] intBufferToIntArray(IntBuffer buffer) {
        if(buffer.hasArray()) {
            if(buffer.arrayOffset() == 0) {
                return buffer.array();
            }

            return Arrays.copyOfRange(buffer.array(), buffer.arrayOffset(), buffer.array().length);
        }

        buffer.rewind();
        int[] array = new int[buffer.remaining()];
        buffer.get(array);

        return array;
    }

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static ShortBuffer storeDataInShortBuffer(short[] data) {
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
