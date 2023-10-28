/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */

//Class responsible for reading and managing imported company data from a file
public final class ImportedCompanies
{   
    
    private String companiesFilePath = "./resources/Companies.txt";
    private HashMap<Company, InvestmentTypeEnum> allCompanies; 
    ShareVersityDatabase shareversitydb;
    int count = 0;
    
    /**
     * Initializes the ImportedCompanies object and reads all company data from the file.
     */
    public ImportedCompanies() 
    {
        shareversitydb = new ShareVersityDatabase();
        this.retrieveAllCompanies(); // sets allCompanies HashMap
    }
    
    // read companies table
    // Retrieve all companies from the database
    public void retrieveAllCompanies() 
    {
        allCompanies = new HashMap<>();
        
        try {
            Statement statement = shareversitydb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COMPANIES");
            
            while (resultSet.next()) {
                count++;
                String companyName = resultSet.getString("COMPANYNAME");
                String companyDescription = resultSet.getString("COMPANYDESCRIPTION");
                String ceo = resultSet.getString("CEO");
                int numEmployees = resultSet.getInt("NUM_EMPLOYEES");

                String[] parts = resultSet.getString("COMPANY_CATEGORIES").split(" ");
                ArrayList<String> categoriesStringList = new ArrayList<>(Arrays.asList(parts));
                
                ArrayList<CategoriesEnum> categoriesEnumList = new ArrayList<>();
                for (String category : categoriesStringList)
                {
                    categoriesEnumList.add(CategoriesEnum.valueOf(category.toUpperCase()));
                }

                InvestmentTypeEnum investmentType = InvestmentTypeEnum.valueOf(resultSet.getString("INVESTMENT_TYPES").toUpperCase());
                double costPerShareNow = resultSet.getDouble("COST_PER_SHARE_NOW");

                // Create a Company object with the retrieved data
                Company company = new Company(companyName, companyDescription, ceo, numEmployees, categoriesEnumList, costPerShareNow, investmentType);
                
                // Add the company to allCompanies hashmap
                allCompanies.put(company, investmentType);
            }
            resultSet.close();
            statement.close();
        } 
        
        catch (SQLException ex) 
        {
            ex.printStackTrace();
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

