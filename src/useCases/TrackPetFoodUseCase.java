package useCases;

import Pet.Dog;
import Pet.InteractWith;
import Pet.Rabbit;
import petSupplies.PetFoodProduct;
import users.ParadiseMayor;

import java.io.IOException;

public class TrackPetFoodUseCase {

//        ArrayList<Dog> dogInstances = new ArrayList<>();
//        public static final String fileName = "Pets_input.txt";
//        //constructor
//        public TrackPetFoodUseCase() {
//        }
//
//        //Read dogs from file and store in dogInstances
//        public void readDogTextFile(String fileName) throws IOException {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//            while((line = br.readLine()) != null) {
//                String[] parts = line.split(" ");
//                char category = parts[0].charAt(0);
//                String petName = parts[1];
//                int petAge = Integer.parseInt(parts[2]);
//                if (category == 'D') {
//                    Dog dog = new Dog(petName, petAge);
//                    this.dogInstances.add(dog);
//                }
//            }
//        }

    public static void main(String[] args) throws IOException {

        // Create instances of PetFoodProduct
        PetFoodProduct product1 = new PetFoodProduct("Royal Canin", 50);
        PetFoodProduct product2 = new PetFoodProduct("Blue Buffalo", 100);
        PetFoodProduct product3 = new PetFoodProduct("Hill's Science Diet", 75);

        // Access all instances stored in thePetFoodProductInstances
        System.out.println("All PetFoodProduct available:");
        for (PetFoodProduct product : PetFoodProduct.thePetFoodProductInstances) {
          product.describe();
        }
        Dog myDog = new Dog("Puffy", 1);
        Rabbit myRabbit = new Rabbit("Mina", 5);
//        testConcurrentUseCase inputDogs = new testConcurrentUseCase();
//        inputDogs.readDogTextFile(fileName);
//
//        Iterator dogsIterator = inputDogs.dogInstances.iterator();
//
//        while (dogsIterator.hasNext()) {
//            Dog dogEntity = (Dog) dogsIterator.next();
//            System.out.println(dogEntity.getPetName());
//        }

        InteractWith<Dog> DogInteraction = new InteractWith<>(myDog);
        InteractWith<Rabbit> RabbitInteraction = new InteractWith<>(myRabbit);

//        ParadiseMayor.theParadiseMayor.TrainPet(DogInteraction);
//        ParadiseMayor.theParadiseMayor.PlayPet(RabbitInteraction);
//        ParadiseMayor.theParadiseMayor.ObservePets(DogInteraction);
        ParadiseMayor.theParadiseMayor.FeedPet(RabbitInteraction);
        ParadiseMayor.theParadiseMayor.ObservePets(RabbitInteraction);
        ParadiseMayor.theParadiseMayor.FeedPet(DogInteraction);
        ParadiseMayor.theParadiseMayor.ObservePets(DogInteraction);

    }
}


//public class test_concurrent_MakeInteraction {
//
//        ArrayList<Dog> dogInstances = new ArrayList<>();
//        public static final String fileName = "Pets_input.txt";
//        //constructor
//        public test_concurrent_MakeInteraction() {
//        }
//
//        //Read dogs from file and store in dogInstances
//        public void readDogTextFile(String fileName) throws IOException {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//            while((line = br.readLine()) != null) {
//                String[] parts = line.split(" ");
//                char category = parts[0].charAt(0);
//                String petName = parts[1];
//                int petAge = Integer.parseInt(parts[2]);
//                if (category == 'D') {
//                    Dog dog = new Dog(petName, petAge);
//                    this.dogInstances.add(dog);
//                }
//            }
//        }
//
//        //call readDog and print
//        public static void main(String[] args) throws IOException {
//            test_concurrent_MakeInteraction inputDogs = new test_concurrent_MakeInteraction();
//            inputDogs.readDogTextFile(fileName);
//
//            Iterator dogsIterator = inputDogs.dogInstances.iterator();
//
//            while (dogsIterator.hasNext()) {
//                Dog dogEntity = (Dog) dogsIterator.next();
//                System.out.println(dogEntity.getPetName());
//
//            }
//    }
//}
