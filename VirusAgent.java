/*
  Written by Benjamin Wintersteen

  Creates an Virus Agent class.

  This is the framework given for creating virus agents who will interact within a landscape.
*/
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

// VirusAgent class extends the Agent class
public class VirusAgent extends Agent {
    private int stage;

    // Constructor initializes the VirusAgent with initial position and radius
    public VirusAgent(double x0, double y0, int radius) {
        super(x0, y0, radius);
        stage = 0;
        setIsViral(true); // Mark the agent as viral
        // TODO Auto-generated constructor stub
    }

    // Overrides the updateState method to define agent behavior in the landscape
    @Override
    public void updateState(Landscape scape) {
        Random rand = new Random();
        stage++; // Increment the stage of the virus agent
        
        // Check the number of neighbors within the agent's radius
        if (scape.getNeighbors(getX(), getY(), getRadius(), isViral).size() < 4) {
            setMoved(true); // Mark the agent as moved

            // Define bounds for random movement based on agent's position
            int xBounds = 10;
            int yBounds = 10;

            // Adjust bounds if the agent is close to the edges of the landscape
            if (getX() < 11) {
                xBounds = 0;
            } else if (getX() > scape.getWidth() - 11) {
                xBounds = 20;
            }
            if (getY() < 11) {
                yBounds = 0;
            } else if (getY() > scape.getHeight() - 11) {
                yBounds = 20;
            }

            // Update agent's position with a random value within the defined bounds
            setX(getX() + rand.nextInt(21) - xBounds);
            setY(getY() + rand.nextInt(21) - yBounds);
        } else {
            setMoved(false); // Mark the agent as not moved
        }
    }

    // Method to check the stage of the virus agent and set it as non-viral if the stage is greater than 5
    public void checkStage() {
        if (stage > 5) {
            setIsViral(false); // Mark the agent as non-viral
        }
    }

    // Overrides the draw method to define how the agent is visually represented
    @Override
    public void draw(Graphics g) {
        // Set color based on whether the agent has moved or not
        if (!moved) g.setColor(new Color(0, 255, 0)); // Green for not moved
        else g.setColor(new Color(125, 255, 125)); // Light green for moved

        // Draw a circular representation of the agent at its current position
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }
}