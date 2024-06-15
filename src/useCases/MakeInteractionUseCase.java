package useCases;
import Pet.*;
import users.ParadiseMayor;

public class MakeInteractionUseCase {

    public static void main(String[] args) {

        Dog myDog = new Dog("Puffy", 1);
        Rabbit myRabbit = new Rabbit("Mina", 5);

        InteractWith<Dog> DogInteraction = new InteractWith<>(myDog);
        InteractWith<Rabbit> RabbitInteraction = new InteractWith<>(myRabbit);

        ParadiseMayor.theParadiseMayor.TrainPet(DogInteraction);
        ParadiseMayor.theParadiseMayor.FeedPet(RabbitInteraction);

    }
}
