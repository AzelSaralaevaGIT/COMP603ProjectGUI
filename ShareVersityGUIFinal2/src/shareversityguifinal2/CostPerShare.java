package shareversityguifinal2;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class represents the cost per share (CPS) of a company at a specific date.
 */
public class CostPerShare
{
    /**private instance variables to ensure encapsulation, retrieved via getters**/
     
    private static final LocalDate today = LocalDate.now();
    
    private String date;
    private double cost;

    /**
     * Constructs a CostPerShare object with the given cost and date.
     *
     * @param cost The cost of one share.
     * @param date The date when the cost per share was recorded.
     */
    public CostPerShare(double cost, String date) 
    {
        this.cost = cost;
        this.date = date;
    }

    /**
     * Gets the date when the cost per share was recorded.
     *
     * @return The date as a string.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date when the cost per share was recorded.
     *
     * @param date The date to set.
     */
    
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the cost per share.
     *
     * @return The cost per share as a double.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost per share.
     *
     * @param cost The cost per share to set.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    
    //this method computes how many days ago from current day the CPS (Cost Per Share) exists
    // @return The number of days ago as an integer
    public int getDaysAgo() 
    {
        LocalDate cpsDate = LocalDate.parse(this.date, Company.getFormatter());
        
        Period period = Period.between(cpsDate, today); // gets number of days between cost per share date and today's date
        
        return period.getDays();
    }

    /**
     * Returns a string representation of the CostPerShare object with the date and cost.
     *
     * @return A string in the format "date: $cost".
     */
    @Override
    public String toString() 
    {
        return String.format("%s: $%.2f", date, cost);
    }
}