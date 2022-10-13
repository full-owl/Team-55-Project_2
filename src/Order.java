package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class Order {
    int id;
    String currentDate;
    float subTotal;
    float total;

    int employeeid;
    LinkedList<OrderItems> itemsInOrder;

    public Order(int id, float subTotal, float total, int employeeid)
    {
        this.id = id;
        this.currentDate = getDate();
        this.subTotal = subTotal;
        this.total = total;
        this.employeeid = employeeid;
    }

    public void insertItem(OrderItems orderItem)
    {
        this.itemsInOrder.add(orderItem);
    }

    public static String getDate() {
        return (new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
    }

}
