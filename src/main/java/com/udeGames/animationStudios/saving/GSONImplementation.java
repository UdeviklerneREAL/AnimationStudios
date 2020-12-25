package com.udeGames.animationStudios.saving;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class GSONImplementation {
    private static final Gson gsonInsistence = new Gson();

    public static Gson getGsonInsistence() {
        return gsonInsistence;
    }

    public static void toJson(Object object, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(getGsonInsistence().toJson(object));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
