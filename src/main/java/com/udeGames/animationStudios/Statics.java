package com.udeGames.animationStudios;

public class Statics {
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

    public static void setProjectPath(String projectPath) {
        ProjectPath = projectPath;
    }

    public static void setProjectName(String projectName) {
        ProjectName = projectName;
    }
}
