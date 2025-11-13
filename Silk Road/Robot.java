
/**
 * Abstract class representing a robot in the Silk Road simulation.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class Robot {

    protected boolean isVisible;
    protected String color;
    protected Circle size;
    protected int initialPosition;
    protected int collectedTenges;
    protected int distanceTraveled;
    protected int location;

    /**
     * Constructor del robot.
     * @param location position inicial to robot
     */
    public Robot(int location) {
        this.location = location;
        this.initialPosition = location;
        this.isVisible = false;
        this.size = new Circle();
        this.size.changeSize(10);
        this.size.changeColor(color);
        this.size.makeVisible();
        this.size.moveHorizontal(location);
        this.collectedTenges = 0;
        this.distanceTraveled = 0;
    }


    /**
     * Change the robot's color.
     * @param newColor A new Color
     */
    public void changeColor(String newColor) {
        this.color = newColor;
        this.size.changeColor(newColor);
    }

    /**
     * The robot makes invisible.
     */
    public void makeInvisible() {
        size.makeInvisible();
        this.isVisible = false;
    }

    /**
     * It makes the robot visible.
     */
    public void makeVisible() {
        this.isVisible = true;
    }

    /**
     * Reset the robot to its initial position.
     */
    public void reboot() throws SilkRoadException {
        if (this.location != initialPosition) {
            int distance = initialPosition - this.location;
            this.size.moveHorizontal(distance);
            this.location = initialPosition;
        }
        this.distanceTraveled = 0;
    }

    /**
     * Reset the collected tenges.
     */
    public void resetCollectedTenges() {
        this.collectedTenges = 0;
    }

    /**
     * Move the robot a certain distance.
     * @param meters distance to move
     */
    public void moveTo(int meters) throws SilkRoadException {
        this.size.moveHorizontal(meters);
        this.distanceTraveled += Math.abs(meters);
        this.collectedTenges -= Math.abs(meters);
        this.location += meters;
    }

    /**
     * Gets the robot's current location.
     * @return to location
     */
    public int getLocation() {
        return location;
    }

    /**
     * He obtains the collected tenge.
     * @return the collected tenge
     */
    public int getCollectedTenges() {
        return collectedTenges;
    }

    /**
     * It obtains the distance traveled.
     * @return the distance in meters
     */
    public int getDistanceTraveled() {
        return distanceTraveled;
    }
    
    /**
     * gets the robot's total profit.
     * @return the profit
     */
    public int getProfit() {
        return this.collectedTenges;
    }

    /**
     * Add tenges to the robot.
     * @param tenges Number of tenge to add
     */
    protected void collectTenges(int tenges) {
        this.collectedTenges += tenges;
    }

    /**
     * Returns the type of the robot.
     * @return the type of the robot
     */
    public String getType() {
        return "normal";
    }
}