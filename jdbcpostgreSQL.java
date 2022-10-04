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
       //create a statement object
       Statement stmt = conn.createStatement();
       //Running a query
       //TODO: update the sql command here
       String createMenuItems = "CREATE TABLE MenuItems (id INT PRIMARY KEY, name VARCHAR, foodType VARCHAR, Description VARCHAR);";
       String createInventory = "CREATE TABLE Inventory (id INT PRIMARY KEY, ingredient VARCHAR, currentAmount INT, unit VARCHAR);";
       String createOrderItems = "CREATE TABLE OrderItems (orderId INT, mealType VARCHAR, menuItem1 INT, menuItem2 INT, menuItem3 INT," +
               " menuItem4 INT, menuItem5 INT, customInstructions VARCHAR, FOREIGN KEY (menuItem1) REFERENCES MenuItems(id)" +
               ", FOREIGN KEY (menuItem2) REFERENCES MenuItems(id), FOREIGN KEY (menuItem3) REFERENCES MenuItems(id)" +
               ", FOREIGN KEY (menuItem4) REFERENCES MenuItems(id), FOREIGN KEY (menuItem5) REFERENCES MenuItems(id));";
       String createMealSizes = "CREATE TABLE MealSizes (foodType VARCHAR, mealType VARCHAR, amountNeeded INT, price DECIMAL(18,2));";
       String createMenuIngredients = "CREATE TABLE MenuIngredients (menuId INT, inventoryId INT, proportion INT, " +
               "FOREIGN KEY (menuId) REFERENCES MenuItems(id), FOREIGN KEY (inventoryId) REFERENCES Inventory(id));";
       String dropTable = "DROP TABLE MealSizes;";
       try {
           Scanner sc = new Scanner(new File("inventory.csv"));
           sc.useDelimiter(",");
           String desc = "Not Available";
           int i = 1;
           String res = "INSERT INTO inventory (id, ingredient, currentAmount, unit) VALUES (?, ?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(res);
           while (sc.hasNext()) {
               String ingred = sc.next();
               int am = sc.nextInt();
               String un = sc.next();
               if (i != 1)
               {
                   ingred = ingred.substring(2,ingred.length());
               }
               ps.setInt(1,i);
               ps.setString(2,ingred);
               ps.setInt(3,am);
               ps.setString(4,un);
               ps.addBatch();
               i++;
               //System.out.print(name);
           }
           ps.executeBatch();
           //System.out.println(res);
           sc.close();
       } catch (FileNotFoundException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
       //send statement to DBMS
       //This executeQuery command is useful for data retrieval
         //String update1 = "UPDATE menuitems set name = 'Chow Mein' where id = '1';";
         //ResultSet result = stmt.executeQuery(createInventory);
       //OR
       //This executeUpdate command is useful for updating data
       //int result = stmt.executeUpdate(sqlStatement);

       //OUTPUT
       //You will need to output the results differently depeninding on which function you use
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
