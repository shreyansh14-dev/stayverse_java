package com.stayverse.view;

import com.stayverse.model.User;
import com.stayverse.util.DatabaseManager;
import com.stayverse.util.LuxuryTheme;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class MyTripsPanel extends JPanel {
    private User user;
    private JPanel list;
    private Runnable onUpdate;

    public MyTripsPanel(User user, Runnable onUpdate) {
        this.user = user;
        this.onUpdate = onUpdate;
        setLayout(new BorderLayout());
        setOpaque(false);
        
        JLabel title = new JLabel("MY LUXURY RESERVATIONS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(LuxuryTheme.TEXT_PRIMARY);
        title.setBorder(new EmptyBorder(0, 0, 30, 0));
        add(title, BorderLayout.NORTH);

        list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setOpaque(false);
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
        
        refresh();
    }

    public void refresh() {
        list.removeAll();
        try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement("SELECT * FROM bookings WHERE guest_name = ? ORDER BY created_at DESC")) {
            pstmt.setString(1, user.getName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String bookingId = rs.getString("id");
                GlassPanel item = new GlassPanel(15, LuxuryTheme.BG_CARD);
                item.setLayout(new BorderLayout());
                item.setBorder(new EmptyBorder(20, 25, 20, 25));
                item.setMaximumSize(new Dimension(1100, 110));
                
                JLabel info = new JLabel("<html><b>BOOKING #" + bookingId + "</b><br>" 
                                        + "<font color='#8B949E'>Property: " + rs.getString("property_id") + " | Total: $" + rs.getDouble("total_cost") + "</font></html>");
                info.setForeground(LuxuryTheme.TEXT_PRIMARY);
                info.setFont(LuxuryTheme.FONT_REGULAR);
                
                JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
                actions.setOpaque(false);
                
                JButton modify = new JButton("Modify");
                modify.setFont(new Font("Segoe UI", Font.BOLD, 12));
                modify.setForeground(LuxuryTheme.ACCENT_BLUE);
                modify.setBorderPainted(false);
                modify.setContentAreaFilled(false);
                modify.setCursor(new Cursor(Cursor.HAND_CURSOR));
                modify.addActionListener(e -> JOptionPane.showMessageDialog(this, "Modification suite coming soon! Please contact concierge."));
                
                JButton cancel = new JButton("Cancel Reservation");
                cancel.setFont(new Font("Segoe UI", Font.BOLD, 12));
                cancel.setForeground(new Color(255, 100, 100));
                cancel.setBorderPainted(false);
                cancel.setContentAreaFilled(false);
                cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cancel.addActionListener(e -> {
                    int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this luxury stay?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        cancelBooking(bookingId);
                    }
                });
                
                actions.add(modify);
                actions.add(cancel);
                
                item.add(info, BorderLayout.CENTER);
                item.add(actions, BorderLayout.EAST);
                
                list.add(item);
                list.add(Box.createVerticalStrut(15));
            }
        } catch (Exception e) {}
        list.revalidate(); list.repaint();
    }

    private void cancelBooking(String id) {
        System.out.println("STAYVERSE_LOG: Requesting cancellation of booking ID: " + id);
        try (PreparedStatement pstmt = DatabaseManager.getInstance().getConnection().prepareStatement("DELETE FROM bookings WHERE id = ?")) {
            pstmt.setString(1, id);
            int affected = pstmt.executeUpdate();
            System.out.println("STAYVERSE_LOG: Cancellation result - Rows affected: " + affected);
            
            if (affected > 0) {
                if (onUpdate != null) onUpdate.run();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Your reservation has been successfully cancelled.", "StayVerse Confirmation", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                });
            } else {
                JOptionPane.showMessageDialog(this, "Could not locate reservation. It may have already been processed.", "StayVerse Notice", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "Error processing cancellation: " + e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
