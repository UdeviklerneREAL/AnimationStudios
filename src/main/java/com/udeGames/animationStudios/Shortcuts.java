package com.udeGames.animationStudios;

import com.udeGames.animationStudios.basic.Lambda;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class Shortcuts {

    private static final HashMap<String, Integer> keysCodes = new HashMap<>();

    static {
        keysCodes.put("A", GLFW.GLFW_KEY_A);
        keysCodes.put("B", GLFW.GLFW_KEY_B);
        keysCodes.put("C", GLFW.GLFW_KEY_C);
        keysCodes.put("D", GLFW.GLFW_KEY_D);
        keysCodes.put("E", GLFW.GLFW_KEY_E);
        keysCodes.put("F", GLFW.GLFW_KEY_F);
        keysCodes.put("G", GLFW.GLFW_KEY_G);
        keysCodes.put("H", GLFW.GLFW_KEY_H);
        keysCodes.put("I", GLFW.GLFW_KEY_I);
        keysCodes.put("J", GLFW.GLFW_KEY_J);
        keysCodes.put("K", GLFW.GLFW_KEY_K);
        keysCodes.put("L", GLFW.GLFW_KEY_L);
        keysCodes.put("M", GLFW.GLFW_KEY_M);
        keysCodes.put("N", GLFW.GLFW_KEY_N);
        keysCodes.put("O", GLFW.GLFW_KEY_O);
        keysCodes.put("P", GLFW.GLFW_KEY_P);
        keysCodes.put("Q", GLFW.GLFW_KEY_Q);
        keysCodes.put("R", GLFW.GLFW_KEY_R);
        keysCodes.put("S", GLFW.GLFW_KEY_S);
        keysCodes.put("T", GLFW.GLFW_KEY_T);
        keysCodes.put("U", GLFW.GLFW_KEY_U);
        keysCodes.put("V", GLFW.GLFW_KEY_V);
        keysCodes.put("W", GLFW.GLFW_KEY_W);
        keysCodes.put("X", GLFW.GLFW_KEY_X);
        keysCodes.put("Y", GLFW.GLFW_KEY_Y);
        keysCodes.put("Z", GLFW.GLFW_KEY_Z);
        keysCodes.put("0", GLFW.GLFW_KEY_0);
        keysCodes.put("1", GLFW.GLFW_KEY_1);
        keysCodes.put("3", GLFW.GLFW_KEY_3);
        keysCodes.put("4", GLFW.GLFW_KEY_4);
        keysCodes.put("5", GLFW.GLFW_KEY_5);
        keysCodes.put("6", GLFW.GLFW_KEY_6);
        keysCodes.put("7", GLFW.GLFW_KEY_7);
        keysCodes.put("8", GLFW.GLFW_KEY_8);
        keysCodes.put("9", GLFW.GLFW_KEY_9);
        keysCodes.put("lctrl", GLFW.GLFW_KEY_LEFT_CONTROL);
        keysCodes.put("rctrl", GLFW.GLFW_KEY_RIGHT_CONTROL);
        keysCodes.put("lshift", GLFW.GLFW_KEY_LEFT_SHIFT);
        keysCodes.put("rshift", GLFW.GLFW_KEY_RIGHT_SHIFT);
        keysCodes.put("lalt", GLFW.GLFW_KEY_LEFT_ALT);
        keysCodes.put("ralt", GLFW.GLFW_KEY_RIGHT_ALT);
    }

    private static boolean isPressed(int keyCode) {
        return GLFW.glfwGetKey(Main.getInstance().getWindow(), keyCode) == GLFW.GLFW_PRESS;
    }

    public static void shortCut(String keys, Lambda lambda) {
        for (String key : keys.split("[+]")) {
            for (String keyCode : keysCodes.keySet()) {
                if (key.equals(keyCode)) {
                    if (!isPressed(keysCodes.get(keyCode))) {
                        return;
                    }
                }
            }
        }
        lambda.method();
    }
}
