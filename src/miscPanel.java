package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {
    JButton addInventory;
    JButton editInventory;
    JButton updatePrices;
    JButton register_view;
    miscPanel() {
        addInventory = new JButton("Add New Item to Inventory");
        addInventory.setBounds(150,100,200,50);
        addInventory.addActionListener(this);

        editInventory = new JButton("Edit Inventory");
        editInventory.setBounds(150,150,200,50);
        editInventory.addActionListener(this);

        updatePrices = new JButton("Edit Prices");
        updatePrices.setBounds(500, 100, 200,50);
        updatePrices.addActionListener(this);

        register_view = new JButton("Register View");
        register_view.setBounds(500,175,200,50);

        this.add(addInventory);
        this.add(editInventory);
        this.add(updatePrices);
        this.add(register_view);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addInventory) {
            NewWindow addInventoryWindow = new NewWindow();
        }
        if (e.getSource() == editInventory) {
            EditInventoryWindow editInventoryWindow = new EditInventoryWindow();
        }
        if(e.getSource() == updatePrices) {
            EditPriceWindow updatePricesWindow = new EditPriceWindow();
        }
        if(e.getSource() == register_view) {

        }
    }
}
