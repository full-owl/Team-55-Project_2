package src.Database;

import src.Database.jdbcpostgreSQL;

import java.util.Vector;

public class DBOrderItem {
    public int orderId;
    public String orderType;
    public int menuItem1;
    public int menuItem2;
    public int menuItem3;
    public int menuItem4;
    public int menuItem5;
    //int menuItem6;
    public String instructions;
    public DBOrderItem(int orderId, String orderType, int menuItem1, int menuItem2, int menuItem3, int menuItem4, int menuItem5, String instructions)
    {
        this.orderId = orderId;
        this.orderType = orderType;
        this.menuItem1 = menuItem1;
        this.menuItem2 = menuItem2;
        this.menuItem3 = menuItem3;
        this.menuItem4 = menuItem4;
        this.menuItem5 = menuItem5;
        this.instructions = instructions;
    }

    public DBOrderItem(String orderType) {
        this.orderType = orderType;
    }
    public DBOrderItem(String orderType, String menuItem) {
        this.orderType = orderType;
        this.menuItem1 = jdbcpostgreSQL.getItemIndex(menuItem);
    }


    public void setSides(Vector<String> sides) {
        // Errors if there is a wrong amount of sides
        if (sides.size() == 2) {
            this.menuItem4 = jdbcpostgreSQL.getItemIndex(sides.elementAt(0));
            this.menuItem5 = jdbcpostgreSQL.getItemIndex(sides.elementAt(1));
        }
        else if (sides.size() == 1) {
            this.menuItem4 = jdbcpostgreSQL.getItemIndex(sides.elementAt(0));
            this.menuItem5 = 0;
        }
        else if (sides.size() == 0) {
            this.menuItem4 = 0;
            this.menuItem5 = 0;
        }
        return;
    }

    public void setEntrees(Vector<String> entrees) {
        // Errors if there is a wrong amount of entrees
        if (entrees.size() == 3) {
            this.menuItem1 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(0));
            this.menuItem2 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(1));
            this.menuItem3 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(2));
        }
        else if (entrees.size() == 2) {
            this.menuItem1 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(0));
            this.menuItem2 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(1));
            this.menuItem3 = 0;
        }
        else if (entrees.size() == 1) {
            this.menuItem1 = jdbcpostgreSQL.getItemIndex(entrees.elementAt(0));
            this.menuItem2 = 0;
            this.menuItem3 = 0;
        }
        else if (entrees.size() == 0){
            this.menuItem1 = 0;
            this.menuItem2 = 0;
            this.menuItem3 = 0;
        }
        return;
    }

}
