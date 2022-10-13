package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {
    JButton addInventory;
    JButton editInventory;
    JButton update_prices;
    JButton register_view;
    miscPanel() {
        addInventory = new JButton("Add New Item to Inventory");
        addInventory.setBounds(150,100,200,50);
        addInventory.addActionListener(this);

        editInventory = new JButton("Edit Inventory");
        editInventory.setBounds(150,150,200,50);
        editInventory.addActionListener(this);

        update_prices = new JButton("Edit Prices");
        update_prices.setBounds(500, 100, 200,50);
        register_view = new JButton("Register View");
        register_view.setBounds(500,175,200,50);

        this.add(addInventory);
        this.add(editInventory);
        this.add(update_prices);
        this.add(register_view);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addInventory) {
            NewWindow inventoryWindow = new NewWindow();
        }
        if (e.getSource() == editInventory) {
            EditInventoryWindow inventoryWindow = new EditInventoryWindow();
        }
        if(e.getSource() == update_prices) {

        }
        if(e.getSource() == register_view) {

        }
    }
}
