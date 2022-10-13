package src;

import javax.swing.*;
import java.awt.*;


public class managerGui {
    public static void main(String args[]){

        JFrame f = new JFrame();
        f.setTitle("Manager GUI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2,2));
        f.setSize(1600, 800);

        f.add(new inventoryPanel());
        f.add(new salesPanel());
        f.add(new mealSizesPanel());
        f.add(new miscPanel());
        f.setVisible(true);

    }
}
