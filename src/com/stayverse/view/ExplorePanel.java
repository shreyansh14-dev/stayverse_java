package com.stayverse.view;

import com.stayverse.model.AbstractProperty;
import com.stayverse.util.LuxuryTheme;
import com.stayverse.ctrl.SearchController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class ExplorePanel extends JPanel {
    private SearchController ctrl = new SearchController();
    private JPanel grid;

    public ExplorePanel(Consumer<AbstractProperty> onSelect) {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 30, 0));
        
        JLabel title = new JLabel("EXPLORE CURATED STAYS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(LuxuryTheme.TEXT_PRIMARY);
        
        JLabel subtitle = new JLabel("Hand-picked luxury properties for the discerning traveler.");
        subtitle.setFont(LuxuryTheme.FONT_REGULAR);
        subtitle.setForeground(LuxuryTheme.TEXT_SECONDARY);
        
        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        grid = new JPanel(new GridLayout(0, 3, 30, 30));
        grid.setOpaque(false);
        
        JScrollPane scroll = new JScrollPane(grid);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        for (AbstractProperty p : ctrl.getAllProperties()) {
            grid.add(new PropertyCard(p, onSelect));
        }
    }
}
