package shareversityguifinal2;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents a specific type of investment, "Low Risk," 
 * It extends the abstract `InvestmentType` class and is responsible for filtering and managing low-risk investment companies 
 * based on the `InvestmentTypeEnum` enumeration. It provides a list of low-risk companies and sets the `companyList` for this type of investment.
 * 
 * @author saral
 */

public class LowRiskInvestment extends InvestmentType
{
    public LowRiskInvestment(ImportedCompanies allCompanies) 
    {
        super(allCompanies);
    }

    //method that iterates through the InvestmentType hashmap, filters for Lowrisk enums only
    //adds to new arrayList of type lowrisk
    //returns arrayList with Lowrisk  companies only
    @Override
    public ArrayList<Company> setCompanyList(ImportedCompanies importedCompanies) 
    {
        ArrayList<Company> lowRisk = new ArrayList<>();
        
        for(Map.Entry<Company, InvestmentTypeEnum> entry: importedCompanies.getAllCompanies().entrySet())
        {
            if(entry.getValue() == InvestmentTypeEnum.LOWRISK)
            {
                lowRisk.add(entry.getKey());
            }
        }
        // Set the companyList 
        this.companyList = lowRisk;
        
        return lowRisk;
    }
    
    public static void main(String[] args) {
        ImportedCompanies i = new ImportedCompanies();
        
        InvestmentType lowRiskInvestments = new LowRiskInvestment(i);
        
        System.out.println(lowRiskInvestments); 
        
        System.out.println(lowRiskInvestments.companyList.get(0));
    }
}