package src.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miscPanel extends JPanel implements ActionListener {
    JButton addInventory;
    JButton editInventory;
    JButton updatePrices;
    JButton addNewItem;
    JButton salesReport;
    JButton excessReport;
    JButton restockReport;
    miscPanel() {
        addInventory = new JButton("Add Ingredients");
        addInventory.setBounds(25,100,200,50);
        addInventory.addActionListener(this);

        editInventory = new JButton("Edit Inventory");
        editInventory.setBounds(25,170,200,50);
        editInventory.addActionListener(this);

        updatePrices = new JButton("Edit Prices");
        updatePrices.setBounds(230, 170, 200,50);
        updatePrices.addActionListener(this);

        addNewItem = new JButton("Add Menu Item");
        addNewItem.setBounds(230, 100, 200,50);
        addNewItem.addActionListener(this);

        salesReport = new JButton("Sales Report");
        salesReport.setBounds(25, 250, 200,50);
        salesReport.addActionListener(this);

        excessReport = new JButton("Excess Report");
        excessReport.setBounds(230, 250, 200,50);
        excessReport.addActionListener(this);

        restockReport = new JButton("Restock Report");
        restockReport.setBounds(435, 250, 200,50);
        restockReport.addActionListener(this);




        this.add(addInventory);
        this.add(editInventory);
        this.add(updatePrices);
        this.add(addNewItem);
        this.add(salesReport);
        this.add(excessReport);
        this.add(restockReport);
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
