package Pet;

public class InteractWith<T extends FacadePet> {
    private T pet;

    public InteractWith(T pet) {this.pet = pet; }

    public T getInteraction() {return pet; }
}