package com.udeGames.animationStudio;

import javax.swing.*;
import java.awt.*;

public class JRenderPanel extends JPanel {

    private final JFrame _parent;
    private Graphics2D graphics2D;

    public JRenderPanel(JFrame _parent) {
        super();
        this._parent = _parent;
    }

    public void prepare(Color color) {
        graphics2D = (Graphics2D) getGraphics();
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, _parent.getWidth(), _parent.getHeight());
    }
}
