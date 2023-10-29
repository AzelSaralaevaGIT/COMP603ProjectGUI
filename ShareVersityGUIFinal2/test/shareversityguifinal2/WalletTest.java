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
    public void testWithdrawal() {
        System.out.println("Testing withdrawal!");
        Wallet walletC = new Wallet();
        walletC.topUp(100.0);
        double withdrawalAmount = 50.0;
        
        walletC.withdraw(withdrawalAmount);
        
        double expected = 50.0;
        double actual = walletC.getAccountBalance();
        //initial amount in wallet $100, withdrawing $50
        assertEquals(expected, actual, 0.001);
    }
    
    /**
    * Test of overWithdraw method, of class Wallet.
    */
    @Test
    public void testOverWithdrawal()
    {
        System.out.println("Testing overWithdrawal!");
        Wallet walletD = new Wallet();
        walletD.topUp(10.0); // top up $10.0 for the test
        double withdrawalAmountOne = 100.0;
        //testing when amount is greater than the balance
        assertTrue(walletD.checkOverWithdraw(withdrawalAmountOne)); //should return true as initial balance is 0, and attempting to withdraw 100.0, so is overwithdrawing
        
        double withdrawalAmountTwo = 10.0;
        //testing when wihtdrawal amountt equal to balance
        assertFalse(walletD.checkOverWithdraw(withdrawalAmountTwo)); // should return false as it's not above balance in wallet and they haven't overwithdrawn
        
        double withdrawalAmountThree = 0.0;
        //testing when withdrawal amount is zero
        assertFalse(walletD.checkOverWithdraw(withdrawalAmountThree)); //should return false as it's trying to withdraw $0.0 and not overwithdrawing
        
    }

    
}
