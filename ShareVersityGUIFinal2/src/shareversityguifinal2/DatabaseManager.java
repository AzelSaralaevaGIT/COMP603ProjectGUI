package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The DatabaseManager class is responsible for managing database connections and executing queries on the database.
 * It establishes a connection to the Derby database and provides methods for querying and updating the database.
 * The class can also be used to close the database connection.
 * 
 * @author saral
 */

// database compnonent of the project responsible for database connections
public final class DatabaseManager {
    
    
     //creates database at the root of project folder
    private static final String URL = "jdbc:derby:ShareVersityDB_Ebd;create=true";  //derby.jar

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
                conn = DriverManager.getConnection(URL);
                //System.out.println(URL + "Connected successfully");
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            } 
        }
    }
// closes connection to derby jar
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

