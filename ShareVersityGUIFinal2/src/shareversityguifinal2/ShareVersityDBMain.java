/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

import java.util.Scanner;

/**
 *
 * @author saral
 */
public class ShareVersityDBMain {
    
    public static void main(String[] args) {
        
        ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
        shareversitydb.connectShareVersityDB();
        shareversitydb.createInvestmentTable();
        shareversitydb.createCompaniesTable();
        //create another table
        shareversitydb.createCostPerShareHistoryTable();
        
        String username = null;
        String password = null;
        String fullname = null;
        String dateOfBirth = null;
        String bankAccountNumber = null;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("user? ");
        username = scan.nextLine();
        System.out.println("pass? ");
        password = scan.nextLine();
        System.out.println("fullname? ");
        fullname = scan.nextLine();
        System.out.println("bank acc: ");
        bankAccountNumber = scan.nextLine();
        System.out.println("dateofbirth? ");
        dateOfBirth = scan.nextLine();
      
        shareversitydb.insertAccount(username, password, fullname, bankAccountNumber, dateOfBirth, 0);
        
        shareversitydb.getAccountInfo();
        shareversitydb.closeConnection();
    }
}
