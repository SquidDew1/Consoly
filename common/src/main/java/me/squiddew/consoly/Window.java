package me.squiddew.consoly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.net.URL;

public class Window {

    private static final Logger LOGGER = LoggerFactory.getLogger("Consoly");
    private static JFrame frame;

    public static void create(String title, int width, int height, boolean icon, String iconPath){
        System.setProperty("java.awt.headless", "false");

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame(title);
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
    public static void close(){
        SwingUtilities.invokeLater(() -> {
            if (frame != null){
                LOGGER.info("Stopping!");
                frame.setVisible(false);
                frame.dispose();
                frame = null;
            }
        });
    }
}
