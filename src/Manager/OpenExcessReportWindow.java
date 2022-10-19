package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

public class OpenExcessReportWindow extends JFrame {

    String date;
    OpenExcessReportWindow(String dateInput){

        date = dateInput;
        // Data to be displayed in the JTable
        Object[][] data;
        String[][] invTable = new String[200][4];
        jdbcpostgreSQL.exce(invTable, date);
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
