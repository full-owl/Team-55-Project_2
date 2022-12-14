package src.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * managerGui creates the main JFrame that displays the entire manager view
 **/
public class managerGui {
    public static JFrame f;

    /**
     * constructor for manager view
     **/
    public static void setFrame() {
        f = new JFrame("Manager View");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setLayout(new GridLayout(2, 2));
        f.setSize(1600, 800);

        f.add(new inventoryPanel());
        f.add(new salesPanel());
        f.add(new mealSizesPanel());
        f.add(new miscPanel());
        f.setVisible(true);
    }

    /**
     * Calls constructor
     **/
    public static void managerGui() {
        setFrame();
    }

    /**
     * Refreshes manager view when data changes on submission by recalling the constructor
     **/
    public static void refreshManager() {
        f.dispose();
        setFrame();
    }
}
