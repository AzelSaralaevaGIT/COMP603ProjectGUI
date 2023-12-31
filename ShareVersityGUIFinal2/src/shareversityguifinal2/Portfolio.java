package shareversityguifinal2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author saral
 */

/**
 *  The Portfolio class represents an investment portfolio for a ShareVersity user account.
 *  It manages a list of investments in various companies, calculates portfolio statistics,
 *  and provides methods for buying, selling, and updating investments.
 * 
 *  It interacts with a database to retrieve and save investments for existing accounts,
 *  and it also performs various computations related to the portfolio's value, profit, return on investment, etc.
 * 
 *  This class includes methods to:
 *      - Retrieve investments from the database for existing accounts.
 *      - Save portfolio investments to the database.
 *      - Update portfolio statistics (total value, total invested, total profit, return on investment).
 *      - Add, remove, and sell investments in the portfolio.
 *      - Print information about the portfolio and its investments.
 * */
public class Portfolio
{
    private final String portfolioFilePath = "./resources/Portfolio.txt";
    
    private ArrayList<Investment> investments;
    private double totalReturnOnInvestment;
    private double totalValue;
    //private double totalHistoricalValue;
    private double totalInvested;
    private double totalProfit;
    
    // If account is new, there are no invested companies
    public Portfolio() 
    {
        this.investments = new ArrayList<>();
        this.totalValue = 0.0;
        //this.totalHistoricalValue = 0.0;
        this.totalInvested = 0.0;
        this.totalProfit = 0.0;
        this.totalReturnOnInvestment = 0.0;
    }
    
    // If account has existing investments
    public Portfolio(Account account, ImportedCompanies importedCompanies) 
    {
        this.investments = retrieveInvestmentsFromDB(account, importedCompanies);
        computeTotalValue();
        //computeTotalHistoricalValue();
        computeTotalInvested();
        computeTotalProfit();
        computeTotalReturnOnInvestment();
    }
    
    /*
        This method retrieves an array of Investment objects assigned to an Account username from the database
        to be used in creating the porfolio of the Account
    */
    public final ArrayList<Investment> retrieveInvestmentsFromDB(Account account, ImportedCompanies importedCompanies)
    {
        ArrayList<Investment> investmentsFromDB = new ArrayList<>();
        Statement statement = null;

        try {
            ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
            statement = shareversitydb.getConnection().createStatement();
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM INVESTMENT");

            while (resultSet.next()) { //USERNAME VARCHAR(50),COMPANYNAME VARCHAR(50), AMOUNT_INVESTED DOUBLE, PURCHASE_CPS DOUBLE
                String dbUsername = resultSet.getString("USERNAME");
                
                if (dbUsername.equalsIgnoreCase(account.getUsername()))
                {
                    String dbCompanyName = resultSet.getString("COMPANYNAME");
                    double dbAmountInvested = resultSet.getDouble("AMOUNT_INVESTED");
                    int dbPurchaseCPSindex = resultSet.getInt("PURCHASE_CPS_INDEX");

                    for (Company company : importedCompanies.getAllCompanies().keySet())
                    {
                        if (dbCompanyName.equals(company.getName()))
                        {
                            investmentsFromDB.add(new Investment(company, dbAmountInvested, company.getCostPerShareHistory().get(dbPurchaseCPSindex)));
                        }
                    }
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
    
        return investmentsFromDB;
    }
    
    /*
        This method Overwrites or appends an account's portfolio investments to the database
    */
    public void saveInvestmentsToDB(Account account)
    {
        Statement statement = null;
        try 
        {  
            ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
            statement = shareversitydb.getConnection().createStatement();

            String clearAccountInvestments = "DELETE FROM INVESTMENT WHERE UPPER(USERNAME) = UPPER('"+account.getUsername()+"')";
            statement.executeUpdate(clearAccountInvestments);
            
            for (Investment investment : account.getAccountPortfolio().getInvestments())
            {
                String insertInvestments = "INSERT INTO INVESTMENT (USERNAME, COMPANYNAME, AMOUNT_INVESTED, PURCHASE_CPS_INDEX)" +
                " VALUES ('" + account.getUsername() + "', '" + investment.getCompanyInvested().getName() + "', " + investment.getAmountInvested() + ", " + investment.getPurchaseCPS().getDaysAgo() + ")";
                statement.executeUpdate(insertInvestments);
            }
            System.out.println("Investments inserted successfully.");
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

    public ArrayList<Investment> getInvestments() 
    {
        return investments;
    }
    
    public void updatePortfolioStats()
    {
        // reset values to 0
        this.totalInvested = 0.0;
        this.totalValue = 0.0;
        //this.totalHistoricalValue = 0.0;
        this.totalProfit = 0.0;
        this.totalReturnOnInvestment = 0.0;
        
        // recompute values
        this.computeTotalInvested();
        this.computeTotalValue();
        //this.computeTotalHistoricalValue();
        this.computeTotalProfit();
        this.computeTotalReturnOnInvestment();
    }
    
    // add new investment transaction to user portfolio
    public void addInvestment(Investment newInvestment)
    {
        this.investments.add(newInvestment);
        this.updatePortfolioStats(); // Adding an investment requires the updating of totalValue, totalHistoricalValue, totalInvested, totalProfit, and totalReturnOnInvestment
    }
    
    // Sum of value 
    public final void computeTotalValue()
    {
        for (Investment i : investments)
        {
            this.totalValue += i.getValue();
        }
    }
    
    // Sum of all invested amounts
    public final void computeTotalInvested()
    {
        for (Investment i : investments)
        {
            this.totalInvested += i.getAmountInvested();
        }
    }
    
    // Sum of all profits/losses
    public final void computeTotalProfit()
    {
        for (Investment i : investments)
        {
            this.totalProfit += i.getProfit();
        }
    }
    
    // Sum of all investment ROIs
    public final void computeTotalReturnOnInvestment()
    {
        double totalAmountInvested = 0.0;
        
        for (Investment i : investments)
        {
            totalAmountInvested += i.getAmountInvested();
        }
        
        this.totalReturnOnInvestment = this.totalProfit / totalAmountInvested;
    }
    
    public String printInvestedCompanies()
    {
        String out = "";
        
        int count = 0;
        for (Investment investment : investments)
        {
            count++; 
            out += String.format("//====== INVESTMENT #%d ======\\\\\n", count);
            out += investment + "\n";
        }
        
        return out;
    }
    
    public String printAnInvestedCompany(int index)
    {
        return String.format("//====== INVESTMENT ======\\\\\n%s", investments.get(index).toString());
    }
    
    @Override
    public String toString() 
    {
        String out = "";
        
        out += "//===== PORTFOLIO STATS =====\\\\\n";
        out += String.format("Total amount invested:  $%.2f \n", this.totalInvested);
        out += String.format("Total profit:           $%.2f \n", this.totalProfit);
        out += String.format("Total ROI:              %.4f%%\n", this.totalReturnOnInvestment);
        //out += String.format("Total historical value: $%.2f \n", this.totalHistoricalValue);
        out += String.format(">>   Total value: $%.2f   <<", this.totalValue);
        
        return out;
    }
    
    /*
        This method checks if the inputted amount is less than or equal to investment value to sell to prevent negative investment value
        Also checks if the inputted amount is negative
    */
    public boolean checkInvestmentOverSell(double amount, Account account, int investmentIndex)
    {
        boolean check = true;
        double investmentValue = account.getAccountPortfolio().getInvestments().get(investmentIndex).getValue();
        if (amount <= investmentValue && amount > 0) // Sell shares if the value of investment is greater than amount to be sold AND check if amount is not negative
        {
            check = false;
        }
        
        return check;
    }
    
    /*
        This method sells (removes value and tops up wallet) the selected Account Portfolio's Investment value if it passes the checkInvestmentOverSell check
    */
    public void sellShares(double amount, Account account, int investmentIndex)
    {
        if (checkInvestmentOverSell(amount, account, investmentIndex) == false) 
        {
            double investmentValue = account.getAccountPortfolio().getInvestments().get(investmentIndex).getValue();
            account.getAccountPortfolio().getInvestments().get(investmentIndex).setValue(investmentValue - amount);
            account.getWallet().topUp(amount);
        }
    };
    
    public void sellAllShares(Account account, Investment investmentToRemove)
    {
        account.getAccountPortfolio().getInvestments().remove(investmentToRemove);
        account.getWallet().topUp(investmentToRemove.getValue());
        account.getAccountPortfolio().updatePortfolioStats();
    };
    
    // Getters

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotalReturnOnInvestment() {
        return totalReturnOnInvestment;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public double getTotalInvested() {
        return totalInvested;
    }
}