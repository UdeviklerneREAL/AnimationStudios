package com.udeGames.animationStudios.apis.imGui;

import com.udeGames.animationStudios.saving.Dialog;
import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import imgui.type.ImString;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;

public class ImGuiExtension {
    public static void drawVec3(String label, Vector3f values, float resetValue) {
        ImGui.pushID(label);

        ImGui.columns(2);
        ImGui.setColumnWidth(0, 100);
        ImGui.text(label);
        ImGui.nextColumn();

        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 0, 0);

        float lineHeight = ImGui.getFontSize() + ImGui.getStyle().getFramePaddingY() * 2;
        Vector2f buttonSize = new Vector2f(lineHeight + 2.0f, lineHeight);
        float widthEach = (ImGui.calcItemWidth() - buttonSize.x) / 3.2f;

        ImGui.pushItemWidth(widthEach);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.8f, 0.1f, 0.15f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonHovered, 0.9f, 0.2f, 0.2f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonActive, 0.8f, 0.1f, 0.15f, 1.0f);
        if (ImGui.button("X", buttonSize.x, buttonSize.y)) {
            values.x = resetValue;
        }
        ImGui.popStyleColor(3);

        ImGui.sameLine();
        float[] vecVectorX = new float[]{values.x};
        ImGui.dragFloat("##X", vecVectorX, 0.01f, 0.0f, 0.0f, "%.2f");
        ImGui.popItemWidth();
        ImGui.columns(1);
        ImGui.sameLine();

        ImGui.pushItemWidth(widthEach);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.2f, 0.7f, 0.2f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonHovered, 0.3f, 0.8f, 0.3f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonActive, 0.2f, 0.7f, 0.2f, 1.0f);
        if (ImGui.button("Y", buttonSize.x, buttonSize.y)) {
            values.y = resetValue;
        }
        ImGui.popStyleColor(3);

        ImGui.sameLine();
        float[] vecVectorY = new float[]{values.y};
        ImGui.dragFloat("##Y", vecVectorY, 0.1f, 0.0f, 0.0f, "%.2f");
        ImGui.popItemWidth();
        ImGui.columns(1);
        ImGui.sameLine();

        ImGui.pushItemWidth(widthEach);
        ImGui.pushStyleColor(ImGuiCol.Button, 0.1f, 0.25f, 0.8f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonHovered, 0.2f, 0.35f, 0.9f, 1.0f);
        ImGui.pushStyleColor(ImGuiCol.ButtonActive, 0.1f, 0.25f, 0.8f, 1.0f);
        if (ImGui.button("Z", buttonSize.x, buttonSize.y)) {
            values.z = resetValue;
        }
        ImGui.popStyleColor(3);

        ImGui.sameLine();
        float[] vecVectorZ = new float[]{values.z};
        ImGui.dragFloat("##Z", vecVectorZ, 0.01f, 0.0f, 0.0f, "%.2f");
        ImGui.popItemWidth();
        ImGui.columns(1);

        values.x = vecVectorX[0];
        values.y = vecVectorY[0];
        values.z = vecVectorZ[0];

        ImGui.nextColumn();

        ImGui.popStyleVar();
        ImGui.columns(1);
        ImGui.popID();
    }

    public static File drawFile(String label, File value, String fileTypes) {
        ImString imString = new ImString(value.getAbsolutePath());
        ImGui.text(label);
        ImGui.sameLine();
        ImGui.inputText("", imString);
        ImGui.sameLine();
        if (ImGui.button("...")) {
            String path = Dialog.openOneFileDialog(fileTypes);
            if (!path.equals("")) {
                return new File(path);
            }
        }
        return value;
    }
}
