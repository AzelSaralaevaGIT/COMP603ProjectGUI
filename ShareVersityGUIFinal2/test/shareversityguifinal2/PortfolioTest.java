package shareversityguifinal2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saral
 */
public class PortfolioTest {

    Account testAccount;
    ImportedCompanies testImportedCompanies;
    InvestmentType testLowRiskInvestments;
    CostPerShare testCPS;
    Investment testNewInvestment;
    
    public PortfolioTest() 
    {
    }
    
    public void setTestVariables()
    {
        testImportedCompanies = new ImportedCompanies();
        testLowRiskInvestments = new LowRiskInvestment(testImportedCompanies);
        
        // wallet balance $90
        // $10 invested into XYZ
        testAccount = new Account();
        testAccount.setLoginAccount("testAccount", "12345", testImportedCompanies); 
        
        testCPS = testAccount.getAccountPortfolio().getInvestments().get(0).getPurchaseCPS();
        testNewInvestment = new Investment(testLowRiskInvestments.companyList.get(0), 10.0, testCPS);
        
        testAccount.getAccountPortfolio().addInvestment(testNewInvestment);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addInvestment method, of class Portfolio.
     * Testing if an investment is added into an account portfolio investment arraylist by:
     * - checking if the investment is contained in the list after addMethod is performed
     * - checking if the account portfolio arraylist size has increased by 1
     */
    @Test
    public void testAddInvestment() {
        System.out.println("testAddInvestment(): Testing addInvestment");
        setTestVariables();
        int originalLength = testAccount.getAccountPortfolio().getInvestments().size();
        
        testAccount.getAccountPortfolio().addInvestment(testNewInvestment);
        
        System.out.println("testAddInvestment(): Testing if new added investment is contained in account portfolio");
        assertTrue(testAccount.getAccountPortfolio().getInvestments().contains(testNewInvestment));
        
        System.out.println("testAddInvestment(): Testing if account portfolio investments arraylist length is higher by 1");
        assertEquals((originalLength+1), testAccount.getAccountPortfolio().getInvestments().size());
    }

    /**
     * Test of sellAllShares method, of class Portfolio.
     * Testing if all shares of an investment is sold in an account portfolio investment arraylist by:
     * - checking if the investment is no longer contained in the list after sellAllShares is performed
     * - checking if the account portfolio arraylist size has decreased by 1
     */
    @Test
    public void testSellAllShares() {
        System.out.println("testSellAllShares(): testing sellAllShares (selling all shares of one investment)");
        setTestVariables();
        int originalLength = testAccount.getAccountPortfolio().getInvestments().size();
        
        testAccount.getAccountPortfolio().sellAllShares(testAccount, testNewInvestment);
        
        System.out.println("testSellAllShares(): Testing if sold investment is not contained in account portfolio");
        assertTrue(!(testAccount.getAccountPortfolio().getInvestments().contains(testNewInvestment)));
        
        System.out.println("");
        
        System.out.println("testAddInvestment(): Testing if account portfolio investments arraylist length is less than 1");
        assertEquals((originalLength-1), testAccount.getAccountPortfolio().getInvestments().size());
    }
}
