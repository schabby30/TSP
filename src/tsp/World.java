package tsp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public final class World extends JPanel {
    
    private static final int NUMBER_OF_CITIES = 10;
    public final ArrayList<int[]> cities = new ArrayList<>();
    private final Simulation simulation;
    
    public World(Simulation simulation) {
        this.simulation = simulation;
        
        // generate x and y coordinates for every cities
        for (int i = 0; i < NUMBER_OF_CITIES; i++) {
            int[] city = new int[2];
            city[0] = (int) (Math.random() * (640 - 0) + 0);
            city[1] = (int) (Math.random() * (480 - 0) + 0);
            this.cities.add(city);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        for (int i = 0; i < NUMBER_OF_CITIES; i++) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(String.valueOf(i), this.cities.get(i)[0], this.cities.get(i)[1]);
        }
        
        if (simulation.simulateRoute) {
            simulation.generation.renderRoute(g);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {}
        }
    }
    
}
