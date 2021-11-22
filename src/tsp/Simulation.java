package tsp;

import javax.swing.JFrame;

public final class Simulation extends JFrame {
    
    private final World world = new World();
    private final Generation generation = new Generation();
    
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
        System.out.println(generation.calculateFitness(world.cities, generation.generation.get(0)));
    }
    
    
}
