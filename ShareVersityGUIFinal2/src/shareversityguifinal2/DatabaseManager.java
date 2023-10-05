/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author saral
 */
public final class DatabaseManager {
    
      /**
     * If you try to connect the database on the server, you must start the
     * server first. Meanwhile, you need to import 'derbyclient.jar' to the
     * libraries.
     */
    //    private static final String URL = "jdbc:derby://localhost:1527/BookStoreDB";
    /**
     * If you try to connect the database embedded in the project, you must stop
     * the server first. Meanwhile, you need to import 'derby.jar' to the
     * libraries.
     */
   // private static final String USER_NAME = "test"; //your DB username
    // private static final String PASSWORD = "test"; //your DB password
    private static final String URL = "jdbc:derby:ShareVersityDB_Ebd;create=true";  //url of the DB host

    Connection conn;

    public DatabaseManager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() 
    {
        //Establish a connection to Database
        if (this.conn == null)
        {
            try 
            {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + "Connected successfully");
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResultSet queryDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public void updateDB(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

