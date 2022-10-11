package Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MenuItemsView extends JPanel {

    ButtonGroup sizeGroup;
    HashMap<String, ItemPanel> itemCategories;

    public MenuItemsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);


        createSizePanel();
        createItemCategories();

        // TODO better name
        var layout = new GridLayout(0,5,5,5);
        var modifyArea = new JPanel();
        modifyArea.setName("Modify");
        modifyArea.setLayout(layout);

        // Deselect all button
        var deselectButton = new JButton();
        deselectButton.setText("Deselect All");
        var deselectAction = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sizeGroup.clearSelection();
                for(var category: itemCategories.values()) {
                    category.group.clearSelection();
                }
            }
        };
        deselectButton.addActionListener(deselectAction);
        modifyArea.add(deselectButton);

        for(String btn: new String[]{"Add (small)", "Add (medium)", "Add (large)", "Add"}) {
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
        var sizeController = new ActionListener() {
            // Whenever you change sizes, update the max number of entrees and sides you can select at one time.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String size = actionEvent.getActionCommand();
                System.out.println(size);
                JToggleButton button = (JToggleButton) actionEvent.getSource();
                if(button.isSelected()) {
                    int entreeMax = numEntrees(size);
                    itemCategories.get("Entrees").setMax(entreeMax);
                    int sideMax = numSides(size);
                    itemCategories.get("Sides").setMax(sideMax);
                } else {
                    itemCategories.get("Entrees").setMax(menuItems("Entrees").length);
                    itemCategories.get("Sides").setMax(menuItems("Sides").length);
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

    private void createItemCategories() {
        itemCategories = new HashMap<>();

        final String[] categories = {"Sides", "Entrees", "Drinks", "Appetizers"};
        for (String category : categories) {
            var itemPanel = new ItemPanel(category, menuItems(category));
            itemCategories.put(category, itemPanel);
            add(itemPanel);
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
            items = new String[]{"Coke", "Diet Coke", "Sprite", "etc.","1","2","3","4","5","6","7","8","9","10"};
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