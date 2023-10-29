package shareversityguifinal2;

/**
 * This method can be run to reset the database
 * @author saral
 */
public class ShareVersityDBMain {
    
    public static void main(String[] args) {
        
        // create tables
        ShareVersityDatabase shareversitydb = new ShareVersityDatabase();
        shareversitydb.connectShareVersityDB();
        shareversitydb.createInvestmentTable();
        shareversitydb.createCompaniesTable();
        
        shareversitydb.closeConnection();
    }
}
