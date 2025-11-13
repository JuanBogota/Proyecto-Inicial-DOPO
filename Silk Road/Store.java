
/**
 * Clase que representa una tienda en la Ruta de la Seda
 * Maneja la ubicación, cantidad de tenges y representación visual
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
    private int initialPosition;
    private int initialTenges;
    private Rectangle size;
    private String originalColor;
    
    /**
     * Constructor of the Store class.
     * @param initialPosition Initial position of the store
     * @param initialTenges Initial amount of tenges in the store
     */
    public Store(int initialPosition, int initialTenges){
        this.location = initialPosition;
        this.tenges = initialTenges;
        this.initialPosition = initialPosition;
        this.initialTenges = initialTenges;
        this.color = "green";
        this.originalColor = this.color;
        this.size = new Rectangle(initialPosition, 20);
        this.size.makeVisible();
    }


    /**
     * Empties the store of all tenges.
     */
    public void empty(){
        this.tenges = 0;
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
     * Returns the initial position of the store.
     * @return the initial position of the store
     */
    public int getInitialPosition(){
        return initialPosition;
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
     * Returns the original color of the store.
     * @return the original color of the store
     */
    public String getOriginalColor(){
        return originalColor;
    }

}   

