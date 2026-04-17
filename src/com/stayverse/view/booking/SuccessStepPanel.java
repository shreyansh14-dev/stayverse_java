package com.stayverse.view.booking;

import com.stayverse.util.LuxuryTheme;
import com.stayverse.util.DatabaseManager;
import com.stayverse.view.components.LuxuryButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.PreparedStatement;

public class SuccessStepPanel extends JPanel {
    public SuccessStepPanel(BookingWizardPanel wizard) {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false);

        JLabel icon = new JLabel("✓");
        icon.setFont(new Font("Segoe UI", Font.BOLD, 72));
        icon.setForeground(LuxuryTheme.ACCENT_GOLD);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("RESERVATION CONFIRMED");
        title.setFont(LuxuryTheme.FONT_BOLD);
        title.setForeground(LuxuryTheme.TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Your luxury escape to " + wizard.getP().getName() + " is ready.");
        subtitle.setFont(LuxuryTheme.FONT_REGULAR);
        subtitle.setForeground(LuxuryTheme.TEXT_SECONDARY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        LuxuryButton finishBtn = new LuxuryButton("VIEW MY TRIPS");
        finishBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishBtn.setMaximumSize(new Dimension(300, 50));
        finishBtn.addActionListener(e -> {
            save(wizard);
            wizard.getOnComplete().run();
        });

        card.add(icon);
        card.add(Box.createVerticalStrut(20));
        card.add(title);
        card.add(Box.createVerticalStrut(10));
        card.add(subtitle);
        card.add(Box.createVerticalStrut(50));
        card.add(finishBtn);

        add(card);
    }

    private void save(BookingWizardPanel w) {
        try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(
            "INSERT INTO bookings(id, property_id, guest_name, total_cost, created_at) VALUES(?,?,?,?,?)")) {
            pstmt.setString(1, "BK-" + System.currentTimeMillis());
            pstmt.setString(2, w.getP().getID());
            pstmt.setString(3, w.getUser().getName());
            pstmt.setDouble(4, w.getP().getBasePrice());
            pstmt.setString(5, java.time.LocalDateTime.now().toString());
            pstmt.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}
