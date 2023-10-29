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
    
    

    /**
     *
     */
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
     * Test of top up method, of class Wallet 
     */
    @Test
    public void testTopUpPositive()
    {
        System.out.println("Testing positive top up!");
        Wallet walletA = new Wallet();
        double positiveTopUp = 100.0;
        walletA.topUp(positiveTopUp); // trying to top up with positive amount
        assertEquals(positiveTopUp, walletA.getAccountBalance(), 0.001); 
        // expecting balance to have been topped up by 100 in Wallet
    }
    
    
    /**
     * Test of top up method, of class Wallet
     */
    @Test
    public void testTopUpNegative()
    {
        System.out.println("Testing negative top up!");
        Wallet walletB = new Wallet();
        double negativeTopUp = -50.0;
        double expected = 0.0;
        double actual = walletB.getAccountBalance();
        walletB.topUp(negativeTopUp); // trying to top up with negative amount
        assertEquals(expected,actual, 0.001); //additional paramter delta to avoid problems with round-off errors with floating point comparisons
        // balance shouldn't change, stay 0
    }
    
    

    /**
     * Test of withdraw method, of class Wallet.
     */
    
    @Test
    public void testWithdraw() {
        System.out.println("Testing positive withdrawal!");
        Wallet walletC = new Wallet();
        double withdrawalAmount = 50.0;
        double actual = walletC.getAccountBalance();
        double expected = 50.0;
        walletC.withdraw(withdrawalAmount);
        //
        assertEquals(expected, actual, 0.001);
        
       
    }
    



    
}
