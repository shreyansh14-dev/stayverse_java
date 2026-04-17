package com.stayverse.view.booking;

import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.GlassPanel;
import com.stayverse.view.components.LuxuryButton;
import com.stayverse.view.components.LuxuryField;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaymentStepPanel extends JPanel {
    public PaymentStepPanel(BookingWizardPanel wizard) {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(40, 40, 40, 40));

        JPanel card = new GlassPanel(30, new Color(30, 35, 45, 200));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(50, 60, 50, 60));

        JLabel title = new JLabel("SECURE LUXURY PAYMENT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(LuxuryTheme.ACCENT_GOLD);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LuxuryField cardNum = new LuxuryField("Card Number (**** **** **** ****)");
        LuxuryField expiry = new LuxuryField("Expiry Date (MM/YY)");
        LuxuryField cvv = new LuxuryField("CVV");
        
        cardNum.setMaximumSize(new Dimension(500, 60));
        expiry.setMaximumSize(new Dimension(500, 60));
        cvv.setMaximumSize(new Dimension(500, 60));

        LuxuryButton payBtn = new LuxuryButton("CONFIRM & BOOK NOW");
        payBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        payBtn.setMaximumSize(new Dimension(500, 60));
        payBtn.addActionListener(e -> wizard.next());

        card.add(title);
        card.add(Box.createVerticalStrut(50));
        card.add(cardNum);
        card.add(Box.createVerticalStrut(25));
        card.add(expiry);
        card.add(Box.createVerticalStrut(25));
        card.add(cvv);
        card.add(Box.createVerticalStrut(50));
        card.add(payBtn);

        add(card);
    }
}
