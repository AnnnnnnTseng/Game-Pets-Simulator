package Pet;

import globalEntities.Parameters;

import java.util.ArrayList;
import java.util.Scanner;

import petSupplies.*;

public class Dog extends FacadePet {
    public static ArrayList<Dog> DogInstances = new ArrayList();
    public static String type = "Dog";

    public Dog(String petName, int age) {
        super(petName, age);
        DogInstances.add(this);
    }

    @Override
    public void getTrain() {
        System.out.println("Training " + this.getPetName() + " makes " + this.getPetName() + " tired");
        System.out.println("Before training: Energy Level - " + this.getEnergyLevel());
        this.setEnergyLevel(this.getEnergyLevel() - 10);
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
                if (product.entityName.equalsIgnoreCase(foodName)) {
                    food = product;
                    validFood = true;
                    this.setGramsPerFeed(food.getSize());
                    System.out.println("----- Feeding " + this.getPetName() + " increase " + this.getPetName() + "'s Energy Level.");
                    System.out.println("----- " + food.entityName + " " + food.getSize() + "g is provided");
                    System.out.println("----- Current energy Level - " + this.getEnergyLevel());
                    this.setEnergyLevel(food.getSize());;
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
//        System.out.println(this.getPetName()+ "EnergyLeve" + this.getEnergyLevel() + " is tired. Dogs normally need 12 - 14 hours of sleep");
//        while (true) {
//            for (FacadePet dog : DogInstances) {
//                System.out.println(this.getPetName() + " falls asleep.......................");
//                this.setEnergyLevel(Parameters.SLEEP_ENERGY_INCREASE);
//            }
//            try {
//                Thread.sleep(Parameters.DOG_TWELVE_HR_SLEEP);
//                System.out.println(this.getPetName() + "EnergyLeve" + this.getEnergyLevel() + " woke up");
//            } catch (InterruptedException e) {
//                System.out.println("Dog Sleep thread interrupted.");
//            }
//        }

//    public void bark() {
//        System.out.println("Woof! Woof! Woof!");
//    }



