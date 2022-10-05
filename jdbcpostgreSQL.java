import java.sql.*;
import java.io.*;
import java.util.*;
/*
CSCE 331
9-28-2022 Lab
 */
public class jdbcpostgreSQL {

  //Commands to run this script
  //This will compile all java files in this directory
  //javac *.java
  //This command tells the file where to find the postgres jar which it needs to execute postgres commands, then executes the code

  /* DON"T COPY PASTE WRITE THE COMMANDS IN YOUR TERMINAL MANUALLY*/

  //Windows: java -cp ".;postgresql-42.2.8.jar" jdbcpostgreSQL
  //Mac/Linux: java -cp ".:postgresql-42.2.8.jar" jdbcpostgreSQL
  //MAKE SURE YOU ARE ON VPN or TAMU WIFI TO ACCESS DATABASE
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
       String createOrders = "CREATE TABLE orders (id INT PRIMARY KEY, date DATE, subTotal DECIMAL(18,2), total DECIMAL(18,2), employeeId INT);";
       String createMenuItems = "CREATE TABLE MenuItems (id INT PRIMARY KEY, name VARCHAR, foodType VARCHAR, Description VARCHAR);";
       String createInventory = "CREATE TABLE Inventory (id INT PRIMARY KEY, ingredient VARCHAR, currentAmount INT, unit VARCHAR);";
       String createOrderItems = "CREATE TABLE OrderItems (orderId INT, mealType VARCHAR, menuItem1 INT, menuItem2 INT, menuItem3 INT," +
               " menuItem4 INT, menuItem5 INT, customInstructions VARCHAR, FOREIGN KEY (menuItem1) REFERENCES MenuItems(id)" +
               ", FOREIGN KEY (menuItem2) REFERENCES MenuItems(id), FOREIGN KEY (menuItem3) REFERENCES MenuItems(id)" +
               ", FOREIGN KEY (menuItem4) REFERENCES MenuItems(id), FOREIGN KEY (menuItem5) REFERENCES MenuItems(id));";
       String createMealSizes = "CREATE TABLE MealSizes (foodType VARCHAR, mealType VARCHAR, amountNeeded INT, price DECIMAL(18,2));";
       String createMenuIngredients = "CREATE TABLE MenuIngredients (menuId INT, inventoryId INT, proportion DECIMAL(18,2), " +
               "FOREIGN KEY (menuId) REFERENCES MenuItems(id), FOREIGN KEY (inventoryId) REFERENCES Inventory(id));";
       String dropTable = "DROP TABLE orders;";

       try {
           Scanner sc = new Scanner(new File("orders.csv"));
           sc.useDelimiter(",");
           int i = 1;
           String res = "INSERT INTO orders (id, date, subTotal, total, employeeId) VALUES (?, ?, ?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(res);
           while (sc.hasNext()) {
               String item1 = sc.next();
               String item2 = sc.next();
               float item3 = sc.nextFloat();
               float item4 = sc.nextFloat();
               int item5 = sc.nextInt();
               ps.setInt(1,i);
               ps.setString(2,item2);
               ps.setFloat(3,item3);
               ps.setFloat(4,item4);
               ps.setInt(5,item5);
               ps.addBatch();
               i++;
           }
           //ps.executeBatch();
           //System.out.println(res);
           sc.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }

       //ResultSet result = stmt.executeQuery(createOrders);
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
