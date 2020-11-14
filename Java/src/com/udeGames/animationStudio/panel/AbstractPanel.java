package com.udeGames.animationStudio.panel;

import com.udeGames.animationStudio.panel.components.AbstractPanelComponent;

public abstract class AbstractPanel {
    protected AbstractPanel() {}
    public abstract String NAME();
    public abstract byte WIDTH();
    public abstract byte HEIGHT();

    public void add(AbstractPanelComponent abstractPanelComponent) {

    }
}
