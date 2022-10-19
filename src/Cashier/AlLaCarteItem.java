package src.Cashier;

import src.Database.DBOrderItem;
import src.Database.jdbcpostgreSQL;

/**
 * Al la Carte, order items individually
 * This is used to order individual items like Drinks, Appetizers or if you want an entree or side by itself.
 */
public class AlLaCarteItem extends OrderItem {

    String item;
    private final double price;

    public AlLaCarteItem(String mealType, String item) {
        super(mealType);
        this.item = item;

        price = jdbcpostgreSQL.getTablePrice(mealType, item);
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
