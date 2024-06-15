package useCases;

import Pet.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class GetPetsInputUseCase {

    // List store all pets input instances
    ArrayList<FacadePet> petsInstances = new ArrayList<>();
    public static final String fileName = "Pets_input.txt";
    public GetPetsInputUseCase() {
    }

    public void readTextFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            char category = parts[0].charAt(0);
            String petName = parts[1];
            int petAge = Integer.parseInt(parts[2]);
            if (category == 'R') {
                Rabbit rabbit = new Rabbit(petName, petAge);
                this.petsInstances.add(rabbit);
            } else if (category == 'D') {
                Dog dog = new Dog(petName, petAge);
                this.petsInstances.add(dog);
            }
        }
    }

    public void showExistingPets () throws IOException {
//        GetPetsInputUseCase useCase = new GetPetsInputUseCase();
//        useCase.readTextFile(fileName);
//
//        Iterator var4 = useCase.petsInstances.iterator();
//
//        while (var4.hasNext()) {
//            FacadePet entity = (FacadePet) var4.next();
//            System.out.println(entity.getPetName());
//        }

        System.out.println("Total Rabbit instances: " + Rabbit.RabbitInstances.size());
        Iterator rabbitIterator = Rabbit.RabbitInstances.iterator();
        while (rabbitIterator.hasNext()) {
            Rabbit rabbitEntity = (Rabbit) rabbitIterator.next();
            System.out.println("Rabbit: " + rabbitEntity.getPetName() + ", Age: " + rabbitEntity.getAge());
        }

        System.out.println("Total Dog instances: " + Dog.DogInstances.size());
        Iterator dogIterator = Dog.DogInstances.iterator();
        while (dogIterator.hasNext()) {
            Dog dogEntity = (Dog) dogIterator.next();
            System.out.println("Name: " + dogEntity.getPetName() + ", Age: " + dogEntity.getAge());
        }
    }

//    public static void main(String[] args) throws IOException {
//
//        GetPetsInputUseCase useCase = new GetPetsInputUseCase();
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
//    }
}
