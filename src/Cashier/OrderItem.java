package src.Cashier;

import src.DBOrderItem;

import java.util.Arrays;

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
