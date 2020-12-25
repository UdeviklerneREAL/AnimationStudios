package com.udeGames.animationStudios.utils;

import java.nio.IntBuffer;
import java.util.Arrays;

public class BufferToArrayUtils {
    private BufferToArrayUtils() {}

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
}
