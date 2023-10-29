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


    
}
