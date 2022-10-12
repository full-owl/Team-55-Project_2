package src;

import javax.swing.*;
import java.awt.*;

public class NewWindow {

    JFrame f = new JFrame("Edit Inventory");
    JLabel l = new JLabel("Enter new inventory fields");
    JTextField textField = new JTextField();
    NewWindow() {
        l.setBounds(0,0,100,50);
        l.setFont(new Font(null,Font.PLAIN, 15));

        textField.setPreferredSize(new Dimension(250,40 ));
        f.add(textField);
        f.add(l);
        //f.pack();
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);
    }

}
