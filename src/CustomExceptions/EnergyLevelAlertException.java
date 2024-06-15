package CustomExceptions;

public class EnergyLevelAlertException extends Exception {
    public EnergyLevelAlertException() {
        super("Energy level is too low. Feed before training.");
    }
}
