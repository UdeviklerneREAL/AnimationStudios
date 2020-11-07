package com.udeGames.animationStudio;

import javax.swing.*;
import java.awt.*;
import net.bramp.ffmpeg.*;

public class Main extends JFrame {

    public Main(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setTitle(title);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setVisible(true);
        FFmpeg
    }

    public void add() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu tools = new JMenu("Tools");
        jMenuBar.add(tools);
        setJMenuBar(jMenuBar);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Main __init__ = new Main("Animation Studios");
        __init__.add();
    }
}
