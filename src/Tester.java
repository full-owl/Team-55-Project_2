package src;

public class Tester {
    public static void main(String args[]) {
        System.out.println(jdbcpostgreSQL.getDBSize("orders"));
        System.out.println(jdbcpostgreSQL.getItemIndex("Diet Coke"));
        System.out.println(jdbcpostgreSQL.getTablePrice("medium", "Sprite"));
        System.out.println(Order.getDate());

        DBOrderItem i1 = new DBOrderItem(2504, "plate", 10, 13, 0, 2, 0, "null");
        DBOrderItem i2 = new DBOrderItem(2504, "plate", 10, 13, 0, 2, 0, "null");
        Order o1 = new Order(2504,11.50+11.50);


    }
}
