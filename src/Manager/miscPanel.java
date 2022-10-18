package src.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {

    // button constructor
    public JButton createButton(String name, int x, int y) {
        JButton btn = new JButton();
        btn.setText(name);
        btn.setBounds(x,y,200,50);
        btn.addActionListener(this);
        return btn;
    }
    // Initialize buttons in manager view
    JButton addInventory = createButton("Add Ingredients", 25, 100);
    JButton editInventory = createButton("Edit Inventory", 25, 170);
    JButton updatePrices = createButton("Edit Prices",230,170);
    JButton addNewItem = createButton("Add Menu Item", 230 ,100);
    JButton salesReport = createButton("Sales Report", 25, 250);
    JButton excessReport = createButton("Excess Report", 230, 250);
    JButton restockReport = createButton("Restock Report", 435, 250);

    // Adds all buttons to a border layout in the fourth quadrant of the manager view
    miscPanel() {
        this.add(addInventory);
        this.add(editInventory);
        this.add(updatePrices);
        this.add(addNewItem);
        this.add(salesReport);
        this.add(excessReport);
        this.add(restockReport);
        this.setLayout(new BorderLayout());
    }

    // Opens new window for each button
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addInventory) {
            AddInventoryWindow addInventoryWindow = new AddInventoryWindow();
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
        if (e.getSource()==salesReport) {
            SalesReportWindow salesReportWindow = new SalesReportWindow();
        }
        if (e.getSource()==excessReport) {
            ExcessReportWindow excessReportWindow = new ExcessReportWindow();
        }
        if (e.getSource()==restockReport) {
            RestockReportWindow restockReportWindow = new RestockReportWindow();
        }
    }
}
