import java.util.List;
import java.util.Collections;
import java.util.Comparator;

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
    
    /**
     * Constructor de la tienda
     * 
     * @param initialPosition Posición inicial de la tienda en la ruta
     * @param initialTenges Cantidad inicial de tenges en la tienda
     */
    public Store(int initialPosition, int initialTenges){
        this.size = new Rectangle(initialPosition, 20);
        this.size.makeVisible();
        this.location = initialPosition;
        this.tenges = initialTenges;
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
     * Resupplies the store with 100 additional tenges.
     */
    public void resupply(){
        this.tenges += 100;
    }

    /**
     * Make this store invisible.
     */
    public void makeInvisible(){
        size.makeInvisible();
        isVisible = false;
    }


    public static void ordenarStores(Store[] stores) {
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

