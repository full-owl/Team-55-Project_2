package src.Manager;

import src.Database.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPriceWindow implements ActionListener {

    JFrame f = new JFrame("Edit Prices");
    JLabel lFoodType = new JLabel("Food Type:");
    JLabel lMealType = new JLabel("Meal Type: ");
    JLabel lNewPrice = new JLabel("New Price: ");

    JTextField foodType = new JTextField();
    JTextField mealType = new JTextField();
    JTextField newPrice = new JTextField();

    JButton submit = new JButton("submit");



    EditPriceWindow() {
        lFoodType.setBounds(0,0,100,50);
        lFoodType.setFont(new Font(null,Font.PLAIN, 15));
        lMealType.setBounds(0,0,100,50);
        lMealType.setFont(new Font(null,Font.PLAIN, 15));
        lNewPrice.setBounds(0,0,100,50);
        lNewPrice.setFont(new Font(null,Font.PLAIN, 15));

        submit.addActionListener(this);

        foodType.setPreferredSize(new Dimension(250,40 ));
        mealType.setPreferredSize(new Dimension(250,40 ));
        newPrice.setPreferredSize(new Dimension(250,40 ));

        f.setLayout(new FlowLayout());
        f.add(lFoodType);
        f.add(foodType);
        f.add(lMealType);
        f.add(mealType);
        f.add(lNewPrice);
        f.add(newPrice);
        f.add(submit);
        f.pack();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //f.setSize(500,500);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            String foodTypeInput = foodType.getText();
            String mealTypeInput = mealType.getText();
            Float newPriceInput = Float.parseFloat(newPrice.getText());
            jdbcpostgreSQL.editPrices(foodTypeInput, mealTypeInput, newPriceInput);
        }
    }
}

