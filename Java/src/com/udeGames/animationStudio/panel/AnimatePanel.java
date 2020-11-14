package com.udeGames.animationStudio.panel;

import com.udeGames.animationStudio.panel.components.Text;

public class AnimatePanel extends AbstractPanel {
    public AnimatePanel() {
        super();
        __INIT__();
    }

    @Override
    public String NAME() {
        return "Animate";
    }

    public void __INIT__() {
        add(new Text("Hello"));
    }

    @Override
    public byte WIDTH() {
        return 25;
    }

    @Override
    public byte HEIGHT() {
        return 25;
    }
}
