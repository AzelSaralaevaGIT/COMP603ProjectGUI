package shareversityguifinal2;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// Account Class
public class Account 
{
    //private instance variables to ensure encapsulation, retrieved via their getters
    private final String accountFilePath = "./resources/AccountInfo.txt";
    private String username;
    private String password;
    private String fullName;
    private String bankAccountNumber;
    private String dateOfBirth;
    private Wallet acountWallet;
    private Portfolio accountPortfolio;

    /** 
     * Account constructor for creating an Account 
     * @param username
     * @param password
     * @param fullName
     * @param bankAccountNumber
     * @param dateOfBirth
     */
    public Account(String username, String password, String fullName, String bankAccountNumber, String dateOfBirth) 
    {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfBirth = dateOfBirth;
        this.acountWallet = new Wallet();
        this.accountPortfolio = new Portfolio();
    }
    
    public void setRegisterAccount(String username, String password, String fullName, String bankAccountNumber, String dateOfBirth)
    {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.bankAccountNumber = bankAccountNumber;
        this.dateOfBirth = dateOfBirth;
        this.acountWallet = new Wallet();
        this.accountPortfolio = new Portfolio();
    }
    
    public void setLoginAccount(String username, String password, ImportedCompanies importedCompanies)
    {
       retrieveAccountFromDB(username,password); 
       this.accountPortfolio = new Portfolio(this, importedCompanies); 
    }
    
    /** 
     * Empty account constructor for when an Account needs to be created when information is not provided yet
     */
    public Account() 
    {
        this.username = "";
        this.password = "";
        this.fullName = "";
        this.bankAccountNumber = "";
        this.dateOfBirth = "";
        this.acountWallet = new Wallet();
        this.accountPortfolio = new Portfolio();
    }
    

    public String getFullName() {
        return this.fullName;
    }
    
    public String getBankAccountNumber() {

        return this.bankAccountNumber;
    }
    
    public String getDateofBirth() {
        return this.dateOfBirth;
    }
    
    public Wallet getWallet() {
        return this.acountWallet;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    // returns the Portfolio of associated Account object
    public Portfolio getAccountPortfolio() {
        return accountPortfolio;
    }
    
    /*
        This method saves the current Account object to the database, if the account doesn't already exist
    */
    public void saveAccountToDB()
    {
        Statement statement = null;
        try 
        {  
            if (!(accountExists(username))) // Insert new account if account doesn't exist
            {
                ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
                statement = shareversitydb.getConnection().createStatement();

                String accountInsert = "INSERT INTO ACCOUNT_TABLE (USERNAME, PASSWORD, FULLNAME, BANKACCOUNTNUMBER, DATEOFBIRTH, WALLETAMOUNT) " +
                    "VALUES ('" + this.username + "', '" + this.password + "','" + this.fullName + "', '" + this.bankAccountNumber + "', '" + this.dateOfBirth + "', " + this.getWallet().getAccountBalance() + ")";
                statement.executeUpdate(accountInsert);
                System.out.println("Account inserted successfully.");
            } 
            else // Update existing account
            {
                ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
                statement = shareversitydb.getConnection().createStatement();

                String accountUpdate = "UPDATE ACCOUNT_TABLE SET " +
                    "PASSWORD = '" + this.password + "', " +
                    "FULLNAME = '" + this.fullName + "', " +
                    "BANKACCOUNTNUMBER = '" + this.bankAccountNumber + "', " +
                    "DATEOFBIRTH = '" + this.dateOfBirth + "', " +
                    "WALLETAMOUNT = " + this.getWallet().getAccountBalance() +
                    "WHERE USERNAME = '" + this.username + "'";
                
                int rowsUpdated = statement.executeUpdate(accountUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Account updated successfully.");
                }
            }
        }
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            if (statement != null)
            {
                try 
                {
                    statement.close();
                } 
                catch (SQLException ex) 
                {
                    System.out.println("Error closing statement");
                }
            }
        }
    }
    
    // Method that retrieves information from database via matching username and password
    public final void retrieveAccountFromDB(String username, String password)  
    {  
        Statement statement = null;
        if (accountExists(username, password))
        {
            try {
                ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
                statement = shareversitydb.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT_TABLE");

                while (resultSet.next()) { //USERNAME, PASSWORD, FULLNAME, BANKACCOUNTNUMBER, DATEOFBIRTH, WALLETAMOUNT
                    String dbUsername = resultSet.getString("USERNAME");
                    String dbPassword = resultSet.getString("PASSWORD");
                    String dbFullName = resultSet.getString("FULLNAME");
                    String dbBankAccountNumber = resultSet.getString("BANKACCOUNTNUMBER");
                    String dbDateOfBirth = resultSet.getString("DATEOFBIRTH");
                    double dbWalletAmount = resultSet.getDouble("WALLETAMOUNT");

                    if (dbUsername.equalsIgnoreCase(username) && dbPassword.equalsIgnoreCase(password))
                    {
                        this.username = dbUsername;
                        this.password = dbPassword;
                        this.fullName = dbFullName;
                        this.bankAccountNumber = dbBankAccountNumber;
                        this.dateOfBirth = dbDateOfBirth;
                        this.acountWallet = new Wallet(dbWalletAmount);
                    }
                }
                resultSet.close();
                statement.close();
            } 

            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
            finally
            {
                if (statement != null)
                {
                    try 
                    {
                        statement.close();
                    } 
                    catch (SQLException ex) 
                    {
                        System.out.println("Error closing statement");
                    }
                }
            }
        }
    }
    
    // Method returns true if there is currently an existing account with same username/password in the database
    public boolean accountExists(String username, String password)
    {
       boolean accountExists = false;
       Statement statement = null;
        
        try {
            ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
            statement = shareversitydb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME, PASSWORD FROM ACCOUNT_TABLE");
            
            while (resultSet.next()) {  
                String dbUsername = resultSet.getString("USERNAME");
                String dbPassword = resultSet.getString("PASSWORD");

                if (dbUsername.equalsIgnoreCase(username) && dbPassword.equalsIgnoreCase(password))
                {
                    accountExists = true;
                }
            }
            resultSet.close();
            statement.close();
        } 
        
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        finally
        {
            if (statement != null)
            {
                try 
                {
                    statement.close();
                } 
                catch (SQLException ex) 
                {
                    System.out.println("Error closing statement");
                }
            }
        }
        
        return accountExists;
    }
    
    // Method that returns true if there is currently an existing account with same username in the database
    public boolean accountExists(String username)
    {
       boolean accountExists = false;
       Statement statement = null;
        
        try {
            ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
            statement = shareversitydb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT USERNAME, PASSWORD FROM ACCOUNT_TABLE");
            
            while (resultSet.next()) {  
                String dbUsername = resultSet.getString("USERNAME");
                String dbPassword = resultSet.getString("PASSWORD");

                if (dbUsername.equalsIgnoreCase(username))
                {
                    accountExists = true;
                }
            }
            resultSet.close();
            statement.close();
        } 
        
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        finally
        {
            if (statement != null)
            {
                try 
                {
                    statement.close();
                } 
                catch (SQLException ex) 
                {
                    System.out.println("Error closing statement");
                }
            }
        }
        
        return accountExists;
    }
    
    //toString that prints username, fullname, bankAccount, dateofBirth, and wallet balance
    @Override
    public String toString()
    {
        return "Username: " + username
                +"\nFull Name: " +fullName 
                +"\nBank Account Number: " + bankAccountNumber
                +"\nDate of Birth: " + dateOfBirth
                +"\nWallet balance: $" + acountWallet.getAccountBalance();
    }
    
}
    

