package globalEntities;
import java.io.Serializable;

public abstract class PPPEntity implements Serializable {
    public String entityName;
//    private FacadePet ownedPet;
    //Default constructor: Allows creating an object without setting its sernName initially. You can set the name later
    public PPPEntity() {
    }

    //Parameterized Constructor: Allows creating an object and setting its userName at the time of creation.
    public PPPEntity(String aName) {
        this.entityName = aName;
    }

    public abstract void describe();

}

