import javax.swing.*;
import java.awt.*;

public class salesPanel extends JPanel {

    salesPanel() {

        JLabel l = new JLabel();
        l.setText("Sales");

        // Data to be displayed in the JTable
        Object[][] data = {

                {"10/9/2022", "1500"},
                {"11/9/2022", "2000"}
        };

        // Column Names
        String[] columnNames = {"date", "amount"};

        // Initializing the JTable
        JTable j = new JTable(data, columnNames);
        //j.setBounds(0, 0, 150, 150);
        j.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        //sp.setBounds(0,0,350,350);

        this.add(l);
        this.add(sp);

    }
}
