/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
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
    
    /** 
     * Account constructor for retrieving account information saved in txt file from Username and Password
     * @param username
     * @param password
     */
    public Account(String username, String password)  
    {
       retrieveAccountFromFile(username,password); 
       this.accountPortfolio = new Portfolio(this); 
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
    
    // Method that saves user's entered information onto txtfile "AccountInfo.txt" 
    // Or will update if the account already ecists 
    
    public void saveAccountToFile()
    {
        boolean accountFound = false;

        try 
        {
            BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));
            StringBuilder fileContents = new StringBuilder(); // StringBuilder to save each line of file contents 

            String line; // read from Portfolio txt file
            
            // Generate the line to add based on Account object variables in the following order
            String accountInfo = String.format("%s,%s,%s,%s,%s,%s", username, password,fullName, bankAccountNumber, dateOfBirth, String.valueOf(acountWallet.getAccountBalance()));
            
// While reading lines from the file, this loop iterates through each line:
// - It splits the line into parts using a comma as the delimiter.
// - It checks if the first part (parts[0]) equals the username associated with the current account and if there are exactly 6 parts in the line.
//   If both conditions are met, it means that the line corresponds to the account we want to update.
//   In this case, it sets the 'accountFound' flag to true and replaces the line with the updated 'accountInfo' in the 'fileContents' StringBuilder.
// - If the line doesn't match the account information being updated, it appends the line to the 'fileContents' StringBuilder to keep it in the updated file.
//   This ensures that the rest of the file remains unchanged.
            while((line = fileReader.readLine()) != null)
            {
                String[] parts = line.split(",");
                
                if(parts[0].equals(this.username) && parts.length == 6)
                {
                    accountFound = true;
                    
                    fileContents.append(accountInfo).append("\n"); // Overwrite line of file with matching Account username
                }
                
                else
                {
                    fileContents.append(line).append("\n"); // Append rest of file to stringbuilder
                }
            }
            fileReader.close();
            
            fileContents.append("\n");
            accountInfo += "\n";
            
            if (accountFound) // If account exists, overwrite the file with the updated line 
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(accountFilePath));
                bufferedWriter.write(fileContents.toString());
                bufferedWriter.close();
            }
            if (accountFound == false) // If account doesn't exist, append to file instead of overwriting 
            {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(accountFilePath, true));
                bufferedWriter.write(accountInfo);
                bufferedWriter.close();
            }
        }
        catch (IOException ex) 
        {
            System.err.println("IOException Error: " + ex.getMessage());
        }
    }
    
    //Method that retrieves information from txt file "AccountInfo.txt"
    //when logging in, users full name entered which retrieves Account Info
    //from AccountInfo.txt file
    //Acts as setter
    public final void retrieveAccountFromFile(String username, String password)  
    {  
        // Check if the account exists in the file with the given username and password.
       if (checkAccountExists(username, password) == true)
       {
            try
            {
                // Initialize a file reader to read from the accountInfo text file. 
                BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));

                String line; // reads from accountInfo txt file
                
                //iterate through each line in the file
                while((line = fileReader.readLine()) != null)
                {
                    String [] parts = line.split(",");
                     // Check if the line has exactly 6 parts and matches the provided username and password.
                    if(parts.length == 6 && parts.length == 6 && username.equals(parts[0]) && password.equals(password))
                    {
                        //checking if user name and password match set account information
                        if(parts.length == 6 && username.equals(parts[0]) && password.equals(password))
                        {
                            // Set the account information based on the data from the matching line.
                            this.username = parts[0].trim();
                            this.password = parts[1].trim();
                            this.fullName = parts[2].trim();
                            this.bankAccountNumber = parts[3].trim();
                            this.dateOfBirth = parts[4].trim();
                            this.acountWallet = new Wallet(Double.valueOf(parts[5].trim()));
                        }
                    }
                }
            } 
            catch (FileNotFoundException ex) // Exception handling
            {
                 Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }

            catch (IOException ex) //Exception handling
            {
                 Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
    //Method that returns true if there is currently an existing account with same username/password
    public boolean checkAccountExists(String username, String password)
    {
       boolean accountExists = false;
        
       try
       {
           BufferedReader fileReader = new BufferedReader(new FileReader(accountFilePath));
           
           String line; 
           
           while((line = fileReader.readLine()) != null)
           {
               String [] parts = line.split(",");
               if(parts.length == 6 && username.equals(parts[0]) && password.equals(password))
               {   
                    accountExists = true; 
               }
           }
       } 
       catch (FileNotFoundException ex) 
       {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       catch (IOException ex) 
       {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
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
    

