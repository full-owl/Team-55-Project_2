package src.Cashier;

import src.jdbcpostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

public class MenuItemsView extends JPanel {

    ButtonGroup sizeGroup;
    HashMap<String, ItemPanel> itemCategories;

    ReceiptTableModel receiptModel;

    public MenuItemsView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);


        createSizePanel();
        createItemCategories();

        // TODO better name
        var modifyArea = new JPanel();
        modifyArea.setName("Modify");
        modifyArea.setLayout(new GridLayout(0,5,5,5));

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


        var buttonLabels =  new String[]{"Add (small)", "Add (medium)", "Add (large)", "Add"};
        var buttonActionCommand = new String[]{"small", "medium", "large", "add"};
        var addAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String action = actionEvent.getActionCommand();
                System.out.println(action);
                Vector<OrderItem> items;
                if (action.equals("add")) { // Only add meal since they don't need to specify what size they are
                    items = new Vector<>();
                    var item = addMeal();
                    if(item != null) {
                        items.add(item);
                    }
                    sizeGroup.clearSelection();
                    itemCategories.get("Sides").group.clearSelection();
                    itemCategories.get("Entrees").group.clearSelection();
                } else {
                    items = addSelectedItems(action);
                    sizeGroup.clearSelection();
                    for (var categoryGroup : itemCategories.values()) {
                        categoryGroup.group.clearSelection();
                    }
                }

                receiptModel.addItems(items);
            }
        };
        for(int i = 0; i < buttonLabels.length; i++) {
            var button = new JButton();
            button.setText(buttonLabels[i]);
            button.setActionCommand(buttonActionCommand[i]);
            button.addActionListener(addAction);
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
                System.out.println("Size: " + size);
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

    public OrderItem addMeal() {

        var mealButton = sizeGroup.getSelection();
        if (mealButton != null) {
            var mealSize = mealButton.getActionCommand();
            var mealItem = new MealItem(mealSize);

            var sides = new Vector<String>();
            for(var button: itemCategories.get("Sides").group.getSelections()) {
                sides.add(button.getActionCommand());
            }
            mealItem.setSides(sides);

            var entrees = new Vector<String>();
            for(var button: itemCategories.get("Entrees").group.getSelections()) {
                entrees.add(button.getActionCommand());
            }
            mealItem.setEntrees(entrees);
            return mealItem;
        }
        return null; // No meal was selected
    }

    public Vector<OrderItem> addSelectedItems(String size) {
        var items = new Vector<OrderItem>();
        OrderItem mealItem = addMeal();
        if (mealItem != null) {
            items.add(mealItem);
        }

        for(var set: itemCategories.entrySet()) {
            // Sides and Entrees are already added, so don't add them again
            if(mealItem != null && (set.getKey().equals("Sides") || set.getKey().equals("Entrees"))) {
                continue;
            }

            // Get Items that are selected for that category
            var selectedButtons = set.getValue().group.getSelections();
            var selected = new Vector<String>();
            for(var button: selectedButtons) {
                System.out.println("Selected: " + button.getActionCommand());
                selected.add(button.getActionCommand());
            }

            // Add Sides and Entrees al la carte
            for(var itemName: selected) {
                var item = new AlLaCarteItem(size, itemName);
                items.add(item);
            }
        }
        return items;
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
            String[] sides = new String[7];
            jdbcpostgreSQL.getSideMenuItems(sides);
            items = sides;
        } else if (category.equals("Entrees")) {
            String[] entrees = new String[14];
            jdbcpostgreSQL.getEntreeMenuItems(entrees);
            items = entrees;
        } else if (category.equals("Drinks")) {
            String[] drinks = new String[19];
            jdbcpostgreSQL.getDrinkMenuItems(drinks);
            items = drinks;
        } else if (category.equals("Appetizers")) {
            String[] appetizers = new String[4];
            jdbcpostgreSQL.getAppetizerMenuItems(appetizers);
            items = appetizers;
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