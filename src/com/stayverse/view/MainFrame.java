package com.stayverse.view;

import com.stayverse.model.*;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.view.auth.*;
import com.stayverse.view.booking.*;
import com.stayverse.view.components.LuxuryButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainContainer;
    private CardLayout cardLayout;
    private User currentUser;
    
    private JPanel appContainer;
    private JPanel sidebar;
    private JPanel contentArea;
    private CardLayout contentLayout;
    private DashboardPanel dashboard;

    public MainFrame() {
        setTitle("StayVerse Premium | Luxury Travel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);
        getContentPane().setBackground(LuxuryTheme.BG_DEEP);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        mainContainer.setOpaque(false);

        LoginPanel login = new LoginPanel(this::onLoginSuccess, () -> cardLayout.show(mainContainer, "Register"));
        RegisterPanel reg = new RegisterPanel(() -> cardLayout.show(mainContainer, "Login"));

        mainContainer.add(login, "Login");
        mainContainer.add(reg, "Register");
        add(mainContainer);
    }

    private void onLoginSuccess(User user) {
        this.currentUser = user;
        initializeApp();
        cardLayout.show(mainContainer, "App");
    }

    private void initializeApp() {
        appContainer = new JPanel(new BorderLayout());
        appContainer.setOpaque(false);

        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(280, 0));
        sidebar.setBackground(new Color(18, 22, 30));
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel logo = new JLabel("STAYVERSE");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logo.setForeground(LuxuryTheme.ACCENT_GOLD);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(50));

        addSidebarBtn("Dashboard", "Dashboard");
        addSidebarBtn("Explore Luxury", "Explore");
        addSidebarBtn("My Trips", "Trips");

        contentLayout = new CardLayout();
        contentArea = new JPanel(contentLayout);
        contentArea.setOpaque(false);
        contentArea.setBorder(new EmptyBorder(30, 40, 30, 40));

        dashboard = new DashboardPanel();
        contentArea.add(dashboard, "Dashboard");
        contentArea.add(new ExplorePanel(this::onPropertySelected), "Explore");
        contentArea.add(new MyTripsPanel(currentUser, () -> dashboard.refresh()), "Trips");

        appContainer.add(sidebar, BorderLayout.WEST);
        appContainer.add(contentArea, BorderLayout.CENTER);
        mainContainer.add(appContainer, "App");
    }

    private void addSidebarBtn(String text, String target) {
        JButton b = new JButton(text);
        b.setFont(LuxuryTheme.FONT_BOLD);
        b.setForeground(LuxuryTheme.TEXT_SECONDARY);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setMaximumSize(new Dimension(240, 50));
        b.addActionListener(e -> {
            if (target.equals("Dashboard")) dashboard.refresh();
            contentLayout.show(contentArea, target);
        });
        
        sidebar.add(b);
        sidebar.add(Box.createVerticalStrut(10));
    }

    private void onPropertySelected(AbstractProperty p) {
        PropertyDetailPanel d = new PropertyDetailPanel(p, currentUser, 
            () -> contentLayout.show(contentArea, "Explore"),
            (prop) -> {
                BookingWizardPanel w = new BookingWizardPanel(prop, currentUser, () -> {
                    dashboard.refresh();
                    contentLayout.show(contentArea, "Trips");
                });
                contentArea.add(w, "Wizard");
                contentLayout.show(contentArea, "Wizard");
            }
        );
        contentArea.add(d, "Detail");
        contentLayout.show(contentArea, "Detail");
    }
}
