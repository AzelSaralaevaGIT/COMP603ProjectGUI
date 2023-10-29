package shareversityguifinal2;

/**
 * The Investment class represents an investment in a specific company's shares.
 * It calculates and stores various financial stats related to the investment, 
 * such as profit and return on investment
 * 
 * This class provides methods for computing and accessing these financial stats.
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
    private double value;

    public Investment(Company companyInvested, double amountInvested, CostPerShare purchaseCPS) 
    {
        this.companyInvested = companyInvested;
        this.amountInvested = amountInvested;
        this.purchaseCPS = purchaseCPS;
        this.currentCPS = companyInvested.getCurrentCPS();
        
        // Compute these instance variables from input
        this.computeNumShares();
        this.computeProfit();
        this.computeROI();
        this.computeValue();
    }
    
    // Computed Investment stats 
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

    // Getters
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

    // Setters
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

    // toString (for debugging)
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
        out += String.format(">> Value: $%.2f <<\n", this.value);
        
        return out;
    }
}
