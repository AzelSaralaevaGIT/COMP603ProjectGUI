/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shareversityguifinal2;

/**
 *
 * @author saral
 */
public class ShareVersityDBMain {
    
    public static void main(String[] args) {
        
        ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
        shareversitydb.connectShareVersityDB();
        shareversitydb.createAccountTable();
        //create another table
        shareversitydb.closeConnection();
    }
}
