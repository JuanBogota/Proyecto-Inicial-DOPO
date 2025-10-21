import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the SilkRoad class.
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */
public class SilkRoadTest {

    /**
     * Sets up the test fixture.
     * 
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Test the constructor and getLength method of SilkRoad.
     */
    @Test
    public void testConstructorAndGetLength(){
        SilkRoad silkRoad = new SilkRoad(10);
        assertEquals(10, silkRoad.getLength());
    }

    /**
     * Test the length and roads of SilkRoad.
     */
    @Test 
    public void testGetRoadsAndLength(){
        int n = 12;
        SilkRoad silkRoad = new SilkRoad(n);
        ArrayList<Road> roads = silkRoad.getRoads();
        assertEquals(n, roads.size());
        assertEquals(n, silkRoad.getLength());
    }

    /**
     * Test that the robots list is not null after SilkRoad construction.
     */
    @Test 
    public void testRobotsNoIsNull(){
        SilkRoad silkRoad = new SilkRoad(5);
        ArrayList<Robot> robots = silkRoad.getRobots();
        assertNotNull(robots);
    }

    /**
     * Test that the stores list is not null after SilkRoad construction.
     */
    @Test 
    public void testStoresNoIsNull(){
        SilkRoad silkRoad = new SilkRoad(5);
        ArrayList<Store> stores = silkRoad.getStores();
        assertNotNull(stores);
    }

    /**
     * Test adding a robot to the SilkRoad.
     */
    @Test 
    public void testPlaceRobot(){
        SilkRoad silkRoad = new SilkRoad(5);
        silkRoad.placeRobot(2);
        ArrayList<Robot> robots = silkRoad.getRobots();
        assertEquals(1, robots.size());
        assertEquals(2, robots.get(0).getLocation());
    }

    /**
     * Test adding a store to the SilkRoad.
     */
    @Test 
    public void testPlaceStore(){
        SilkRoad silkRoad = new SilkRoad(5);
        silkRoad.placeStore(2, 50);
        ArrayList<Store> stores = silkRoad.getStores();
        assertEquals(1, stores.size());
        assertEquals(2, stores.get(0).getLocation());
        assertEquals(50, stores.get(0).getTenges());
    }

    /**
     * Test removing a robot on the SilkRoad.
     */
    @Test 
    public void testRemoveRobot(){
        SilkRoad silkRoad = new SilkRoad(5);
        silkRoad.placeRobot(2);
        silkRoad.removeRobot(2);
        ArrayList<Robot> robots = silkRoad.getRobots();
        assertEquals(0, robots.size());
    }


    /**
     * Test removing a store on the SilkRoad.
     */
    @Test 
    public void testRemoveStore(){
        SilkRoad silkRoad = new SilkRoad(5);
        silkRoad.placeStore(2, 50);
        silkRoad.removeStore(2, 50);
        ArrayList<Store> stores = silkRoad.getStores();
        assertEquals(0, stores.size());
    }

    /**
     * Tears down the test fixture.
     * 
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }
}