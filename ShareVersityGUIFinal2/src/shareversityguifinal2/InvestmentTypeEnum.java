package shareversityguifinal2;

//Enumeration representing different types of investments
public enum InvestmentTypeEnum 
{
    LOWRISK("Low Risk", 0.05), 
    STARTUP("Startup", 0.2);
    
    private final String name; // Name of investment type
    private final double volatility; //Asscoaited Volatility
    
    //constructor for InvestmentTypeEnum
    private InvestmentTypeEnum(String name, double volatility) 
    {
        this.name = name;
        this.volatility = volatility;
    }

    public String getName() 
    {
        return name;
    }

    public double getVolatility()
    {
        return volatility;
    }
    
    /* returns a string representing the investment type*/
    @Override
    public String toString() 
    {
        return "Investment Type: " + getName();
    }
}
