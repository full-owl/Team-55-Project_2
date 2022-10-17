package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesReportWindow implements ActionListener {

    JFrame f = new JFrame("Sales Report");
    JLabel lDateTo = new JLabel("Date(To):");
    JLabel lDateFrom = new JLabel("Date(From):");
    JTextField dateTo = new JTextField();
    JTextField dateFrom = new JTextField();
    JButton submit = new JButton("submit");



    SalesReportWindow() {
        lDateTo.setBounds(0,0,100,50);
        lDateTo.setFont(new Font(null,Font.PLAIN, 15));

        lDateFrom.setBounds(0,0,100,50);
        lDateFrom.setFont(new Font(null,Font.PLAIN, 15));


        submit.addActionListener(this);

        dateTo.setPreferredSize(new Dimension(250,40 ));

        dateFrom.setPreferredSize(new Dimension(250,40 ));

        //tf.setBounds(0,30,100,50);

        f.setLayout(new FlowLayout());
        f.add(lDateFrom);
        f.add(dateFrom);
        f.add(lDateTo);
        f.add(dateTo);
        f.add(submit);
        f.pack();
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(500,500);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            String dateToInput = dateTo.getText();
            String dateFromInput = dateFrom.getText();
            //jdbcpostgreSQL.editInventory(idInput, amountInput);
        }
    }
}


