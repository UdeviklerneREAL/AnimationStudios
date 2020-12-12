package com.udeGames.animationStudios;

public class Time {
    private static int FPS = 0;

    public static int getFPS() {
        return FPS;
    }

    protected static void setFPS(int fps) {
        FPS = fps;
    }
}
