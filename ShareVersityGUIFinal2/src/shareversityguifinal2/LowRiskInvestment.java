package shareversityguifinal2;

import java.util.ArrayList;
import java.util.Map;

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
    
    
      

    
    

