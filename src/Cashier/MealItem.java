package src.Cashier;

import src.DBOrderItem;

import java.util.Arrays;
import java.util.Vector;

public class MealItem extends OrderItem {

    Vector<String> sides = new Vector<>();
    int sideLen;
    Vector<String> entrees = new Vector<>();
    int entreeLen;

    public MealItem(String mealType) {
        super(mealType);

        int[] entrees = {1,2,3,3};
        int[] sides = {1,1,1,2};

        int i = Arrays.asList(sizes).indexOf(mealType);
        entreeLen = entrees[i];
        sideLen = sides[i];
    }

    @Override
    public double getPrice() {
        return 0;
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