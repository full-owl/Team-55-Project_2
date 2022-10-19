package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RestockReportWindow extends  JFrame{
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

