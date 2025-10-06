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
    private int tenges;
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
        this.tenges = 0;
        this.color = "blue";
        this.isVisible = false;
        this.size = new Circle();
        this.size.makeVisible();
        this.initialPosition = location;
        this.collectedTenges = 0;
        this.distanceTraveled = 0;
    }

    /**
     * Returns the current position of the robot.
     * @return current position
     */
    public int getLocation() {
        return location;
    }

    /**
     * Makes the robot invisible in the graphical interface.
     */
    public void makeInvisible() {
        size.makeInvisible();
        this.isVisible = false;
    }

    /**
     * Moves the robot by a given number of meters.
     * Positive values move to the right, negative to the left.
     * 
     * @param meters the number of meters to move
     */
    public void moveTo(int meters) {
        this.location += meters;
        this.distanceTraveled += Math.abs(meters);
    }

    /**
     * Resets the robot to its initial position and clears the traveled distance.
     */
    public void resetToInitialPosition() {
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
     * Returns the amount of tenges collected (alias of getCollectedTenges).
     * @return tenges collected
     */
    public int getTenges() {
        return collectedTenges;
    }

    /**
     * Makes the robot visible in the graphical interface.
     */
    public void makeVisible() {
        this.isVisible = true;
    }

    

    /**
     * Increases the number of collected tenges by one.
     * Useful when tenges are collected individually.
     */
    public void collectTenge() {
        this.collectedTenges++;
    }

    /**
     * Sorts an array of robots by their current location in ascending order.
     * 
     * @param robots array of robots to sort
     */
    public static void ordenarRobots(Robot[] robots) {
        int n = robots.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (robots[j].getLocation() > robots[j + 1].getLocation()) {
                    Robot temp = robots[j];
                    robots[j] = robots[j + 1];
                    robots[j + 1] = temp;
                }
            }
        }
    }
}
