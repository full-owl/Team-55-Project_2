package Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MenuItemsView extends JPanel {

    ButtonGroup sizeGroup;
    HashMap<String, MyButtonGroup> itemGroups;
    HashMap<String, JPanel> itemPanels;

    public MenuItemsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);


        createSizePanel();
        createItemPanels();

        // TODO better name
        var layout = new GridLayout(0,5,5,5);
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


    private void createSizePanel() {
        var sizePanel = new JPanel();
        sizePanel.setName("Sizes");
        sizePanel.setBorder(BorderFactory.createTitledBorder("Sizes"));
        sizePanel.setLayout(new GridLayout(0,5,5,5));
        add(sizePanel);

        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        // Why does button group not have a way to deselect stuff. uuuggghhh
        // sauce: https://stackoverflow.com/questions/4904086/how-to-create-buttongroup-of-jtogglebuttons-that-allows-to-deselect-the-actual
        var sizeGroup = new ButtonGroup(){
            @Override
            public void setSelected(ButtonModel model, boolean selected) {
                if (selected) {
                    super.setSelected(model, selected);
                } else {
                    clearSelection();
                }
            }
        };
        var sizeController = new ActionListener() {
            // Whenever you change sizes, update the max number of entrees and sides you can select at one time.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String size = actionEvent.getActionCommand();
                JToggleButton button = (JToggleButton) actionEvent.getSource();
                if(button.isSelected()) {
                    int entreeMax = numEntrees(size);
                    itemGroups.get("Entrees").setMax(entreeMax);
                    int sideMax = numSides(size);
                    itemGroups.get("Sides").setMax(sideMax);
                } else {
                    itemGroups.get("Entrees").setMax(menuItems("Entrees").length);
                    itemGroups.get("Sides").setMax(menuItems("Sides").length);
                }
            }
        };
        for(String size: sizes) {
            var button = new JToggleButton();
            button.setText(size);
            button.addActionListener(sizeController);
            sizePanel.add(button);
            sizeGroup.add(button);
        }
    }

    private void createItemPanels() {
        itemGroups = new HashMap<>();
        itemPanels = new HashMap<>();

        final String[] categories = {"Sides", "Entrees", "Drinks", "Appetizers"};
        for (String category : categories) {
            var panel = new JPanel();
            panel.setName(category);
            panel.setBorder(BorderFactory.createTitledBorder(category));
            panel.setLayout(new GridLayout(0,5,5,5));
            add(panel);

            String[] items = menuItems(category);
            var group = new MyButtonGroup(items.length);
            for (String item: items) {
                var button = new JToggleButton();
                button.setText(item);
                panel.add(button);
                group.add(button);
            }

            itemGroups.put(category, group);
            itemPanels.put(category, panel);
        }
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

    public static int numEntrees(String size) {
        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        int[] nums = {1,2,3,3};
        for (int i = 0; i < sizes.length; i++) {
            if(sizes[i].equals(size)) {
                return nums[i];
            }
        }
        return -1;
    }

    public static int numSides(String size) {
        String[] sizes = {"Bowl", "Plate", "Bigger Plate", "Family"};
        int[] nums = {1,1,1,2};
        for (int i = 0; i < sizes.length; i++) {
            if(sizes[i].equals(size)) {
                return nums[i];
            }
        }
        return -1;
    }
}