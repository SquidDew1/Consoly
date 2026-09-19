package me.squiddew.consoly;

import javax.swing.*;
import java.net.URL;

public class Window {

    public static void create(String title, int width, int height, boolean icon, String iconPath){
        System.setProperty("java.awt.headless", "false");

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (icon){
                URL iconUrl = Window.class.getResource(iconPath);
                if (iconUrl != null){
                    frame.setIconImage(new ImageIcon(iconUrl).getImage());
                }
            }
            frame.setResizable(true);
            frame.setVisible(true);
        });
    }
}
