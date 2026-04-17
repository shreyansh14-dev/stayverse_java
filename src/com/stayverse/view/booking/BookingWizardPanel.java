package com.stayverse.view.booking;

import com.stayverse.model.AbstractProperty;
import com.stayverse.model.User;
import javax.swing.*;
import java.awt.*;

public class BookingWizardPanel extends JPanel {
    private AbstractProperty p;
    private User user;
    private Runnable onComplete;
    private CardLayout cl = new CardLayout();
    private JPanel container = new JPanel(cl);

    public BookingWizardPanel(AbstractProperty p, User user, Runnable onComplete) {
        this.p = p; this.user = user; this.onComplete = onComplete;
        setLayout(new BorderLayout());
        setOpaque(false);
        container.setOpaque(false);
        
        container.add(new GuestStepPanel(this), "Guest");
        container.add(new PaymentStepPanel(this), "Pay");
        container.add(new SuccessStepPanel(this), "Done");
        
        add(container, BorderLayout.CENTER);
        cl.show(container, "Guest");
    }

    public void next() { cl.next(container); }
    public AbstractProperty getP() { return p; }
    public User getUser() { return user; }
    public Runnable getOnComplete() { return onComplete; }
}
