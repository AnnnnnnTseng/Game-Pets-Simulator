package Pet;

import globalEntities.Parameters;
import petSupplies.PetFoodProduct;

import java.util.ArrayList;
import java.util.Scanner;

public class Rabbit extends FacadePet {
    public static ArrayList<Rabbit> RabbitInstances = new ArrayList();
    public static String type = "Rabbit";

    public Rabbit(String petName, int age) {
        super(petName, age);
        RabbitInstances.add(this);
    }

    @Override
    public void getTrain(){
        System.out.println("Training " + this.getPetName() + " makes " + this.getPetName() + " tired");
        System.out.println("Before training: Energy Level - " + this.getEnergyLevel());
        this.modifyEnergyLevel(this.getEnergyLevel() - 10);
        System.out.println("After training: Energy Level - " + this.getEnergyLevel());
    }

    @Override
    public void getFeed(){
        Scanner scanner = new Scanner(System.in);
        boolean validFood = false;

        PetFoodProduct food;
        while (!validFood) {
            System.out.print("What pet food do you want to feed " + this.getPetName() + "? ");
            String foodName = scanner.nextLine();

            // Check if the food name exists in the list of pet food products
            for (PetFoodProduct product : PetFoodProduct.thePetFoodProductInstances) {
                System.out.println("New -- Found: " + product.entityName);
                if (product.entityName.equalsIgnoreCase(foodName)) {
                    food = product;
                    validFood = true;
                    this.setGramsPerFeed(food.getSize());
                    System.out.println("----- Feeding " + this.getPetName() + " increase " + this.getPetName() + "'s Energy Level.");
                    System.out.println("----- " + food.entityName + " " + food.getSize() + "g is provided");
                    System.out.println("----- Current energy Level - " + this.getEnergyLevel());
                    this.modifyEnergyLevel(food.getSize());;
                    System.out.println("----- Energy Level after eating " + food.entityName + ": " + this.getEnergyLevel());
                    break;
                }
            }
            if (!validFood) {
                System.out.println("----- " + this.getPetName() + " does not have " + foodName);
            }
        }

    };

    @Override
    public void run() {
        int g = this.getGramsPerFeed();
        System.out.println("----- " + this.getPetName() + " starts eating...");
        while (g > 0) {
            System.out.println("----- " + this.getPetName() + " is eating happily.");
            g -= 10;
            if (g > 0) {
                System.out.println("----- " + this.getPetName() + " still has " + g + " grams left to eat.");
            } else {
                System.out.println("----- " + this.getPetName() + " has finished eating.");
            }
            try {
                Thread.sleep(Parameters.DOG_TWELVE_HR_SLEEP);
            } catch (InterruptedException e) {
                System.out.println("----- Feeding thread interrupted");
            }
        }
    }

}

