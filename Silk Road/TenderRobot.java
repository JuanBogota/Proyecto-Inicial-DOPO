
/**
 * Robot tender that only takes half the time from stores.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class TenderRobot extends Robot {

    /**
     * Robot tender builder.
    * @param location initial position
     */
    public TenderRobot(int location) {
        super(location);
    }

    
    /**
     * Returns the default color associated with this object.
     *
     * @return a string representing the default color, which is "yellow".
     */
    private String getDefaultColor() {
        return "yellow"; 
    }

    /**
     * Collects half of the tenges from the given store if the robot is allowed 
     * to take them. The robot only adds half of the store's tenges to its own 
     * total and does not empty the store.
     *
     * @param store the store from which to collect tenges
     */
    public void collectFrom(Store store) {
        if (store.canRobotTake(this)) {
            int half = store.getTenges() / 2;
            collectTenges(half);

        }
    }

    /**
     * Returns the type of this object.
     *
     * @return a string representing the type, which is "tender".
     */
    @Override
    public String getType() {
        return "tender";
    }

}