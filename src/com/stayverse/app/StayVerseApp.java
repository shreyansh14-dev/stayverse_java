package com.stayverse.app;

import com.formdev.flatlaf.FlatDarkLaf;
import com.stayverse.view.MainFrame;
import javax.swing.*;

public class StayVerseApp {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(new FlatDarkLaf()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
