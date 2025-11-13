import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Solver for the Silk Road Marathon Problem.
 * 
 * @author Nicolas Felipe Bernal Gallo  
 * @author Juan Daniel Bogota Fuentes
 * @version 1.0 
 */

 public class SilkRoadContest{


    public int[] solve(int[][] days) {
        if (days == null || days.length == 0) {
            return new int[0];
        }
        
        int maxLocation = 0;
        for (int[] day : days) {
            if (day != null && day.length >= 2 && day[1] > maxLocation) {
                maxLocation = day[1];
            }
        }
        
        SilkRoad road = new SilkRoad(maxLocation);
        int[] profits = new int[days.length];
        
        for (int i = 0; i < days.length; i++) {
            int[] day = days[i];
            
            if (day == null || day.length < 1) {
                profits[i] = 0;
                continue;
            }
            
            if (i > 0) {
                road.resupplyStores();
                road.returnRobots();
            }
            
            if (day[0] == 1 && day.length >= 2) {
                road.placeRobot(day[1]);
            } else if (day[0] == 2 && day.length >= 3) {
                road.placeStore(day[1], day[2]);
            }
            
            try {
                road.moveRobots();
            } catch (SilkRoadException e) {
                
            }
            
            profits[i] = road.porfit();
            }
        
            return profits;
        }



    
    /**
     * Simulates the solution to the Silk Road problem.
     * 
     * @param days 2D array of day actions
     * @param slow if true, adds delay between days
     */
    public void simulate(int[][] days, boolean slow) {
        if (days == null || days.length == 0) {
            System.out.println(SilkRoadException.DONT_HAVE_DAY);
            return;
        }
        
        // Encontrar la ubicación máxima
        int maxLocation = 0;
        for (int[] day : days) {
            if (day != null && day.length >= 2 && day[1] > maxLocation) {
                maxLocation = day[1];
            }
        }
        
        SilkRoad road = new SilkRoad(maxLocation);
        
        for (int i = 0; i < days.length; i++) {
            int[] day = days[i];
            
            if (day == null || day.length < 2) {
                System.out.println("Día " + (i + 1) + ": Acción inválida");
                continue;
            }
            
            System.out.print("Día " + (i + 1) + ": ");
            
            // ANTES de agregar, resuppliar y retornar
            if (i > 0) {
                road.resupplyStores();
                road.returnRobots();
            }
            
            // Agregar robot o tienda
            if (day[0] == 1 && day.length >= 2) {
                road.placeRobot(day[1]);
                System.out.print("Agregar Robot en posición " + day[1]);
            } else if (day[0] == 2 && day.length >= 3) {
                road.placeStore(day[1], day[2]);
                System.out.print("Agregar Tienda en posición " + day[1] + 
                            " con " + day[2] + " tenges");
            }
            
            // Mover los robots
            try {
                road.moveRobots();
            } catch (SilkRoadException e) {
                // Ignorar excepción
            }
            
            // Mostrar resultado
            int profit = road.porfit();
            System.out.println(" -> Ganancia Máxima: " + profit);
            
            System.out.println("  Robots: " + road.getRobots().size() + 
                            ", Tiendas: " + road.getStores().size());
            System.out.println();
        }
    
        System.out.println("Simulación Completa");
    }
}