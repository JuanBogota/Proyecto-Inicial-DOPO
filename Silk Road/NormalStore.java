
/**
 * Class representing a normal store in the Silk Road game.
 * 
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class NormalStore extends Store {

    /**
     * Constructor for the normalStore class.
     * @param position posisition of the store
     * @param initialTenges initial tenges
     */
    public NormalStore(int position, int initialTenges) {
        super(position, initialTenges);
    }

    /**
     * If a robot can take tenges from this store.
     * @param robot the robot trying to take tenges
     * @return true always for normal stores
     */
    @Override 
    public boolean canRobotTake(Robot robot) {
        return true;
    }

    /**
     * Returns the default color of the store.
     * @return the default color
     */
    public String getDefaultColor() {
        return "green";
    }

    /**
     * Returns the type of the store.
     * @return the type of the store
     */
    @Override 
    public String getType() {
        return "normal";
    }
}
