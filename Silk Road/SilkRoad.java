import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing the Silk Road simulation.
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
    private HashMap<Integer, Integer> storeEmptiedCount;
    private HashMap<Integer, ArrayList<Integer>> robotProfitPerMove;
    private static final String[] COLORS = {
        "red", "blue", "green", "yellow", "orange", "cyan", "magenta", "grey", "white"
    };
    private static final String EMPTY_STORE_COLOR = "black";


    /**
    * Constructor for objects of class SilkRoad
    * @param length Length of the Silk Road
    */
    public SilkRoad(int length){
        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.length = Math.max(0, length);
        this.days = 0;
        this.robotColorIndex = 0;
        this.storeColorIndex = 0;
        this.storeEmptiedCount = new HashMap<Integer,Integer>();
        this.robotProfitPerMove = new HashMap<Integer,ArrayList<Integer>>();
        generateRoadSpiral();
    }

    /**
     * Constructor for objects of class SilkRoad from marathon input format.
     * Processes an array of actions where each action can be:
     * [1, location]: add robot at location
     * [2, location, tenges]: add store at location with tenges
     * 
     * @param actions array of actions in marathon format
     */
    public SilkRoad(int[][] days){
        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.days = 0;
        this.robotColorIndex = 0;
        this.storeColorIndex = 0;
        this.storeEmptiedCount = new HashMap<Integer, Integer>();
        this.robotProfitPerMove = new HashMap<Integer, ArrayList<Integer>>();
        int maxLocation = 0;
        for (int[] day : days) {
            if (day.length >= 2 && day[1] > maxLocation) {
                maxLocation = day[1];
            }
        }
        this.length = maxLocation;
        generateRoadSpiral();
        for (int[] day : days) {
            if(day[0] == 1 && day.length >= 2) {
                placeRobot(day[1]);
            } else if(day[0] == 2 && day.length >= 3) {
                placeStore(day[1], day[2]);
            }
            if(this.days > 0) {
                resupplyStores();
                returnRobots();
            }
            this.days++;
        }
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
     * @param location the position of the store on the road
     * @param tenges the initial amount of tenges in the store
     */
    public void placeStore(int location, int tenges) {
        lastOperationOk = false;
        if (location >= 0 && location <= length && tenges >= 0) {
            String color = getNextStoreColor();
            Store store = new Store(location, tenges);
            store.changeColor(color);
            stores.add(store);
            lastOperationOk = true;
        }
    }

    /**
     * Removes a store at a given location.
     * @param location the position of the store to be removedd
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
                robot.changeColor(color);
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
     * Moves all robots towards their nearest store.
     */
    public void moveRobots() throws SilkRoadException {
        lastOperationOk = false;
        try {
            for(Robot robot : robots) {
                Store bestStore = findNearStoreForRobot(robot);
                if(bestStore != null) {
                    int distance = bestStore.getLocation() - robot.getLocation();
                    moveRobot(robot.getLocation(), distance);
                    stealTenges();
                }
            }
            lastOperationOk = true;
        } catch(SilkRoadException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Finds the best store for a robot to visit based on profit.
     * @param robot the robot for which to find the best store
     * @return the best store for the robot
     */
    private Store findBestStoreForRobot(Robot robot) {
        Store bestStore = null;
        int maxProfit = 0;

        for(Store store : stores) {
            if(store.getTenges() > 0){
                int distance = Math.abs(store.getLocation() - robot.getLocation());
                int profit = store.getTenges() - distance;
                if(profit > maxProfit) {
                    maxProfit = profit;
                    bestStore = store;
                }
            }
        }
        return bestStore;
    }

    /**
     * Finds the best store for a robot to visit based on profit.
     * @param robot the robot for which to find the best store
     * @return the best store for the robot
     */
    private  Store findNearStoreForRobot(Robot robot) {
        Store nearestStore = null;
        int minDistance = Integer.MAX_VALUE;

        for(Store store : stores) {
            if(store.getTenges() > 0){
                int distance = Math.abs(store.getLocation() - robot.getLocation());
                if(distance < minDistance) {
                    minDistance = distance;
                    nearestStore = store;
                }
            }
        }
        return nearestStore;
    }

    public Store test(){
        Store store = null;
        for(Robot robot : robots) {
                Store bestStore = findBestStoreForRobot(robot);
                store = bestStore;
        }
        
        return store;
    }

    /**
     * Moves a robot at a given location by a specified number of meters.
     * @param location the position of the robot to be moved
     * @param meters the number of meters to move the robot
     */
    public void moveRobot(int location, int meters) throws SilkRoadException {
        lastOperationOk = false;
        for(Robot robot : robots) {
            if(robot.getLocation() == location) {
                try {
                    robot.moveTo(meters);
                    lastOperationOk = true;
                    stealTenges();
                } catch (SilkRoadException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else{
                try {
                    throw new SilkRoadException(SilkRoadException.ROBOT_NOT_FOUND);
                } catch (SilkRoadException e) {
                    System.out.println("Error: " + e.getMessage());
                } break;
            }
        }
    }

    /**
     * Steals tenges from stores when robots reach them.
     */
    private void stealTenges(){
        for(Robot robot : robots){
            for(Store store : stores){
                if(robot.getLocation() == store.getLocation() && store.getTenges() > 0){
                    robot.collectTenges(store.getTenges());
                    int location = store.getLocation();
                    storeEmptiedCount.put(location, storeEmptiedCount.getOrDefault(location, 0) + 1);
                    store.empty();
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
        } catch(SilkRoadException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }   

    /**
     * Returns all robots to their starting positions
     */
    public void returnRobots() {
        lastOperationOk = false;
        try {
            for(Robot robot : robots) {
                robot.reboot();
            }
            lastOperationOk = true;
        } catch(SilkRoadException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Reboots the Silk Road simulation to its initial state
     */
    public void reboot() {
        lastOperationOk = false;
        try {
            for(Robot robot : robots) {
                robot.reboot();
                robot.resetCollectedTenges();
            }
            for(Store store : stores) {
                store.resupply();
            }
            lastOperationOk = true;
        } catch(SilkRoadException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Calculates the profits for all robots on the Silk Road
     * @return total profit from all robots
     */
    public int porfit(){
        int totalProfit = 0;
        for(Robot robot : robots){
            totalProfit += robot.getProfit();
        }
        return totalProfit;
    }

    /**
     * Returns a 2D array with the location and number of times each store has been emptied.
     * @return a 2D array with emptied store data
     */
    public int[][] emptiedStores(){
        Store[] storeArray = new Store[stores.size()];
        stores.toArray(storeArray);
        orderStores(storeArray);
        int[][] result = new int[storeArray.length][2];
        for(int i = 0; i < storeArray.length; i++){
            int location = storeArray[i].getLocation();
            result[i][0] = location;
            result[i][1] = storeEmptiedCount.getOrDefault(location, 0);
        }
        return result;
    }

    /**
     * Returns the profit per move for each robot.
     * Robots are sorted by initial location in ascending order.
     * @return a 2D array where first column is initial location,
     * subsequent columns show profit after each move
     */
    public int[][] profitPerMove() {
        Robot[] robotArray = new Robot[robots.size()];
        robots.toArray(robotArray);

        orderRobots(robotArray);
        
        int maxMoves = 0;
        for(ArrayList<Integer> profits : robotProfitPerMove.values()) {
            if(profits.size() > maxMoves) {
                maxMoves = profits.size();
            }
        }

        int[][] result = new int[robotArray.length][maxMoves + 1];
        
        for(int i = 0; i < robotArray.length; i++) {
            int initialLocation = robotArray[i].getLocation();
            result[i][0] = initialLocation;
            
            ArrayList<Integer> profits = robotProfitPerMove.get(initialLocation);
            if(profits != null) {
                for(int j = 0; j < profits.size(); j++) {
                    result[i][j + 1] = profits.get(j);
                }
            }
        }
        
        return result;
    }

    /**
     * Returns a 2D array with the location and tenges of each store.
     * @return a 2D array with store data
     */
    public int[][] stores(){
        Store[] storeArray = new Store[stores.size()];
        stores.toArray(storeArray);

        orderStores(storeArray);

        int[][] storeData = new int[storeArray.length][2];
        for(int i = 0; i < storeArray.length; i++){
            storeData[i][0] = storeArray[i].getLocation();
            storeData[i][1] = storeArray[i].getTenges();

        }
        return storeData;
    }   

    /**
     * Returns a 2D array with the location and profit of each robot.
     * @return a 2D array with robot data
     */
    public int[][] robots(){

        Robot[] robotArray = new Robot[robots.size()];
        robots.toArray(robotArray);

        orderRobots(robotArray);


        int[][] robotData = new int[robotArray.length][2];
        for(int i = 0; i < robotArray.length; i++){
            robotData[i][0] = robotArray[i].getLocation();
            robotData[i][1] = robotArray[i].getCollectedTenges() - robotArray[i].getDistanceTraveled();
        }
        return robotData;
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
            System.out.println("Error: " + e.getMessage());
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
     * @param robots array of robots to sort
     */
    private static void orderRobots(Robot[] robots) {
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

    /**
     * To order an array of stores based on their location in ascending order.
     * @param stores
     */
    private static void orderStores(Store[] stores) {
        int n = stores.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (stores[j].getLocation() > stores[j + 1].getLocation()) {
                    Store temp = stores[j];
                    stores[j] = stores[j + 1];
                    stores[j + 1] = temp;
                    }
                }
            }
    }
}