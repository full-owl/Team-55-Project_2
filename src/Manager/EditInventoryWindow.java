package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditInventoryWindow implements ActionListener {

    JFrame f = new JFrame("Edit Inventory");
    JLabel lId = new JLabel("id:");
    JLabel lAmount = new JLabel("amount: ");
    JTextField id = new JTextField();
    JTextField amount = new JTextField();
    JButton submit = new JButton("submit");



    EditInventoryWindow() {
        lId.setBounds(0,0,100,50);
        lId.setFont(new Font(null,Font.PLAIN, 15));

        lAmount.setBounds(0,0,100,50);
        lAmount.setFont(new Font(null,Font.PLAIN, 15));


        submit.addActionListener(this);

        id.setPreferredSize(new Dimension(250,40 ));

        amount.setPreferredSize(new Dimension(250,40 ));

        //tf.setBounds(0,30,100,50);

        f.setLayout(new FlowLayout());
        f.add(lId);
        f.add(id);
        f.add(lAmount);
        f.add(amount);
        f.add(submit);
        f.pack();
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setSize(500,500);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            int idInput = Integer.parseInt(id.getText());
            int amountInput = Integer.parseInt(amount.getText());
            jdbcpostgreSQL.editInventory(idInput, amountInput);

        }
    }
}

