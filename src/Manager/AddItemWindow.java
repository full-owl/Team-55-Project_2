package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddItemWindow creates a JFrame text-fields used for gathering input
 * from the user upon clicking the 'Add Menu Item' button in the managerGUI.
 * Upon submission the inputs are used to update the database with the function from jdbc
 **/
public class AddItemWindow implements ActionListener {

    JFrame f = new JFrame("Add Menu Item");
    JLabel lId = new JLabel("id:");
    JLabel lName = new JLabel("name: ");
    JLabel lType = new JLabel("type: ");
    JLabel lDescription = new JLabel("description: ");
    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField type = new JTextField();
    JTextField description = new JTextField();
    JButton submit = new JButton("submit");

    /**
     * Constructor for the JFrame
     **/
    AddItemWindow() {
        lId.setBounds(0, 0, 100, 50);
        lId.setFont(new Font(null, Font.PLAIN, 15));

        lName.setBounds(0, 0, 100, 50);
        lName.setFont(new Font(null, Font.PLAIN, 15));

        lType.setBounds(0, 0, 100, 50);
        lType.setFont(new Font(null, Font.PLAIN, 15));

        lDescription.setBounds(0, 0, 100, 50);
        lDescription.setFont(new Font(null, Font.PLAIN, 15));

        submit.addActionListener(this);

        id.setPreferredSize(new Dimension(250, 40));
        name.setPreferredSize(new Dimension(250, 40));
        type.setPreferredSize(new Dimension(250, 40));
        description.setPreferredSize(new Dimension(250, 40));

        f.setLayout(new FlowLayout());
        f.add(lId);
        f.add(id);
        f.add(lName);
        f.add(name);
        f.add(lType);
        f.add(type);
        f.add(lDescription);
        f.add(description);
        f.add(submit);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            int idInput = Integer.parseInt(id.getText());
            String nameInput = name.getText();
            String typeInput = type.getText();
            String descriptionInput = description.getText();
            jdbcpostgreSQL.addMenuItem(idInput, nameInput, typeInput, descriptionInput);

        }
    }
}

