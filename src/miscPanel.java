package src;

import src.Cashier.CashierView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {
    JButton addInventory;
    JButton editInventory;
    JButton updatePrices;
    JButton addNewItem;
    //JButton registerView;
    miscPanel() {
        addInventory = new JButton("Add New Item to Inventory");
        addInventory.setBounds(50,100,200,50);
        addInventory.addActionListener(this);

        editInventory = new JButton("Edit Inventory");
        editInventory.setBounds(50,170,200,50);
        editInventory.addActionListener(this);

        updatePrices = new JButton("Edit Prices");
        updatePrices.setBounds(275, 170, 200,50);
        updatePrices.addActionListener(this);

        addNewItem = new JButton("Add New Menu Item");
        addNewItem.setBounds(275, 100, 200,50);
        addNewItem.addActionListener(this);

//        registerView = new JButton("Register View");
//        registerView.setBounds(540,300,200,50);
//        registerView.addActionListener(this);

        this.add(addInventory);
        this.add(editInventory);
        this.add(updatePrices);
        this.add(addNewItem);
        //this.add(registerView);
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
        if (e.getSource()==addNewItem) {
            AddItemWindow addItemWindow = new AddItemWindow();
        }
//        if(e.getSource() == registerView) {
//            CashierView cashierWindow = new CashierView();
//            cashierWindow.startCashierView();
//            managerGui.f.dispose();
//        }
    }
}
