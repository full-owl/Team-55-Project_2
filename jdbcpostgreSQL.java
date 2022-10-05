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

    // Parses a csv file and pushes data to the database
    public static void convertData(string fileName)
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

       convertData("order_items.csv");
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
