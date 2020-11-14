package com.udeGames.animationStudio;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame implements Runnable {

    JRenderPanel jRenderPanel;

    public Main(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setTitle(title);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setVisible(true);
        add();
    }

    public void add() {
        jRenderPanel = new JRenderPanel(this);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu tools = new JMenu("Tools");
        jMenuBar.add(tools);
        setJMenuBar(jMenuBar);
        add(jRenderPanel);
    }

    public static void main(String[] args) {
        try {
            Main __init__ = new Main("Animation Studios");
            new Thread(__init__).start();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            jRenderPanel.prepare(Color.WHITE);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
