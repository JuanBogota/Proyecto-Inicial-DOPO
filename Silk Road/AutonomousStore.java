import java.util.Random;

/**
 * Store type that autonomously selects its own position on the Silk Road.
 * 
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class AutonomousStore extends Store {

    private static Random random = new Random();

    /**
     * Constructor for autonomousStore.
     * @param initialTenges initial tenges
     * @param maxRoadLength maximum length of the road
     */
    public AutonomousStore(int initialTenges, int maxRoadLength) {
        super(chooseOwnPosition(maxRoadLength), initialTenges);
    }

    /**
     * Chooses its own position randomly within the maximum length.
     * @param maxLength maximum length of the road
     * @return the chosen position
     */
    private static int chooseOwnPosition(int maxLength) {
        return random.nextInt(maxLength + 1);
    }

    /**
     * Returns the default color of the store.
     * @return the default color
     */
    public String getDefaultColor() {
        return "magenta";
    }

    /**
     * If a robot can take tenges from this store.
     * @param robot the robot trying to take tenges
     * @return true always for autonomous stores
     */
    @Override
    public boolean canRobotTake(Robot robot) {
        return true;
    }

    /**
     * Returns the type of the store.
     * @return the type of the store
     */
    @Override
    public String getType() {
        return "autonomous";
    }
}