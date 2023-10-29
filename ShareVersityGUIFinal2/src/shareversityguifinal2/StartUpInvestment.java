package shareversityguifinal2;

/**
 * The `StartUpInvestment` class represents a specific type of investment, "Startup," 
 * It extends the abstract `InvestmentType` class and is responsible for filtering and managing startup investment 
 * companies based on the `InvestmentTypeEnum` enumeration. It provides a list of startup companies and sets the `companyList` for this type of investment.
 * 
 * @author saral
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
