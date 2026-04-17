package com.stayverse.view.booking;

import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.GlassPanel;
import com.stayverse.view.components.LuxuryButton;
import com.stayverse.view.components.LuxuryField;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GuestStepPanel extends JPanel {
    public GuestStepPanel(BookingWizardPanel wizard) {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel card = new GlassPanel(30, new Color(30, 35, 45, 200));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(50, 60, 50, 60));

        JLabel title = new JLabel("GUEST INFORMATION");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(LuxuryTheme.ACCENT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LuxuryField guests = new LuxuryField("Number of Guests");
        LuxuryField checkIn = new LuxuryField("Check-in Date (YYYY-MM-DD)");
        LuxuryField checkOut = new LuxuryField("Check-out Date (YYYY-MM-DD)");
        
        // Ensure fields are centered
        guests.setMaximumSize(new Dimension(500, 60));
        checkIn.setMaximumSize(new Dimension(500, 60));
        checkOut.setMaximumSize(new Dimension(500, 60));

        LuxuryButton next = new LuxuryButton("CONTINUE TO PAYMENT");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.setMaximumSize(new Dimension(500, 60));
        next.addActionListener(e -> wizard.next());

        card.add(title);
        card.add(Box.createVerticalStrut(50));
        card.add(guests);
        card.add(Box.createVerticalStrut(25));
        card.add(checkIn);
        card.add(Box.createVerticalStrut(25));
        card.add(checkOut);
        card.add(Box.createVerticalStrut(50));
        card.add(next);

        add(card);
    }
}
