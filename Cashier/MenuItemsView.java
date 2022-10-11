package Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MenuItemsView extends JPanel {

    ButtonGroup sizeGroup;
    HashMap<String, MyButtonGroup> itemGroups;

    public MenuItemsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);

        var layout = new GridLayout(0,5,5,5);
        var controller = new MenuItemsController();

        // Add Sizes
        var sizePanel = new JPanel();
        sizePanel.setName("Sizes");
        sizePanel.setBorder(BorderFactory.createTitledBorder("Sizes"));
        sizePanel.setLayout(layout);

        // Why does button group not have a way to deselect stuff. uuuggghhh
        // sauce: https://stackoverflow.com/questions/4904086/how-to-create-buttongroup-of-jtogglebuttons-that-allows-to-deselect-the-actual
        sizeGroup = new ButtonGroup(){
            @Override
            public void setSelected(ButtonModel model, boolean selected) {
                if (selected) {
                    super.setSelected(model, selected);
                } else {
                    clearSelection();
                }
            }
        };
        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        for(String size: sizes) {
            var button = new JToggleButton();
            button.setText(size);
            button.addActionListener(controller);
            sizePanel.add(button);
            sizeGroup.add(button);
        }
        this.add(sizePanel);

        // Add menu items
        final String[] categories = {"Sides", "Entrees", "Drinks", "Appetizers"};
        itemGroups = new HashMap<>();

        for (String category : categories) {
            var panel = new JPanel();
            panel.setName(category);
            panel.setBorder(BorderFactory.createTitledBorder(category));
            panel.setLayout(layout);

            String[] items = menuItems(category);
            var group = new MyButtonGroup(items.length);
            for (String item: items) {
                var button = new JToggleButton();
                button.setText(item);
                panel.add(button);
                group.add(button);
            }

            itemGroups.put(category, group);
            this.add(panel);
        }

        // TODO better name
        var modifyArea = new JPanel();
        modifyArea.setName("Modify");
        modifyArea.setLayout(layout);
        for(String btn: new String[]{"Deselect All", "Add (small)", "Add (medium)", "Add (large)", "Add"}) {
            var button = new JButton();
            button.setText(btn);
            modifyArea.add(button);
        }
        add(modifyArea);
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

class MenuItemsController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var source = actionEvent.getSource();
        if (source instanceof JToggleButton) {
            System.out.println(((JToggleButton) source).getParent().getName());
        }
    }
}