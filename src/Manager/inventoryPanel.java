package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * InventoryPanel creates a JPanel in the II quadrant of the main JFrame displaying the invetory table from the database in a JTable
 **/
public class inventoryPanel extends JPanel {

    /**
     * constructor for InventoryPanel
     **/
    inventoryPanel() {

        JLabel l = new JLabel();
        l.setText("Inventory");

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] invTable = new String[jdbcpostgreSQL.getDBSize("inventory") + 3][4];
        jdbcpostgreSQL.getInvTable(invTable);
        data = invTable;

        // Column Names
        String[] columnNames = {"id", "ingredient", "amount", "units"};

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        //j.setBounds(0, 0, 200, 200);
        j.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        this.add(l);
        this.add(sp);
    }
}
