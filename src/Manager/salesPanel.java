package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

/**
 * SalesPanel creates a JPanel in the I quadrant of the main JFrame displaying the orders table from the database in a JTable
 **/
public class salesPanel extends JPanel {

    /**
     * Constructor for SalesPanel
     **/
    salesPanel() {

        JLabel l = new JLabel();
        l.setText("Sales");

        // Data to be displayed in the JTable
        Object[][] data;

        String[][] ordTable = new String[100][5];
        jdbcpostgreSQL.getOrdTable(ordTable);
        data = ordTable;
        // Column Names
        String[] columnNames = {"Order ID", "Date", "Subtotal", "Total", "EmployeeID"};

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        //j.setBounds(0, 0, 150, 150);
        j.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        //sp.setBounds(0,0,350,350);

        this.add(l);
        this.add(sp);

    }
}
