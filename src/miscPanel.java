package src;

import javax.swing.*;
import java.awt.*;

public class miscPanel extends JPanel {
    miscPanel() {
        JButton update_inventory = new JButton("Update Inventory");
        update_inventory.setBounds(200,100,200,50);
        JButton add_inventory = new JButton("Add to Inventory");
        add_inventory.setBounds(0, 100, 200,50);
        JButton update_prices = new JButton("Update Prices");
        update_prices.setBounds(500, 100, 200,50);
        JButton register_view = new JButton("Register View");
        register_view.setBounds(500,175,200,50);

        this.add(update_inventory);
        this.add(add_inventory);
        this.add(update_prices);
        this.add(register_view);
        this.setLayout(new BorderLayout());
    }
}
