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
    private int robotColorIndex;
    private int storeColorIndex;
    private static final String[] COLORS = {
        "red", "black", "blue", "green", "yellow", "orange", "cyan", "magenta", "grey", "white"
    };

    /**
    * Constructor for objects of class SilkRoad
    */
    public SilkRoad(int length){
        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.length = Math.max(0, length);
        this.days = 0;
        this.robotColorIndex = 0;
        this.storeColorIndex = 0;
        generateRoadSpiral();
    }
    
    /**
     * Generates a spiral road layout for the Silk Road.
     */
    private void generateRoadSpiral(){
        int x = 50;
        int y = 50;
        int dx = 50;
        int dy = 0;
        int segmentLength = 1;
        int segmentPassed = 0;
        
        for (int i = 0; i < length; i++) {
            Road road = new Road();
            int currentX = road.getStreet().getXPosition();
            int currentY = road.getStreet().getYPosition();
            road.moveHorizontal(x - currentX);
            road.moveVertical(y - currentY);
            if (dy != 0) {
                road.rotateRoad();
            }
            
            roads.add(road);
        
            x += dx;
            y += dy;
            segmentPassed++;
            
            if (segmentPassed == segmentLength) {
                segmentPassed = 0;
                int temp = dx;
                dx = -dy;
                dy = temp;
                if (dy == 0) {
                    segmentLength++;
                }
            }
        }
    }

        /**
     * Gets the next color for a robot.
     * @return the next available color
     */
    private String getNextRobotColor() {
        String color = COLORS[robotColorIndex % COLORS.length];
        robotColorIndex++;
        return color;
    }
    
    /**
     * Gets the next color for a store.
     * @return the next available color
     */
    private String getNextStoreColor() {
        String color = COLORS[storeColorIndex % COLORS.length];
        storeColorIndex++;
        return color;
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
            String color = getNextStoreColor();
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
                String color = getNextRobotColor();
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
        for(Road road : roads){
            road.makeInvisible();
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
        for(Road road : roads){
            road.makeVisible();
        }
    }

    /**
     * Method to finish the simulator.
     */
    public void finish(){
        lastOperationOk = false;
        try {
            makeInvisible();
            robots.clear();
            stores.clear();
            roads.clear();
            days = 0;
            robotColorIndex = 0;
            storeColorIndex = 0;
            lastOperationOk = true;
        } catch(Exception e) {
            System.out.println("Error al finalizar el simulador: " + e.getMessage());
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
}