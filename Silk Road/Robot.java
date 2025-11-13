import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class representing a robot in the Silk Road simulation.
 * 
 * A robot has:
 *   A current position on the road (location).
 *   An initial position to which it can return (initialPosition).
 *   The amount of tenges collected during a "day".
 *   The total distance traveled in meters.
 *   Visual attributes (color, circle shape, visibility).
 * 
 * The robot can move, collect tenges from a store, reset to its initial state,
 * and be shown or hidden in the graphical representation.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class Robot {

    private int location;
    private boolean isVisible;
    private String color;
    private Circle size;
    private int initialPosition;
    private int collectedTenges;
    private int distanceTraveled;

    /**
     * Constructs a robot at a given initial location on the road.
     * @param location the initial position of the robot on the road
     */
    public Robot(int location) {
        this.location = location;
        this.color = "blue";
        this.isVisible = false;
        this.size = new Circle();
        this.size.makeVisible();
        this.initialPosition = location;
        this.collectedTenges = 0;
        this.distanceTraveled = 0;
    }

    
    /**
     * Makes the robot invisible in the graphical interface.
     */
    public void makeInvisible() {
        size.makeInvisible();
        this.isVisible = false;
    }

    /**
     * Makes the robot visible in the graphical interface.
     */
    public void makeVisible() {
        this.isVisible = true;
    }

    /**
     * Resets the robot to its initial position and clears the traveled distance.
     */
    public void reboot() {
        this.location = initialPosition;
        this.distanceTraveled = 0;
    }

    /**
     * Resets the amount of collected tenges to zero.
     */
    public void resetCollectedTenges() {
        this.collectedTenges = 0;
    }

    /**
     * Moves the robot one unit to the right, increases distance traveled,
     * and decreases tenges by one.
     */
    public void moveTo(int meters) throws SilkRoadException {
        if (this.collectedTenges <= 0) {
            throw new SilkRoadException(SilkRoadException.DOES_NOT_HAVE_ENOUGH_TENGES);
        }
        this.location = meters;
        this.distanceTraveled += 1;
        this.collectedTenges -= 1;
    }

    /**
     * Returns the current position of the robot.
     * @return current position
     */
    public int getLocation() {
        return location;
    }

    /**
     * Returns the amount of tenges collected during the day.
     * @return tenges collected
     */
    public int getCollectedTenges() {
        return collectedTenges;
    }

    /**
     * Returns the total distance traveled during the day.
     * @return distance in meters
     */
    public int getDistanceTraveled() {
        return distanceTraveled;
    }
    
    /**
     * Returns the total profit (collected tenges) of the robot.
     * @return profit in tenges
     */
    public int getProfit() {
        int totalProfit = this.collectedTenges - this.distanceTraveled;
        return totalProfit;
    }
}
