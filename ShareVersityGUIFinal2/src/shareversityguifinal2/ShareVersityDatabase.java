
package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author saral
 */
public class ShareVersityDatabase 
{
    private final DatabaseManager dbManager;
    private final Connection conn;
    private Statement statement;

    public ShareVersityDatabase() {
        dbManager = new DatabaseManager();
        conn = dbManager.getConnection();
    }
    
     public void connectBookStoreDB() {
        //use the conn, initialize database by creating BOOK Table and insert records
        try
        {
            this.statement = conn.createStatement();
            this.checkExistedTable("BOOK");
            
            this.statement.addBatch("CREATE TABLE ACCOUNTINFO (USERNAME VARCHAR(50), PASSWORD VARCHAR(50), FULLNAME VARCHAR(50), BANKACCOUNTNUMBER INT, DATEOFBIRTH VARCHAR(40), WALLETAMOUNT FLOAT)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('AZEL00', 'Slum Dog Millionaire', 'Fiction', 19.90)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('FRAN123', 'Run Mummy Run', 'Fiction', 28.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('MARY1', 'The Land of Painted Caves', 'Fiction', 15.40)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('JORDAN99', 'Cook with Jamie', 'Non-fiction', 55.20)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES ('SARAHMA33', 'Far Eastern Odyssey', 'Non-fiction', 24.90)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES (ANAHIT35, 'Open', 'Non-fiction', 33.60)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES (LUKE77, 'Big Java', 'Textbook', 55.00)");
            this.statement.addBatch("INSERT INTO ACCOUNTINFO VALUES (JOHNSMITH51, 'Financial Accounting', 'Textbook', 24.80)");
            
            this.statement.executeBatch();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void createPromotionTable() 
    {
            /* You may need the following SQL statements for this method:
            
            * Create the table:
            CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT);
            
            * Insert records into the table:
            INSERT INTO PROMOTION VALUES ('Fiction', 0),
            ('Non-fiction', 10),
            ('Textbook', 20);
            
            */
        try 
        {
            Statement statement = conn.createStatement();
            
            String newTable = "PROMOTION";
            this.checkExistedTable(newTable);
            this.statement.addBatch("CREATE TABLE PROMOTION (CATEGORY VARCHAR(20), DISCOUNT INT)");
            this.statement.addBatch("INSERT INTO PROMOTION VALUES ('Fiction', 0),\n" 
                    + "('Non-fiction', 10),\n" 
                    + "('Textbook', 20)");
            this.statement.executeBatch();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ex.getMessage());
        }
    }
    
    public ResultSet getWeekSpecial() {
        /* You may need the following SQL statements for this method:

        * Query multiple tables:
        
          SELECT TITLE, PRICE, DISCOUNT FROM BOOK, PROMOTION WHERE BOOK.CATEGORY=PROMOTION.CATEGORY;

         */

        ResultSet rs = null;
        try 
        {
            rs = this.statement.executeQuery("SELECT TITLE, PRICE, DISCOUNT "
                + "FROM BOOK, PROMOTION "
                + "WHERE BOOK.CATEGORY=PROMOTION.CATEGORY");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return (rs);
    }

    public void createWeekSpecialTable(ResultSet rs) 
    {
        try 
        {
            this.checkExistedTable("WEEKSPECIAL");
            this.statement = conn.createStatement();
            this.statement.addBatch("CREATE TABLE WEEKSPECIAL (TITLE VARCHAR(50), SPECIALPRICE FLOAT)");
            while (rs.next()) {
                String title = rs.getString("TITLE");
                float price = rs.getFloat("PRICE");
                int discount = rs.getInt("DISCOUNT");
                float newPrice = price * (100 - discount) / 100;
                this.statement.addBatch("INSERT INTO WEEKSPECIAL VALUES('" + title + "', " + newPrice + ")");
            }
            this.statement.executeBatch();
            rs.close();
        }
        
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void checkExistedTable(String name)
    {
        try 
        {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            
            while (rs.next())
            {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);
                if (table_name.equalsIgnoreCase(name))
                {
                    statement.executeUpdate("Drop table " + name);
                    System.out.println("Table "+name+" has been deleted.");
                    break;
                }
            }
            rs.close();
        }
        
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection()
    {
        this.dbManager.closeConnections();
    }

}
