package com.stayverse.view.components;

import com.stayverse.util.LuxuryTheme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LuxuryButton extends JButton {
    private boolean hovered = false;
    private Color baseColor = LuxuryTheme.ACCENT_GOLD;

    public LuxuryButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK);
        setFont(LuxuryTheme.FONT_BOLD);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
            @Override
            public void mouseExited(MouseEvent e) { hovered = false; repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color mix = hovered ? baseColor.brighter() : baseColor;
        g2.setColor(mix);
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        
        g2.dispose();
        super.paintComponent(g);
    }
}
