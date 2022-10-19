package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddInventoryWindow creates a JFrame with text-fields used for gathering input
 * from the user upon clicking the 'Add Ingredients' button in the managerGUI.
 * Upon submission the inputs are used to update the database with the function from jdbc
 **/
public class AddInventoryWindow implements ActionListener {

    JFrame f = new JFrame("Add Ingredient");
    JLabel lId = new JLabel("id:");
    JLabel lIngredient = new JLabel("ingredient: ");
    JLabel lAmount = new JLabel("amount: ");
    JLabel lUnits = new JLabel("units: ");
    JTextField id = new JTextField();
    JTextField ingredient = new JTextField();
    JTextField amount = new JTextField();
    JTextField units = new JTextField();
    JButton submit = new JButton("submit");

    /**
     * Constructor for the JFrame
     **/
    AddInventoryWindow() {
        lId.setBounds(0,0,100,50);
        lId.setFont(new Font(null,Font.PLAIN, 15));
        lIngredient.setBounds(0,0,100,50);
        lIngredient.setFont(new Font(null,Font.PLAIN, 15));
        lAmount.setBounds(0,0,100,50);
        lAmount.setFont(new Font(null,Font.PLAIN, 15));
        lUnits.setBounds(0,0,100,50);
        lUnits.setFont(new Font(null,Font.PLAIN, 15));

        submit.addActionListener(this);

        id.setPreferredSize(new Dimension(250,40 ));
        ingredient.setPreferredSize(new Dimension(250,40 ));
        amount.setPreferredSize(new Dimension(250,40 ));
        units.setPreferredSize(new Dimension(250,40 ));

        f.setLayout(new FlowLayout());
        f.add(lId);
        f.add(id);
        f.add(lIngredient);
        f.add(ingredient);
        f.add(lAmount);
        f.add(amount);
        f.add(lUnits);
        f.add(units);
        f.add(submit);
        f.pack();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            int idInput = Integer.parseInt(id.getText());
            String ingredientInput = ingredient.getText();
            int amountInput = Integer.parseInt(amount.getText());
            String unitsInput = units.getText();
            jdbcpostgreSQL.addInventory(idInput, ingredientInput, amountInput, unitsInput);

        }
    }
}
