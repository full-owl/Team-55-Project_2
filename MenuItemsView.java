import javax.swing.*;
import java.awt.*;

public class MenuItemsView extends JPanel {
    public MenuItemsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);

        // Add Sizes
        var sizePanel = new JPanel();
        sizePanel.setName("Sizes");
        sizePanel.setBorder(BorderFactory.createTitledBorder("Sizes"));
        var layout = new GridLayout(0,5,5,5);
        sizePanel.setLayout(layout);
        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        for(String size: sizes) {
            var button = new JToggleButton();
            button.setText(size);
            sizePanel.add(button);
        }
        this.add(sizePanel);

        // Add menu items
        final String[] categories = {"Sides", "Entrees", "Drinks", "Appetizers"};
        for (String category : categories) {
            var panel = new JPanel();
            panel.setName(category);
            panel.setBorder(BorderFactory.createTitledBorder(category));
            layout = new GridLayout(0, 5, 5, 5);
            panel.setLayout(layout);

            String[] items = menuItems(category);
            for (String item: items) {
                var button = new JToggleButton();
                button.setText(item);
                panel.add(button);
            }
            this.add(panel);
        }

        // TODO better name
        var modifyArea = new JPanel();
        modifyArea.setName("Modify");
        layout = new GridLayout(0,5,5,5);
        modifyArea.setLayout(layout);
        for(String btn: new String[]{"Deselect All", "Add (small)", "Add (medium)", "Add (large)", "Add"}) {
            var button = new JButton();
            button.setText(btn);
            modifyArea.add(button);
        }
        add(modifyArea);

        setVisible(true);
    }

    public static void main(String[] args) {
        var frame = new JFrame();
        var cv = new MenuItemsView();
        frame.add(cv);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

    private void createUIComponents() {
    }
}
