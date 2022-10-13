package src;

import java.awt.font.TextHitInfo;
import java.util.Vector;

public class OrderItems {
    int orderId;
    String orderType;
    int menuItem1;
    int menuItem2;
    int menuItem3;
    int menuItem4;
    int menuItem5;
    int menuItem6;

    public OrderItems(int orderId, String orderType, int menuItem1, int menuItem2, int menuItem3, int menuItem4, int menuItem5, int menuItem6)
    {
        this.orderId = orderId;
        this.orderType = orderType;
        this.menuItem1 = menuItem1;
        this.menuItem2 = menuItem2;
        this.menuItem3 = menuItem3;
        this.menuItem4 = menuItem4;
        this.menuItem5 = menuItem5;
        this.menuItem6 = menuItem6;
    }

    // TODO WHAT does this do?
    public OrderItems(String orderType) {

    }


    public void setSides(Vector<String> sides) {
        // Errors if there is a wrong amount of sides

    }

    // TODO Implement add to have a meal to it
    public void setEntrees(Vector<String> entrees) {
        // Errors if there is a wrong amount of entrees
    }

    public void add(String item) {
        // Look up index by name
        int index = jdbcpostgreSQL.getItemIndex(item);
        add(index);

    }

    public void add(int index) {

        // Add item based on index
        // Should error if there is more than 6 menuItems

    }

    public double getPrice() {
        // Look up based off of the table in the db
        return 0.0;
    }

    @Override
    public String toString() {
        // Ex. Bowl with white rice with orange chicken
        // Plate with white rice with orange chicken and bejing beef.
        return "TODO";
    }
}
