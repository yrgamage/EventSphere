package org.example;

import java.sql.*;

public class DataExtractor {

    public static Configuration dataExtractor() {
        Connection connection = null;
        PreparedStatement selectStmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_d", "root", "yoshani#28");
            String selectSQL = "SELECT * FROM configuration LIMIT 1 FOR UPDATE";
            // SQL query to fetch the last row based on descending order of ID
            String query = "SELECT * FROM configuration ORDER BY configID DESC LIMIT 1";

            // Disable auto-commit for transaction management
            connection.setAutoCommit(false);

            selectStmt = connection.prepareStatement(selectSQL);
            ResultSet resultSet = selectStmt.executeQuery(query);

            if (resultSet.next()) {
                // Row is locked and can be used
                System.out.println("Row is locked for processing.");


                float CRR = resultSet.getFloat("customer_retrieval_rate");
                int MCT = resultSet.getInt("max_capacity_tickets");
                float TRR = resultSet.getFloat("ticket_release_rate");
                int TT = resultSet.getInt("total_tickets");
                connection.commit();
                System.out.println("Transaction committed. Last row data extracted.");

                return new Configuration(TT, TRR, CRR, MCT);

            }
             else {
                System.out.println("Table is empty.");
                return null; // No data in the table
            }
        } catch (Exception e) {
            // Rollback in case of error
            try {
                if (connection != null) {
                    connection.rollback();
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Unable to retrieve data: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            // Clean up resources
            try {
                if (selectStmt != null) selectStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
