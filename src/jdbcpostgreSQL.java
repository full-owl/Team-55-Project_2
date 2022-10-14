package src;

import src.Cashier.OrderItem;

import java.sql.*;
import java.io.*;
import java.sql.Date;
import java.util.*;
/*
CSCE 331
9-28-2022 Lab
 */
/**
 * @author evanw
 *
 */
public class jdbcpostgreSQL {

  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code

  /* DON"T COPY PASTE WRITE THE COMMANDS IN YOUR TERMINAL MANUALLY*/

  //Windows: java -cp ".;postgresql-42.2.8.jar" src.jdbcpostgreSQL
  //Mac/Linux: java -cp ".:postgresql-42.2.8.jar" src.jdbcpostgreSQL
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

    public static void insertOrder(Vector<OrderItem> items) {

        int orderid = getDBSize("orders") + 1;

        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();
        try
        {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

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
            ps.setDate(2, Date.valueOf(order.currentDate));
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

            } catch (Exception e)
            {
                e.printStackTrace();
                System.err.println(e.getClass().getName()+": "+e.getMessage());
                System.exit(0);
            }

        }
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
        new managerGui();
    }

    public static void editInventory(int id, int amount)
    {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try
        {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try
        {
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
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
        new managerGui();
    }

    public static void addInventory(int id, String name, int amount, String unit)
    {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try
        {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

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

        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
        new managerGui();
    }

    public static void editPrices(String foodtype, String mealtype, float newPrice)
    {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try
        {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

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
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
        new managerGui();
    }

    public static void addMenuItem(int id, String name, String type, String description)
    {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try
        {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e)
        {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

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
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
    }

    public static void getSideMenuItems(String[] sideTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            String sqlStatement = "SELECT name FROM menuitems WHERE foodtype = 'side'";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            int r = 0;

            while(result.next()) {
                sideTable[r] = result.getString("name");
                r++;
            }

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
    }

    public static void getEntreeMenuItems(String[] entreeTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            String sqlStatement = "SELECT name FROM menuitems WHERE foodtype = 'entree' or foodtype = 'premium entree'";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            int r = 0;

            while(result.next()) {
                entreeTable[r] = result.getString("name");
                r++;
            }

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
    }

    public static void getDrinkMenuItems(String[] drinkTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            String sqlStatement = "SELECT name FROM menuitems WHERE foodtype = 'drink'";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            int r = 0;

            while(result.next()) {
                drinkTable[r] = result.getString("name");
                r++;
            }

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
    }

    public static void getAppetizerMenuItems(String[] appetizerTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            String sqlStatement = "SELECT name FROM menuitems WHERE foodtype = 'appetizer'";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            int r = 0;

            while(result.next()) {
                appetizerTable[r] = result.getString("name");
                r++;
            }

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
    }

    public static void getInvTable(String[][] invTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM inventory";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();

            // id, ingredient, currentamount, unit

            System.out.println("--------------------Query Results--------------------");

            int r = 0;
//            invTable[0][0] = "ID";
//            invTable[0][1] = "Ingredient";
//            invTable[0][2] = "Current Amount";
//            invTable[0][3] = "Unit";

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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
    }

    public static void getOrdTable(String[][] ordTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM orders ORDER BY id DESC;";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();



            System.out.println("--------------------Query Results--------------------");

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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
    }

    public static void getMealSizeTable(String[][] mealSizeTable) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        try{
            //String sqlStatement = "INSERT INTO TeamMembers (student_name, section, favority_movie, favorite_holiday) VALUES('Plunky', 905, 'Jeepers Creepers', '2022-10-31')";
            String sqlStatement = "SELECT * FROM mealsizes"; // ORDER BY id DESC;";

            PreparedStatement p = conn.prepareStatement(sqlStatement);
            ResultSet result = p.executeQuery();



            System.out.println("--------------------Query Results--------------------");

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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
    }

    public static int getDBSize(String tName) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
        return -1;
    }

    public static int getItemIndex(String itemName) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
        return -1; // not in table
    }

    public static double getTablePrice(String mealtype) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
        return -1.0;
    }
    public static double getTablePrice(String mealtype, String foodname) {
        Connection conn = null;
        String teamNumber = "55";
        String sectionNumber = "904";
        String dbName = "csce331_" + sectionNumber + "_" + teamNumber;
        String dbConnectionString = "jdbc:postgresql://csce-315-db.engr.tamu.edu/" + dbName;
        dbSetup myCredentials = new dbSetup();

        try {
            conn = DriverManager.getConnection(dbConnectionString, dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
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

        //closing the connection
        try {
            conn.close();
            System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }//end try catch
        return -1.0;
    }
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
