package com.udeGames.animationStudios.apis.imGui.components;

import com.udeGames.animationStudios.apis.imGui.ImGuiComponent;
import com.udeGames.animationStudios.apis.imGui.ImGuiExtension;
import java.io.File;

public class TextureComponent extends ImGuiComponent {

    private File file = new File("");

    public TextureComponent() {
        super("Texture");
    }

    @Override
    public void render() {
        file = ImGuiExtension.drawFile("Texture:", file, "png,jpg,jpeg,gif;");
    }
}
