package src.Cashier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for a specific category of items like Sides, Entrées, or Drinks
 */
public class ItemPanel extends JPanel {
    String category;
    JLabel label;

    MyButtonGroup group;

    /**
     * Creates ItemPanel, based of the list of items and category
     *
     * @param category String name of the category that the items belong to
     * @param items    String[] List of items
     */
    public ItemPanel(String category, String[] items) {
        super(new BorderLayout(5, 5));
        this.setName(category);
        this.setBorder(BorderFactory.createTitledBorder(category));

        label = new JLabel("0/" + items.length);
        this.add(label, BorderLayout.NORTH);


        // Item grid
        var innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 5, 5, 5));

        // Each button has an action listener to update the number selected so that the indicator it
        var action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("Item: " + actionEvent.getActionCommand());
                updateNumSelected();
            }
        };
        group = new MyButtonGroup(items.length);
        for (String item : items) {
            var button = new JToggleButton();
            button.setText(item);
            button.setActionCommand(item);
            button.addActionListener(action);
            innerPanel.add(button);
            group.add(button);
        }
        this.add(innerPanel, BorderLayout.CENTER);

        // Deselect button
        var deselectButton = new JButton("X");
        var deselectAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                group.clearSelection();
                updateNumSelected();
            }
        };
        deselectButton.addActionListener(deselectAction);
        this.add(deselectButton, BorderLayout.EAST);
    }

    public void updateNumSelected() {
        label.setText(group.getSelections().size() + "/" + group.getMax());
    }

    public int getMax() {
        return group.getMax();
    }

    public void setMax(int max) {
        group.setMax(max);
        updateNumSelected();
    }

    public void resetMax() {
        setMax(group.getButtonCount());
    }

}
