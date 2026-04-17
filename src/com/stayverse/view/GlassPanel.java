package com.stayverse.view;

import com.stayverse.util.LuxuryTheme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GlassPanel extends JPanel {
    private int arc;
    private Color bgColor;

    public GlassPanel(int arc, Color bgColor) {
        this.arc = arc;
        this.bgColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background Gradient/Fill
        g2.setColor(bgColor);
        g2.fill(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, arc, arc));
        
        // Border Highlight
        g2.setColor(LuxuryTheme.GLASS_BORDER);
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, arc, arc));
        
        g2.dispose();
    }
}
