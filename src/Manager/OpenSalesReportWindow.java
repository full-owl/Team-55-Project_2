package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

public class OpenSalesReportWindow extends JFrame{

    String dateTo;
    String dateFrom;

    OpenSalesReportWindow(String dateToInput, String dateFromInput) {
        dateTo = dateToInput;
        dateFrom = dateFromInput;

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] ordTable = new String[100][2];
        jdbcpostgreSQL.getSalesReportTable(ordTable, dateTo, dateFrom);
        data = ordTable;
        // Column Names
        String[] columnNames = {"Order Item", "Total"};

        JTable salesReport = new JTable(data, columnNames);
        salesReport.setFillsViewportHeight(true);

        // // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(salesReport);

        this.setTitle("Sales Report");
        this.add(sp);
        this.setVisible(true);
    }
}
