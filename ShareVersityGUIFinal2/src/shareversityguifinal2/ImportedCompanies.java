package shareversityguifinal2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is responsible for managing database connections and executing queries on the database. 
 * It provides methods to establish a connection to the Derby database, query the database, update the database, and close the connection.
 * 
 * @author Fran
 */

//Class responsible for reading and managing imported company data from a file
public final class ImportedCompanies
{   
    
    private String companiesFilePath = "./resources/Companies.txt";
    private HashMap<Company, InvestmentTypeEnum> allCompanies; 
    
    /**
     * Initializes the ImportedCompanies object and reads all company data from the file.
     */
    public ImportedCompanies() 
    {
        this.retrieveAllCompanies(); // sets allCompanies HashMap
    }
    
    // read companies table
    // Retrieve all companies from the database
    public void retrieveAllCompanies() 
    {
        allCompanies = new HashMap<>();
        Statement statement = null;
        
        try {
        ShareVersityDatabase shareversitydb = new ShareVersityDatabase();

            statement = shareversitydb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COMPANIES");
            
            while (resultSet.next()) {
                String companyName = resultSet.getString("COMPANYNAME");
                String companyDescription = resultSet.getString("COMPANYDESCRIPTION");
                String ceo = resultSet.getString("CEO");
                int numEmployees = resultSet.getInt("NUM_EMPLOYEES");

                String[] parts = resultSet.getString("COMPANY_CATEGORIES").split(" "); // Get company categories as split string
                ArrayList<String> categoriesStringList = new ArrayList<>(Arrays.asList(parts)); // Then save categories as String arrayList
                
                // Finally save string categories as the value of the corresponding CategoriesEnum by name (valueOf)
                ArrayList<CategoriesEnum> categoriesEnumList = new ArrayList<>();
                for (String category : categoriesStringList)
                {
                    categoriesEnumList.add(CategoriesEnum.valueOf(category.toUpperCase())); 
                }

                InvestmentTypeEnum investmentType = InvestmentTypeEnum.valueOf(resultSet.getString("INVESTMENT_TYPES").toUpperCase()); // Save investment type String as the InvestmentTypeEnum by name (valueOf)
                double costPerShareNow = resultSet.getDouble("COST_PER_SHARE_NOW");

                // Create a Company object with the retrieved data
                Company company = new Company(companyName, companyDescription, ceo, numEmployees, categoriesEnumList, costPerShareNow, investmentType);
                
                // Add the company to allCompanies hashmap
                allCompanies.put(company, investmentType);
            }
            resultSet.close();
            statement.close();
            shareversitydb.getConnection().close();
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
    
    /**
     * Gets the HashMap containing all imported companies and their investment types.
     *
     * @return The HashMap of companies and their investment types.
     */
    public HashMap<Company, InvestmentTypeEnum> getAllCompanies() 
    {
        return allCompanies;
    }
}

