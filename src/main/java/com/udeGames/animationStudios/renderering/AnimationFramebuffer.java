package com.udeGames.animationStudios.renderering;

import com.udeGames.animationStudios.Statics;

public class AnimationFramebuffer {
    private static Framebuffer framebuffer;

    public static Framebuffer getFramebuffer() {
        return framebuffer;
    }

    public static void init() {
        framebuffer = new Framebuffer(Statics.getWidth(), Statics.getHeight());
    }
}
