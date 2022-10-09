import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;


public class inventoryPanel extends JPanel {

    inventoryPanel() {

        JLabel l = new JLabel();
        l.setText("Inventory");

        // Data to be displayed in the JTable
        Object[][] data = {

                {"0", "rice", "100"},
                {"1", "noodles", "100"}
        };

        // Column Names
        String[] columnNames = {"id", "ingredient", "amount"};

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
