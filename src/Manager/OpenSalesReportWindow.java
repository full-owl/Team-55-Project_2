package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

public class OpenSalesReportWindow extends JFrame{

    String dateTo;
    String dateFrom;
    JTable salesReport;
    String[][] ordTable;

    OpenSalesReportWindow(String dateToInput, String dateFromInput) {
        dateTo = dateToInput;
        dateFrom = dateFromInput;

        // Data to be displayed in the JTable
        Object[][] data;
        ordTable = new String[100][5];
        jdbcpostgreSQL.getSalesReportTable(ordTable, dateTo, dateFrom);
        data = ordTable;
        // Column Names
        String[] columnNames = {"Order ID", "Date", "Subtotal", "Total", "EmployeeID"};

        salesReport = new JTable(data, columnNames);
        salesReport.setFillsViewportHeight(true);

        // // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(salesReport);

        this.add(sp);
        this.setVisible(true);
    }
}
