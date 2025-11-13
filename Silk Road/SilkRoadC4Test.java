import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test para the SilkRoad class - Cycle 2.
 * @author Juan Daniel Bogota Fuentes
 * @author Nicolas Felipe Bernal Gallo
 * @version 1.0
 */
public class SilkRoadC4Test {

    private SilkRoad silkRoad;

    /**
     * Sets up the test fixture.
     * Called beparae every test case method.
     */
    @Before
    public void setUp() {
        silkRoad = new SilkRoad(100);
    }

    // ========== Test para placeStore ==========
    
    /**
     * Test that placeStore adds a store correctly.
     */
    @Test
    public void testPlaceStoreShouldAddStoreSuccessfully() {
        silkRoad.placeStore(50, 100);
        assertTrue(silkRoad.ok());
        assertEquals(1, silkRoad.getStores().size());
        assertEquals(50, silkRoad.getStores().get(0).getLocation());
    }

    /**
     * Test that placeStore rejects invalid location.
     */
    @Test
    public void testPlaceStoreShouldRejectInvalidLocation() {
        silkRoad.placeStore(150, 100); // location > length
        assertFalse(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Pruebas para NormalStore ==========
    
    /**
     * Test that NormalStore allows any robot to take tenges.
     */
    @Test
    public void testNormalStoreShouldAllowAnyRobotToTake() {
        silkRoad.placeStore("normal", 50, 100);
        silkRoad.placeRobot("normal", 30);
        
        Robot robot = silkRoad.getRobots().get(0);
        Store store = silkRoad.getStores().get(0);
        
        assertTrue(store.canRobotTake(robot));
        assertEquals("normal", store.getType());
    }

    // ========== Pruebas para FighterStore ==========
    
    /**
     * Test that FighterStore only allows rich robots to take tenges.
     */
    @Test
    public void testFighterStoreShouldOnlyAllowRichRobots() {
        silkRoad.placeStore("fighter", 50, 100);
        silkRoad.placeRobot("normal", 30);
        
        Robot robot = silkRoad.getRobots().get(0);
        Store store = silkRoad.getStores().get(0);
        
        // Robot con 0 tenges no puede tomar de tienda con 100
        assertFalse(store.canRobotTake(robot));
        
        // Si el robot tiene más tenges, sí puede
        robot.collectTenges(150);
        assertTrue(store.canRobotTake(robot));
    }

    // ========== Pruebas para AutonomousStore ==========
    
    /**
     * Test that AutonomousStore is created correctly.
     */
    @Test
    public void testAutonomousStoreShouldBeCreated() {
        silkRoad.placeStore("autonomous", 50, 100);
        
        assertEquals(1, silkRoad.getStores().size());
        Store store = silkRoad.getStores().get(0);
        assertEquals("autonomous", store.getType());
    }

    // ========== Test para removeStore ==========
    
    /**
     * Test that removeStore removes an existing store.
     */
    @Test
    public void testRemoveStoreShouldRemoveExistingStore() {
        silkRoad.placeStore(50, 100);
        silkRoad.removeStore(50);
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getStores().size());
    }

    /**
     * Test that removeStore fails when store doesn't exist.
     */
    @Test
    public void testRemoveStoreShouldFailWhenStoreNotFound() {
        silkRoad.removeStore(50);
        assertFalse(silkRoad.ok());
    }

    // ========== Test para placeRobot ==========
    
    /**
     * Test that placeRobot adds a robot correctly.
     */
    @Test
    public void testPlaceRobotShouldAddRobotSuccessfully() {
        silkRoad.placeRobot(30);
        assertTrue(silkRoad.ok());
        assertEquals(1, silkRoad.getRobots().size());
        assertEquals(30, silkRoad.getRobots().get(0).getLocation());
    }

     // ========== Pruebas para NormalRobot ==========

    /**
     * Test that NormalRobot returns to its initial position.
     */
    @Test
    public void testNormalRobotShouldReturnToInitialPosition() throws SilkRoadException {
        silkRoad.placeRobot("normal", 30);
        Robot robot = silkRoad.getRobots().get(0);
        
        int initialPos = robot.getLocation();
        robot.moveTo(20);
        assertEquals(50, robot.getLocation());
        
        robot.reboot();
        assertEquals(initialPos, robot.getLocation());
    }

    /**
     * Test that NormalRobot takes all tenges from store.
     */
    @Test
    public void testNormalRobotShouldTakeAllTenges() {
        silkRoad.placeRobot("normal", 30);
        silkRoad.placeStore("normal", 30, 100);
        
        Robot robot = silkRoad.getRobots().get(0);
        Store store = silkRoad.getStores().get(0);
        
        robot.collectFrom(store);
        assertEquals(100, robot.getCollectedTenges());
        assertEquals(0, store.getTenges());
    }

    // ========== Pruebas para NeverBackRobot ==========

    /**
     * Test that NeverBackRobot does not return to its initial position.
     */
    @Test
    public void testNeverBackRobotShouldNotReturnToInitialPosition() throws SilkRoadException {
        silkRoad.placeRobot("neverback", 30);
        Robot robot = silkRoad.getRobots().get(0);
        
        robot.moveTo(20);
        int newPos = robot.getLocation();
        
        robot.reboot();
        assertEquals(newPos, robot.getLocation());
    }

    // ========== Pruebas para TenderRobot ==========

    /**
     * Test that TenderRobot takes half tenges from store.
     */
    @Test
    public void testTenderRobotShouldTakeHalfTenges() {
        silkRoad.placeRobot("tender", 30);
        silkRoad.placeStore("normal", 30, 100);
        
        Robot robot = silkRoad.getRobots().get(0);
        Store store = silkRoad.getStores().get(0);
        
        robot.collectFrom(store);
        assertEquals(50, robot.getCollectedTenges()); // Mitad de 100
    }

    // ========== Test para removeRobot ==========
    
    /**
     * Test that removeRobot removes an existing robot.
     */
    @Test
    public void testRemoveRobotShouldRemoveExistingRobot() {
        silkRoad.placeRobot(30);
        silkRoad.removeRobot(30);
        assertTrue(silkRoad.ok());
        assertEquals(0, silkRoad.getRobots().size());
    }

    /**
     * Test that removeRobot fails when robot doesn't exist.
     */
    @Test
    public void testRemoveRobotShouldFailWhenRobotNotFound() {
        silkRoad.removeRobot(30);
        assertFalse(silkRoad.ok());
    }

    // ========== Test para moveRobot ==========
    
    /**
     * Test that moveRobot moves a robot correctly.
     */
    @Test
    public void testMoveRobotShouldMoveRobotSuccessfully() throws SilkRoadException {
        silkRoad.placeRobot(20);
        silkRoad.placeStore(30, 50);
        silkRoad.moveRobot(20, 10);
        assertNotNull(silkRoad.getRobots());
    }

    /**
     * Test that moveRobot does nothing when robot doesn't exist.
     */
    @Test
    public void testMoveRobotShouldFailWhenRobotNotFound() throws SilkRoadException {
        silkRoad.moveRobot(20, 10);
        assertFalse(silkRoad.ok());
    }

    // ========== Test para moveRobots ==========
    
    /**
     * Test that moveRobots moves all robots to stores.
     */
    @Test
    public void testMoveRobotsShouldMoveAllRobots() {
        silkRoad.placeRobot(10);
        silkRoad.placeRobot(50);
        silkRoad.placeStore(20, 100);
        silkRoad.placeStore(60, 80);
        
        try {
            silkRoad.moveRobots();
            assertTrue(silkRoad.ok());
        } catch(SilkRoadException e) {
            fail("Should not throw exception");
        }
    }

    /**
     * Test that moveRobots works when no stores exist.
     */
    @Test
    public void testMoveRobotsShouldWorkWithNoStores() {
        silkRoad.placeRobot(10);
        silkRoad.placeRobot(50);
        
        try {
            silkRoad.moveRobots();
            assertTrue(silkRoad.ok());
        } catch(SilkRoadException e) {
            fail("Should not throw exception");
        }
    }

    // ========== Test para resupplyStores ==========
    
    /**
     * Test that resupplyStores restores tenges in all stores.
     */
    @Test
    public void testResupplyStoresShouldRestoreAllStoreTenges() {
        silkRoad.placeStore(20, 50);
        silkRoad.placeStore(50, 75);
        
        silkRoad.resupplyStores();
        
        assertTrue(silkRoad.ok());
        assertEquals(2, silkRoad.getStores().size());
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
        silkRoad.placeRobot(20);
        int initialLocation = silkRoad.getRobots().get(0).getLocation();
        
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
        silkRoad.placeRobot(20);
        silkRoad.placeStore(50, 100);
        
        silkRoad.reboot();
        
        assertTrue(silkRoad.ok());
        assertEquals(20, silkRoad.getRobots().get(0).getLocation());
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

    // ========== Test para profit ==========
    
    /**
     * Test that profit calculates total correctly.
     */
    @Test
    public void testProfitShouldCalculateTotalProfit() {
        silkRoad.placeRobot(10);
        silkRoad.placeRobot(30);
        
        int totalProfit = silkRoad.porfit();
        
        assertTrue(totalProfit >= 0);
    }

    /**
     * Test that profit returns 0 when no robots exist.
     */
    @Test
    public void testProfitShouldReturnZeroWithNoRobots() {
        int totalProfit = silkRoad.porfit();
        assertEquals(0, totalProfit);
    }

    // ========== Test para emptiedStores ==========
    
    /**
     * Test that emptiedStores returns correct sorted data.
     */
    @Test
    public void testEmptiedStoresShouldReturnSortedData() {
        silkRoad.placeStore(50, 100);
        silkRoad.placeStore(20, 80);
        silkRoad.placeStore(80, 120);
        
        int[][] emptied = silkRoad.emptiedStores();
        
        assertEquals(3, emptied.length);
        assertTrue(emptied[0][0] < emptied[1][0]);
        assertTrue(emptied[1][0] < emptied[2][0]);
    }

    /**
     * Test that emptiedStores returns empty array when no stores exist.
     */
    @Test
    public void testEmptiedStoresShouldReturnEmptyArrayWhenNoStores() {
        int[][] emptied = silkRoad.emptiedStores();
        assertEquals(0, emptied.length);
    }

    // ========== Test para profitPerMove ==========
    
    /**
     * Test that profitPerMove returns correct sorted data.
     */
    @Test
    public void testProfitPerMoveShouldReturnSortedData() {
        silkRoad.placeRobot(30);
        silkRoad.placeRobot(10);
        silkRoad.placeRobot(50);
        
        int[][] profits = silkRoad.profitPerMove();
        
        assertEquals(3, profits.length);
        assertTrue(profits[0][0] < profits[1][0]);
        assertTrue(profits[1][0] < profits[2][0]);
    }

    /**
     * Test that profitPerMove returns empty array when no robots exist.
     */
    @Test
    public void testProfitPerMoveShouldReturnEmptyArrayWhenNoRobots() {
        int[][] profits = silkRoad.profitPerMove();
        assertEquals(0, profits.length);
    }

    // ========== Test para stores ==========
    
    /**
     * Test that stores returns correct sorted data array.
     */
    @Test
    public void TesttoresShouldReturnSortedDataArray() {
        silkRoad.placeStore(70, 100);
        silkRoad.placeStore(30, 200);
        silkRoad.placeStore(50, 150);
        
        int[][] storeData = silkRoad.stores();
        
        assertEquals(3, storeData.length);
        assertEquals(30, storeData[0][0]);
        assertEquals(50, storeData[1][0]);
        assertEquals(70, storeData[2][0]);
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
     * Test that robots returns correct sorted data array.
     */
    @Test
    public void testRobotsShouldReturnSortedDataArray() {
        silkRoad.placeRobot(80);
        silkRoad.placeRobot(20);
        silkRoad.placeRobot(50);
        
        int[][] robotData = silkRoad.robots();
        
        assertEquals(3, robotData.length);
        assertEquals(20, robotData[0][0]);
        assertEquals(50, robotData[1][0]);
        assertEquals(80, robotData[2][0]);
    }

    /**
     * Test that robots returns empty array when no robots exist.
     */
    @Test
    public void testRobotsShouldReturnEmptyArrayWhenNoRobots() {
        int[][] robotData = silkRoad.robots();
        assertEquals(0, robotData.length);
    }

    // ========== Test para makeVisible ==========
    
    /**
     * Test that makeVisible makes elements visible.
     */
    @Test
    public void testMakeVisibleShouldShowElements() {
        silkRoad.placeRobot(20);
        silkRoad.placeStore(50, 100);
        
        silkRoad.makeVisible();
        
        assertNotNull(silkRoad.getRobots());
        assertNotNull(silkRoad.getStores());
    }

    /**
     * Test that makeVisible works with no elements.
     */
    @Test
    public void testMakeVisibleShouldWorkWithNoElements() {
        silkRoad.makeVisible();
        assertEquals(0, silkRoad.getRobots().size());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Test para makeInvisible ==========
    
    /**
     * Test that makeInvisible hides elements.
     */
    @Test
    public void testMakeInvisibleShouldHideElements() {
        silkRoad.placeRobot(20);
        silkRoad.placeStore(50, 100);
        
        silkRoad.makeInvisible();
        
        assertEquals(1, silkRoad.getRobots().size());
        assertEquals(1, silkRoad.getStores().size());
    }

    /**
     * Test that makeInvisible works with no elements.
     */
    @Test
    public void testMakeInvisibleShouldWorkWithNoElements() {
        silkRoad.makeInvisible();
        assertEquals(0, silkRoad.getRobots().size());
        assertEquals(0, silkRoad.getStores().size());
    }

    // ========== Test para finish ==========
    
    /**
     * Test that finish clears all elements.
     */
    @Test
    public void testFinishShouldClearAllElements() {
        silkRoad.placeRobot(20);
        silkRoad.placeStore(50, 100);
        
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

    // ========== Test para ok ==========
    
    /**
     * Test that ok returns true after successful operation.
     */
    @Test
    public void testOkShouldReturnTrueAfterSuccessfulOperation() {
        silkRoad.placeRobot(20);
        assertTrue(silkRoad.ok());
    }

    /**
     * Test that ok returns false after failed operation.
     */
    @Test
    public void testOkShouldReturnFalseAfterFailedOperation() {
        silkRoad.placeRobot(200);
        assertFalse(silkRoad.ok());
    }

    // ========== Test para getRobots ==========
    
    /**
     * Test that getRobots returns the robot list.
     */
    @Test
    public void testGetRobotsShouldReturnRobotList() {
        silkRoad.placeRobot(20);
        silkRoad.placeRobot(50);
        
        ArrayList<Robot> robots = silkRoad.getRobots();
        
        assertEquals(2, robots.size());
    }

    /**
     * Test that getRobots returns empty list when no robots exist.
     */
    @Test
    public void testGetRobotsShouldReturnEmptyListWhenNoRobots() {
        ArrayList<Robot> robots = silkRoad.getRobots();
        assertEquals(0, robots.size());
    }

    // ========== Test para getStores ==========
    
    /**
     * Test that getStores returns the store list.
     */
    @Test
    public void testGetStoresShouldReturnStoreList() {
        silkRoad.placeStore(20, 100);
        silkRoad.placeStore(50, 150);
        
        ArrayList<Store> stores = silkRoad.getStores();
        
        assertEquals(2, stores.size());
    }

    /**
     * Test that getStores returns empty list when no stores exist.
     */
    @Test
    public void testGetStoresShouldReturnEmptyListWhenNoStores() {
        ArrayList<Store> stores = silkRoad.getStores();
        assertEquals(0, stores.size());
    }

    // ========== Test para getRoads ==========
    
    /**
     * Test that getRoads returns road list with correct size.
     */
    @Test
    public void testGetRoadsShouldReturnCorrectSize() {
        ArrayList<Road> roads = silkRoad.getRoads();
        assertEquals(100, roads.size());
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
        assertEquals(100, silkRoad.getLength());
    }

    /**
     * Test that getLength is immutable.
     */
    @Test
    public void testGetLengthShouldBeImmutable() {
        int originalLength = silkRoad.getLength();
        silkRoad.placeRobot(50);
        assertEquals(originalLength, silkRoad.getLength());
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        silkRoad = null;
    }
}