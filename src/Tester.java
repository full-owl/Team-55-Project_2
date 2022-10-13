package src;

public class Tester {
    public static void main(String args[]) {
        System.out.println(jdbcpostgreSQL.getDBSize("orders"));
        System.out.println(jdbcpostgreSQL.getItemIndex("Diet Coke"));
        System.out.println(jdbcpostgreSQL.getTablePrice("side", "medium"));
        System.out.println(Order.getDate());
    }
}
