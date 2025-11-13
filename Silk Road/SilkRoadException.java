
/**
 * A class representing exceptions specific to the Silk Road application.
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class SilkRoadException extends Exception{

    public static final String DOES_NOT_HAVE_ENOUGH_TENGES = "The robot does not have enough tenges.";
    public static final String ROBOT_MOVEMENT_FAILED = "Failed to move robots automatically.";
    public static final String INVALID_LOCATION = "The specified location is invalid.";
    public static final String STORE_NOT_FOUND = "No store found at the specified location.";
    public static final String ROBOT_NOT_FOUND = "No robot found at the specified location.";
    public static final String FINISH_FAILED = "Failed to finish the day properly.";
    public static final String FAILDED_RESUPPLY = "Failed to resupply the store";
    public static final String FAILED_REBOOT_ROBOT = "Failed to reboot the roboot";
    public static final String DONT_HAVE_DAY = "No have days to simulate"; 
    
    /**
     * Constructor for objects of class SilkRoadException
     * @param message The detail message for the exception.
     */
    public SilkRoadException(String message)
    {
        super(message);
    }
}
