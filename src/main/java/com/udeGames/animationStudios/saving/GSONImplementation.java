package com.udeGames.animationStudios.saving;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

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
