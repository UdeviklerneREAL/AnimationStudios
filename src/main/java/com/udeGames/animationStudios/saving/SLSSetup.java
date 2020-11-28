package com.udeGames.animationStudios.saving;

import com.udeGames.saveLoadSystem.SLSMain;

import java.io.File;
import java.io.IOException;

public class SLSSetup {

    private SLSSetup() {}

    public static void saveToFiles() {
        String result = Dialog.openOneFileDialog("sls,aniprj");

        if (result.equals("")) {
            return;
        }

        File file = new File(result);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SLSMain slsMain = new SLSMain(result, SLSMain.FileOrDir.CREATEDIR);
    }
}
