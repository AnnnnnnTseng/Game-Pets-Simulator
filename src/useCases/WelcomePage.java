package useCases;

import Pet.Dog;
import Pet.FacadePet;
import Pet.InteractWith;
import Pet.Rabbit;
import petSupplies.PetFoodProduct;
import users.ParadiseMayor;

import java.io.IOException;
import java.util.Scanner;

import java.sql.*;

public class WelcomePage {
    public WelcomePage() {
    }

    public static void loadData(GetPetsInputUseCase getPets, String url) throws IOException {
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
                getPets.LoadPets(getPets, url);
                System.out.println("[ Finish Reading Existing Pets ]");
                System.out.println("[ Total Pets: " + getPets.petsInstances.size() + " ]");
                System.out.println();
                // Create default instances of PetFoodProduct
                System.out.println("[ Finish Loading Existing Products ]");
                System.out.println();
                System.out.println();
                PetFoodProduct product = new PetFoodProduct("Royal Canin", 50);
                PetFoodProduct product2 = new PetFoodProduct("Blue Buffalo", 100);
                PetFoodProduct product3 = new PetFoodProduct("Hill's Science Diet", 75);
                System.out.println("Welcome to the Pet Pals Paradise.");
                break;
            } else {
                System.out.print("Sorry, Pet Pals Paradise game cannot proceed without loading data. ");
                continue;
            }
        }
    }

    public static void printMenu() {
        System.out.println("What do you want to do now?");
        System.out.println("1 - Show Pets");
        System.out.println("2 - Add food to pet food pantry");
        System.out.println("3 - Show pet food pantry");
        System.out.println("4 - Feed Pets (You Can Feed Multiple Pets At The Same Time)");
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

    public static void showPantry() {
        System.out.println("_____________Food List_____________");
        System.out.println();
        // Access all instances stored in thePetFoodProductInstances
        System.out.println("All Pet Food Products available:");
        for (PetFoodProduct p : PetFoodProduct.thePetFoodProductInstances) {
            p.describe();
        }
        System.out.println();
    }

    public static void feedPet(String chosenPetName) {
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
                System.out.println("You want to feed dog " + chosenDog.getName());
                showPantry();
                InteractWith<Dog> DogInteraction = new InteractWith<>(chosenDog);
                ParadiseMayor.theParadiseMayor.FeedPet(DogInteraction);
                ParadiseMayor.theParadiseMayor.ObservePets(DogInteraction);
            } else if (chosenPet instanceof Rabbit) {
                Rabbit chosenRabbit = (Rabbit) chosenPet;
                System.out.println("You want to feed rabbit " + chosenRabbit.getName());
                showPantry();
                InteractWith<Rabbit> RabbitInteraction = new InteractWith<>(chosenRabbit);
                ParadiseMayor.theParadiseMayor.FeedPet(RabbitInteraction);
                ParadiseMayor.theParadiseMayor.ObservePets(RabbitInteraction);

            }
        } else {
            System.out.println("Sorry, the specified dog does not exist.");
        }
    }

    public static void menu(GetPetsInputUseCase getPets, WelcomePage page, String PPPName, String url) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean notQuit = true;

        while (notQuit) {
            System.out.println("Welcome to " + PPPName + " Pet Pals Paradise!");
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
                    System.out.println("You chose " + choice + "Goodbye! Thank you for using the database");
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
                    PetFoodProduct newProduct = new PetFoodProduct(productName, productWeight);
                    System.out.println("[ " + productName + " ] , " + productWeight + "g - has been added to the pantry.");
                    System.out.println();
                    continue;
                case 3:
                    System.out.println("You chose " + choice + " - Show pet food pantry");
                    showPantry();
                    continue;

                case 4:
                    System.out.println("You chose " + choice + " - Feet Pets (You Can Feed Multiple Pets At The Same Time)");
//                    ArrayList<String> chosenPetNames = new ArrayList<>();
                    while (true) {
                        System.out.print("Do you want to feed a new pet now? (Y/N) ");
                        String toFeed = scanner.nextLine();
                        if (toFeed.equalsIgnoreCase("Y")) {
                            // Prompt the user to choose which dog to feed
                            System.out.println("Which pet do you want to feed?");
                            System.out.println("___________________________________");
                            System.out.println();
                            System.out.println("_____________Pet List_____________");
                            showPets(getPets, url);

                            System.out.println();
                            String chosenPetName = scanner.nextLine();

//                            chosenPetNames.add(chosenPetName);
                            feedPet(chosenPetName);
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
                default:
                    System.out.println("That's not a valid selection.");
            }
        }


    }


    public static void main(String[] args) throws IOException {
        //path to Database
        String url = "jdbc:sqlite:/Users/ann/Desktop/BU CS622/Assignment 6/Database/PPP_DB.db";

        //Load datas
        GetPetsInputUseCase getPets = new GetPetsInputUseCase();

//        //Database connection
//        System.out.println("Connecting to PPP database...");
//        try (Connection conn = DriverManager.getConnection(url)) {
//            // Set the busy timeout to 30 seconds
//            if (conn.isValid(0)) {
//                conn.createStatement().execute("PRAGMA busy_timeout = 30000");
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }

        loadData(getPets, url);
        System.out.println("Hi there, what is your name? ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        System.out.println("Hi " + userName + ", now your the Mayor of Pet Pals Paradise. What is the name of your Pet Pals Paradise? ");

        String PPPName = scanner.nextLine();

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.menu(getPets, welcomePage, PPPName, url);


    }
}
