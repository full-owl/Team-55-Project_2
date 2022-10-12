package src;

import javax.swing.*;
import java.awt.*;

public class miscPanel extends JPanel {
    miscPanel() {
        JButton schedule_button = new JButton("Schedule");
        schedule_button.setSize(100,100);
        JButton alerts_button = new JButton("Alerts");
        alerts_button.setSize(100,100);
        JButton receipts_button = new JButton("Receipts");
        receipts_button.setSize(100,100);
        JButton register_button = new JButton("Register");
        register_button.setSize(100,100);

        this.add(schedule_button);
        this.add(alerts_button);
        this.add(receipts_button);
        this.add(register_button);
        this.setLayout(new GridLayout(1,4));
    }
}
