/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

/*
Each company in the text file will have: 
    1 name
    2 description
    3 CEO
    4 number of employees
    5 categories
    6 cost per share now
    7 shares avaliable
    8 investment type
    (( empty line separator )) >> next 8 line Company ...
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
        this.readAllCompanies(); // sets allCompanies HashMap
    }
    
    /**
     * Reads all company data from the specified file and populates the allCompanies HashMap.
     */
    public void readAllCompanies()
    {
        allCompanies = new HashMap<>();
        
        FileReader fileReader = null;
        try 
        {
            fileReader = new FileReader(companiesFilePath);
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(ImportedCompanies.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader inputStream = new BufferedReader(fileReader);
        String line;

        // Temporary variables
        String name = null;
        String description = null;
        String ceo = null;
        int numEmployees = 0;
        ArrayList<CategoriesEnum> categories = new ArrayList<>();
        CostPerShare cpsNow = null;
        String sharesAvaliable = null;
        InvestmentTypeEnum investment = null;

        int count = 0;

        try 
        {
            while((line=inputStream.readLine()) != null) // Read until end of file (null)
            {
                StringTokenizer str = new StringTokenizer(line);

                switch(count)
                {
                    case 0:
                        name = line;
                        break;
                    case 1:
                        description = line;
                        break;
                    case 2:
                        ceo = line;
                        break;
                    case 3:
                        numEmployees = Integer.valueOf(line);
                        break;
                    case 4:
                        while(str.hasMoreTokens())
                        {
                            String[] splitCategory = str.nextToken().split(" "); // read each category on line, split by spaces
                            for (String c : splitCategory)
                            {
                                categories.add(CategoriesEnum.valueOf(c));
                            }
                        }
                        break;
                    case 5:
                        cpsNow = new CostPerShare(Double.valueOf(line), LocalDate.now().format(Company.getFormatter()));
                        break;
                    case 6:
                        sharesAvaliable = line;
                        break;
                    case 7:
                        investment = InvestmentTypeEnum.valueOf(line);
                        break;
                }
                
                count++;
                
                if (count == 8) // When up to the final line, create new Company object from file info
                {
                    // Create new Company object from 8 read lines from txt file
                    Company company = new Company(name, description, ceo, numEmployees, categories, cpsNow, sharesAvaliable, investment);

                    // Add company into allCompanies HashMap
                    this.allCompanies.put(company, investment);

                    // resetting variables for next company (8 lines)
                    count = 0;
                    categories = new ArrayList<>();

                    inputStream.readLine(); // Read extra line -- empty line separator between companies
                }
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ImportedCompanies.class.getName()).log(Level.SEVERE, null, ex);
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

