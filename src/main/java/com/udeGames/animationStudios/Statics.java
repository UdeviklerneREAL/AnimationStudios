package com.udeGames.animationStudios;

public class Statics {
    private static int width = 0, height = 0;
    private static String ProjectPath = "";
    private static String ProjectName = "";
    private final static String title = "Animation Studios";

    public static String getProjectPath() {
        return ProjectPath;
    }

    public static String getProjectName() {
        return ProjectName;
    }

    public static String getTitle() {
        return title;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setProjectPath(String projectPath) {
        ProjectPath = projectPath;
    }

    public static void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public static void setWidth(int width) {
        Statics.width = width;
    }

    public static void setHeight(int height) {
        Statics.height = height;
    }
}
