package shareversityguifinal2;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; 
import java.util.Random;

/*
This class holds information for the Company name, description, CEO, number of 
employees, company categories, shares avaliable, current cost per share, and cost per share history.
*/

public class Company
{
    //private instance variables to ensure encapsulation, retrieved via their getters
    private static final LocalDate today = LocalDate.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // date formatter
    private double spVolatility; // Volatility of the share price (percentage)
    
    private String name; //name of Company
    private String description; //description of Company
    private String ceo; //current Company CEO 
    private int numEmployees; //number of Employees in Company
    private ArrayList<CategoriesEnum> companyCategories;
    private String sharesAvaliable; //available shares on stock market
    private CostPerShare costPerShareNow; //current cost per share
    private ArrayList<CostPerShare> costPerShareHistory;
    
    private double maxPrice = 0.0;; // maximum share price is used to scale the graph
    private ArrayList<String> graphLines; // graph to be generated
    int maxLine; // max printing length
    
    private Random random;
   
   /**
 * Initializes a new Company with the provided information.
 *
 * @param name              The name of the company.
 * @param desc              A brief description of the company.
 * @param ceo               The CEO of the company.
 * @param numEmp            The number of employees in the company.
 * @param companyCategories The categories to which the company belongs.
 * @param costPerShareNow   The current cost per share.
 * @param sharesAvailable   The number of shares available for trading.
 * @param investmentType    The type of investment associated with the company.
 */
    public Company(String name, String desc, String ceo, int numEmp, ArrayList<CategoriesEnum> companyCategories, CostPerShare costPerShareNow, String sharesAvaliable, InvestmentTypeEnum investmentType) 
    {
        this.name = name;
        this.description = desc;
        this.ceo = ceo;
        this.numEmployees = numEmp;
        this.companyCategories = companyCategories;
        this.sharesAvaliable = sharesAvaliable;
        this.costPerShareNow = costPerShareNow;
        this.spVolatility = investmentType.getVolatility();
        
        this.random = new Random();
        this.costPerShareHistory = generateSharePrices(costPerShareNow.getCost(), 31);
        
        this.maxPrice = computeMaxPrice();
        this.graphLines = generateGraph(costPerShareHistory, 31);
        this.maxLine = computeMaxPrintingLength();
    }
    
    // Getters
    
    public String getName() 
    {
        return name;
    }

    public String getCeo() 
    {
        return ceo;
    }

    public String getDescription() 
    {
        return description;
    }

    public int getNumEmployees() 
    {
        return numEmployees;
    }
/**
 * Retrieves the list of categories to which the company belongs.
 *
 * @return An ArrayList of CategoriesEnum representing the company's categories.
 */
    public ArrayList<CategoriesEnum> getCompanyCategories() 
    {
        return companyCategories;
    }

    public CostPerShare getCostPerShareNow() 
    {
        return costPerShareNow;
    }

    public String getSharesAvaliable() 
    {
        return sharesAvaliable;
    }
    
    public CostPerShare getCurrentCPS() 
    {
        return this.costPerShareHistory.get(0);
    }
    
    public ArrayList<CostPerShare> getCostPerShareHistory()
    {
        return costPerShareHistory;
    }

    public static DateTimeFormatter getFormatter() 
    {
        return formatter;
    }
    
    // Setters
    
 /**
 * Computes and returns the maximum historical share price of the company.
 *
 * @return The maximum historical share price.
 */
    public final double computeMaxPrice()
    {
        double max = 0.0;
        
        for (int i=costPerShareHistory.size(); i-- > 0;)
        {
            if (costPerShareHistory.get(i).getCost() > max)
            {
                max = costPerShareHistory.get(i).getCost();
            }
        }
        
        return max;
    }
    
    // This method randomly generates the share price history of the company over the past inputted number of days 
    public final ArrayList<CostPerShare> generateSharePrices(double currentPrice, int numDays)
    {
        ArrayList<CostPerShare> cpsh = new ArrayList<>();
        
        // Loops from today's date backwards to number of inputted days 
        for (LocalDate date = today; date.isAfter(today.minusDays(numDays)); date = date.minusDays(1))
        {
            double randomFactor = 1.0 + (random.nextDouble() - 0.5) * 2.0 * spVolatility; // randomFactor determines how drastic each cost per share changes per date
            currentPrice = (date == today ? currentPrice : currentPrice * randomFactor); // ensures todays date equals the current price
            cpsh.add(new CostPerShare(currentPrice, date.format(formatter)));
        }
        return cpsh;
    } 
    
    // This method generates each line of the cost per share graph as an ArrayList,
    // making it easier to enclose the graph into a table
    public final ArrayList<String> generateGraph(ArrayList<CostPerShare> costPerShareHistory, int numDays)
    {
        ArrayList<String> graph = new ArrayList<>();
        String todayIndicator = " <-- Today"; //indicates current day on graph
        
        double scaleFactor = 70.0 / maxPrice;
        
        for (int i=costPerShareHistory.size(), count=0; i-- > 0 && count++ < costPerShareHistory.size();) 
        {
            String out = "";
            out += " " + (count) + (count<10?"  ":" "); // Print day numbering
            out += String.format("%s | $%.2f | ", costPerShareHistory.get(i).getDate(), costPerShareHistory.get(i).getCost()); // Print date and cost per share
            int costLength = (int)(costPerShareHistory.get(i).getCost() * scaleFactor); // determine length of graph based on scaleFactor
            for (int k=0; k<costLength; k++){out += ":";} // Print graph
            out += (count == numDays ? todayIndicator:""); // Print today indicator if count is the inputted day
            graph.add(out);
        }
        
        return graph;
    }
    
    // this method computes the maximum printing length for the graph and description
    private int computeMaxPrintingLength()
    {
        int max = 0;
        
        for (String line : graphLines)
        {
            max = (line.length() > max ? line.length() : max);
        } 
        max += 3;
        
        return max;
    }
    
    //this method returns a String with Company information printed
    public String printCompanyInfo()
    {
        String out = ""; // String that is appended and returned at the end of the toString
        
        out += "COMPANY NAME: " + name + "\n\n";
        
        // find every instance of space, newline from the space before the word exceeding maxLine
        int countChar = 0;
        for (int descChar=0; descChar < description.length(); descChar++)
        {
            out += description.charAt(descChar);
            if(countChar >= maxLine && description.charAt(descChar) == ' ')
            {
                out += "\n";
                countChar = 0;
            }
            countChar++;
        }
        
        out += "\n\nCEO: " + ceo + "\n" +
               "NUMBER OF EMPLOYEES: " + String.format("%,d", numEmployees) + "\n" + 
               "CATEGORIES: "; 

        for (int i=0; i<companyCategories.size(); i++)
        {
            out += companyCategories.get(i).getName();
            out += (i == companyCategories.size()-1 ? "" : ", ");
        }
        
        out += "\nSHARES AVAILABLE: " + sharesAvaliable + "\n" + 
               "COST PER SHARE: " + costPerShareNow;
        
        return out;
    }
    
    //this method returns a String and prints the Company Graph 
    public String printCompanyGraph()
    {
        String out = ""; // String that is appended and returned at the end of the toString
        
        String headers = "|    Day        | Price   |  Graph";
        
        for(int i=0; i<maxLine; i++){out += '=';}
        out += "\n"+headers;
        for(int i=0; i<maxLine-headers.length()-1; i++){out += ' ';}
        out += "|\n";
        for(int i=0; i<maxLine; i++){out += '=';}
        out += "\n";
        
        for(String line : graphLines)
        {
         out += "|" + line;
         for (int i=0; i<maxLine-line.length()-2; i++){out += " ";}
         out += "|\n";
        }
        
        for(int i=0; i<maxLine; i++){out += '=';}
        
        return out;
    }
    
    // This toString prints the company information and cost per share history graph
    @Override
    public String toString() 
    {
        return printCompanyInfo() + "\n\n" + printCompanyGraph();
    }
}