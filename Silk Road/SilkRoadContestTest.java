import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Test for the SilkRoadContest class.
 * @author Juan Daniel Bogota Fuentes
 * @author Nicolas Felipe Bernal Gallo
 * @version 1.0
 */
public class SilkRoadContestTest {

    private SilkRoadContest contest;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        contest = new SilkRoadContest();
    }

    /**
     * Test that solve handles null days array.
     */
    @Test
    public void testSolveShouldHandleNullDays() {
        int[] result = contest.solve(null);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    /**
     * Test that solve handles empty days array.
     */
    @Test
    public void testSolveShouldHandleEmptyDays() {
        int[][] days = {};
        int[] result = contest.solve(days);
        assertEquals(0, result.length);
    }

    /**
     * Test that solve calculates profit with one robot and one store.
     */
    @Test
    public void testSolveShouldCalculateProfitOneRobotOneStore() {
        int[][] days = {
            {1, 20},      
            {2, 15, 15}  
        };
        int[] result = contest.solve(days);
        
        assertEquals(2, result.length);
        assertEquals(0, result[0]);
        assertEquals(10, result[1]); 
    }

    /**
     * Test that solve returns correct output for Sample Input 1.
     */
    @Test
    public void testSolveShouldReturnCorrectOutputForSampleInput1() {
        int[][] days = {
            {2, 20, 0},
            {1, 20},
            {2, 15, 15},
            {2, 40, 50},
            {1, 50},
            {2, 80, 20},
            {2, 70, 30}
        };
        int[] expected = {0, 10, 35, 50, 50, 60};
        int[] result = contest.solve(days);
        
        assertArrayEquals(expected, result);
    }

    /**
     * Test that solve assigns robots optimally with two robots and two stores.
     */
    @Test
    public void testSolveShouldAssignRobotsOptimally() {
        int[][] days = {
            {1, 20},      
            {2, 15, 15},  
            {2, 40, 50},  
            {1, 50}       
        };
        int[] result = contest.solve(days);
        
        assertEquals(50, result[3]); 
    }

    /**
     * Test that solve ignores negative profits.
     */
    @Test
    public void testSolveShouldIgnoreNegativeProfits() {
        int[][] days = {
            {1, 10},
            {2, 100, 20}  
        };
        int[] result = contest.solve(days);
        
        assertEquals(0, result[1]);
    }

    /**
     * Test that solve resupplies stores between days.
     */
    @Test
    public void testSolveShouldResupplyStoresBetweenDays() {
        int[][] days = {
            {1, 20},
            {2, 15, 15},
            {1, 10}  
        };
        int[] result = contest.solve(days);
        
        assertTrue(result[2] > 0);
    }

    /**
     * Test that solve returns robots to initial positions between days.
     */
    @Test
    public void testSolveShouldReturnRobotsBetweenDays() {
        int[][] days = {
            {1, 20},
            {2, 15, 25},
            {2, 25, 30}  
        };
        int[] result = contest.solve(days);
        
        assertEquals(20, result[1]);
        assertEquals(25, result[2]);
    }

    /**
     * Test that solve handles multiple robots and multiple stores.
     */
    @Test
    public void testSolveShouldHandleMultipleRobotsAndStores() {
        int[][] days = {
            {1, 10},
            {1, 50},
            {2, 20, 30},
            {2, 40, 40}
        };
        int[] result = contest.solve(days);
        
        assertEquals(50, result[3]);
    }

    /**
     * Test that solve handles more robots than stores.
     */
    @Test
    public void testSolveShouldHandleMoreRobotsThanStores() {
        int[][] days = {
            {1, 10},
            {1, 20},
            {1, 30},
            {2, 15, 50}
        };
        int[] result = contest.solve(days);
        
        assertTrue(result[3] > 0 && result[3] <= 50);
    }

    /**
     * Test that solve handles more stores than robots.
     */
    @Test
    public void testSolveShouldHandleMoreStoresThanRobots() {
        int[][] days = {
            {1, 20},
            {2, 10, 30},
            {2, 25, 40},
            {2, 30, 50}
        };
        int[] result = contest.solve(days);
        
        assertEquals(40, result[3]); 
    }

    /**
     * Test that solve handles stores with zero tenges.
     */
    @Test
    public void testSolveShouldHandleStoresWithZeroTenges() {
        int[][] days = {
            {1, 20},
            {2, 15, 0},
            {2, 25, 10}
        };
        int[] result = contest.solve(days);
        
        assertEquals(5, result[2]);
    }

    // ========== Pruebas Negativas - Lo que NO debería hacer ==========

    /**
     * Test that solve does not throw NullPointerException with null days.
     */
    @Test
    public void testSolveShouldNotThrowNPEWithNullDays() {
        try {
            contest.solve(null);
        } catch (NullPointerException e) {
            fail("Should not throw NullPointerException");
        }
    }

    /**
     * Test that solve does not throw exception with null day elements.
     */
    @Test
    public void testSolveShouldNotThrowWithNullDayElements() {
        int[][] days = {
            {1, 20},
            null,
            {2, 15, 15}
        };
        try {
            int[] result = contest.solve(days);
            assertEquals(0, result[1]);
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    /**
     * Test that solve does not throw exception with malformed days.
     */
    @Test
    public void testSolveShouldNotThrowWithMalformedDays() {
        int[][] days = {
            {1},
            {2, 15},
            {1, 20}
        };
        try {
            contest.solve(days);
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    /**
     * Test that solve does not return negative profits.
     */
    @Test
    public void testSolveShouldNotReturnNegativeProfits() {
        int[][] days = {
            {1, 0},
            {2, 100, 10}
        };
        int[] result = contest.solve(days);
        
        assertTrue(result[1] >= 0);
    }

    /**
     * Test that solve does not allow two robots to visit same store.
     */
    @Test
    public void testSolveShouldNotAllowTwoRobotsSameStore() {
        int[][] days = {
            {1, 10},
            {1, 15},
            {2, 12, 100}
        };
        int[] result = contest.solve(days);
        
        assertTrue(result[2] <= 100);
    }

    /**
     * Test that solve does not give profit without stores.
     */
    @Test
    public void testSolveShouldNotGiveProfitWithoutStores() {
        int[][] days = {
            {1, 10},
            {1, 20}
        };
        int[] result = contest.solve(days);
        
        assertEquals(0, result[0]);
        assertEquals(0, result[1]);
    }

    /**
     * Test that solve does not give profit without robots.
     */
    @Test
    public void testSolveShouldNotGiveProfitWithoutRobots() {
        int[][] days = {
            {2, 10, 50},
            {2, 20, 100}
        };
        int[] result = contest.solve(days);
        
        assertEquals(0, result[0]);
        assertEquals(0, result[1]);
    }

    /**
     * Test that solve does not modify input array.
     */
    @Test
    public void testSolveShouldNotModifyInputArray() {
        int[][] days = {
            {1, 20},
            {2, 15, 15}
        };
        int[][] originalDays = {
            {1, 20},
            {2, 15, 15}
        };
        
        contest.solve(days);
        
        assertArrayEquals(originalDays[0], days[0]);
        assertArrayEquals(originalDays[1], days[1]);
    }

    /**
     * Test that solve gives consistent results on multiple executions.
     */
    @Test
    public void testSolveShouldGiveConsistentResults() {
        int[][] days = {
            {1, 20},
            {2, 15, 15},
            {2, 40, 50},
            {1, 50}
        };
        
        int[] result1 = contest.solve(days);
        int[] result2 = contest.solve(days);
        
        assertArrayEquals(result1, result2);
    }

    /**
     * Test that solve does not fail with invalid action type.
     */
    @Test
    public void testSolveShouldNotFailWithInvalidActionType() {
        int[][] days = {
            {1, 20},
            {3, 15, 15},
            {2, 25, 20}
        };
        try {
            contest.solve(days);
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }
}