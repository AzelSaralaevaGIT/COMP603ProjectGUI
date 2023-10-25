import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {
    private Connection connection;

    public CompanyManager() {
        try {
            // Load the Derby embedded driver and connect to the database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:mydb;create=true");

            // Create a table to store company information
            createCompanyTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create the company table if it doesn't exist
    private void createCompanyTable() {
        try {
            String createTableSQL = "CREATE TABLE companies (" +
                    "name VARCHAR(255), " +
                    "ceo VARCHAR(255), " +
                    "employees INT, " +
                    "industry VARCHAR(255), " +
                    "revenue DECIMAL, " +
                    "marketCap DECIMAL, " +
                    "riskLevel VARCHAR(255))";

            PreparedStatement createTable = connection.prepareStatement(createTableSQL);
            createTable.execute();
        } catch (SQLException e) {
            // Table may already exist; ignore the error
        }
    }

    // Insert a new company into the database
    public void insertCompany(String name, String ceo, int employees, String industry, double revenue, double marketCap, String riskLevel) {
        try {
            String insertSQL = "INSERT INTO companies VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL);

            insertStatement.setString(1, name);
            insertStatement.setString(2, ceo);
            insertStatement.setInt(3, employees);
            insertStatement.setString(4, industry);
            insertStatement.setDouble(5, revenue);
            insertStatement.setDouble(6, marketCap);
            insertStatement.setString(7, riskLevel);

            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a list of all companies from the database
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();

        try {
            String selectSQL = "SELECT * FROM companies";
            PreparedStatement selectStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company(
                    resultSet.getString("name"),
                    resultSet.getString("ceo"),
                    resultSet.getInt("employees"),
                    resultSet.getString("industry"),
                    resultSet.getDouble("revenue"),
                    resultSet.getDouble("marketCap"),
                    resultSet.getString("riskLevel")
                );
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

    // Close the database connection
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
