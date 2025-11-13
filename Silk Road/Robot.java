
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

    private boolean isVisible;
    private String color;
    private Circle size;
    private int initialPosition;
    private int collectedTenges;
    private int distanceTraveled;
    private int location;

    /**
     * Constructs a robot at a given initial location on the road.
     * @param location the initial position of the robot on the road
     */
    public Robot(int location) {
        this.location = location;
        this.initialPosition = location;
        this.color = "blue";
        this.isVisible = false;
        this.size = new Circle();
        this.size.changeSize(10);
        this.size.makeVisible();
        this.size.moveHorizontal(location);
        this.collectedTenges = 0;
        this.distanceTraveled = 0;
    }

    /**
     * Changes the color of the robot in the graphical interface.
     * @param newColor the new color for the robot
     */
    public void changeColor(String newColor){
        this.color = newColor;
        this.size.changeColor(newColor);
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
    public void reboot() throws SilkRoadException {
        if(this.location != initialPosition || this.distanceTraveled != 0){
            throw new SilkRoadException(SilkRoadException.FAILED_REBOOT_ROBOT);
        }
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
        this.size.moveHorizontal(meters);
        this.distanceTraveled = distanceTraveled + meters;
        this.collectedTenges = collectedTenges - Math.abs(meters);
        this.location = this.location + meters;
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
        int totalProfit = this.collectedTenges;
        return totalProfit;
    }

    public void collectTenges(int tenges) {
        this.collectedTenges += tenges;
    }
}
