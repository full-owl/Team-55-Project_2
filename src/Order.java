package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class Order {
    int id;
    String currentDate;
    float subTotal;
    float total;
    LinkedList<Order> itemsInOrder;

    public Order(int id, String currentDate, float subTotal, float total)
    {
        this.id = id;
        this.currentDate = currentDate;
        this.subTotal = subTotal;
        this.total = total;
    }

    public void insertItem(Order orderItem)
    {
        this.itemsInOrder.add(orderItem);
    }
}
