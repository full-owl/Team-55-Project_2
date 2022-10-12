package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {
    JButton update_inventory;
    JButton update_prices;
    JButton register_view;
    miscPanel() {
        update_inventory = new JButton("Edit Inventory");
        update_inventory.setBounds(200,100,200,50);
        //JButton add_inventory = new JButton("Add to Inventory");
        //add_inventory.setBounds(0, 100, 200,50);
        update_prices = new JButton("Edit Prices");
        update_prices.setBounds(500, 100, 200,50);
        register_view = new JButton("Register View");
        register_view.setBounds(500,175,200,50);

        this.add(update_inventory);
        //this.add(add_inventory);
        this.add(update_prices);
        this.add(register_view);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update_inventory) {

        }
        if(e.getSource() == update_prices) {

        }
        if(e.getSource() == register_view) {

        }
    }
}
