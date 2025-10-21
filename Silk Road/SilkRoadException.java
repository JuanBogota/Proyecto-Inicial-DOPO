
/**
 * A class representing exceptions specific to the Silk Road application.
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class SilkRoadException extends Exception
{
    // instance variables - replace the example below with your own
    public static final String DOES_NOT_HAVE_ENOUGH_TENGES = "The robot does not have enough tenges.";
    
    public SilkRoadException(String message)
    {
        super(message);
    }
}
