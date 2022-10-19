package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * RestockReportWindow creates a JFrame with a JTable that displays the inventory below 10%
 * requested from the user upon clicking the 'Restock Report' button in the managerGUI.
 **/
public class RestockReportWindow extends  JFrame{

    /**
     * Constructor for the RestockReportWindow
     **/
    RestockReportWindow() {

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] invTable = new String[200][4];
        jdbcpostgreSQL.getRestockTable(invTable);
        data = invTable;
        // Column Names
        String[] columnNames = {"id", "ingredients", "amount", "units"};

        JTable salesReport = new JTable(data, columnNames);
        salesReport.setFillsViewportHeight(true);

        // // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(salesReport);

        this.setTitle("Restock Report");
        this.add(sp);
        this.pack();
        this.setVisible(true);
    }
}

