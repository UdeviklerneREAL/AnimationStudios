package com.udeGames.animationStudios.imGui.panels;

import com.google.gson.reflect.TypeToken;
import com.udeGames.animationStudios.Statics;
import com.udeGames.animationStudios.renderering.ImageLoader;
import com.udeGames.animationStudios.renderering.Texture;
import com.udeGames.animationStudios.saving.GSONImplementation;
import imgui.internal.ImGui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportsPanel extends ImGuiPanel {

    private static ArrayList<Texture> textureArrayList = new ArrayList<>();

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

    @Override
    public void onOpenNewProject() {
        File file = new File(Statics.getProjectPath() + "/.animationStudios/imports.json");
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                StringBuilder builder = new StringBuilder();

                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine()).append("\n");
                }

                scanner.close();
                System.out.println(builder.toString());
                textureArrayList = GSONImplementation.getGsonInsistence().fromJson(builder.toString(), new TypeToken<ArrayList<Texture>>() {}.getType());
                for (Texture texture : textureArrayList) {
                    texture.setId(ImageLoader.loadImage(ImageIO.read(new File(texture.getFilePath())), "").getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dispose() {
        if (!Statics.getProjectName().equals("")) {
            GSONImplementation.toJson(textureArrayList, Statics.getProjectPath() + "/.animationStudios/imports.json");
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
            } /*else if (string.endsWith("mp4") || string.endsWith("mov") || string.endsWith("flv") || string.endsWith("avi")) {

            }*/
        }
    }
}
