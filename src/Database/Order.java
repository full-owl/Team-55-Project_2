package src.Database;

import java.util.LinkedList;

public class Order {
    public int id;
    public String currentDate;
    public double subTotal;
    public double total;

    public int employeeid;
    public LinkedList<DBOrderItem> itemsInOrder;

    public Order(int id, double subTotal)
    {
        this.id = id;
        this.currentDate = getDate();
        this.subTotal = subTotal;
        this.total = subTotal * (1 + 0.0875);
        this.employeeid = 1; // Only one employee lol
    }

    public void insertItem(DBOrderItem orderItem)
    {
        this.itemsInOrder.add(orderItem);
    }

    public static String getDate() {
        return (new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
    }

}
