package com.udeGames.animationStudios.saving;

import com.udeGames.saveLoadSystem.SLSMain;
import imgui.type.ImBoolean;

import java.util.HashMap;

public class SLSImplementation {

    public static void saveImGUILayout (HashMap<String, ImBoolean> imBooleanHashMap) {
        SLSMain slsMain = new SLSMain(System.getProperty("user.dir"), SLSMain.FileOrDir.CREATEDIR);
        for (String keys : imBooleanHashMap.keySet())  {
            slsMain.encodeBoolean(imBooleanHashMap.get(keys).get(), keys, "UILayout.saveLoadSystem");
        }
    }

    public static ImBoolean loadImGuiLayout(String var) {
        SLSMain slsMain = new SLSMain(System.getProperty("user.dir"), SLSMain.FileOrDir.CREATEDIR);
        try {
            return new ImBoolean(slsMain.decodeBoolean(var, "UILayout.saveLoadSystem"));
        } catch (RuntimeException e) {
            return null;
        }
    }
}