package src;

import javax.swing.*;
import java.awt.*;


public class managerGui {
    public static void main(String args[]){


        // JPanel inventory_panel = new src.inventoryPanel();
        // inventory_panel.setSize(300,100);

        // JPanel sales_panel = new src.salesPanel();
        // sales_panel.setSize(500,100);

        JFrame f = new JFrame();
        f.setTitle("Manager GUI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2,2));
        f.setSize(1600, 800);


        // f.add(inventory_panel);
        // f.add(sales_panel);
        f.add(new inventoryPanel());
        f.add(new salesPanel());
        f.add(new mealSizesPanel());
        f.add(new miscPanel());
        f.setVisible(true);

    }
}
