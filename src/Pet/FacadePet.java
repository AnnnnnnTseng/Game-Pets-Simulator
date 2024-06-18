package Pet;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class FacadePet extends Thread implements Serializable {

    // allows for a centralized management of all action instances.
    public static ArrayList<FacadePet> FacadePetInstances = new ArrayList<FacadePet>();
    public static String type;

    private String petName;
    private int age;
    private int mood = 80;
    private int energyLevel = 80;
    private int gramsPerFeed;

    public FacadePet(String petName, int age) {
        this.petName = petName;
        this.age = age;
        //Each Action instance adds itself to the static theActions list upon creation. This static list can be accessed
        // and manipulated by any instance of Action.
        FacadePetInstances.add(this);
    }

    //methods
    public void hungry() {
        this.energyLevel -= 10;
    }

    public void bored() {
        this.mood -= 10;
    }

    // Getters and Setters
    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getGramsPerFeed() {
        return gramsPerFeed;
    }

    public void setGramsPerFeed(int grams) {
        this.gramsPerFeed += grams;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyTooAdd) {
        this.energyLevel += energyTooAdd;
    }



    //abstract methods
    public abstract void getTrain();

    public abstract void getFeed();

//    public abstract void getPlay();
//
//    public void sleep() {
//        System.out.println(this.petName + " falls asleep");
//    }

    //Contain the specific code to be executed in a new thread when start() is called on an Action instance.
     public abstract void run();

}
