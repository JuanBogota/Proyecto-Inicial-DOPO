/**
 * Robot never back to inicial position.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class NeverbackRobot extends Robot {

    /**
     * Constructor de robot neverback.
     * @param location posicition inicial
     */
    public NeverbackRobot(int location) {
        super(location);
    }

    
/**
 * Returns the default color associated with this robot.
 *
 * @return a string representing the default color, which is "orange".
 */
protected String getDefaultColor() {
    return "orange"; 
}

/**
 * Resets the robot's traveled distance during a reboot operation.
 *
 * @throws SilkRoadException if an error occurs during the reboot process
 */
@Override
public void reboot() throws SilkRoadException {
    this.distanceTraveled = 0;
}

/**
 * Collects all the tenges from the given store if the robot is allowed
 * to take them. The robot adds the store's tenges to its own total and
 * then empties the store.
 *
 * @param store the store from which tenges are collected
 */
public void collectFrom(Store store) {
    if (store.canRobotTake(this)) {
        collectTenges(store.getTenges());
        store.empty();
    }
}

/**
 * Returns the type of this robot.
 *
 * @return a string representing the robot type, which is "neverback".
 */
@Override
public String getType() {
    return "neverback";
}

}