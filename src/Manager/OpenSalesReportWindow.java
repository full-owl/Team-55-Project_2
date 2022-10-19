package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

/**
 * OpenSalesReportWindow creates a JFrame with a JTable that displays the sales from a window of two
 * given dates that are requested from the user upon clicking the 'Sales Report' button in the managerGUI.
 * The dates are taken as a parameters from the SalesReportWindow class
 **/
public class OpenSalesReportWindow extends JFrame {

    String dateTo;
    String dateFrom;

    /**
     * Constructor for the OpenSalesReportWindow
     *
     * @param dateFromInput String providing the first date (yyyy-mm-dd)
     * @param dateToInput   String providing the second date (yyyy-mm-dd)
     **/
    OpenSalesReportWindow(String dateToInput, String dateFromInput) {
        dateTo = dateToInput;
        dateFrom = dateFromInput;

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] ordTable = new String[200][2];
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
        this.pack();
        this.setVisible(true);
    }
}
