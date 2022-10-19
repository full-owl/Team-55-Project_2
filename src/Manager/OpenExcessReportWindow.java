package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

/**
 * OpenExcessReportWindow creates a JFrame with a JTable that displays the specific results for
 * a given date requested from the user upon clicking the 'Excess Report' button in the managerGUI.
 * The date is taken in as a parameter from the ExcessReportWindow class
 **/
public class OpenExcessReportWindow extends JFrame {

    String date;

    /**
     * Constructor for the OpenExcessReportWindow
     *
     * @param dateInput String providing the date (yyyy-mm-dd)  to examine excess
     **/
    OpenExcessReportWindow(String dateInput) {

        date = dateInput;
        // Data to be displayed in the JTable
        Object[][] data;
        String[][] invTable = new String[200][4];
        jdbcpostgreSQL.getExcessReport(invTable, date);
        data = invTable;
        // Column Names
        String[] columnNames = {"ingredients", "amount used", "current amount", "units"};

        JTable salesReport = new JTable(data, columnNames);
        salesReport.setFillsViewportHeight(true);

        // // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(salesReport);

        this.setTitle("Excess Report");
        this.add(sp);
        this.pack();
        this.setVisible(true);
    }
}
