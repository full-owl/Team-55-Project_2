package src.Cashier;

import javax.swing.*;
import java.util.Vector;

/**
 * ButtonGroup only lets you select one at a time, and you can't deselect after you do
 * So I made my own.
 * Can select upto a max amount, and you can change the max dynamically
 */
public class MyButtonGroup extends ButtonGroup {

    private int max;
    private final Vector<ButtonModel> selections = new Vector<>();

    /**
     * @param maxSelection
     */
    public MyButtonGroup(int maxSelection) {
        super();
        max = maxSelection;
    }

    @Override
    public void clearSelection() {
        while (!selections.isEmpty()) {
            var button = selections.firstElement();
            button.setSelected(false);
        }
    }

    public Vector<ButtonModel> getSelections() {
        return selections;
    }

    public int getMax() {
        return max;
    }

    @Override
    public ButtonModel getSelection() {
        return selections.isEmpty() ? null : selections.lastElement();
    }

    public void setMax(int max) {
        if (max < this.max) {
            // Remove extra selected
            this.max = max;
            while (selections.size() > max) {
                selections.firstElement().setSelected(false);
            }
        }
        this.max = max;
        resetDisabledButtons();
    }

    @Override
    public void setSelected(ButtonModel button, boolean selected) {
        if (selected) {
            // For some reason, button.setSelected calls this function so to make sure you don't get infinite recursion
            // you have to check if it is selected before adding it
            if (button != null && !selections.contains(button)) {
                if (selections.size() >= max) {
                    setSelected(selections.firstElement(), false);
                }
                selections.add(button);
                button.setSelected(true);
            }
        } else {
            if (button != null && selections.contains(button)) {
                selections.remove(button);
                button.setSelected(false);
            }
        }
        resetDisabledButtons();
    }

    public void resetDisabledButtons() {
        enableButtons();
        if (selections.size() >= max) {
            disableUnselected();
        }
    }

    public void disableUnselected() {
        for (var button : buttons) {
            if (!button.isSelected()) {
                button.setEnabled(false);
            }
        }
    }

    public void enableButtons() {
        for (var button : buttons) {
            button.setEnabled(true);
        }
    }

    @Override
    public boolean isSelected(ButtonModel m) {
        return selections.contains(m);
    }
}
