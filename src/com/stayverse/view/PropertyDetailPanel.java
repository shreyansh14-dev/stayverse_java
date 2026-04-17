package com.stayverse.view;

import com.stayverse.model.AbstractProperty;
import com.stayverse.model.User;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.components.LuxuryButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.function.Consumer;

public class PropertyDetailPanel extends JPanel {
    private Image imgMain;
    private Image imgSmall1;
    private Image imgSmall2;

    public PropertyDetailPanel(AbstractProperty p, User user, Runnable onBack, Consumer<AbstractProperty> onBook) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(30, 40, 30, 40));

        // --- TOP HEADER ---
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        titlePanel.setOpaque(false);
        JLabel name = new JLabel(p.getName());
        name.setFont(new Font("Segoe UI", Font.BOLD, 32));
        name.setForeground(LuxuryTheme.TEXT_PRIMARY);
        titlePanel.add(name);

        JLabel stars = new JLabel("*****"); // Safe Character rendering
        stars.setFont(new Font("Segoe UI", Font.BOLD, 22));
        stars.setForeground(LuxuryTheme.ACCENT_GOLD);
        titlePanel.add(stars);
        
        header.add(titlePanel, BorderLayout.WEST);
        
        JButton back = new JButton("BACK TO EXPLORE");
        back.setFont(LuxuryTheme.FONT_BOLD);
        back.setForeground(LuxuryTheme.ACCENT_BLUE);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(e -> onBack.run());
        header.add(back, BorderLayout.EAST);
        
        add(header, BorderLayout.NORTH);

        // --- MAIN CENTER CONTENT ---
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // 1. GALLERY (Left - Weight 70%)
        JPanel gallery = new JPanel(new GridBagLayout());
        gallery.setOpaque(false);
        GridBagConstraints gGallery = new GridBagConstraints();
        gGallery.fill = GridBagConstraints.BOTH;
        gGallery.weightx = 1.0;
        gGallery.weighty = 1.0;

        // Load Images
        List<String> images = p.getImages();
        if (images != null && !images.isEmpty()) {
            imgMain = new ImageIcon(images.get(0)).getImage();
            if (images.size() > 1) imgSmall1 = new ImageIcon(images.get(1)).getImage();
            if (images.size() > 2) imgSmall2 = new ImageIcon(images.get(2)).getImage();
        }

        // Main Large Image (Spans 2 rows)
        JPanel mainImg = createRoundedImagePanel(imgMain);
        gGallery.gridx = 0; gGallery.gridy = 0; gGallery.gridheight = 2;
        gGallery.weightx = 0.7;
        gGallery.insets = new Insets(0, 0, 0, 15);
        gallery.add(mainImg, gGallery);

        // Small Image 1
        JPanel s1 = createRoundedImagePanel(imgSmall1 != null ? imgSmall1 : imgMain);
        gGallery.gridx = 1; gGallery.gridy = 0; gGallery.gridheight = 1;
        gGallery.weightx = 0.3;
        gGallery.insets = new Insets(0, 0, 10, 0);
        gallery.add(s1, gGallery);

        // Small Image 2
        JPanel s2 = createRoundedImagePanel(imgSmall2 != null ? imgSmall2 : imgMain);
        gGallery.gridx = 1; gGallery.gridy = 1;
        gGallery.insets = new Insets(10, 0, 0, 0);
        gallery.add(s2, gGallery);

        gbc.gridx = 0; gbc.weightx = 0.7; gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 30);
        content.add(gallery, gbc);

        // 2. SUPREME BOOKING CARD (Right - Weight 30%)
        GlassPanel bookingCard = new GlassPanel(25, LuxuryTheme.GLASS_BG);
        bookingCard.setLayout(new GridBagLayout());
        bookingCard.setBorder(new EmptyBorder(35, 35, 35, 35));
        
        GridBagConstraints cardGbc = new GridBagConstraints();
        cardGbc.fill = GridBagConstraints.HORIZONTAL;
        cardGbc.weightx = 1.0;
        cardGbc.gridx = 0;

        // --- Header ---
        JLabel rType = new JLabel(p.getRoomType());
        rType.setFont(new Font("Segoe UI", Font.BOLD, 24));
        rType.setForeground(LuxuryTheme.TEXT_PRIMARY);
        cardGbc.gridy = 0;
        bookingCard.add(rType, cardGbc);

        JLabel caps = new JLabel(p.getCapacity());
        caps.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        caps.setForeground(LuxuryTheme.TEXT_SECONDARY);
        cardGbc.gridy = 1;
        cardGbc.insets = new Insets(5, 0, 30, 0);
        bookingCard.add(caps, cardGbc);

        // --- Features ---
        addCardLabel(bookingCard, cardGbc, 2, "KEY FEATURES", LuxuryTheme.ACCENT_GOLD, true);
        addCardLabel(bookingCard, cardGbc, 3, "- Artisan Breakfast Included", LuxuryTheme.TEXT_PRIMARY, false);
        addCardLabel(bookingCard, cardGbc, 4, "- Infinity Pool Access", LuxuryTheme.TEXT_PRIMARY, false);
        addCardLabel(bookingCard, cardGbc, 5, "- Private Butler Service", LuxuryTheme.TEXT_PRIMARY, false);

        addCardLabel(bookingCard, cardGbc, 6, "STAY POLICIES", LuxuryTheme.ACCENT_GOLD, true);
        addCardLabel(bookingCard, cardGbc, 7, "- Mobile Check-in Ready", new Color(150, 255, 180), false);
        addCardLabel(bookingCard, cardGbc, 8, "- Free Flex Cancellation", new Color(150, 255, 180), false);

        // --- Bottom Pricing ---
        cardGbc.weighty = 1.0;
        cardGbc.gridy = 9;
        bookingCard.add(new JLabel(" "), cardGbc); // Flex spacer

        cardGbc.weighty = 0.0;
        cardGbc.insets = new Insets(30, 0, 5, 0);
        JLabel oldP = new JLabel("<html><s>$" + String.format("%.0f", p.getBasePrice()*1.2) + "</s></html>");
        oldP.setForeground(LuxuryTheme.TEXT_SECONDARY);
        cardGbc.gridy = 10;
        bookingCard.add(oldP, cardGbc);

        JLabel newP = new JLabel("<html><b>$" + String.format("%.0f", p.getBasePrice()) + "</b> <font color='#8B949E' size='4'>Per Night</font></html>");
        newP.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        newP.setForeground(Color.WHITE);
        cardGbc.gridy = 11;
        cardGbc.insets = new Insets(0, 0, 25, 0);
        bookingCard.add(newP, cardGbc);

        LuxuryButton bookBtn = new LuxuryButton("BOOK RESERVATION NOW");
        bookBtn.setPreferredSize(new Dimension(200, 60));
        bookBtn.addActionListener(e -> onBook.accept(p));
        cardGbc.gridy = 12;
        cardGbc.insets = new Insets(0, 0, 0, 0);
        bookingCard.add(bookBtn, cardGbc);

        gbc.gridx = 1; gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, 0, 0);
        content.add(bookingCard, gbc);

        add(content, BorderLayout.CENTER);
    }

    private void addCardLabel(JPanel card, GridBagConstraints gbc, int y, String text, Color color, boolean title) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", title ? Font.BOLD : Font.PLAIN, title ? 12 : 14));
        l.setForeground(color);
        gbc.gridy = y;
        gbc.insets = new Insets(title ? 20 : 0, 0, title ? 12 : 6, 0);
        card.add(l, gbc);
    }

    private JPanel createRoundedImagePanel(Image img) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (img != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    Shape clip = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30);
                    g2.setClip(clip);
                    
                    double imgW = img.getWidth(null);
                    double imgH = img.getHeight(null);
                    double panelW = getWidth();
                    double panelH = getHeight();
                    double scale = Math.max(panelW / imgW, panelH / imgH);
                    int w = (int) (imgW * scale);
                    int h = (int) (imgH * scale);
                    int x = (int) ((panelW - w) / 2);
                    int y = (int) ((panelH - h) / 2);
                    g2.drawImage(img, x, y, w, h, this);
                    g2.dispose();
                }
            }
        };
    }
}
