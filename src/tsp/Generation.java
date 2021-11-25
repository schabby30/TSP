package tsp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

public class Generation {
    
    public ArrayList<ArrayList<Integer>> generation;
    public final World world;
    private final int MUTATION_RATE = 2;
    private ArrayList fitnessScores;
    private ArrayList<Integer> fittest;
    private ArrayList<ArrayList<Integer>> fittestList;
    
    public Generation(World world) {
        this.world = world;
        this.generation = new ArrayList<>();
        this.fitnessScores = new ArrayList<>();
        this.fittestList = new ArrayList<>();
        
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
            this.generation.add(salesman);
            this.fitnessScores.add(calculateFitness(this.world.cities, salesman));
        }
        
        int index = fittestIndex();
        
        this.fittest = this.generation.get(index);
        this.fittestList.add((ArrayList<Integer>) this.fittest.clone());
        System.out.println("fittest salesman index: " + index + 
                            " and their fitness scopre is : " + this.fitnessScores.get(index));
        System.out.println("Fittest list : " + this.fittestList);
//        System.out.println(this.generation);
    }

    public final double calculateFitness(ArrayList cities, ArrayList salesman) {
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
        // System.out.println(x1 + "," + y1 + " " + x2 + "," + y2 + "   Dist:" + Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
    
    public void nextGeneration() {
        ArrayList<ArrayList<Integer>> nextGeneration = new ArrayList<>();
        int fittestIndex = fittestIndex();
//        System.out.println(this.generation);
        
        for (int i = 0; i < this.generation.size(); i++) {
            ArrayList<Integer> salesman = this.generation.get(i);
//            System.out.println(salesman);
//            System.out.println(mutate(salesman));
                
            if (fittestIndex != i) {
                ArrayList<Integer> mutated = mutate(salesman);
                nextGeneration.add(mutated);
//                System.out.println(nextGeneration);
            } else {
                nextGeneration.add(salesman);
//                System.out.println(salesman);
            }
        }
        
//        System.out.println(this.generation);
//        System.out.println(nextGeneration);
//        System.out.println("fittest salesman index: " + fittestIndex());
        this.generation = nextGeneration;
//        System.out.println("fittest salesman index: " + fittestIndex());
        updateFitnessScores();
        
        int index = fittestIndex();
        
        if (this.fittest != this.generation.get(index)) {
            this.fittest = this.generation.get(index);
            this.fittestList.add((ArrayList<Integer>) this.fittest.clone());
            System.out.println("fittest salesman index: " + index + 
                            " and their fitness scopre is : " + this.fitnessScores.get(index));
            System.out.println("Fittest list : " + this.fittestList);
        }
//        System.out.println("fittest salesman index: " + fittestIndex());
    }
    
    private void updateFitnessScores() {
        for (int i = 0; i < this.generation.size(); i++) {
            this.fitnessScores.set(i, calculateFitness(this.world.cities, this.generation.get(i)));
        }
    }
    
    public int fittestIndex() {
//        System.out.println("Fittest Salesman is : " + fitnessScores.indexOf(Collections.min(fitnessScores)));
//        System.out.println("Their score is : " + Collections.min(fitnessScores));
        return fitnessScores.indexOf(Collections.min(fitnessScores));
    }
    
    private ArrayList<Integer> mutate(ArrayList<Integer> salesman) {
        for (int i = 0; i < MUTATION_RATE; i++) {
            int changeIndex = (int) (Math.random() * 10);
//            System.out.println("index : " + changeIndex + " " + (salesman.size() - changeIndex - 1));
//            System.out.println(salesman);
            Collections.swap(salesman, changeIndex, salesman.size() - changeIndex - 1);
//            System.out.println(salesman);
        }
        return salesman;
    }
    
    //render the route of salesman with best fitness score in generation
    public void renderRoute(Graphics2D g) {
        int x1,y1,x2,y2;
        
        for (int i = 0; i < 100000; i++) {
            int R = (int) Math.round(Math.random() * 255);
            int G = (int) Math.round(Math.random() * 255);
            int B = (int) Math.round(Math.random() * 255);
            Color color =  new Color(R, G, B);
        
            g.setColor(color);
            
            ArrayList salesman = this.generation.get(fittestIndex());
//          System.out.println("fittest salesman index: " + fittestIndex());

            for(int j = 0; j < 9; j++) {
                x1 = world.cities.get((int) salesman.get(j) - 1)[0];
                y1 = world.cities.get((int) salesman.get(j) - 1)[1];
                x2 = world.cities.get((int) salesman.get(j + 1) - 1)[0];
                y2 = world.cities.get((int) salesman.get(j + 1) - 1)[1];
                g.drawLine(x1,y1,x2,y2);
            }

            x1 = world.cities.get((int) salesman.get(9) - 1)[0];
            y1 = world.cities.get((int) salesman.get(9) - 1)[1];
            x2 = world.cities.get((int) salesman.get(0) - 1)[0];
            y2 = world.cities.get((int) salesman.get(0) - 1)[1];
            g.drawLine(x1,y1,x2,y2);
            
            nextGeneration();
        }
    }
    
//    public void evolve(Graphics2D g) {
//        for (int i = 0; i < 10; i++) {
//            nextGeneration();
//            renderRoute(g);
//        }
//    }
    
}
