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

    /**
    * Constructor for objects of class SilkRoad
    */
    public SilkRoad(int length){

        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.length = Math.max(0, length);
        this.days = 0;
            // Límites de la espiral
        int left = 0, right = (int)Math.ceil(Math.sqrt(length)) - 1;
        int top = 0, bottom = right;
        int x = 0, y = 0;
        int dir = 0; // 0: derecha, 1: abajo, 2: izquierda, 3: arriba

        for(int i = 0; i < length; i++){
            Road road = new Road();
            roads.add(road);
            road.makeVisible();
            road.getStreet().moveHorizontal(x * 40);
            road.getStreet().moveVertical(y * 40);

            // Cambia dirección y límites
            switch(dir){
                case 0: // Derecha
                    if(x < right) x++;
                    else { dir = 1; top++; y++; }
                    break;
                case 1: // Abajo
                    if(y < bottom) y++;
                    else { dir = 2; right--; x--; }
                    break;
                case 2: // Izquierda
                    if(x > left) x--;
                    else { dir = 3; bottom--; y--; }
                    break;
                case 3: // Arriba
                    if(y > top) y--;
                    else { dir = 0; left++; x++; }
                    break;
            }
        }   
    }
    
    /**
     * Returns the length of the Silk Road.
     * @return the length of the Silk Road
     */
    public int getLength(){
        return length;
    }

    /**
     * Places a store at a given location with a specified amount of tenges.
     * 
     * @param location the position of the store on the road
     * @param tenges the initial amount of tenges in the store
     */
    public void placeStore(int location, int tenges){
        int initialPosition = location;
        int initialTenges = tenges;
        Store store = new Store(initialPosition, initialTenges);
        stores.add(store);
    }

    /**
     * Removes a store at a given location.
     * 
     * @param location the position of the store to be removed
     * @param tenges the amount of tenges in the store to be removed
     */
    public void removeStore(int location, int tenges){
        for(int i = 0; i < stores.size(); i++){
            if(stores.get(i).getLocation() == location){
                stores.get(i).makeInvisible();
                stores.remove(i);
                break;
            }
        }
    }

    /**
     * Places a robot at a given location on the Silk Road.
     * 
     * @param location the position of the robot on the road
     */
    public void placeRobot(int location){
        for(int i = 0; i < robots.size(); i++){
            if(robots.get(i).getLocation() != location){
                Robot robot = new Robot(location);
                robots.add(robot);
                break;
            }
        }
    }

    /**
     * Removes a robot at a given location.
     * @param location the position of the robot to be removed
     */
    public void removeRobot(int location){
        for(int i = 0; i < robots.size(); i++){
            if(robots.get(i).getLocation() == location){
                robots.get(i).makeInvisible();
                robots.remove(i);
                break;
            }
        }
    }

    /**
     * Moves a robot at a given location by a specified number of meters.
     * @param location the position of the robot to be moved
     * @param meters the number of meters to move the robot
     */
    public void moveRobots(int location, int meters){
        for(Robot robot : robots) {
        if(robot.getLocation() == location) {
            try {
                // Si meters es positivo, mover el robot hacia adelante
                for(int i = 0; i < Math.abs(meters); i++) {
                    robot.moveTo();
                }
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
    public void resupplyStores(){
        for(Store store : stores){
            store.resupply();
        }
    }   

    /**
     * Reboots all robots to their initial positions
     */
    public void reboot(){
        for(Robot robot : robots){
            robot.rebootRobot();
        }
        for(Store store : stores){
            store.rebootStore();
        }
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