package com.stayverse.view;

import com.stayverse.model.AbstractProperty;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.components.LuxuryButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class PropertyCard extends GlassPanel {
    private Image img;

    public PropertyCard(AbstractProperty p, Consumer<AbstractProperty> onDetails) {
        super(LuxuryTheme.CORNER_ARC, LuxuryTheme.BG_CARD);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(320, 420));
        
        try {
            if (p.getImages() != null && !p.getImages().isEmpty()) {
                img = new ImageIcon(p.getImages().get(0)).getImage();
            } else {
                img = new ImageIcon("C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\hotel_card_placeholder_1776386977666.png").getImage();
            }
        } catch (Exception e) {}

        JPanel body = new JPanel(new GridLayout(0, 1));
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel name = new JLabel(p.getName().toUpperCase());
        name.setFont(new Font("Segoe UI", Font.BOLD, 16));
        name.setForeground(LuxuryTheme.TEXT_PRIMARY);

        JLabel type = new JLabel(p.getPropertyType() + " • " + p.getCity());
        type.setFont(LuxuryTheme.FONT_REGULAR);
        type.setForeground(LuxuryTheme.TEXT_SECONDARY);

        JLabel price = new JLabel("$" + p.getBasePrice() + " / night");
        price.setFont(new Font("Segoe UI", Font.BOLD, 18));
        price.setForeground(LuxuryTheme.ACCENT_GOLD);

        LuxuryButton btn = new LuxuryButton("DISCOVER");
        btn.setPreferredSize(new Dimension(100, 40));
        btn.addActionListener(e -> onDetails.accept(p));

        body.add(name);
        body.add(type);
        body.add(Box.createVerticalStrut(10));
        body.add(price);
        body.add(Box.createVerticalStrut(10));
        body.add(btn);

        add(body, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Clip for rounded corners at the top
            Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), LuxuryTheme.CORNER_ARC, LuxuryTheme.CORNER_ARC);
            g2.setClip(clip);
            g2.drawImage(img, 0, 0, getWidth(), 220, this);
            g2.dispose();
        }
    }
}
