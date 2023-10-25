
package shareversityguifinal2;

import org.apache.derby.iapi.sql.ResultSet;

/**
 *
 * @author saral
 */
public class ShareVersityDatabaseMain {
 

    public static void main(String[] args) {
        ShareVersityDatabase shareVersityDB = new ShareVersityDatabase();

        // Connect to the database and create the table and insert data
        shareVersityDB.connectShareVersityDB();

        // Perform database operations, for example, retrieve account information
        ResultSet accountInfo = (ResultSet) shareVersityDB.getAccountInfo();
        try {
            while (accountInfo.next()) {
                String username = accountInfo.getString("USERNAME");
                String fullName = accountInfo.getString("FULLNAME");
                int bankAccountNumber = accountInfo.getInt("BANKACCOUNTNUMBER");
                String dateOfBirth = accountInfo.getString("DATEOFBIRTH");
                double walletAmount = accountInfo.getDouble("WALLETAMOUNT");

                System.out.println("Username: " + username);
                System.out.println("Full Name: " + fullName);
                System.out.println("Bank Account Number: " + bankAccountNumber);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("Wallet Amount: " + walletAmount);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the database connection
        shareVersityDB.closeConnection();
    }


    
    }
    
  
