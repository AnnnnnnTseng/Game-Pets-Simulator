package useCases;

import Pet.Dog;
import Pet.FacadePet;
import Pet.InteractWith;
import Pet.Rabbit;
import petSupplies.PetFoodProduct;
import users.ParadiseMayor;
import database.*;

import java.io.IOException;
import java.util.Scanner;

import java.sql.*;

import static database.FacadePetTable.updatePetEnergy;

public class WelcomePage {
    public WelcomePage() {
    }

    public static void loadData(PetFoodProductTable food, GetPetsInputUseCase getPets, String url) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Please do load data before proceeding. Loading now? (Y/N) ");
//        String choice = scanner.nextLine();

        while (true) {
            System.out.print("Please do load data before proceeding. Loading now? (Y/N) ");
            String toLoad = scanner.nextLine();
            if (toLoad.equalsIgnoreCase("Y")) {
                System.out.println("You chose " + toLoad + " - Load Data.");
                System.out.println();
//                getPets.readTextFile(GetPetsInputUseCase.fileName);
//                getPets.readFromDatabase();
                getPets.printReadFromDatabase(getPets, url);
                System.out.println("[ Finish Reading Existing Pets ]");
                System.out.println("[ Total Pets: " + getPets.petsInstances.size() + " ]");
                System.out.println();
                // Create default instances of PetFoodProduct
                showPantry(food, url);
                System.out.println("[ Finish Loading Existing Products ]");
//                System.out.println();
//                System.out.println();
//                PetFoodProduct product = new PetFoodProduct("Royal Canin", 50);
//                PetFoodProduct product2 = new PetFoodProduct("Blue Buffalo", 100);
//                PetFoodProduct product3 = new PetFoodProduct("Ant", 75);
                System.out.println("Welcome to the Pet Pals Paradise.");
                break;
            } else {
                System.out.print("Sorry, Pet Pals Paradise game cannot proceed without loading data. ");
            }
        }
    }

    public static void printMenu() {
        System.out.println("What do you want to do now?");
        System.out.println("1 - Show Pets");
        System.out.println("2 - Add food to pet food pantry");
        System.out.println("3 - Show pet food pantry");
        System.out.println("4 - Feed Pets (You Can Feed Multiple Pets At The Same Time)");
        System.out.println("5 - Feed (one of) a pet with the lowest energy level.");
        System.out.println("9 - Quit");
    }

    public static void showPets(GetPetsInputUseCase getPets, String url) {
        try (Connection conn = DriverManager.getConnection(url)) {
            // Set the busy timeout to 30 seconds
            if (conn.isValid(0)) {
                conn.createStatement().execute("PRAGMA busy_timeout = 30000");
            }

            // print existing pets
            getPets.showExistingPets(conn, getPets, url);

//            // Print the loaded pet details
//            for (String detail : getPets.petDetails) {
//                System.out.println(detail);
//            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showPantry(PetFoodProductTable databaseFood, String url) throws SQLException {
        System.out.println();
        // Access all instances stored in thePetFoodProductInstances

        databaseFood.queryAndPrintFoodProducts(url);

//        System.out.println("All Pet Food Products available:");
//        System.out.println("--------------------------Food List--------------------------");
//        for (PetFoodProduct p : PetFoodProduct.thePetFoodProductInstances) {
//            p.describe();
//        }
//        System.out.println("-------------------------------------------------------------");
//        System.out.println();
    }
    public static void feedPet(PetFoodProductTable databaseFood, GetPetsInputUseCase getPets, String chosenPetName, String url) throws SQLException {
        // Find the chosen pet in theFacadePet list
        FacadePet chosenPet = null;
        for (FacadePet pet : FacadePet.FacadePetInstances) {
            if (pet.getPetName().equalsIgnoreCase(chosenPetName)) {
                chosenPet = pet;
                break;
            }
        }
        // If the chosen dog is found, feed it
        if (chosenPet != null) {
            if (chosenPet instanceof Dog) {
                Dog chosenDog = (Dog) chosenPet;
                System.out.println("Ready to feed dog " + chosenPetName);
                showPantry(databaseFood, url);
                InteractWith<Dog> DogInteraction = new InteractWith<>(chosenDog);
                ParadiseMayor.theParadiseMayor.FeedPet(DogInteraction);
                ParadiseMayor.theParadiseMayor.ObservePets(DogInteraction);
            } else if (chosenPet instanceof Rabbit) {
                Rabbit chosenRabbit = (Rabbit) chosenPet;
                System.out.println("Ready to feed rabbit " + chosenPetName);
                showPantry(databaseFood, url);
                InteractWith<Rabbit> RabbitInteraction = new InteractWith<>(chosenRabbit);
                ParadiseMayor.theParadiseMayor.FeedPet(RabbitInteraction);
                ParadiseMayor.theParadiseMayor.ObservePets(RabbitInteraction);
            }
            updatePetEnergy(url, chosenPet.getPetName(), chosenPet.getEnergyLevel());
        } else {
            System.out.println("Sorry, the specified dog does not exist.");
        }
        getPets.updateDatabaseWithDefaults(getPets.petsInstances, url);
    }

    public static void menu(PetFoodProductTable databaseFood, GetPetsInputUseCase getPets, WelcomePage page, String PPPName, String url) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean notQuit = true;

        while (notQuit) {
            System.out.println("[ Welcome to " + PPPName + " Pet Pals Paradise! ]");
            printMenu();

            System.out.print("What is your choice? ");
            String menuChoice = scanner.nextLine();
            //verify user input is an int
            int choice = -1;
            try {
                choice = Integer.parseInt(menuChoice);
            } catch (NumberFormatException e) {
                System.out.println("Only enter a number please!");
                continue;
            }
            switch (choice) {
                case 9:
                    System.out.println("You chose " + choice + " Goodbye! Thank you for visiting Paradise");
                    notQuit = false;
                    break;
                case 1:
                    System.out.println("You chose " + choice + " - Show Pets");
                    System.out.println();
                    showPets(getPets, url);
                    System.out.println();
                    continue;
                case 2:
                    System.out.println("You chose " + choice);
                    System.out.println();
                    // Prompt the user for the product name
                    System.out.print("What product do you want to add to the pantry? (Enter string only): ");
                    String productName = scanner.nextLine();

                    // Prompt the user for the weight of the product in grams
                    System.out.print("What is the weight of the product? (Enter integer only): ");
                    int productWeight = scanner.nextInt();

                    // Add new Product Instance
//                    System.out.print("Adding Ant2------------------------------------------------");
//                    PetFoodProduct product3 = new PetFoodProduct("Ant2", 75);
//                    System.out.print("Adding another");
                    PetFoodProduct newProduct = new PetFoodProduct(productName, productWeight);
                    databaseFood.addFoodProduct(url, productName, productWeight);
                    System.out.println("[ " + productName + " ] , " + productWeight + "g - has been added to the pantry.");
                    System.out.println();
                    continue;
                case 3:
                    System.out.println("You chose " + choice + " - Show pet food pantry");
                    showPantry(databaseFood, url);
                    continue;

                case 4:
                    System.out.println("You chose " + choice + " - Feet Pets, You Can Feed Multiple Pets At The Same Time");
//                    ArrayList<String> chosenPetNames = new ArrayList<>();
                    while (true) {
                        System.out.print("Do you want to feed a new pet now? (Y/N) ");
                        String toFeed = scanner.nextLine();
                        if (toFeed.equalsIgnoreCase("Y")) {
                            // Prompt the user to choose which dog to feed
                            System.out.println("Which pet do you want to feed? (Enter Pet Name only.)");

                            showPets(getPets, url);

                            System.out.println();
                            String chosenPetName = scanner.nextLine();

//                            chosenPetNames.add(chosenPetName);
                            feedPet(databaseFood, getPets, chosenPetName, url);
//                            updatePetEnergy(url, chosenPetName, new)
                        } else {
                            System.out.print("Finish choosing pets to feed. Start Feeding!");
                            break;
                        }
                    }
                    // Iterate through each chosenPetName in the ArrayList and feed the pet
//                    for (String chosenPetName : chosenPetNames) {
//                        feedPet(chosenPetName);
//                    }
                    continue;
                case 5:
                    System.out.println("You chose " + choice + " - Feed (one of) a pet with the lowest energy level.");
                    // Get the pet name with the minimum energy level
                    String petName = getPets.getPetWithMinEnergyLevel(url);
                    // Check if a pet was found and feed it
                    if (petName != null) {
                        System.out.println( "[ " + petName + " ] has the lowest energy level: ");
                        feedPet(databaseFood, getPets, petName, url);
                    } else {
                        System.out.println("No pets found in the paradise.");
                    }
                    continue;
                default:
                    System.out.println("That's not a valid selection.");
            }
        }
    }
    public static void main(String[] args) throws IOException, SQLException {
        //path to Database
        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/v6_Project_PPP_SQLite＿CS622 /Database/PPP_DB.db";
        //Load datas
        GetPetsInputUseCase getPets = new GetPetsInputUseCase();
        PetFoodProductTable foodDatabase = new PetFoodProductTable();


        loadData(foodDatabase, getPets, url);
        System.out.println("Hi there, what is your name? ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        System.out.println("Hi " + userName + ", now your the Mayor of Pet Pals Paradise. What is the name of your Pet Pals Paradise? ");

        String PPPName = scanner.nextLine();

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.menu(foodDatabase, getPets, welcomePage, PPPName, url);

    }
}
