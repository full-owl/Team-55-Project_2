import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class inventoryPanel extends JPanel {

    inventoryPanel() {

        // Table
        JTable j;

        // Data to be displayed in the JTable
        Object[][] data = {

                {"0, rice, 100"},
                {"1", "noodles", "100"}
        };

        // Column Names
        String[] columnNames = {"id", "ingredient", "amount"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        this.add(sp);
    }
}
