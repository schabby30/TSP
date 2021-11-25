package tsp;

import javax.swing.JFrame;

public final class Simulation extends JFrame {
    
    public World world = new World(this);
    public Generation generation = new Generation(world);
    public boolean simulateRoute = true;
    
    public Simulation() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(world);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void run() {
        world.repaint();
//        for (int i= 0; i < 10; i++) {
//            System.out.println("Fitness : " + generation.calculateFitness(world.cities, generation.generation.get(i)));
//        }
    }    
}
