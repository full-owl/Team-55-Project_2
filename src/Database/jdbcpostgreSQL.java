package src.Database;

import src.Cashier.OrderItem;
//import src.Manager.managerGui;
import src.Manager.*;

import java.sql.*;
import java.io.*;
import java.util.*;


public class jdbcpostgreSQL {

    static Connection conn;

    public static Connection getConn() {
        if(conn == null) {
            String teamNumber = "55";
            String sectionNumber = "904";
            String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
            String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
            try {
                conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
            } catch (Exception e) {
                System.out.println("error");
                e.printStackTrace();
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return conn;
    }
  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code

  /* DON"T COPY PASTE WRITE THE COMMANDS IN YOUR TERMINAL MANUALLY*/

  //Windows: java -cp ".;postgresql-42.2.8.jar" src.Database.jdbcpostgreSQL
  //Mac/Linux: java -cp ".:postgresql-42.2.8.jar" src.Database.jdbcpostgreSQL
  //MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE

    // Parses a csv file and pushes data to the database

    /**
     * Inserts data from a csv into the database
     * @param conn connection to the database
     * @param fileName the csv data
     * @throws SQLException
     */
    public static void convertData(Connection conn, String fileName) throws SQLException
    {
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter(",");
            int i = 1;
            String res = "INSERT INTO orderitems (orderId, mealType, menuItem1, menuItem2, menuItem3, menuItem4, menuItem5, " +
                    "customInstructions) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(res);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String tokens[] = line.split(",");
                ps.setInt(1,Integer.parseInt(tokens[1]));
                ps.setString(2,tokens[2]);
                if (tokens[3].length() > 3)
                {
                    ps.setInt(3,0);
                }
                else
                {
                    ps.setInt(3,Integer.parseInt(tokens[3]));
                }

                if (tokens[4].length() > 3)
                {
                    ps.setInt(4,0);
                }
                else
                {
                    ps.setInt(4,Integer.parseInt(tokens[4]));
                }

                if (tokens[5].length() > 3)
                {
                    ps.setInt(5,0);
                }
                else
                {
                    ps.setInt(5,Integer.parseInt(tokens[5]));
                }

                if (tokens[6].length() > 3)
                {
                    ps.setInt(6,0);
                }
                else
                {
                    ps.setInt(6,Integer.parseInt(tokens[6]));
                }

                if (tokens[7].length() > 3)
                {
                    ps.setInt(7,0);
                }
                else
                {
                    ps.setInt(7,Integer.parseInt(tokens[7]));
                }
                ps.setString(8,tokens[8]);
                ps.addBatch();
                i++;
            }
            ps.executeBatch();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Inserts order into the orders database and the items into orderitems database
     * @param items vector of orderitems
     */
    public static void insertOrder(Vector<OrderItem> items) {

        int orderid = getDBSize("orders") + 1;

        Connection conn = getConn();

        // Calculate subTotal
        double subTotal = 0;
        for(var it: items) {
            subTotal += it.getPrice();
        }

        // adding to orders
        Order order = new Order(orderid, subTotal);
        try
        {
            String stmt ="INSERT INTO orders (id, date, subtotal, total, employeeid) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setInt(1, orderid);
            ps.setString(2, order.currentDate);
            ps.setDouble(3, order.subTotal);
            ps.setDouble(4, order.total);
            ps.setInt(5, order.employeeid);
            int result = ps.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }



        for (var it: items) {
            var item = it.toDBOrderItem();
            try
            {
                String stmt ="INSERT INTO orderitems (orderid, mealtype, menuitem1, menuitem2, menuitem3, side1, side2," +
                        "custominstructions) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement ps = conn.prepareStatement(stmt);
                ps.setInt(1, orderid);
                ps.setString(2, item.orderType);
                ps.setInt(3, item.menuItem1);
                ps.setInt(4, item.menuItem2);
                ps.setInt(5, item.menuItem3);
                ps.setInt(6, item.menuItem4);
                ps.setInt(7, item.menuItem5);
                ps.setString(8, item.instructions);

                int result = ps.executeUpdate();
                updateInventory(conn, orderid);

            } catch (Exception e)
            {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }

        }


        // new managerGui();
    }

    /**
     *
     * Update the inventory table based by the ingredients used for the order
     * @param conn
     * @param orderid
     * @return ResultSet of the updated rows in ingredients table
     * @throws SQLException
     */
    private static ResultSet updateInventory(Connection conn, int orderid) throws SQLException {

        String stmt = """
                update inventory
                set currentamount = currentamount - amountneeded
                from orderingredients
                where orderingredients.inventoryid = inventory.id and orderingredients.orderid = ?
                returning inventory.*;
                """;
        PreparedStatement ps = conn.prepareStatement(stmt);
        ps.setInt(1,orderid);
        ResultSet result = ps.executeQuery();
        return result;

    }
    /**
     * Edit amount of item in inventory databasee
     * @param id id of inventory item in database
     * @param amount amount to put into inventory item of id
     */
    public static void editInventory(int id, int amount)
    {
        try
        {
            Connection conn = getConn();
            String stmt = "UPDATE INVENTORY SET currentamount = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setInt(1, amount);
            ps.setInt(2, id);
            int result = ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        managerGui.refreshManager();
    }

    /**
     * Adds item of id, name, amount, unit
     * @param id id of inventory item in database
     * @param name name of ingredient to put into inventory
     * @param unit unit name for inventory item
     */
    public static void addInventory(int id, String name, int amount, String unit)
    {
        Connection conn = getConn();
        try
        {
            String stmt = "INSERT INTO INVENTORY(id, ingredient, currentamount, unit) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3,amount);
            ps.setString(4, unit);
            int result = ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        managerGui.refreshManager();
    }

    /**
     * edits price of foodtype and mealtype in mealsizes database
     * @param foodtype name of food item
     * @param mealtype name of mealtype attribute in mealsizes database
     * @param newPrice new float price of item
    * */
    public static void editPrices(String foodtype, String mealtype, float newPrice)
    {

        Connection conn = getConn();
        // maybe get rid of some fluff stuff
        try
        {
            String stmt = "UPDATE mealsizes SET price = ? WHERE foodtype = ? AND mealtype = ?";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setFloat(1, newPrice);
            ps.setString(2, foodtype);
            ps.setString(3, mealtype);
            int result = ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        managerGui.refreshManager();
    }

    /**
     * adds menu item of id and name to menuitems database with a description
     * @param id unique id code of menuitem, should be incremented up one from db
     * @param name name of menuitem
     * @param description string description that describes item
     * */
    public static void addMenuItem(int id, String name, String type, String description)
    {
        Connection conn = getConn();
        try
        {
            String stmt = "INSERT INTO menuitems(id, name, foodtype, description) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(stmt);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, description);
            int result = ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    /**
     * returns vector of strings of menuitems with foodtype
     * @param foodType string type of food item in database
     * @return vector of strings of food types in menuitems table
     * */
    public static Vector<String> getMenuItems(String foodType) {
        Connection conn = getConn();
        try{
            String sqlStatement = "SELECT name FROM menuitems WHERE foodtype = ?";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            p.setString(1,foodType);
            ResultSet result = p.executeQuery();

            Vector<String> out = new Vector<>();
            while(result.next()) {
                out.add(result.getString("name"));
            }

            return out;
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return null;
    }

    /**
     * takes in invTable by reference and makes it usable to display in manager gui. invTable holds data from the inventory database
     * @param invTable 2D - String array that should be empty with a specific size for rows and must be 4 columns
     */
    public static void getInvTable(String[][] invTable) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM inventory ORDER BY id";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            // id, ingredient, currentamount, unit

            // System.out.println("--------------------Query Results--------------------");

            int r = 0;

            while(result.next()) {

                invTable[r][0] = result.getString("id");
                invTable[r][1] = result.getString("ingredient");
                invTable[r][2] = result.getString("currentamount");
                invTable[r][3] = result.getString("unit");
                r++;
            }

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
    /**
     * edits price of foodtype and mealtype in mealsizes database
     * @param restockTable 2D - String array that should be empty with a specific size for rows and must be 4 columns
     * */
    public static void getRestockTable(String[][] restockTable) {
        Connection conn = getConn();
        try{
            String sqlStatement = "SELECT * FROM inventory WHERE currentamount < 20";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            // id, ingredient, currentamount, unit

            // System.out.println("--------------------Query Results--------------------");

            int r = 0;

            while(result.next()) {

                restockTable[r][0] = result.getString("id");
                restockTable[r][1] = result.getString("ingredient");
                restockTable[r][2] = result.getString("currentamount");
                restockTable[r][3] = result.getString("unit");
                r++;
            }

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
    /**
     * edits price of foodtype and mealtype in mealsizes database
     * @param SalesTable 2D - String array that should be empty with a specific size for rows and must be 2 columns
     * @param date1 string containing the first date
     * @param date2 string containing the second date
     * */
    public static void getSalesReportTable(String[][] SalesTable, String date1, String date2) {
        Connection conn = getConn();
        try{
            String sqlStatement = "select menuitems.name, count(menuitems.id) from " +
                    "((orderitems inner join orders on orderitems.orderid = orders.id) " +
                    "inner join menuitems on orderitems.menuitem1 = menuitems.id " +
                    "or orderitems.menuitem2 = menuitems.id or orderitems.menuitem3 = menuitems.id " +
                    "or orderitems.side1 = menuitems.id or orderitems.side2 = menuitems.id) " +
                    "where date between '" + date2 +  "' and '" + date1 + "' group by menuitems.name;";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            // id, ingredient, currentamount, unit

            // System.out.println("--------------------Query Results--------------------");

            int r = 0;
            while(result.next()) {
                if (r == 0)
                {
                    String rand = result.getString("name");
                    int num = result.getInt("count");
                    String rand2 = Integer.toString(num);
                    r++;
                }
                else
                {
                    SalesTable[r][0] = result.getString("name");
                    int num = result.getInt("count");
                    SalesTable[r][1] = Integer.toString(num);
                    r++;
                }

            }

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    /**
     * Gets the report of the ingredients that have not been sold in a lot of items (less than 10% of total)
     * @param excessReport 2D - String array that should be 4 columns wide
     * @param date string containing the start date
     */
    public static void getExcessReport(String[][] excessReport, String date) {
        String curr_date = Order.getDate();
        Connection conn = getConn();
        try {
            String stmt = """
                    select i.id, i.ingredient, sum(amountneeded) as used, currentamount, i.unit
                    from orders
                    join orderingredients on orderingredients.orderid = orders.id
                    join inventory i on i.id = orderingredients.inventoryid
                    where date between ? and ?
                    group by i.id
                    having (sum(amountneeded))/(currentamount + sum(amountneeded)) < 0.10
                    order by i.id;""";
            PreparedStatement p = conn.prepareStatement(stmt);
            p.setString(1,date);
            p.setString(1,curr_date);

            ResultSet result = p.executeQuery();

            int r = 0;
            while(result.next()) {
                excessReport[r][0] = result.getString("ingredient");
                excessReport[r][1] = Integer.toString(result.getInt("used"));
                excessReport[r][2] = Integer.toString(result.getInt("currentamount"));
                excessReport[r][3] = result.getString("unit");
                r++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    /**
     * takes in ordTable and makes it hold data from orders database - no return
     * @param ordTable 2D - String array of size [> 0][5]
     * */
    public static void getOrdTable(String[][] ordTable) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM orders ORDER BY id DESC;";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();



            // System.out.println("--------------------Query Results--------------------");

            int r = 0;


            while(result.next() && r < ordTable.length) {

                ordTable[r][0] = result.getString("id");
                ordTable[r][1] = result.getString("date");
                ordTable[r][2] = result.getString("subtotal");
                ordTable[r][3] = result.getString("total");
                ordTable[r][4] = result.getString("employeeid");
                r++;
            }

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    /**
     * takes in empty mealSizeTable and makes it hold data from mealsizes database - no return
     * @param mealSizeTable 2D String array of size[>0][4] that holds data from each entry in mealsizes database
     * */
    public static void getMealSizeTable(String[][] mealSizeTable) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM mealsizes"; // ORDER BY id DESC;";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();



            // System.out.println("--------------------Query Results--------------------");

            int r = 0;


            while(result.next() && r < mealSizeTable.length) {

                mealSizeTable[r][0] = result.getString("foodtype");
                mealSizeTable[r][1] = result.getString("mealtype");
                mealSizeTable[r][2] = result.getString("amountneeded");
                mealSizeTable[r][3] = result.getString("price");
                r++;
            }

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }

    /**
     * return integer of size of databse of String tName
     * @param tName string name of database
     * @return int that is size of database tName
     * */
    public static int getDBSize(String tName) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT COUNT(1) FROM " + tName + ";";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();


            result.next();
            return result.getInt(1);

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return -1;
    }
    /**
     * returns index of item of itemName for menuitems database
     * @param itemName String name of food item
     * @return int index of item name in menuitems database
     * */
    public static int getItemIndex(String itemName) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM menuitems WHERE name = '" + itemName + "';";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();


            result.next();
            return result.getInt("id");

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return -1; // not in table
    }
    /**
     * returns a double, price of item of name meatype. returns -1 if price not in table
     * @param mealtype name of mealtype attribute in mealsizes database
     * @return double, price of item of mealtype in mealsizes table
     * */
    public static double getTablePrice(String mealtype) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
//            String sqlStatement = "SELECT mealsizes.price FROM mealsizes INNER JOIN menuitems ON mealsizes.foodtype = menuitems.foodtype " +
//                    "WHERE (menuitems.name = '" + foodname + "' AND mealtype ='" + mealtype +"');";
            String sqlStatement = "SELECT price FROM mealsizes WHERE mealtype = ?";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            p.setString(1,mealtype);
            ResultSet result = p.executeQuery();
            if (result.next())
                return result.getDouble("price");
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return -1.0;
    }
    /**
     * returns double, price from mealsizes database of String mealtype and foodname
     * @param mealtype name of mealtype in mealsizes database
     * @param foodname name of food item in menuitems database
     * @return double, price of mealtype and foodname from mealsizes table
     * */
    public static double getTablePrice(String mealtype, String foodname) {
        Connection conn = getConn();
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT mealsizes.price FROM mealsizes INNER JOIN menuitems ON mealsizes.foodtype = menuitems.foodtype " +
                    "WHERE (menuitems.name = '" + foodname + "' AND mealtype ='" + mealtype +"');";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();
            if (result.next())
                return result.getDouble("price");
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

        return -1.0;
    }

    /*
    * main function for jdbcpostgreSQL - used as template for functions and testing to access database
    * */
    public static void main(String args[]) {

        //Building the connection with your credentials
        Connection conn = null;
        String teamNumber = "55"; // Your team number
        String sectionNumber = "904"; // Your section number
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        //Connecting to the database
        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
         } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
         }

         System.out.println("Opened database successfully");

         try{
           Statement stmt = conn.createStatement();

           //convertData(conn, "order_items.csv");
           //ResultSet result = stmt.executeQuery(insert1);
           //int result = stmt.executeUpdate(sqlStatement);


           System.out.println("--------------------Query Results--------------------");
           //while (result.next()) {
           //System.out.println(result.getString("column_name"));
           //}
           //OR
           //System.out.println(result);
        } catch (Exception e){
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }

        //closing the connection
        try {
          conn.close();
          System.out.println("Connection Closed.");
        } catch(Exception e) {
          System.out.println("Connection NOT Closed.");
        }//end try catch
    }//end main

}//end Class

//select menuitems.name, count(menuitems.id) from((orderitems inner join orders on orderitems.orderid = orders.id) inner join menuitems on orderitems.menuitem1 = menuitems.id or orderitems.menuitem2 = menuitems.id or orderitems.menuitem3 = menuitems.id or orderitems.side1 = menuitems.id or orderitems.side2 = menuitems.id) where date between '2022-10-01' and '2022-10-02' group by menuitems.name;
//select menuitems.name, count(menuitems.id) from((orderitems inner join orders on orderitems.orderid = orders.id) inner join menuitems on orderitems.menuitem1 = menuitems.id or orderitems.menuitem2 = menuitems.id or orderitems.menuitem3 = menuitems.id or orderitems.side1 = menuitems.id or orderitems.side2 = menuitems.id) where date between '2022-10-02' and '2022-10-01' group by menuitems.name;