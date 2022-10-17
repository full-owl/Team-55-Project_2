package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExcessReportWindow implements ActionListener {

    JFrame f = new JFrame("ExcessReport");
    JLabel lDate = new JLabel("date:");
    JTextField date = new JTextField();
    JButton submit = new JButton("submit");



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
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(500,500);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            String dateInput = date.getText();
            //jdbcpostgreSQL.editInventory(idInput, amountInput);
        }
    }
}

