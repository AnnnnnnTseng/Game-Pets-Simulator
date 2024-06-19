package database;
import java.sql.*;

public class FacadePetTable {

    private static void insertPets(Connection conn) throws SQLException {
        // Update the SQL statement to include only the NOT NULL columns
        String sql = "INSERT INTO FacadePet(PetName, type, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Insert first record
            pstmt.setString(1, "Flopsy");
            pstmt.setString(2, "R");
            pstmt.setInt(3, 4);
            pstmt.executeUpdate();

            // Insert second record
            pstmt.setString(1, "Lola");
            pstmt.setString(2, "R");
            pstmt.setInt(3, 3);
            pstmt.executeUpdate();

            // Insert third record
            pstmt.setString(1, "Miffy");
            pstmt.setString(2, "R");
            pstmt.setInt(3, 1);
            pstmt.executeUpdate();

            // Insert fourth record
            pstmt.setString(1, "Abby");
            pstmt.setString(2, "D");
            pstmt.setInt(3, 5);
            pstmt.executeUpdate();

            // Insert fifth record
            pstmt.setString(1, "Luna");
            pstmt.setString(2, "D");
            pstmt.setInt(3, 10);
            pstmt.executeUpdate();

            // Insert sixth record
            pstmt.setString(1, "Cooper");
            pstmt.setString(2, "D");
            pstmt.setInt(3, 12);
            pstmt.executeUpdate();
        }
    }
    private static void query(Connection conn) throws SQLException {
        String sql = "SELECT PetName, type, age FROM FacadePet";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("%s\t%-10s\t%d%n",
                    rs.getString("PetName"), rs.getString("type"), rs.getInt("age"));
            }
        }
    }
    // Method to add a row to the FoodProduct table
    public static void updatePetEnergy(String url, String petName, int newEnergyLevel) throws SQLException {
        String sql = "UPDATE FacadePet SET energyLevel = ? WHERE PetName = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, newEnergyLevel);
                pstmt.setString(2, petName);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Updated to database successfully: " + petName + " with new energy level: " + newEnergyLevel);
            if (affectedRows > 0) {
                System.out.println("Energy level updated successfully for pet: " + petName);
            } else {
                System.out.println("Pet not found in the database: " + petName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) throws SQLException {
//        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/v6_Project_PPP_SQLite＿CS622 /Database/PPP_DB.db";
//        try (Connection conn = DriverManager.getConnection(url)) {
//            insertPets(conn);
//            query(conn);
//        }
    }
}

