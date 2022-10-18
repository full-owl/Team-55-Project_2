package src.Cashier;

import src.Database.DBOrderItem;

import java.util.Arrays;

/**
 * Generic Class for Order Items
 * like Bowl of white rice and chicken or Medium Coke
 */
public abstract class OrderItem {
    static String[] sizes = {"bowl", "plate", "bigger plate", "family"};
    String mealType;


    public OrderItem(String mealType) {
        assert Arrays.asList(sizes).contains(mealType);
        this.mealType = mealType;
    }

    public abstract double getPrice();
    public abstract DBOrderItem toDBOrderItem();
}
