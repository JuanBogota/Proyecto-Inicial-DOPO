
/**
 * Robot normal  have go to come back and take a tenges free.
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class NormalRobot extends Robot {

    /**
     * Constructor to robot normal.
     * @param location posicition inicial
     */
    public NormalRobot(int location) {
        super(location);
    }

    
    /**
     * Returns the default color associated with this object.
     * @return a string representing the default color, which is "blue".
     */
    public String getDefaultColor() {
        return "blue";
    }

    /**
     * Collects tenges from the given store if the robot is allowed to take them.
     * If the store permits it, the robot adds the store's tenges to its own count
     * and then empties the store.
     * @param store the store from which to collect tenges
     */
    public void collectFrom(Store store) {
        if (store.canRobotTake(this)) {
            collectTenges(store.getTenges());
            store.empty();
        }
    }

    /**
     * Returns the type of this object.
     * @return a string representing the type, which is "normal".
     */
    @Override
    public String getType() {
        return "normal";
    }

}