import javax.swing.*;
import java.awt.*;

public class CashierView extends JFrame {
    public JPanel menuItemsView;

    public CashierView() {
        // createUIComponents();
        // add(menuItemsView);
        menuItemsView = new JPanel();
        menuItemsView.setLayout(new BoxLayout(menuItemsView, BoxLayout.Y_AXIS));
        menuItemsView.setVisible(true);

        // Add menu items
        final String[] categories = {"Sides", "Entrees", "Drinks", "Appetizers"};
        for (String category : categories) {
            var panel = new JPanel();
            panel.setName(category);
            panel.setBorder(BorderFactory.createTitledBorder(category));
            var layout = new GridLayout(0, 5, 5, 5);
            panel.setLayout(layout);

            String[] items = menuItems(category);
            for (String item: items) {
                var button = new JToggleButton();
                button.setText(item);
                panel.add(button);
            }
            menuItemsView.add(panel);
        }

        // Add Sizes
        var panel = new JPanel();
        panel.setName("Sizes");
        panel.setBorder(BorderFactory.createTitledBorder("Sizes"));
        var layout = new GridLayout(0,5,5,5);
        panel.setLayout(layout);
        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        for(String size: sizes) {
            var button = new JToggleButton();
            button.setText(size);
            panel.add(button);
        }
        menuItemsView.add(panel);

        add(menuItemsView);
        // Bottom
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        var cv = new CashierView();
    }

    public static String[] menuItems(String category) {
        String[] items = {};
        if (category.equals("Sides")) {
            items = new String[]{"White Steamed Rice", "Brown Steamed Rice", "Fried Rice"};
        } else if (category.equals("Entrees")) {
            items = new String[]{"Beef", "Chicken", "Super Greens", "etc."};
        } else if (category.equals("Drinks")) {
            items = new String[]{"Coke", "Diet Coke", "Sprite", "etc."};
        } else if (category.equals("Appetizers")) {
            items = new String[]{"Egg rolls"};
        }
        return items;
    }

}
