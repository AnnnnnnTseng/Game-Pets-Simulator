package petSupplies;

import globalEntities.PPPEntity;

import java.util.ArrayList;

// Aggregates all instances================================================
public class PetFoodProduct extends PPPEntity {

  public static ArrayList<PetFoodProduct> thePetFoodProductInstances = new ArrayList<>();
//  public ArrayList<PetFoodBrand> petFoodBrands_ = new ArrayList<>();
  private int size; // Number of grams per bag

  public PetFoodProduct(String ProductName, int size) {
    // POSTCONDITION: thePetFoodProductInstances contains this
    super(ProductName);
    this.size = size;
    thePetFoodProductInstances.add(this);
//    System.out.println("Current instances: ");
//    for (int i = 0; i < thePetFoodProductInstances.size(); i++) {
//        System.out.println(thePetFoodProductInstances.get(i).entityName);
//    }
  }

  public int getSize() {
      return size;
  }

//  public void setSize(int size) {
//      this.size = size;
//  }
//
//  public void addFood(String foodName, int foodWeight) {
//    PetFoodProduct newFood = new PetFoodProduct(foodName, foodWeight);
//    System.out.println(newFood.entityName + ", " + newFood.getSize() + "g has been added to the pantry.");
//
//  }
  @Override
  public void describe() {
    System.out.println("Pet Food Product: " + entityName + ", Size: " + size + " grams per bag.");
  }
//  public static void main(String[] args) {
//    // Create instances of PetFoodProduct.
//    PetFoodProduct product1 = new PetFoodProduct("ABC", 500);
//    PetFoodProduct product2 = new PetFoodProduct("XYZ", 1000);
//    PetFoodProduct product3 = new PetFoodProduct("DEF", 750);
//

//      PetFoodProduct product = new PetFoodProduct("ABC", 500);
//      product.addFood("ZZZ", 100);
//
//    // Access all instances stored in thePetFoodProductInstances
//      System.out.println("All PetFoodProduct instances:");
//      for (PetFoodProduct p : PetFoodProduct.thePetFoodProductInstances) {
//          product.describe();
//      }
//
//  }


}