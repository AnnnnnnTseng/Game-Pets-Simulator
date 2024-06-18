package database;

import java.sql.*;

public class insertFoodProduct {
    // Method to insert records into the PetFoodProduct table
    private static void insertFoodProduct(Connection conn) throws SQLException {
        String sql = "INSERT INTO PetFoodProduct(ProductName, size) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Insert first record
            pstmt.setString(1, "Carrot");
            pstmt.setInt(2, 35);
            pstmt.executeUpdate();

            // Insert second record
            pstmt.setString(1, "Apple");
            pstmt.setInt(2, 25);
            pstmt.executeUpdate();

            // Insert third record
            pstmt.setString(1, "Berry");
            pstmt.setInt(2, 10);
            pstmt.executeUpdate();
        }
    }

    // Main method to establish a connection and call the above methods
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/v6_Project_PPP_SQLite＿CS622 /Database/PPP_DB.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            insertFoodProduct(conn);
        }
    }
}

