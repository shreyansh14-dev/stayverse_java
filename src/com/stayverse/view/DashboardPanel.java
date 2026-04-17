package com.stayverse.view;

import com.stayverse.util.LuxuryTheme;
import com.stayverse.util.DatabaseManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class DashboardPanel extends JPanel {
    private StatCard bookingsCard;
    private StatCard spendingCard;
    private JLabel gVal;

    public DashboardPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(40, 60, 40, 60));
        
        // Header Row
        JPanel headerRow = new JPanel(new BorderLayout());
        headerRow.setOpaque(false);
        
        JPanel welcomeText = new JPanel(new BorderLayout());
        welcomeText.setOpaque(false);
        JLabel welcome = new JLabel("Welcome back, User 👋");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 32));
        welcome.setForeground(LuxuryTheme.TEXT_PRIMARY);
        
        JLabel sub = new JLabel("Here is what is happening with your travel plans today.");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sub.setForeground(LuxuryTheme.TEXT_SECONDARY);
        
        welcomeText.add(welcome, BorderLayout.NORTH);
        welcomeText.add(sub, BorderLayout.CENTER);
        
        JButton planBtn = new JButton("⊕ Plan a Trip");
        planBtn.setFont(LuxuryTheme.FONT_BOLD);
        planBtn.setForeground(Color.WHITE);
        planBtn.setBackground(new Color(30, 35, 45));
        planBtn.setFocusPainted(false);
        planBtn.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        headerRow.add(welcomeText, BorderLayout.WEST);
        headerRow.add(planBtn, BorderLayout.EAST);
        
        add(headerRow, BorderLayout.NORTH);

        // Body with Stat Cards
        JPanel body = new JPanel(new GridLayout(3, 1, 0, 25));
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(40, 0, 0, 0));
        
        bookingsCard = new StatCard("Total Bookings", "0", "LIVE STATUS", new Color(200, 255, 220), new Color(0, 100, 50));
        spendingCard = new StatCard("Total Spending", "$0", "SECURE PAY", new Color(220, 230, 255), new Color(50, 50, 150));
        body.add(bookingsCard);
        body.add(spendingCard);
        
        JPanel geniusCard = new GlassPanel(20, new Color(20, 25, 35));
        geniusCard.setLayout(new BorderLayout());
        geniusCard.setBorder(new EmptyBorder(25, 35, 25, 35));
        
        JLabel gTitle = new JLabel("Genius Status");
        gTitle.setFont(LuxuryTheme.FONT_BOLD);
        gTitle.setForeground(LuxuryTheme.TEXT_SECONDARY);
        
        gVal = new JLabel("Level 1");
        gVal.setFont(new Font("Segoe UI", Font.BOLD, 48));
        gVal.setForeground(Color.WHITE);
        
        JLabel gBadge = new JLabel(" 10% OFF STAYS ");
        gBadge.setOpaque(true);
        gBadge.setBackground(LuxuryTheme.ACCENT_GOLD);
        gBadge.setForeground(Color.BLACK);
        gBadge.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        geniusCard.add(gTitle, BorderLayout.NORTH);
        geniusCard.add(gVal, BorderLayout.WEST);
        geniusCard.add(gBadge, BorderLayout.EAST);
        
        body.add(geniusCard);
        add(body, BorderLayout.CENTER);
        
        refresh();
    }

    public void refresh() {
        try {
            Connection conn = DatabaseManager.getInstance().getConnection();
            
            // Count Bookings
            PreparedStatement psCount = conn.prepareStatement("SELECT COUNT(*) FROM bookings");
            ResultSet rsCount = psCount.executeQuery();
            int count = rsCount.next() ? rsCount.getInt(1) : 0;
            bookingsCard.setVal(String.valueOf(count));
            
            // Sum Spending
            PreparedStatement psSum = conn.prepareStatement("SELECT SUM(total_cost) FROM bookings");
            ResultSet rsSum = psSum.executeQuery();
            double sum = rsSum.next() ? rsSum.getDouble(1) : 0.0;
            spendingCard.setVal("$" + String.format("%.0f", sum));
            
            // Update Level
            if (count >= 5) gVal.setText("Level 2");
            else gVal.setText("Level 1");
            
        } catch (Exception e) { e.printStackTrace(); }
    }

    private class StatCard extends GlassPanel {
        private JLabel v;
        public StatCard(String title, String val, String badge, Color badgeBg, Color badgeText) {
            super(20, Color.WHITE);
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(25, 35, 25, 35));
            
            JLabel t = new JLabel(title);
            t.setFont(LuxuryTheme.FONT_BOLD);
            t.setForeground(new Color(120, 130, 145));
            
            v = new JLabel(val);
            v.setOpaque(false);
            v.setFont(new Font("Segoe UI", Font.BOLD, 48));
            v.setForeground(new Color(20, 25, 30));
            
            JLabel b = new JLabel(" " + badge + " ");
            b.setOpaque(true);
            b.setBackground(badgeBg);
            b.setForeground(badgeText);
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
            center.setOpaque(false);
            center.add(v);
            center.add(b);
            
            add(t, BorderLayout.NORTH);
            add(center, BorderLayout.CENTER);
        }
        public void setVal(String val) { v.setText(val); }
    }
}
