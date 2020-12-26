package com.udeGames.animationStudios.apis.imGui.panels;

import com.google.gson.reflect.TypeToken;
import com.udeGames.animationStudios.Statics;
import com.udeGames.animationStudios.apis.imGui.ImGuiPanel;
import com.udeGames.animationStudios.rendering.ImageLoader;
import com.udeGames.animationStudios.rendering.Texture;
import com.udeGames.animationStudios.saving.Dialog;
import com.udeGames.animationStudios.saving.GSONImplementation;
import imgui.internal.ImGui;

import javax.imageio.ImageIO;
import java.io.File;
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
        int i = 0;
        for (Texture texture : textureArrayList) {
           ImGui.imageButton(texture.getId(), ImGui.getWindowSizeX(), (float)(texture.getHeight()) / (float)(texture.getWidth()) * ImGui.getWindowSizeX());
           if (ImGui.beginDragDropSource()) {
               ImGui.setDragDropPayloadObject("image" + i, texture.getId());
               ImGui.endDragDropSource();
           }
           ImGui.text(texture.getFileName());
           i++;
        }

        if (ImGui.beginPopupContextWindow()) {
            if (ImGui.menuItem("Import...")) {
                String[] stringArray = Dialog.openMultipleFileDialog("mp4,mov,flv,avi,png,jpg,jpeg,gif,mp3,wav");
                ImportsPanel.add(stringArray);
            }
            ImGui.endPopup();
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

    public static int getTextureArrayListLength() {
        return textureArrayList.size();
    }
}
