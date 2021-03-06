package com.udeGames.animationStudios.saving;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.nfd.NFDPathSet;
import org.lwjgl.util.nfd.NativeFileDialog;

import java.nio.ByteBuffer;

public class Dialog {

    public static String openOneFileDialog(String fileTypes) {
        PointerBuffer outPath = MemoryUtil.memAllocPointer(1);

        try {
            return checkResult(NativeFileDialog.NFD_OpenDialog(fileTypes, null, outPath), outPath);
        } finally {
            MemoryUtil.memFree(outPath);
        }
    }

    public static String[] openMultipleFileDialog(String fileTypes) {
        try (NFDPathSet pathSet = NFDPathSet.calloc()) {
            int result = NativeFileDialog.NFD_OpenDialogMultiple(fileTypes, null, pathSet);
            switch (result) {
                case NativeFileDialog.NFD_OKAY:
                    long count = NativeFileDialog.NFD_PathSet_GetCount(pathSet);
                    String[] stringArray = new String[(int)count];
                    for (long i = 0; i < count; i++) {
                        String path = NativeFileDialog.NFD_PathSet_GetPath(pathSet, i);
                        stringArray[(int)i] = path;
                    }
                    NativeFileDialog.NFD_PathSet_Free(pathSet);
                    return stringArray;
                case NativeFileDialog.NFD_CANCEL:
                    return new String[0];
                default:
                    System.err.format("Error: %s\n", NativeFileDialog.NFD_GetError());
                    throw new RuntimeException("Error");
            }
        }
    }

    public static String openFolderDialog() {
        PointerBuffer outPath = MemoryUtil.memAllocPointer(1);

        try {
            return checkResult(NativeFileDialog.NFD_PickFolder((ByteBuffer) null, outPath), outPath);
        } finally {
            MemoryUtil.memFree(outPath);
        }
    }

    public static void saveDialog(String fileTypes) {
        PointerBuffer outPath = MemoryUtil.memAllocPointer(1);

        try {
            checkResult(NativeFileDialog.NFD_SaveDialog(fileTypes, null, outPath), outPath);
        } finally {
            MemoryUtil.memFree(outPath);
        }
    }

    private static String checkResult(int result, PointerBuffer path) {
        switch (result) {
            case NativeFileDialog.NFD_OKAY:
                String res = path.getStringUTF8(0);
                NativeFileDialog.nNFD_Free(path.get(0));
                return res;
            case NativeFileDialog.NFD_CANCEL:
                return "";
            default:
                System.err.format("Error %s\n", NativeFileDialog.NFD_GetError());
                throw new RuntimeException("Error");
        }
    }
}
