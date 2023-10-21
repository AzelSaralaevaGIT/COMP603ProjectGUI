/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

     /*
        Represents a wallet associated with a user account, allowing top-up and withdrawal of funds.
     */

public class Wallet
{
    private double accountBalance;
   
    public Wallet()
    {
        this.accountBalance = 0.0;
    }
    
    /**
     * Constructs an empty wallet with zero account balance.
     */
    public Wallet(double accountBalance)
    {
        this.accountBalance = accountBalance;
    }

    /*
        This method tops up (adds funds to) the Wallet balance if it passes the negativeTopUp check
    */
    public void topUp(double amount)
    {
        if (this.checkNegativeTopUp(amount) == false)
        {
            this.accountBalance += amount;
        }
    }
    
    /*
        This method withdraws (removes funds) from the Wallet balance if it passes the checkOverWithdraw check
    */
    public void withdraw(double amount)
    {
        if(this.checkOverWithdraw(amount) == false)
        {
            this.accountBalance -= amount;
        }
    }
    
    /*
        This method returns a boolean checking if the topped up amount is negative
    */
    public boolean checkNegativeTopUp(double amount)
    {
        boolean check = true;
        if (amount >= 0)
        {
            check = false;
        }
        return check;
    }
    
    /*
        This method returns a boolean checking if the withdraw amount is greater than the Wallet accountBalance
        also checks if inputted amount is negative
    */
    public boolean checkOverWithdraw(double amount)
    {
        boolean check = true;
        if(this.accountBalance-amount >= 0 && amount >= 0)
        {
            check = false;
        }
        return check;
    }

    /**
     * Gets the current account balance.
     *
     * @return The current account balance as a double.
     */
    public double getAccountBalance() 
    {
        return accountBalance;
    }
    
    /**
     * Returns a string representation of the wallet, showing the current account balance.
     *
     * @return A string in the format "Current Account Balance is: $accountBalance".
     */
    @Override
    public String toString()
    {
        return "\nCurrent Account Balance is: $" + this.accountBalance;
    }
}
