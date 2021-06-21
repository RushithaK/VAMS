
import java.sql.*;
import java.util.*;

public class DBConnection {

  private static DBConnection driver;
    private Connection con;
    private Statement stmt;
    private DBConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());
        }
        // connect to Oracle
        connect();
    }

    public static DBConnection getInstance() {
        if (driver == null)
            driver = new DBConnection();
        return driver;
    }

    /*
     * connects to Oracle database named using username and password
     */
    private void connect() {
        String username = "project";
        String password = "rushitha";
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
            //System.out.println("\nConnected to Oracle DataBase");
            stmt = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage() + "\nFailed to connect to Oracle DataBase");
        }
    }

    /*
     * execute any SQL statements alter the database (update, delete, insert)
     */
    public String executeAlter(String sqlstmt) {
        try {
            int res=stmt.executeUpdate(sqlstmt);
            con.commit();
            return "inserted "+Integer.toString(res)+" rows in table";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }


    /*
     * execute query statements
     */
    public ResultSet executeQuery(String sqlstmt) {
        try {
            return stmt.executeQuery(sqlstmt);
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage() + "\nUnable to execute: " + sqlstmt);
            return null;
        }
    }

    /*
     * disconnect from Oracle database
     */
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("\nFailed to disconnect from Oracle");
        }
    }
}