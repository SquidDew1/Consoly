package me.squiddew.consoly.console;

import me.squiddew.consoly.ConsolyOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ConsoleWindow {

    private static final Logger LOGGER = LoggerFactory.getLogger("Consoly");
    private static JFrame frame;
    public static JTextArea textArea;
    private static JScrollPane scrollPane;

    public static void createWindow(String title, int width, int height, boolean icon, String iconPath, int initialColor){
        System.setProperty("java.awt.headless", "false");

        if (frame != null){
            LOGGER.error("Frame already exists!, ignoring creation");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            setStyle(ConsolyOptions.style);
            frame = new JFrame(title);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            if (icon){
                URL iconUrl = ConsoleWindow.class.getResource(iconPath);
                if (iconUrl != null){
                    frame.setIconImage(new ImageIcon(iconUrl).getImage());
                }
            }
            textArea = new JTextArea();
            textArea.setEditable(false);
            scrollPane = new JScrollPane(textArea);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
            scrollPane.getVerticalScrollBar().setBackground(new Color(0, 0, 0));
            scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 5));
            scrollPane.getHorizontalScrollBar().setBackground(new Color(0, 0, 0));

            applyTheme(initialColor);

            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setResizable(true);

            frame.setFocusableWindowState(false);
            frame.setVisible(true);
            SwingUtilities.invokeLater(() -> {
                if (frame != null) {
                    frame.setFocusableWindowState(true);
                }
            });
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
    public static void setTheme(int color){
        SwingUtilities.invokeLater(() -> {
            if (frame != null && textArea != null) {
                applyTheme(color);
                frame.repaint();
            }
        });
    }

    private static void applyTheme(int color) {
        Color c = new Color(color, color, color);
        textArea.setBackground(c);
        scrollPane.setBackground(c);
        scrollPane.getViewport().setBackground(c);
        textArea.setForeground(color < 128 ? Color.WHITE : Color.DARK_GRAY);
    }

    public static void setStyle(boolean nimbus) {
        try {
            if (nimbus) {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }

            if (frame != null) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                        SwingUtilities.updateComponentTreeUI(frame);
                        frame.repaint();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFont(boolean bold){
        if (bold){
            textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
        } else {
            textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        }
    }

    public static void setWindowVisibility(boolean visible){
        SwingUtilities.invokeLater(() -> {
            if (frame != null){
                frame.setVisible(visible);
                if (visible){
                    frame.toFront();
                }
            }
        });
    }
}
