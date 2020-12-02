package com.udeGames.animationStudios.imGui.panels;

import com.udeGames.animationStudios.ffmpeg.FFMpegImplementation;
import com.udeGames.animationStudios.renderering.ImageLoader;
import com.udeGames.animationStudios.renderering.Texture;
import imgui.internal.ImGui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImportsPanel extends ImGuiPanel {

    private static final ArrayList<Texture> textureArrayList = new ArrayList<>();

    public ImportsPanel() {
        super("Imports");
    }

    @Override
    public void render() {
        for (Texture texture : textureArrayList) {
           ImGui.image(texture.getId(), ImGui.getWindowSizeX(), (float)(texture.getHeight()) / (float)(texture.getWidth()) * ImGui.getWindowSizeX());
           ImGui.text(texture.getFileName());
        }
    }

    public static void add(String[] strings) {
        for (String string : strings) {
            if (string.endsWith("png") || string.endsWith("jpg") || string.endsWith("jpeg") || string.endsWith(".gif")) {
                try {
                    textureArrayList.add(ImageLoader.loadImage(ImageIO.read(new File(string)), string));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (string.endsWith("mp4") || string.endsWith("mov") || string.endsWith("flv") || string.endsWith("avi")) {

            }
        }
    }
}
