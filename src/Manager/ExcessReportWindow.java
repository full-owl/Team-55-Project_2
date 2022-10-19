package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ExcessReportWindow creates a JFrame with text-fields used for gathering input
 * from the user upon clicking the 'Excess Report' button in the managerGUI.
 * Upon submission the inputs are used to call a new JFrame displaying the results
 **/
public class ExcessReportWindow implements ActionListener {

    JFrame f = new JFrame("ExcessReport");
    JLabel lDate = new JLabel("date:");
    JTextField date = new JTextField();
    JButton submit = new JButton("submit");

    /**
     * constructor for the JFrane
     **/
    ExcessReportWindow() {
        lDate.setBounds(0,0,100,50);
        lDate.setFont(new Font(null,Font.PLAIN, 15));

        submit.addActionListener(this);

        date.setPreferredSize(new Dimension(250,40 ));

        f.setLayout(new FlowLayout());
        f.add(lDate);
        f.add(date);
        f.add(submit);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            String dateInput = date.getText();
            new OpenExcessReportWindow(dateInput);
        }
    }
}

