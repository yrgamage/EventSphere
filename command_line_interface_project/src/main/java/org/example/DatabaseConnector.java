package org.example;

import org.example.Configuration;

import java.sql.*;

public class DatabaseConnector {
    public static void databaseUpdater(Configuration config) {
        Connection connection = null;
        PreparedStatement selectStmt = null;
        PreparedStatement statement = null;

        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_d", "root", "yoshani#28");

            // SQL for row-level locking
            String selectSQL = "SELECT * FROM configuration LIMIT 1 FOR UPDATE";
            String query = "INSERT INTO configuration (customer_retrieval_rate, max_capacity_tickets, ticket_release_rate, total_tickets) VALUES (?, ?, ?, ?)";

            // Disable auto-commit for transaction management
            connection.setAutoCommit(false);

            // Lock the next row (if exists)
            selectStmt = connection.prepareStatement(selectSQL);
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                // Row is locked and can be used
                System.out.println("Row is locked for processing.");

                // Insert the new configuration data
                statement = connection.prepareStatement(query);
                statement.setFloat(1, config.getCustomerRetrievalRate());
                statement.setInt(2, config.getMaxTicket());
                statement.setFloat(3, config.getTReleaseRate());
                statement.setInt(4, config.getTTickets());

                // Execute the insert query
                int rowsAffected = statement.executeUpdate();
                System.out.println("Parameters saved.");
            }

            // Commit the transaction
            connection.commit();
            System.out.println("Transaction committed.");
        } catch (Exception e) {
            // Handle exceptions and roll back if an error occurs
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Unable to update: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (selectStmt != null) selectStmt.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
