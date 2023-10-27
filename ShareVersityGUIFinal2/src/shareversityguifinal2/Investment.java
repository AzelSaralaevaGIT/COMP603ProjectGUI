/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

/**
 *
 * @author Fran
 */
public class Investment 
{
    private Company companyInvested;
    private double amountInvested;
    private double numShares;
    private CostPerShare purchaseCPS;
    private CostPerShare currentCPS;
    private double profit;
    private double returnOnInvestment;
    private double historicalValue; // value before withdrawals
    private double value;

    public Investment(Company companyInvested, double amountInvested, CostPerShare purchaseCPS) 
    {
        this.companyInvested = companyInvested;
        this.amountInvested = amountInvested;
        this.purchaseCPS = purchaseCPS;
        this.currentCPS = companyInvested.getCurrentCPS();
        this.computeNumShares();
        this.computeProfit();
        this.computeROI();
        this.computeValue();
        this.historicalValue = value;
    }
    
    public final void computeNumShares()
    {
        this.numShares = this.amountInvested / this.purchaseCPS.getCost();
    }
    
    public final void computeProfit()
    {
        this.profit = ((this.currentCPS.getCost()*this.numShares)-(this.purchaseCPS.getCost()*this.numShares));
    }
    
    public final void computeValue()
    {
        this.value = (this.profit + this.amountInvested);
    }
    
    public final void computeROI()
    {
        this.returnOnInvestment = this.profit/this.amountInvested;
    }

    public Company getCompanyInvested() {
        return companyInvested;
    }
    
    public double getAmountInvested() 
    {
        return amountInvested;
    }
    
    public double getProfit() 
    {
        return profit;
    }

    public double getValue() 
    {
        return value;
    }

    public double getHistoricalValue() 
    {
        return historicalValue;
    }

    public double getNumShares() 
    {
        return numShares;
    }

    public double getReturnOnInvestment() 
    {
        return returnOnInvestment;
    }

    public CostPerShare getCurrentCPS() {
        return currentCPS;
    }

    public CostPerShare getPurchaseCPS() {
        return purchaseCPS;
    }

    public void setAmountInvested(double amountInvested) 
    {
        this.amountInvested = amountInvested;
    }

    public void setNumShares(double numShares) 
    {
        this.numShares = numShares;
    }

    public void setValue(double value) 
    {
        this.value = value;
    }

    public void setCompanyInvested(Company companyInvested) {
        this.companyInvested = companyInvested;
    }

    @Override
    public String toString() 
    {
        String out = "";
        
        out += "Company invested: "+this.companyInvested.getName() + "\n";
        out += "Purchase CPS:  "+this.purchaseCPS + "\n";
        out += "Current CPS:   "+this.companyInvested.getCurrentCPS() + "\n";
        out += String.format("Number of shares: %.2f \n", this.numShares);
        out += "Amount invested: $"+this.amountInvested + "\n";
        out += String.format("Profit/Loss: $%.2f \n", this.profit);
        out += String.format("Return on Investment: %.4f%% \n", this.returnOnInvestment);
        out += String.format("Historical value: $%.2f \n", this.historicalValue);
        out += String.format(">> Value: $%.2f <<\n", this.value);
        
        return out;
    }
    
    public static void main(String[] args) 
    {
        /*
        Company apple = new Company();
        
        Investment investment1 = new Investment(apple, 900.0, apple.getCostPerShareHistory().get(30));
        
        System.out.println(apple.printCompanyGraph());
        
        System.out.println(investment1);*/
        
    }
}
