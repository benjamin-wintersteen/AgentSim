/*
  Written by Benjamin Wintersteen

  Creates an AntisocialAgent class.

  This is the framework given for creating antisocial agents who will not enjoy being near other agents.
*/
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

// AntiSocialAgent class extends the Agent class
public class AntiSocialAgent extends Agent {

    // Constructor initializes the AntiSocialAgent with initial position and radius
    public AntiSocialAgent(double x0, double y0, int radius) {
        super(x0, y0, radius);
        // TODO Auto-generated constructor stub
    }

    // Overrides the updateState method to define agent behavior in the landscape
    @Override
    public void updateState(Landscape scape) {
        Random rand = new Random();

        // Check the number of neighbors within the agent's radius
        if (scape.getNeighbors(getX(), getY(), getRadius(), false).size() > 1) {
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

    // Overrides the draw method to define how the agent is visually represented
    @Override
    public void draw(Graphics g) {
        // Set color based on whether the agent has moved or not
        if (!moved) g.setColor(new Color(255, 0, 0)); // Red for not moved
        else g.setColor(new Color(255, 125, 125)); // Light red for moved

        // Draw a circular representation of the agent at its current position
        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }
}
