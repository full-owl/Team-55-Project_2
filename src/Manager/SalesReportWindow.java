package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.Manager.OpenSalesReportWindow.*;

/**
 * SalesReportWindow creates a JFrame with text-fields used for gathering input
 * from the user upon clicking the 'Sales Report' button in the managerGUI.
 * Upon submission the inputs are used to call a new JFrame displaying the results
 **/
public class SalesReportWindow implements ActionListener {

    JFrame f = new JFrame("Sales Report");
    JLabel lDateTo = new JLabel("Date(To):");
    JLabel lDateFrom = new JLabel("Date(From):");
    JTextField dateTo = new JTextField();
    JTextField dateFrom = new JTextField();
    JButton submit = new JButton("submit");

    /**
     * constructor for SalesReportWindow
     **/
    SalesReportWindow() {

        lDateTo.setBounds(0, 0, 100, 50);
        lDateTo.setFont(new Font(null, Font.PLAIN, 15));

        lDateFrom.setBounds(0, 0, 100, 50);
        lDateFrom.setFont(new Font(null, Font.PLAIN, 15));

        submit.addActionListener(this);

        dateTo.setPreferredSize(new Dimension(250, 40));
        dateFrom.setPreferredSize(new Dimension(250, 40));

        f.setLayout(new FlowLayout());
        f.add(lDateFrom);
        f.add(dateFrom);
        f.add(lDateTo);
        f.add(dateTo);
        f.add(submit);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String dateToInput = dateTo.getText();
            String dateFromInput = dateFrom.getText();
            System.out.println("Opening Sales Report..");
            new OpenSalesReportWindow(dateToInput, dateFromInput);
        }
    }
}


