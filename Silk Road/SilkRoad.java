import java.util.ArrayList;

/**
 * Clase que representa la Ruta de la Seda
 * Maneja los robots y las tiendas en la ruta
 * 
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class SilkRoad {
    
    private ArrayList<Robot> robots;
    private ArrayList<Store> stores;
    private ArrayList<Road> roads;
    private int length;
    private int days;
    private boolean lastOperationOk;

    /**
    * Constructor for objects of class SilkRoad
    */
    public SilkRoad(int length){

        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.length = Math.max(0, length);
        this.days = 0;
    }
    

    /**
     * Places a store at a given location with a specified amount of tenges.
     * 
     * @param location the position of the store on the road
     * @param tenges the initial amount of tenges in the store
     */
    public void placeStore(int location, int tenges) {
        lastOperationOk = false;
        if (location >= 0 && location <= length && tenges >= 0) {
            Store store = new Store(location, tenges);
            stores.add(store);
            lastOperationOk = true;
        }
    }

    /**
     * Removes a store at a given location.
     * 
     * @param location the position of the store to be removed
     * @param tenges the amount of tenges in the store to be removed
     */
    public void removeStore(int location) {
        lastOperationOk = false;
        for(int i = 0; i < stores.size(); i++) {
            if(stores.get(i).getLocation() == location) {
                stores.remove(i);
                lastOperationOk = true;
                break;
            }
        }
    }

    /**
     * Places a robot at a given location on the Silk Road.
     * 
     * @param location the position of the robot on the road
     */
    public void placeRobot(int location) {
        lastOperationOk = false;
        if (location >= 0 && location <= length) {
            boolean positionOccupied = false;
            for(Robot robot : robots) {
                if(robot.getLocation() == location) {
                    positionOccupied = true;
                    break;
                }
            }
            if (!positionOccupied) {
                Robot robot = new Robot(location);
                robots.add(robot);
                lastOperationOk = true;
            }
        }
    }

    /**
     * Removes a robot at a given location.
     * @param location the position of the robot to be removed
     */
    public void removeRobot(int location) {
        lastOperationOk = false;
        for(int i = 0; i < robots.size(); i++) {
            if(robots.get(i).getLocation() == location) {
                robots.remove(i);
                lastOperationOk = true;
                break;
            }
        }
    }

    /**
     * Moves a robot at a given location by a specified number of meters.
     * @param location the position of the robot to be moved
     * @param meters the number of meters to move the robot
     */
    public void moveRobot(int location, int meters) {
        lastOperationOk = false;
        for(Robot robot : robots) {
            if(robot.getLocation() == location) {
                try {
                    for(int i = 0; i < Math.abs(meters); i++) {
                        robot.moveTo();
                    }
                    lastOperationOk = true;
                    break;
                } catch(SilkRoadException e) {
                    System.out.println("Error al mover el robot: " + e.getMessage());
                    break;
                }
            }
        }
    }

    /**
     * Resupplies all stores on the Silk Road
     */
    public void resupplyStores() {
        lastOperationOk = false;
        try {
            for(Store store : stores) {
                store.resupply();
            }
            lastOperationOk = true;
        } catch(Exception e) {
            System.out.println("Error al reabastecer las tiendas: " + e.getMessage());
        }
    }   

    /**
     * Returns all robots to their starting positions
     */
    public void returnRobots() {
        lastOperationOk = false;
        try {
            for(Robot robot : robots) {
                robot.rebootRobot();
            }
            lastOperationOk = true;
        } catch(Exception e) {
            System.out.println("Error al retornar los robots: " + e.getMessage());
        }
    }

    /**
     * Reboots all robots to their initial positions
     */
    public void reboot() {
        lastOperationOk = false;
        try {
            for(Robot robot : robots) {
                robot.rebootRobot();
                robot.resetCollectedTenges();
            }
            for(Store store : stores) {
                store.resupply();
            }
            lastOperationOk = true;
        } catch(Exception e) {
            System.out.println("Error al reiniciar el sistema: " + e.getMessage());
        }
    }

    /**
     * Calculates the profits for all robots on the Silk Road
     */
    public int porfit(){
        int totalProfit = 0;
        for(Robot robot : robots){
            totalProfit += robot.getProfit();
        }
        return totalProfit;
    }

    /**
     * Returns a 2D array with the location and tenges of each store.
     * @return a 2D array with store data
     */
    public int[][] stores(){
        int[][] storeData = new int[stores.size()][2];
        for(int i = 0; i < stores.size(); i++){
            storeData[i][0] = stores.get(i).getLocation();
            storeData[i][1] = stores.get(i).getTenges();
        }
        return storeData;
    }   

    /**
     * Returns a 2D array with the location and profit of each robot.
     * @return a 2D array with robot data
     */
    public int[][] robots(){
        int[][] robotData = new int[robots.size()][2];
        for(int i = 0; i < robots.size(); i++){
            robotData[i][0] = robots.get(i).getLocation();
            robotData[i][1] = robots.get(i).getProfit();
        }
        return robotData;
    }

    /**
     * Makes all robots and stores invisible.
     */
    public void makeInvisible(){
        for(Robot robot : robots){
            robot.makeInvisible();
        }
        for(Store store : stores){
            store.makeInvisible();
        }
    }

    /**
     * Makes all robots and stores visible.
     */
    public void makeVisible(){
        for(Robot robot : robots){
            robot.makeVisible();
        }
        for(Store store : stores){
            store.makeVisible();
        }
    }

    /**
     * Method to finish the simulator.
     */
    public void finishSimulator(){
        days += 1;
        for(Robot robot : robots){
            robot.finishDay();
        }
        for(Store store : stores){
            store.finishDay();
        }
    }

    /**
     * Indicates whether the last operation was successfully completed
     * @return true if the last operation was successful, false otherwise
     */
    public boolean ok(){
        return lastOperationOk;
    }

    /**
     * Returns the list of robots on the Silk Road.
     * @return the list of robots
     */
    public ArrayList<Robot> getRobots(){
        return robots;
    }

    /**
     * Returns the list of stores on the Silk Road.
     * @return the list of stores
     */
    public ArrayList<Store> getStores(){
        return stores;
    }

    /**
     * Returns the list of roads on the Silk Road.
     * @return the list of roads
     */
    public ArrayList<Road> getRoads(){
        return roads;
    }
    
     /**
     * Returns the length of the Silk Road.
     * @return the length of the Silk Road
     */
    public int getLength(){
        return length;
    }

    /**
     * Sorts an array of robots by their current location in ascending order.
     * 
     * @param robots array of robots to sort
     */
    public static void orderRobots(Robot[] robots) {
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