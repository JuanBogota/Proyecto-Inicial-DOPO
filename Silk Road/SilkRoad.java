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
    private String days;

    /**
    * Constructor for objects of class SilkRoad
    */
    public SilkRoad(int length){

        robots = new ArrayList<Robot>();
        stores = new ArrayList<Store>();
        roads = new ArrayList<Road>();
        this.length = Math.max(0, length);
        this.days = null;
        //ciclo 3
        
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
}