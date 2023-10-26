package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ShareVersityDatabase {
    private final DatabaseManager dbManager;
    private final Connection conn;
    private Statement statement;

    public ShareVersityDatabase() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
    }

    public void connectShareVersityDB() {
        try {
            this.statement = conn.createStatement();
            this.checkExistedTable("ACCOUNT_TABLE");

            // Create the ACCOUNTINFO table and insert records
            this.statement.addBatch("CREATE TABLE ACCOUNT_TABLE(USERNAME VARCHAR(50), PASSWORD VARCHAR(50), FULLNAME VARCHAR(50), BANKACCOUNTNUMBER VARCHAR(20), DATEOFBIRTH VARCHAR(40), WALLETAMOUNT FLOAT)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('AZEL00', 'password123', 'Azel Saralaeva', '71-3246-432456-97', '20 May 2000', 10000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('FRAN123', 'academic111', 'Fran Caveman', '65-2097-4567-87', '10 August 1998', 56000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('MARY1', 'comp603', 'Mary Johnstonson', '01-4564-856743-65', '5 December 1967', 33000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('JORDAN99', 'tvtv33', 'Jordan Smith', '09-5467-847426-78', '1 July 2005', 500.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('SARAHMA33', '100percentplease', 'Sarah Maverye', '71-3366-236430-65', '20 June 1994', 10000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('ANAHIT35', 'weworkedhardonthis', 'Ana Hit', '98-5467-456931-94', '17 January 1977', 5555.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('LUKE77', 'thankyou!', 'Luke Monte', '06-9564-183331-79', '8 September 2001', 78564.00)");
            this.statement.addBatch("INSERT INTO ACCOUNT_TABLE VALUES ('JOHNSMITH51', 'hehehe', 'John Smith', '78-9025-564823-50', '12 August 1989', 3345.00)");

            this.statement.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet getAccountInfo() {
        ResultSet rs = null;
        try {
            rs = this.statement.executeQuery("SELECT USERNAME, FULLNAME, BANKACCOUNTNUMBER, DATEOFBIRTH, WALLETAMOUNT FROM ACCOUNTINFO");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public void checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = { "TABLE" };
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                if (table_name.equalsIgnoreCase(name)) {
                    statement.executeUpdate("DROP TABLE " + name);
                    System.out.println("Table " + name + " has been deleted.");
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void createAccountTable() {
    Statement statement = null;
    try {
        statement = conn.createStatement();

        // Define the table name and check if it exists
        String tableName = "ACCOUNT_INFORMATION";
        this.checkExistedTable(tableName);

        // Define the SQL statement for creating the table
        String createTableSQL = "CREATE TABLE " + tableName + " ("
                + "USERNAME VARCHAR(50),"
                + "FULLNAME VARCHAR(50),"
                + "DATEOFBIRTH VARCHAR(40),"
                + "WALLETAMOUNT FLOAT"
                + ")";

        // Execute the SQL statement to create the table
        statement.addBatch(createTableSQL);

        // Execute the batch of SQL statements
        statement.executeBatch();
        System.out.println("Table " + tableName + " created successfully.");
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    


    public void closeConnection() {
        this.dbManager.closeConnections();
    }
    
    
    
}
