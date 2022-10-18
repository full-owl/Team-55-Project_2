package src.Database;

import java.util.LinkedList;

/**
 * class Order - object that will go in order database
 * */
public class Order {

    public int id;
    public String currentDate;
    public double subTotal;
    public double total;
    public int employeeid;
    public LinkedList<DBOrderItem> itemsInOrder; // lists of orderitems relates to orderitems database class

    // constructors
    public Order(int id, double subTotal)
    {
        this.id = id;
        this.currentDate = getDate();
        this.subTotal = subTotal;
        this.total = subTotal * (1 + 0.0875);
        this.employeeid = 1; // Only one employee lol
    }

    /**
     * inserts orderItem into itemsInOrder linkedlist
     * @param orderItem DBOrderItem to be inserted into orders itemsInOrder
    * */
    public void insertItem(DBOrderItem orderItem)
    {
        this.itemsInOrder.add(orderItem);
    }


    /**
     * used to get current date
     * @return String of format "yyyy-MM-dd"
     * */
    public static String getDate() {
        return (new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
    }

}
