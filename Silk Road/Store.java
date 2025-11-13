
/**
 * Class Store represents a store in the Silk Road simulation.
 * 
 * @author Nicolas Felipe Bernal Gallo
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0
 */
public class Store{

    private int location;
    private int tenges;
    private boolean isVisible;
    private String color;
    private int initialTenges;
    private Rectangle size;
    
    /**
     * Constructor of the Store class.
     * @param position Initial position of the store
     * @param initialTenges Initial amount of tenges in the store
     */
    public Store(int position, int initialTenges){
        this.location = position;
        this.tenges = initialTenges;
        this.color = "green";
        this.size = new Rectangle(position, 20);
        this.size.changeSize(10, 10);
        this.size.makeVisible();
    }

    /**
     * Empties the store of all tenges.
     */
    public void empty(){
        this.tenges = 0;
        changeColor("gray");
    }

    public void changeColor(String newColor){
        this.color = newColor;
        this.size.changeColor(newColor);
    }
    
    /**
     * Resupplies the store with 100 additional tenges.
     */
    public void resupply() throws SilkRoadException {
        if(this.tenges != initialTenges){
            throw new SilkRoadException(SilkRoadException.FAILDED_RESUPPLY);
        }
        this.tenges = initialTenges;
    }

    /**
     * Make this store invisible.
     */
    public void makeInvisible(){
        size.makeInvisible();
        isVisible = false;
    }

    /**
     * Make this store visible.
     */
    public void makeVisible(){
        size.makeVisible();
        isVisible = true;
    }

    /**
     * Returns the location of the store.
     * @return the location of the store
     */
    public int getLocation() {
        return location;
    }

    /**
     * Returns the amount of tenges in the store.
     * @return the amount of tenges in the store
     */
    public int getTenges(){
        return tenges;
    }

    /**
     * Returns the initial amount of tenges in the store.
     * @return the initial amount of tenges
     */
    public int getInitialTenges(){
        return initialTenges;
    }

    /**
     * Returns the color of the store.
     * @return the color of the store
     */
    public String getColor(){
        return color;
    }

    /**
     * Determines if a robot can take tenges from this store.
     * @param robot the robot trying to take tenges
     * @return false by default, to be overridden in subclasses
     */
    public boolean canRobotTake(Robot robot) {
        return false;
    }

    /**
     * Returns the type of the store.
     * @return the type of the store
     */
    public String getType() {
        return "normal";
    }
}   

