package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            this.checkExistedTable("ACCOUNTINFO");

            // Create the ACCOUNTINFO table and insert records
            this.statement.addBatch("CREATE TABLE ACCOUNTINFO (USERNAME VARCHAR(50), PASSWORD VARCHAR(50), FULLNAME VARCHAR(50), BANKACCOUNTNUMBER INT, DATEOFBIRTH VARCHAR(40), WALLETAMOUNT FLOAT)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('AZEL00', 'password123', 'Azel Saralaeva', 714652622, '20 May 2000', 10000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('FRAN123', 'academic111', 'Fran Caveman', 658618632, '10 August 1998', 56000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('MARY1', 'comp603', 'Mary Johnstonson', 524897456, '5 December 1967', 33000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('JORDAN99', 'tvtv33', 'Jordan Smith', 432897812, '1 July 2005', 500.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('SARAHMA33', 'giveus100percentplease', 'Sarah Maverye', 714342652622, '20 June 1994', 10000.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('ANAHIT35', 'weworkedhardonthis', 'Ana Hit', 985263523, '17 January 1977', 5555.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('LUKE77', 'thankyou!', 'Luke Monte', 55678952202, '8 September 2001', 78564.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('JOHNSMITH51', 'hehehe', 'John Smith', 714342622, '12 August 1989', 3345.00)");

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

    public void closeConnection() {
        this.dbManager.closeConnections();
    }
}
