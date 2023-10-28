/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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
public class WalletTest {
    
    public WalletTest() {
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
     * Test of topUp method, of class Wallet.
     */
    @Test
    public void testTopUp() {
        System.out.println("topUp");
        double amount = 0.0;
        Wallet instance = new Wallet();
        instance.topUp(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of withdraw method, of class Wallet.
     */
    @Test
    public void testWithdraw() {
        System.out.println("withdraw");
        double amount = 0.0;
        Wallet instance = new Wallet();
        instance.withdraw(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNegativeTopUp method, of class Wallet.
     */
    @Test
    public void testCheckNegativeTopUp() {
        System.out.println("checkNegativeTopUp");
        double amount = 0.0;
        Wallet instance = new Wallet();
        boolean expResult = false;
        boolean result = instance.checkNegativeTopUp(amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOverWithdraw method, of class Wallet.
     */
    @Test
    public void testCheckOverWithdraw() {
        System.out.println("checkOverWithdraw");
        double amount = 0.0;
        Wallet instance = new Wallet();
        boolean expResult = false;
        boolean result = instance.checkOverWithdraw(amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountBalance method, of class Wallet.
     */
    @Test
    public void testGetAccountBalance() {
        System.out.println("getAccountBalance");
        Wallet instance = new Wallet();
        double expResult = 0.0;
        double result = instance.getAccountBalance();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Wallet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Wallet instance = new Wallet();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
