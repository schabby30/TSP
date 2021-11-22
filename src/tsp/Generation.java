package tsp;

import java.util.ArrayList;
import java.util.Collections;

class Generation {
    
    public final ArrayList<ArrayList<Integer>> generation;
    
    public Generation() {
        this.generation = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> salesman = new ArrayList<>();
            salesman.add(0,1);
            salesman.add(1,2);
            salesman.add(2,3);
            salesman.add(3,4);
            salesman.add(4,5);
            salesman.add(5,6);
            salesman.add(6,7);
            salesman.add(7,8);
            salesman.add(8,9);
            salesman.add(9,10);
        
            Collections.shuffle(salesman);
            generation.add(salesman);
        }
        System.out.println(this.generation);
    }

    public double calculateFitness(ArrayList cities, ArrayList salesman) {
        double fitness = 0;
        
        for (int i = 0; i < 9; i++) {
            Integer salesmanFrom = (Integer) salesman.get(i);
            Integer salesmanTo = (Integer) salesman.get(i + 1);
            
            int[] city1 = (int[]) cities.get(salesmanFrom - 1);
            int[] city2 = (int[]) cities.get(salesmanTo - 1);
            
            fitness += calculateDistance(city1[0], city1[1], city2[0], city2[1]);
        }
        
        Integer salesmanFrom = (Integer) salesman.get(9);
        Integer salesmanTo = (Integer) salesman.get(0);
        
        int[] city1 = (int[]) cities.get(salesmanFrom - 1);
        int[] city2 = (int[]) cities.get(salesmanTo - 1);
        
        fitness += calculateDistance(city1[0], city1[1], city2[0], city2[1]);
        
        return fitness;
    }
    
    private double calculateDistance(Integer x1, Integer y1, Integer x2, Integer y2) {
        System.out.println(x1 + "," + y1 + " " + x2 + "," + y2 + "   Dist:" + Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
    
}
