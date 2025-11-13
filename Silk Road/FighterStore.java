/**
 * Store class represents a fighter store in the Silk Road simulation.
 * 
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class FighterStore extends Store {

    /**
     * Constructor for the FighterStore class.
     * @param position posisition of the store
     * @param initialTenges initial tenges
     */
    public FighterStore(int position, int initialTenges) {
        super(position, initialTenges);
    }

    /**
     * Returns the default color of the store.
     * @return the default color
     */
    public String getDefaultColor() {
        return "red";
    }

    /**
     * If a robot can take tenges from this store.
     * @param robot the robot trying to take tenges
     * @return true if the robot has more collected tenges than the store has
     */
    @Override
    public boolean canRobotTake(Robot robot) {
        return robot.getCollectedTenges() > this.getTenges();
    }

    /**
     * Returns the type of the store.
     * @return the type of the store
     */
    @Override
    public String getType() {
        return "fighter";
    }
}