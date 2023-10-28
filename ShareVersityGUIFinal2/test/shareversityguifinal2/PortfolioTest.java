/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package shareversityguifinal2;

import java.util.ArrayList;
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
    
    public PortfolioTest() {
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
     * Test of retrieveInvestmentsFromFile method, of class Portfolio.
     */
    @Test
    public void testRetrieveInvestmentsFromFile() {
        System.out.println("retrieveInvestmentsFromFile");
        Account account = null;
        Portfolio instance = new Portfolio();
        ArrayList<Investment> expResult = null;
        ArrayList<Investment> result = instance.retrieveInvestmentsFromFile(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveInvestmentsToFile method, of class Portfolio.
     */
    @Test
    public void testSaveInvestmentsToFile() {
        System.out.println("saveInvestmentsToFile");
        Account account = null;
        Portfolio instance = new Portfolio();
        instance.saveInvestmentsToFile(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvestments method, of class Portfolio.
     */
    @Test
    public void testGetInvestments() {
        System.out.println("getInvestments");
        Portfolio instance = new Portfolio();
        ArrayList<Investment> expResult = null;
        ArrayList<Investment> result = instance.getInvestments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePortfolioStats method, of class Portfolio.
     */
    @Test
    public void testUpdatePortfolioStats() {
        System.out.println("updatePortfolioStats");
        Portfolio instance = new Portfolio();
        instance.updatePortfolioStats();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addInvestment method, of class Portfolio.
     */
    @Test
    public void testAddInvestment() {
        System.out.println("addInvestment");
        Investment newInvestment = null;
        Portfolio instance = new Portfolio();
        instance.addInvestment(newInvestment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeTotalValue method, of class Portfolio.
     */
    @Test
    public void testComputeTotalValue() {
        System.out.println("computeTotalValue");
        Portfolio instance = new Portfolio();
        instance.computeTotalValue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeTotalHistoricalValue method, of class Portfolio.
     */
    @Test
    public void testComputeTotalHistoricalValue() {
        System.out.println("computeTotalHistoricalValue");
        Portfolio instance = new Portfolio();
        instance.computeTotalHistoricalValue();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeTotalInvested method, of class Portfolio.
     */
    @Test
    public void testComputeTotalInvested() {
        System.out.println("computeTotalInvested");
        Portfolio instance = new Portfolio();
        instance.computeTotalInvested();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeTotalProfit method, of class Portfolio.
     */
    @Test
    public void testComputeTotalProfit() {
        System.out.println("computeTotalProfit");
        Portfolio instance = new Portfolio();
        instance.computeTotalProfit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of computeTotalReturnOnInvestment method, of class Portfolio.
     */
    @Test
    public void testComputeTotalReturnOnInvestment() {
        System.out.println("computeTotalReturnOnInvestment");
        Portfolio instance = new Portfolio();
        instance.computeTotalReturnOnInvestment();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printInvestedCompanies method, of class Portfolio.
     */
    @Test
    public void testPrintInvestedCompanies() {
        System.out.println("printInvestedCompanies");
        Portfolio instance = new Portfolio();
        String expResult = "";
        String result = instance.printInvestedCompanies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printAnInvestedCompany method, of class Portfolio.
     */
    @Test
    public void testPrintAnInvestedCompany() {
        System.out.println("printAnInvestedCompany");
        int index = 0;
        Portfolio instance = new Portfolio();
        String expResult = "";
        String result = instance.printAnInvestedCompany(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Portfolio.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Portfolio instance = new Portfolio();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkInvestmentOverSell method, of class Portfolio.
     */
    @Test
    public void testCheckInvestmentOverSell() {
        System.out.println("checkInvestmentOverSell");
        double amount = 0.0;
        Account account = null;
        int investmentIndex = 0;
        Portfolio instance = new Portfolio();
        boolean expResult = false;
        boolean result = instance.checkInvestmentOverSell(amount, account, investmentIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sellShares method, of class Portfolio.
     */
    @Test
    public void testSellShares() {
        System.out.println("sellShares");
        double amount = 0.0;
        Account account = null;
        int investmentIndex = 0;
        Portfolio instance = new Portfolio();
        instance.sellShares(amount, account, investmentIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sellAllShares method, of class Portfolio.
     */
    @Test
    public void testSellAllShares() {
        System.out.println("sellAllShares");
        Account account = null;
        Investment investmentToRemove = null;
        Portfolio instance = new Portfolio();
        instance.sellAllShares(account, investmentToRemove);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Portfolio.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Portfolio.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalValue method, of class Portfolio.
     */
    @Test
    public void testGetTotalValue() {
        System.out.println("getTotalValue");
        Portfolio instance = new Portfolio();
        double expResult = 0.0;
        double result = instance.getTotalValue();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalReturnOnInvestment method, of class Portfolio.
     */
    @Test
    public void testGetTotalReturnOnInvestment() {
        System.out.println("getTotalReturnOnInvestment");
        Portfolio instance = new Portfolio();
        double expResult = 0.0;
        double result = instance.getTotalReturnOnInvestment();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalProfit method, of class Portfolio.
     */
    @Test
    public void testGetTotalProfit() {
        System.out.println("getTotalProfit");
        Portfolio instance = new Portfolio();
        double expResult = 0.0;
        double result = instance.getTotalProfit();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalInvested method, of class Portfolio.
     */
    @Test
    public void testGetTotalInvested() {
        System.out.println("getTotalInvested");
        Portfolio instance = new Portfolio();
        double expResult = 0.0;
        double result = instance.getTotalInvested();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
