package com.stayverse.view.auth;

import com.stayverse.ctrl.AuthController;
import com.stayverse.model.User;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.GlassPanel;
import com.stayverse.view.components.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.function.Consumer;

public class LoginPanel extends JPanel {
    private AuthController ctrl = new AuthController();
    private Image bgImage;

    public LoginPanel(Consumer<User> onSuccess, Runnable onSwitch) {
        setLayout(new GridBagLayout());
        
        try {
            // Load the generated luxury background
            bgImage = new ImageIcon("C:\\Users\\Owner\\.gemini\\antigravity\\brain\\47c8e557-052b-4369-8e85-df9b2318e7a6\\luxury_hotel_bg_1776386960436.png").getImage();
        } catch (Exception e) {}

        GlassPanel card = new GlassPanel(30, LuxuryTheme.GLASS_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(40, 40, 40, 40));
        card.setPreferredSize(new Dimension(450, 550));

        JLabel title = new JLabel("STAYVERSE");
        title.setFont(LuxuryTheme.FONT_TITLE);
        title.setForeground(LuxuryTheme.ACCENT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Luxury Beyond Boundaries");
        subtitle.setFont(LuxuryTheme.FONT_REGULAR);
        subtitle.setForeground(LuxuryTheme.TEXT_SECONDARY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        LuxuryField email = new LuxuryField("Email Address");
        LuxuryField pass = new LuxuryField("Password"); // Note: In a real app, use a masked field
        
        LuxuryButton loginBtn = new LuxuryButton("SIGN IN");
        loginBtn.setMaximumSize(new Dimension(400, 50));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e -> {
            User u = ctrl.login(email.getText(), pass.getText());
            if (u != null) onSuccess.accept(u);
            else JOptionPane.showMessageDialog(this, "Authentication Failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        });

        JButton sw = new JButton("New here? Create an Account");
        sw.setFont(LuxuryTheme.FONT_REGULAR);
        sw.setForeground(LuxuryTheme.ACCENT_BLUE);
        sw.setBorderPainted(false);
        sw.setContentAreaFilled(false);
        sw.setAlignmentX(Component.CENTER_ALIGNMENT);
        sw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sw.addActionListener(e -> onSwitch.run());

        card.add(title); 
        card.add(Box.createVerticalStrut(10));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(50));
        card.add(email);
        card.add(Box.createVerticalStrut(20));
        card.add(pass);
        card.add(Box.createVerticalStrut(40));
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(20));
        card.add(sw);

        add(card);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            // Overlay dimming
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            g.setColor(LuxuryTheme.BG_DEEP);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
