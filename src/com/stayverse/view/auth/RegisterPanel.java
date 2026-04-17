package com.stayverse.view.auth;

import com.stayverse.ctrl.AuthController;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.GlassPanel;
import com.stayverse.view.components.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class RegisterPanel extends JPanel {
    private AuthController ctrl = new AuthController();
    private Image bgImage;

    public RegisterPanel(Runnable onSwitch) {
        setLayout(new GridBagLayout());
        
        try {
            bgImage = new ImageIcon("C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\luxury_hotel_bg_1776386960436.png").getImage();
        } catch (Exception e) {}

        GlassPanel card = new GlassPanel(30, LuxuryTheme.GLASS_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40));
        card.setPreferredSize(new Dimension(450, 600));

        JLabel title = new JLabel("JOIN STAYVERSE");
        title.setFont(LuxuryTheme.FONT_TITLE);
        title.setForeground(LuxuryTheme.ACCENT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LuxuryField name = new LuxuryField("Full Name");
        LuxuryField email = new LuxuryField("Email Address");
        LuxuryField pass = new LuxuryField("Password");
        
        LuxuryButton regBtn = new LuxuryButton("CREATE ACCOUNT");
        regBtn.setMaximumSize(new Dimension(400, 50));
        regBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        regBtn.addActionListener(e -> {
            if (ctrl.register(email.getText(), name.getText(), pass.getText())) {
                JOptionPane.showMessageDialog(this, "Welcome to StayVerse! Please Sign In.", "Success", JOptionPane.INFORMATION_MESSAGE);
                onSwitch.run();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Email might already be in use.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton sw = new JButton("Already a member? Sign In");
        sw.setFont(LuxuryTheme.FONT_REGULAR);
        sw.setForeground(LuxuryTheme.ACCENT_BLUE);
        sw.setBorderPainted(false);
        sw.setContentAreaFilled(false);
        sw.setAlignmentX(Component.CENTER_ALIGNMENT);
        sw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sw.addActionListener(e -> onSwitch.run());

        card.add(title);
        card.add(Box.createVerticalStrut(40));
        card.add(name);
        card.add(Box.createVerticalStrut(20));
        card.add(email);
        card.add(Box.createVerticalStrut(20));
        card.add(pass);
        card.add(Box.createVerticalStrut(40));
        card.add(regBtn);
        card.add(Box.createVerticalStrut(20));
        card.add(sw);

        add(card);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            g.setColor(LuxuryTheme.BG_DEEP);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
