package src.Cashier;

import src.DBOrderItem;
import src.jdbcpostgreSQL;

public class AlLaCarteItem extends OrderItem {

    String item;
    private double price;

    public AlLaCarteItem(String mealType, String item) {
        super(mealType);
        this.item = item;

        price = jdbcpostgreSQL.getTablePrice(mealType,item);
    }

    @Override
    public String toString() {
        return mealType + " " + item;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public DBOrderItem toDBOrderItem() {
        return new DBOrderItem(mealType, item);
    }
}
