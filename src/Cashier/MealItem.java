package src.Cashier;

import src.Database.DBOrderItem;
import src.Database.jdbcpostgreSQL;

import java.util.Arrays;
import java.util.Vector;

/**
 * MealItems represent items that are made of multiple things
 * For example, Plate of rice with chicken, and beef
 */
public class MealItem extends OrderItem {

    Vector<String> sides = new Vector<>();
    int sideLen;
    Vector<String> entrees = new Vector<>();
    int entreeLen;

    private double price;

    /**
     *
     * @param mealType
     */
    public MealItem(String mealType) {
        super(mealType);

        // Number of items
        int[] entrees = {1,2,3,3};
        int[] sides = {1,1,1,2};

        int i = Arrays.asList(sizes).indexOf(mealType);
        entreeLen = entrees[i];
        sideLen = sides[i];

        price = jdbcpostgreSQL.getTablePrice(mealType);
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public DBOrderItem toDBOrderItem() {
        DBOrderItem dbItem = new DBOrderItem(mealType);
        dbItem.setSides(sides);
        dbItem.setEntrees(entrees);
        return dbItem;
    }

    public void setEntrees(Vector<String> entrees) {
        assert entrees.size() == entreeLen;
        this.entrees = entrees;
    }

    public void setSides(Vector<String> sides) {
        assert sides.size() == sideLen;
        this.sides = sides;
    }

    @Override
    public String toString() {
        return mealType + " of " + String.join(", ", sides) + " with " + String.join(", ", entrees);
    }
}
