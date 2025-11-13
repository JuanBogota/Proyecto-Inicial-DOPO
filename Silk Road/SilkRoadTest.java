import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Beparae;
import org.junit.Test;

/**
 * Unit Test para the SilkRoad class.
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */
public class SilkRoadTest {

    private SilkRoad silkRoad;

    /**
     * Sets up the test fixture.
     * Called beparae every test case method.
     */
    @Before
    public void setUp() {
        silkRoad = new SilkRoad(10);
    }

    // ========== Test de PlaceStore ==========
    
    /**
     * Test that placeStore adds a store correctly.
     */
    @Test
    public void testPlaceStoreShouldAddStoreSuccessfully() {
        silkRoad.placeStore(5, 100);
        assertTrue(silkRoad.ok());
        assertEquals(1, silkRoad.getStores().size());
        assertEquals(5, silkRoad.getStores().get(0).getLocation());
    }

    /**
     * Test that placeStore rejects invalid location.
     */
    @Test
    public void testPlaceStoreShouldRejectInvalidLocation() {
        silkRoad.placeStore(15, 100); // location > length
        assertFalse(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    /**
     * Test that placeStore rejects negative tenges.
     */
    @Test
    public void testPlaceStoreShouldRejectNegativeTenges() {
        silkRoad.placeStore(5, -50);
        assertFalse(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Test para RemoveStore ==========
    
    /**
     * Test that removeStore removes an existing store.
     */
    @Test
    public void testRemoveStoreShouldRemoveExistingStore() {
        silkRoad.placeStore(5, 100);
        silkRoad.removeStore(5);
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    /**
     * Test that removeStore fails when store doesn't exist.
     */
    @Test
    public void testRemoveStoreShouldFailWhenStoreNotFound() {
        silkRoad.removeStore(5); // no store at location 5
        assertFalse(silkRoad.ok());
    }

    // ========== Test para placeRobot ==========
    
    /**
     * Test that placeRobot adds a robot correctly.
     */
    @Test
    public void testPlaceRobotShouldAddRobotSuccessfully() {
        silkRoad.placeRobot(3);
        assertTrue(silkRoad.ok());
        assertEquals(1, silkRoad.getRobots().size());
        assertEquals(3, silkRoad.getRobots().get(0).getLocation());
    }

    /**
     * Test that placeRobot rejects duplicate location.
     */
    @Test
    public void testPlaceRobotShouldRejectDuplicateLocation() {
        silkRoad.placeRobot(3);
        silkRoad.placeRobot(3); // same location
        assertFalse(silkRoad.ok());
        assertEquals(1, silkRoad.getRobots().size());
    }

    /**
     * Test that placeRobot rejects invalid location.
     */
    @Test
    public void testPlaceRobotShouldRejectInvalidLocation() {
        silkRoad.placeRobot(20); // location > length
        assertFalse(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
    }

    // ========== Test para removeRobot ==========
    
    /**
     * Test that removeRobot removes an existing robot.
     */
    @Test
    public void testRemoveRobotShouldRemoveExistingRobot() {
        silkRoad.placeRobot(3);
        silkRoad.removeRobot(3);
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
    }

    /**
     * Test that removeRobot fails when robot doesn't exist.
     */
    @Test
    public void testRemoveRobotShouldFailWhenRobotNotFound() {
        silkRoad.removeRobot(3); // no robot at location 3
        assertFalse(silkRoad.ok());
    }

    // ========== Test para resupplyStores ==========
    
    /**
     * Test that resupplyStores increases tenges in all stores.
     */
    @Test
    public void testResupplyStoresShouldIncreaseAllStoreTenges() {
        silkRoad.placeStore(2, 50);
        silkRoad.placeStore(5, 75);
        int initialTenges1 = silkRoad.getStores().get(0).getTenges();
        int initialTenges2 = silkRoad.getStores().get(1).getTenges();
        
        silkRoad.resupplyStores();
        
        assertTrue(silkRoad.ok());
        assertTrue(silkRoad.getStores().get(0).getTenges() > initialTenges1);
        assertTrue(silkRoad.getStores().get(1).getTenges() > initialTenges2);
    }

    /**
     * Test that resupplyStores works when no stores exist.
     */
    @Test
    public void testResupplyStoresShouldWorkWithNoStores() {
        silkRoad.resupplyStores();
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Test para returnRobots ==========
    
    /**
     * Test that returnRobots resets robot positions.
     */
    @Test
    public void testReturnRobotsShouldResetRobotPositions() {
        silkRoad.placeRobot(2);
        int initialLocation = silkRoad.getRobots().get(0).getLocation();
        
        // Move robot would change location (if implemented properly)
        silkRoad.returnRobots();
        
        assertTrue(silkRoad.ok());
        assertEquals(initialLocation, silkRoad.getRobots().get(0).getLocation());
    }

    /**
     * Test that returnRobots works when no robots exist.
     */
    @Test
    public void testReturnRobotsShouldWorkWithNoRobots() {
        silkRoad.returnRobots();
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
    }

    // ========== Test para reboot ==========
    
    /**
     * Test that reboot resets all robots and stores.
     */
    @Test
    public void testRebootShouldResetAllElements() {
        silkRoad.placeRobot(2);
        silkRoad.placeStore(5, 100);
        
        silkRoad.reboot();
        
        assertTrue(silkRoad.ok());
        assertEquals(2, silkRoad.getRobots().get(0).getLocation());
    }

    /**
     * Test that reboot works with empty road.
     */
    @Test
    public void testRebootShouldWorkWithEmptyRoad() {
        silkRoad.reboot();
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Test para stores ==========
    
    /**
     * Test that stores returns correct data array.
     */
    @Test
    public void TesttoresShouldReturnCorrectDataArray() {
        silkRoad.placeStore(3, 100);
        silkRoad.placeStore(7, 200);
        
        int[][] storeData = silkRoad.stores();
        
        assertEquals(2, storeData.length);
        assertEquals(3, storeData[0][0]);
        assertEquals(100, storeData[0][1]);
        assertEquals(7, storeData[1][0]);
        assertEquals(200, storeData[1][1]);
    }

    /**
     * Test that stores returns empty array when no stores exist.
     */
    @Test
    public void TesttoresShouldReturnEmptyArrayWhenNoStores() {
        int[][] storeData = silkRoad.stores();
        assertEquals(0, storeData.length);
    }

    // ========== Test para robots ==========
    
    /**
     * Test that robots returns correct data array.
     */
    @Test
    public void testRobotsShouldReturnCorrectDataArray() {
        silkRoad.placeRobot(2);
        silkRoad.placeRobot(8);
        
        int[][] robotData = silkRoad.robots();
        
        assertEquals(2, robotData.length);
        assertEquals(2, robotData[0][0]);
        assertEquals(8, robotData[1][0]);
    }

    /**
     * Test that robots returns empty array when no robots exist.
     */
    @Test
    public void testRobotsShouldReturnEmptyArrayWhenNoRobots() {
        int[][] robotData = silkRoad.robots();
        assertEquals(0, robotData.length);
    }

    // ========== Test para makeVisible/makeInvisible ==========
    
    /**
     * Test that makeVisible makes elements visible.
     */
    @Test
    public void testMakeVisibleShouldShowElements() {
        silkRoad.placeRobot(2);
        silkRoad.placeStore(5, 100);
        
        silkRoad.makeVisible();
        
        // Elements should be visible (no exception thrown)
        assertNotNull(silkRoad.getRobots());
        assertNotNull(silkRoad.getStores());
    }

    /**
     * Test that makeInvisible hides elements.
     */
    @Test
    public void testMakeInvisibleShouldHideElements() {
        silkRoad.placeRobot(2);
        silkRoad.placeStore(5, 100);
        
        silkRoad.makeInvisible();
        
        // Elements should still exist but be invisible
        assertEquals(1, silkRoad.getRobots().size());
        assertEquals(1, silkRoad.getStores().size());
    }

    // ========== Test para finish ==========
    
    /**
     * Test that finish clears all elements.
     */
    @Test
    public void testFinishShouldClearAllElements() {
        silkRoad.placeRobot(2);
        silkRoad.placeStore(5, 100);
        
        silkRoad.finish();
        
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
        assertEquals(0, silkRoad.getStores().size());
        assertEquals(0, silkRoad.getRoads().size());
    }

    /**
     * Test that finish works on empty simulator.
     */
    @Test
    public void testFinishShouldWorkOnEmptySimulator() {
        silkRoad.finish();
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
    }

    // ========== Test para getRoads ==========
    
    /**
     * Test that getRoads returns road list with correct size.
     */
    @Test
    public void testGetRoadsShouldReturnCorrectSize() {
        ArrayList<Road> roads = silkRoad.getRoads();
        assertEquals(10, roads.size()); // length = 10
    }

    /**
     * Test that getRoads returns empty list para length 0.
     */
    @Test
    public void testGetRoadsShouldReturnEmptyparaZeroLength() {
        SilkRoad emptyRoad = new SilkRoad(0);
        assertEquals(0, emptyRoad.getRoads().size());
    }

    // ========== Test para getLength ==========
    
    /**
     * Test that getLength returns correct value.
     */
    @Test
    public void testGetLengthShouldReturnCorrectValue() {
        assertEquals(10, silkRoad.getLength());
    }

    /**
     * Test that getLength is immutable.
     */
    @Test
    public void testGetLengthShouldBeImmutable() {
        int originalLength = silkRoad.getLength();
        silkRoad.placeRobot(5);
        assertEquals(originalLength, silkRoad.getLength());
    }

    /**
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        silkRoad = null;
    }

}