package src;

import javax.swing.*;
import java.awt.*;


public class managerGui {
    public static JFrame f;
    public static void setFrame() {
        f = new JFrame("Manager View");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setLayout(new GridLayout(2,2));
        f.setSize(1600, 800);

        f.add(new inventoryPanel());
        f.add(new salesPanel());
        f.add(new mealSizesPanel());
        f.add(new miscPanel());
        f.setVisible(true);
    }
    public static void managerGui() {
        setFrame();
    }

//    public static void main(String args[]){
//        setFrame();
//    }
//
    public static void refreshManager() {
        f.dispose();
       setFrame();
    }
}
