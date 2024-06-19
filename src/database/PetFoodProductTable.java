package database;

import petSupplies.PetFoodProduct;

import java.sql.*;

public class PetFoodProductTable {
    // Method to insert records into the PetFoodProduct table
    private static void insertFoodProduct(Connection conn) throws SQLException {
        String sql = "INSERT INTO PetFoodProduct(ProductName, size) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Insert first record
            pstmt.setString(1, "Carrot");
            pstmt.setInt(2, 35);
            pstmt.executeUpdate();
            PetFoodProduct c = new PetFoodProduct("Carrot", 35);

            // Insert second record
            pstmt.setString(1, "Apple");
            pstmt.setInt(2, 25);
            pstmt.executeUpdate();
            PetFoodProduct a = new PetFoodProduct("Apple", 25);

            // Insert third record
            pstmt.setString(1, "Berry2");
            pstmt.setInt(2, 10);
            pstmt.executeUpdate();
            PetFoodProduct b = new PetFoodProduct("Berry2", 10);
        }
    }

    // Method to query the PetFoodProduct table and print the results
    public static void queryAndPrintFoodProducts(String url) throws SQLException {
        String sql = "SELECT ProductName, size FROM PetFoodProduct";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("All Pet Food Products available:");
            System.out.println("---------------------Food List---------------------");

            while (rs.next()) {
                String productName = rs.getString("ProductName");
                int size = rs.getInt("size");
                System.out.printf("Pet Food Product: %s, Size: %d grams per bag.%n", productName, size);
                PetFoodProduct newProduct = new PetFoodProduct(productName, size);
            }
            System.out.println("---------------------------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method to add a row to the FoodProduct table
    public static void addFoodProduct(String url, String productName, int size) throws SQLException {
        String sql = "INSERT INTO PetFoodProduct(ProductName, size) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            pstmt.setInt(2, size);
            pstmt.executeUpdate();
            System.out.println("Product added to database successfully: " + productName + " with size " + size);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Main method to establish a connection and call the above methods
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/v6_Project_PPP_SQLite＿CS622 /Database/PPP_DB.db";
        try (Connection conn = DriverManager.getConnection(url)) {
//            insertFoodProduct(conn);
//            queryAndPrintFoodProducts(url);
        }
    }
}

