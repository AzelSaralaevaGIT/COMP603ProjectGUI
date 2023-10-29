 package shareversityguifinal2;

import java.lang.System.Logger.Level;
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
            System.out.println("Account Table has been created.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
    
   
//creation of Companies table
    public void createCompaniesTable() {
    
    try {
        statement = conn.createStatement();
        String tableName = "COMPANIES";
        this.checkExistedTable(tableName);

        // Define the SQL statement for creating the table
        this.statement.addBatch("CREATE TABLE COMPANIES(COMPANYNAME VARCHAR(100), COMPANYDESCRIPTION VARCHAR(350), CEO VARCHAR(150), NUM_EMPLOYEES INT, COMPANY_CATEGORIES VARCHAR(100), INVESTMENT_TYPES VARCHAR(150), COST_PER_SHARE_NOW DOUBLE)");
        
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('XYZ Tech Solutions', 'XYZ Tech Solutions is a leadingty provider of software and hardware solutions for businesses worldwide. The company specializes in developing innovative tools for streamlining operations and enhancing productivity.', 'Jane Smith', 800, 'TECHNOLOGY MANUFACTURING', 'LOWRISK', 125.45)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Global Learning Institute', 'Global Learning Institute is a renowned educational organization that offers online and on-site courses for students of all ages. Their comprehensive curriculum covers a wide range of subjects and skills.', 'Michael Johnson', 350, 'EDUCATION', 'LOWRISK', 65.78)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('GreenHarvest Farms', 'GreenHarvest Farms is a sustainable agriculture company that focuses on organic farming practices and eco-friendly crop management techniques to produce high-quality and nutritious food.', 'Emily Rodriguez', 120, 'AGRICULTURE FOOD', 'LOWRISK', 30.20)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Precision Manufacturing Co.', 'Precision Manufacturing Co. specializes in precision engineering and manufacturing of complex components for aerospace and automotive industries, ensuring top-notch quality and performance.', 'David Chen', 250, 'MANUFACTURING', 'LOWRISK', 80.15)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Swift Logistics', 'Swift Logistics provides efficient and reliable transportation solutions for businesses globally. Their cutting-edge logistics platform ensures timely delivery and real-time tracking.', 'Sarah Thompson', 600, 'TRANSPORT', 'LOWRISK', 45.90)");
        
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('EduSpark Learning', 'EduSpark Learning is an education technology startup that leverages AI to create personalized learning experiences for students, enhancing their understanding and academic performance.', 'Ryan Adams', 15, 'EDUCATION', 'STARTUP', 8.50)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('AgriGenetic Solutions', 'AgriGenetic Solutions is a startup that pioneers advanced genetic techniques to create disease-resistant and high-yielding crop varieties, aiming to revolutionize modern agriculture.', 'Maria Ramirez', 20, 'AGRICULTURE', 'STARTUP', 10.25)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('NanoFab Creations', 'NanoFab Creations is a startup specializing in nanoscale fabrication, producing intricate nanostructures for use in electronics, optics, and other emerging technologies.', 'Chris Bennett', 10, 'MANUFACTURING', 'STARTUP', 12.90)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('SwiftGo', 'SwiftGo is a startup that offers an on-demand transportation platform, providing affordable and convenient rides through a user-friendly app that connects drivers and passengers.', 'Kevin Clark', 25, 'TRANSPORT', 'STARTUP', 5.50)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('HealthTech Innovators', 'HealthTech Innovators is a startup focused on developing innovative healthcare solutions, including wearable devices and telemedicine platforms, to improve patient outcomes.', 'Linda Foster', 40, 'HEALTHCARE TECHNOLOGY', 'STARTUP', 7.80)");


// Execute the SQL statement to create the table
        this.statement.executeBatch();
        System.out.println("Companies Table has been created.");
        
    } catch (SQLException ex) {
       Logger.getLogger(ex.getMessage());
    } 
}
    
  


    //creation of createInvestmentTable
    public void createInvestmentTable()
    {
         try {
        statement = conn.createStatement();
        String tableName = "INVESTMENT";
        this.checkExistedTable(tableName);

        // Define the SQL statement for creating the table
        this.statement.addBatch("CREATE TABLE INVESTMENT(USERNAME VARCHAR(50),COMPANYNAME VARCHAR(50), AMOUNT_INVESTED DOUBLE, PURCHASE_CPS_INDEX INTEGER)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('AZEL00', 'XYZ Tech Solutions', 50.00, 5)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('AZEL00', 'Swift Logistics', 20.00, 7)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('FRAN123', 'Global Learning Institute', 10.00, 6)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('MARY1', 'GreenHarvest Farms', 45.00, 5)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('JORDAN99', 'XYZ Tech Solutions', 5.00, 3)");
        this.statement.addBatch("INSERT INTO INVESTMENT VALUES ('SARAHMA33', 'XYZ Tech Solutions', 12.00, 2)");
        // Execute the SQL statement to create the table
        this.statement.executeBatch();
        System.out.println("Investment table has been created");
        
        } catch (SQLException ex) {
           Logger.getLogger(ex.getMessage());
        } 
    }
    
    
    
    //creation of cost per share history
    public void createCostPerShareHistoryTable() {
    
    try {
        statement = conn.createStatement();
        String tableName = "COST_PER_SHARE_HISTORY";
        this.checkExistedTable(tableName);

        // Define the SQL statement for creating the table
        this.statement.addBatch("CREATE TABLE COST_PER_SHARE_HISTORY(COMPANYNAME VARCHAR(100), COST_PER_SHARE_VALUE DOUBLE)"); 
        
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('XYZ Tech Solutions', 3.45)"); //name of company, cost for each of their shares that the CPS graph is based off (the cps for today) and id number in table
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('Global Learning Institute', 6.11)");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('GreenHarvest Farms', 1.99)");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('Precision Manufacturing Co.', 2.01)");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('Swift Logistics', 1.75)");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('EduSpark Learning', 3.33)");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('AgriGenetic Solutions', 4.88");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('NanoFab Creations', 4.19");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('SwiftGo', 6.21");
        this.statement.addBatch("INSERT INTO COST_PER_SHARE_HISTORY VALUES ('HealthTech Innovators', 5.76");

// Execute the SQL statement to create the table
        this.statement.executeBatch();
        System.out.println("Cost Per Share History Table created.");
        
    } catch (SQLException ex) {
       Logger.getLogger(ex.getMessage());
    } 
}

    //insert new account created into the Account table
    public void insertAccount(String username, String password, String fullname, String bankAccountNumber, String dateOfBirth, double walletAmount) {
    try {
        String insertSQL = "INSERT INTO ACCOUNT_TABLE (USERNAME, PASSWORD, FULLNAME, BANKACCOUNTNUMBER, DATEOFBIRTH, WALLETAMOUNT) " +
            "VALUES ('" + username + "', '" + password + "','" + fullname + "', '" + bankAccountNumber + "', '" + dateOfBirth + "', " + walletAmount + ")";
        statement.executeUpdate(insertSQL);
        System.out.println("Account inserted successfully.");
    } catch (SQLException ex) {
         Logger.getLogger(ex.getMessage());
        
    }
}

    // retrieves account info
    public ResultSet getAccountInfo() {

        ResultSet rs = null;
        try 
        {
            rs = this.statement.executeQuery("SELECT USERNAME, FULLNAME, WALLETAMOUNT "
                + "FROM ACCOUNT_TABLE");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return (rs);
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }
}
