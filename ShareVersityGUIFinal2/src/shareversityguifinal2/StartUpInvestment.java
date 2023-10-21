/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

/**
 * Represents a type of investment focused on startup companies.
 * Extends the InvestmentType class.
 */
import java.util.ArrayList;
import java.util.Map;


public class StartUpInvestment extends InvestmentType
{

    public StartUpInvestment(ImportedCompanies allCompanies) 
    {
        super(allCompanies);
    }
    
 /**
 * Overrides the method in InvestmentType to filter and retrieve startup companies.
 * Iterates through the InvestmentType hashmap and adds startup companies to a new ArrayList.
 *
 * @param importedCompanies The ImportedCompanies object containing all companies and their investment types.
 * @return An ArrayList of Company objects representing startup companies.
 */
     @Override
    public ArrayList<Company> setCompanyList(ImportedCompanies importedCompanies) 
    {
        ArrayList<Company> startUp = new ArrayList<>();
        
        for(Map.Entry<Company, InvestmentTypeEnum> entry: importedCompanies.getAllCompanies().entrySet())
        {
            if(entry.getValue() == InvestmentTypeEnum.STARTUP)
            {
                startUp.add(entry.getKey());
            }
        }
        // Set the companyList 
        this.companyList = startUp;
        
        return startUp;
    }

}
