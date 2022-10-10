import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;


public class inventoryPanel extends JPanel {

    inventoryPanel() {

        JLabel l = new JLabel();
        l.setText("Inventory");

        // Data to be displayed in the JTable
        Object[][] data;
        String[][] invTable = new String[29][4];
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
