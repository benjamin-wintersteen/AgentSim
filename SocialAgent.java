/*
  Written by Benjamin Wintersteen

  Creates an Social Agent class.

  This is the framework given for creating social agents who will enjoy being around other agents.
*/
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * SocialAgent class represents an agent with social behavior.
 */
public class SocialAgent extends Agent {

    /**
     * Constructs a SocialAgent with the specified initial position.
     * @param x0 the initial x-coordinate.
     * @param y0 the initial y-coordinate.
     */
    public SocialAgent(double x0, double y0) {
        super(x0, y0);
    }

    /**
     * Constructs a SocialAgent with the specified initial position and radius.
     * @param x0 the initial x-coordinate.
     * @param y0 the initial y-coordinate.
     * @param radius the radius of the agent.
     */
    public SocialAgent(double x0, double y0, int radius) {
        super(x0, y0, radius);
    }

    /**
     * Updates the state of the SocialAgent based on its neighbors in the landscape.
     * @param scape the landscape in which the agent exists.
     */
    @Override
    public void updateState(Landscape scape) {
        Random rand = new Random();
        if(scape.getNeighbors(getX(), getY(), getRadius(), false).size() < 4){
            setMoved(true);
            int xBounds = 10;
            int yBounds = 10;
            
            if(getX() < 11){
                xBounds = 0;
            }
            else if(getX() > scape.getWidth() - 11){
                xBounds = 20;
            }
            if(getY() < 11){
                yBounds = 0;
            }
            else if(getY() > scape.getHeight() - 11){
                yBounds = 20;
            }

            setX(getX() + rand.nextInt(21) - xBounds);
            setY(getY() + rand.nextInt(21) - yBounds);
        }
        else{
            setMoved(false);
        }
    }

    /**
     * Draws the SocialAgent on the graphics object.
     * @param g the graphics object.
     */
    @Override
    public void draw(Graphics g){
        if(!moved) g.setColor(new Color(0, 0, 255));
        else g.setColor(new Color(125, 125, 255));

        g.fillOval((int) getX(), (int) getY(), 5, 5);
    }
}
