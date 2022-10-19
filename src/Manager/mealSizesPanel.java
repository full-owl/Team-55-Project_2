package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;

/**
 * MealSizesPanel creates a JPanel in the III quadrant of the main JFrame displaying the mealSizes table of the database in a JTable
 **/
public class mealSizesPanel extends JPanel {

    /**
     * Constructor for the mealSizesPanel
     **/
    mealSizesPanel() {

        JLabel l = new JLabel();
        l.setText("Meal Pricing");

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] mealSizeTable = new String[jdbcpostgreSQL.getDBSize("mealsizes") + 1][4];
        jdbcpostgreSQL.getMealSizeTable(mealSizeTable);
        data = mealSizeTable;

        // Column Names
        String[] columnNames = {"Food Type", "Meal Size", "Serving", "Price"};

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        //j.setBounds(0, 0, 150, 150);
        j.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        //sp.setBounds(0,0,150,150);

        this.add(l);
        this.add(sp);

    }
}
