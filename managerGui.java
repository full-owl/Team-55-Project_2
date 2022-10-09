import javax.swing.*;
import java.awt.*;

public class managerGui {
    public static void main(String args[]){

        JFrame f = new JFrame();
        f.setTitle("Manager GUI");
        f.add(new inventoryPanel());
        f.setSize(500, 200);
        f.setVisible(true);

    }
}
