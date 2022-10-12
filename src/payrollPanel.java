package src;

import javax.swing.*;

public class payrollPanel extends JPanel {

    payrollPanel() {

        JLabel l = new JLabel();
        l.setText("Payroll");

        // Data to be displayed in the JTable
        Object[][] data = {

                {"Employee1", "cook"},
                {"Employee2", "cashier"}
        };

        // Column Names
        String[] columnNames = {"employee", "position"};

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
