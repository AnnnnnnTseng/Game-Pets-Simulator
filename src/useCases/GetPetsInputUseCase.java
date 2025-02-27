package useCases;

import Pet.*;
import java.util.ArrayList;
import java.io.IOException;

import java.sql.*;
import java.util.List;

public class GetPetsInputUseCase {

    // List store all pets input instances
    ArrayList<FacadePet> petsInstances = new ArrayList<>();

//    public static final String fileName = "Pets_input.txt";

    //constructor
    public GetPetsInputUseCase() {
    }
    // read from txt file, and create instances for dog/rabbits
//    public void readTextFile(String fileName) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        String line;
//        while((line = br.readLine()) != null) {
//            String[] parts = line.split(" ");
//            char category = parts[0].charAt(0);
//            String petName = parts[1];
//            int petAge = Integer.parseInt(parts[2]);
//            if (category == 'R') {
//                //create a new rabbit instance
//                Rabbit rabbit = new Rabbit(petName, petAge);
//                this.petsInstances.add(rabbit);
//            } else if (category == 'D') {
//                //create a new dog instance
//                Dog dog = new Dog(petName, petAge);
//                this.petsInstances.add(dog);
//            }
//        }
//    }
    // read from Database, and create instances for dog/rabbits
    public void readFromDatabase(Connection conn, String url) throws SQLException {
        String querySQL = "SELECT PetName, type, age, energyLevel FROM FacadePet ORDER BY age";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                String type = rs.getString("type");
                String petName = rs.getString("PetName");
                int petAge = rs.getInt("age");
                int energyLevel = rs.getInt("energyLevel");

                if (type.equalsIgnoreCase("R")) {
                    Rabbit rabbit = new Rabbit(petName, petAge);
                    rabbit.setEnergyLevel(energyLevel);
                    this.petsInstances.add(rabbit);
                } else if (type.equalsIgnoreCase("D")) {
                    Dog dog = new Dog(petName, petAge);
                    dog.setEnergyLevel(energyLevel);
                    this.petsInstances.add(dog);
                }
            }
        }
        // Update database with default values
        updateDatabaseWithDefaults(petsInstances, url);
    }

    //after create instance, write the default value(energy level...etc)back to DB.
    public void updateDatabaseWithDefaults(ArrayList<FacadePet> petsInstances, String url) throws SQLException {
    String updateSQL = "UPDATE FacadePet SET mood = ?, energyLevel = ?, gramsPerFeed = ? WHERE PetName = ?";
        try (Connection conn = DriverManager.getConnection(url)) {
            // Set the busy timeout to 30 seconds
            if (conn.isValid(0)) {
                conn.createStatement().execute("PRAGMA busy_timeout = 30000");
            }
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            for (FacadePet pet : petsInstances) {
                pstmt.setInt(1, pet.getMood());
                pstmt.setInt(2, pet.getEnergyLevel());
                pstmt.setInt(3, pet.getGramsPerFeed());
                pstmt.setString(4, pet.getPetName());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    //print from petsInstances stored in this class. petInstances stored pets that just loaded and created.
    public void printReadFromDatabase(GetPetsInputUseCase useDatabase, String url) {
        //Sets up the SQLite database connection.
        try (Connection conn = DriverManager.getConnection(url)) {
            // Set the busy timeout to 30 seconds
            if (conn.isValid(0)) {
                conn.createStatement().execute("PRAGMA busy_timeout = 30000");
            }

            // Create an instance of UseDatabase and read data from database
            useDatabase.readFromDatabase(conn, url);

            //Optionally, print the loaded pets
            System.out.println("---------Pet List (young - old)---------");
            for (FacadePet pet : useDatabase.petsInstances) {
                System.out.printf("%s: %s, %d years old%n",
                    pet instanceof Dog ? "Dog" : "Rabbit", pet.getPetName(), pet.getAge());

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showExistingPets (Connection conn, GetPetsInputUseCase useDatabase, String url) throws IOException {
        //ShowExistingPet method usage
        List<String> petDetails = new ArrayList<>();
//        GetPetsInputUseCase useCase = new GetPetsInputUseCase();
//        useCase.readTextFile(fileName);
//
//        Iterator var4 = useCase.petsInstances.iterator();
//
//        while (var4.hasNext()) {
//            FacadePet entity = (FacadePet) var4.next();
//            System.out.println(entity.getPetName());
//        }

        //printing rabbit and dog lists respectively
//        System.out.println("[ Total Rabbit instances: " + Rabbit.RabbitInstances.size() + " ]");
//        Iterator rabbitIterator = Rabbit.RabbitInstances.iterator();
//        while (rabbitIterator.hasNext()) {
//            Rabbit rabbitEntity = (Rabbit) rabbitIterator.next();
//            System.out.println("Rabbit: " + rabbitEntity.getPetName() + ", Age: " + rabbitEntity.getAge());
//        }
//
//        System.out.println("[ Total Dog instances: " + Dog.DogInstances.size() + " ]");
//        Iterator dogIterator = Dog.DogInstances.iterator();
//        while (dogIterator.hasNext()) {
//            Dog dogEntity = (Dog) dogIterator.next();
//            System.out.println("Name: " + dogEntity.getPetName() + ", Age: " + dogEntity.getAge());
//        }

        //Select all pets name and age from Facadepet table and match petType ID with AnimalPet that has same id in petType table
        String querySQL = "SELECT FacadePet.PetName, FacadePet.age, FacadePet.energyLevel, PetType.AnimalType " +
                  "FROM FacadePet " +
                  "JOIN PetType ON FacadePet.type = PetType.TypeId";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(querySQL)) {
            while (rs.next()) {
                String petName = rs.getString("PetName");
                int petAge = rs.getInt("age");
                int energyLevel = rs.getInt("energyLevel");
                String animalType = rs.getString("AnimalType");
                petDetails.add("| Pet Name: " + petName + " Type: " + animalType + " Age: " + petAge + " Energy Level: " + energyLevel + " |");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // print a existing pets table
        System.out.println("------------------------Pet List------------------------");
        for (String detail : petDetails) {
                System.out.println(detail);
        }
        System.out.println("-------------------------------------------------------");
    }
    public static String getPetWithMinEnergyLevel(String url) {
        String minEnergySQL = "SELECT PetName FROM FacadePet " +
                              "WHERE energyLevel = (SELECT MIN(energyLevel) FROM FacadePet)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(minEnergySQL)) {

            if (rs.next()) {
                return rs.getString("PetName");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

//        //Read from DB
//
//        //Sets up the SQLite database connection.
//        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/Database/PPP_DB.db";
//
//                try (Connection conn = DriverManager.getConnection(url)) {
//            // Set the busy timeout to 30 seconds
//            if (conn.isValid(0)) {
//                conn.createStatement().execute("PRAGMA busy_timeout = 30000");
//            }
//
//            // Create an instance of UseDatabase and read data from database
//            GetPetsInputUseCase useDatabase = new GetPetsInputUseCase();
//            useDatabase.readFromDatabase(conn);
//
//            // Optionally, print the loaded pets
//            for (FacadePet pet : useDatabase.petsInstances) {
//                System.out.printf("%s: %s, %d years old%n",
//                    pet instanceof Dog ? "Dog" : "Rabbit", pet.getPetName(), pet.getAge());
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }


//        //Read from txt file--
//        useCase.readTextFile(fileName);
//        useCase.showExistingPets();
//        System.out.println("Total Pets: " + useCase.petsInstances.size());
//
//        Iterator var4 = useCase.petsInstances.iterator();
//
//        while (var4.hasNext()) {
//            FacadePet entity = (FacadePet) var4.next();
//            System.out.println(entity.getPetName());
//        }
    }
}
