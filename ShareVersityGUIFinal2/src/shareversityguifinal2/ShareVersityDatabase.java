package shareversityguifinal2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ShareVersityDatabase {
    
    private final DatabaseManager dbManager;
   

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
    
    public void createCompaniesList() {
   
    Statement statement = null;
    try {
        statement = conn.createStatement();

        // Define the table name and check if it exists
        String tableName = "COMPANIES";
        this.checkExistedTable(tableName);

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);

        if (!tables.next()) {
        // Define the SQL statement for creating the table
        String createTableSQL = "CREATE TABLE " + tableName + " ("
                + "COMPANYNAME VARCHAR(50),"
                + "COMPANYDESCRIPTION VARCHAR(200),"
                + "CEO VARCHAR(40),"
                + "NUM_EMPLOYEES INT,"
                + "COMPANY_CATEGORIES VARCHAR(30),"
                + "INVESTMENT_TYPES VARCHAR(20)," 
                + "COST_PER_SHARE_NOW DOUBLE"
                + ")";
        // Execute the SQL statement to create the table
        statement.addBatch(createTableSQL);
        }
        
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('XYZ Tech Solutions', 'XYZ Tech Solutions is a leadingty provider of software and hardware solutions for businesses worldwide. The company specializes in developing innovative tools for streamlining operations and enhancing productivity.', 'Jane Smith', 800, 'TECHNOLOGY MANUFACTURING', 'LOWRISK', 125.45)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Global Learning Institute', 'Global Learning Institute is a renowned educational organization that offers online and on-site courses for students of all ages. Their comprehensive curriculum covers a wide range of subjects and skills.', 'Michael Johnson', 350, 'EDUCATION', 'LOWRISK', 65.78)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('GreenHarvest Farms', 'GreenHarvest Farms is a sustainable agriculture company that focuses on organic farming practices and eco-friendly crop management techniques to produce high-quality and nutritious food.', 'Emily Rodriguez', 120, 'AGRICULTURE FOOD', 'LOWRISK', 30.20)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Precision Manufacturing Co.', 'Precision Manufacturing Co. specializes in precision engineering and manufacturing of complex components for aerospace and automotive industries, ensuring top-notch quality and performance.', 'David Chen', 250, 'MANUFACTURING', 'LOWRISK', 80.15)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Swift Logistics', 'Swift Logistics provides efficient and reliable transportation solutions for businesses globally. Their cutting-edge logistics platform ensures timely delivery and real-time tracking.', 'Sarah Thompson', 600, 'TRANSPORT', 'LOWRISK', 45.90)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('HealthWell Hospitals', 'HealthWell Hospitals is a network of state-of-the-art medical facilities that offer comprehensive healthcare services, including specialized treatments and personalized patient care.', 'John Anderson', 1500, 'HEALTHCARE', 'LOWRISK', 120.75)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('MediaWave Entertainment', 'MediaWave Entertainment is a multimedia production company known for creating captivating content, including films, TV shows, and online videos that resonate with audiences worldwide.', 'Lisa Williams', 200, 'MEDIA', 'LOWRISK', 55.60)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('Delicious Bites', 'Delicious Bites is a rapidly growing food chain that serves a variety of delectable dishes prepared with fresh, locally sourced ingredients, catering to diverse culinary preferences.', 'Daniel Martin', 350, 'FOOD', 'LOWRISK', 20.75)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('CapitalEdge Financial', 'CapitalEdge Financial offers a wide range of financial services, including investment management, wealth advisory, and retirement planning, tailored to clients' long-term financial goals.', 'Robert Turner', 75, 'FINANCE', 'LOWRISK', 160.90)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('ConnectCom Telecom', 'ConnectCom Telecom is a leading telecommunications company that provides high-speed internet, mobile services, and advanced communication solutions to keep people connected globally.', 'Sophia Lee', 400, 'TELECOMMUNICATION', 'LOWRISK', 75.30)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('NanoTech Innovations', 'NanoTech Innovations is a cutting-edge technology startup focused on developing nanomaterials with revolutionary applications in electronics, energy storage, and medical devices.', 'Alex Walker', 30, 'TECHNOLOGY', 'STARTUP', 18.75)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('EduSpark Learning', 'EduSpark Learning is an education technology startup that leverages AI to create personalized learning experiences for students, enhancing their understanding and academic performance.', 'Ryan Adams', 15, 'EDUCATION', 'STARTUP', 8.50)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('AgriGenetic Solutions', 'AgriGenetic Solutions is a startup that pioneers advanced genetic techniques to create disease-resistant and high-yielding crop varieties, aiming to revolutionize modern agriculture.', 'Maria Ramirez', 20, 'AGRICULTURE', 'STARTUP', 10.25)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('NanoFab Creations', 'NanoFab Creations is a startup specializing in nanoscale fabrication, producing intricate nanostructures for use in electronics, optics, and other emerging technologies.', 'Chris Bennett', 10, 'MANUFACTURING', 'STARTUP', 12.90)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('SwiftGo', 'SwiftGo is a startup that offers an on-demand transportation platform, providing affordable and convenient rides through a user-friendly app that connects drivers and passengers.', 'Kevin Clark', 25, 'TRANSPORT', 'STARTUP', 5.50)");
        this.statement.addBatch("INSERT INTO COMPANIES VALUES ('HealthTech Innovators', 'HealthTech Innovators is a startup focused on developing innovative healthcare solutions, including wearable devices and telemedicine platforms, to improve patient outcomes.', 'Linda Foster', 40, 'HEALTHCARE TECHNOLOGY', 'STARTUP', 7.80)");
    

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

    //inserts company into database
    public void insertCompanyRecord(String username, String fullname, String dateOfBirth, double walletAmount) {
    try {
        String insertSQL = "INSERT INTO COMPANIES (USERNAME, FULLNAME, DATEOFBIRTH, WALLETAMOUNT) " +
                            "VALUES ('" + username + "', '" + fullname + "', '" + dateOfBirth + "', " + walletAmount + ")";
        statement.executeUpdate(insertSQL);
        System.out.println("Company record inserted successfully.");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

    
    
    // retrieves account information from database 
    public ResultSet getAccountInfo() {
    ResultSet rs = null;
    try {
        rs = this.statement.executeQuery("SELECT USERNAME, FULLNAME, BANKACCOUNTNUMBER, DATEOFBIRTH, WALLETAMOUNT FROM ACCOUNT_TABLE");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return rs;
}


    public void closeConnection() {
        this.dbManager.closeConnections();
    }
    
    
    
}
