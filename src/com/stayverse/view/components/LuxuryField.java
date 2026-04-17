package com.stayverse.view.components;

import com.stayverse.util.LuxuryTheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LuxuryField extends JTextField {
    public LuxuryField(String placeholder) {
        super("");
        putClientProperty("JTextField.placeholderText", placeholder);
        setOpaque(false);
        setForeground(LuxuryTheme.TEXT_PRIMARY);
        setCaretColor(LuxuryTheme.ACCENT_GOLD);
        setFont(LuxuryTheme.FONT_REGULAR);
        setBorder(new EmptyBorder(10, 15, 10, 15));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(new Color(40, 45, 55));
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));
        
        g2.setColor(new Color(255, 255, 255, 20));
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 12, 12));
        
        g2.dispose();
        super.paintComponent(g);
    }
}
