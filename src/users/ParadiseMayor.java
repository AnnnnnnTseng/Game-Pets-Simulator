package users;

import Pet.*;
import globalEntities.PPPEntity;

//Inharitance, Polymorphism
public class ParadiseMayor extends PPPEntity {
    //Singleton Instance: ensuring that there is only one mayor
    public static ParadiseMayor theParadiseMayor = new ParadiseMayor();

    //Private constructor: preventing the creation of additional instances of Mayor.
    private ParadiseMayor() {
    super();
    }

    //Generic method: parameter "a_interaction" is and instance of "InteractWith<T>"
    // where T is an instance of a type extends FacadePet
    public <T extends FacadePet> void TrainPet(InteractWith<T> a_interaction) {
        // Generic because a_request anticipated to vary greatly with T
        a_interaction.getInteraction().getTrain();
    }

    public <T extends FacadePet> void FeedPet(InteractWith<T> a_interaction) {
        // Generic because a_request anticipated to vary greatly with T
        a_interaction.getInteraction().getFeed();
    }
    //concurrency
    public <T extends FacadePet> void ObservePets(InteractWith<T> a_interaction) {
        a_interaction.getInteraction().start();
    }

    @Override
	public void describe() {
		// TODO
	}
}
