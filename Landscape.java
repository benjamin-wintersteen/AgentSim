/*
  Written by Benjamin Wintersteen

  Creates a Landscape.

  This creates and sets rules for the landscape which the agents will inhabit.
*/
import java.awt.Graphics;
import java.util.Random;

/**
 * Landscape class represents a landscape where agents interact.
 */
public class Landscape {
    private int width; 
    private int height; 
    private LinkedList<Agent> agents;
    private int numViral;

    /**
     * Constructs a Landscape with the specified width and height.
     * @param w the width of the landscape.
     * @param h the height of the landscape.
     */
    public Landscape(int w, int h){
        this.width = w;
        this.height = h;
        agents = new LinkedList<>();
        numViral = 0;
    }

    /**
     * Gets the height of the landscape.
     * @return the height of the landscape.
     */
    public int getHeight(){
        return height;
    } 

    /**
     * Gets the width of the landscape.
     * @return the width of the landscape.
     */
    public int getWidth(){
        return width;
    } 

    /**
     * Adds an agent to the landscape.
     * @param a the agent to be added.
     */
    public void addAgent(Agent a){
        agents.add(a);
    }

    /**
     * Returns a string representation of the landscape.
     * @return a string representation of the landscape.
     */
    public String toString(){
        return agents.toString();
    }

    /**
     * Gets the neighbors of a specified location within a given radius.
     * @param x0 the x-coordinate of the location.
     * @param y0 the y-coordinate of the location.
     * @param radius the radius within which neighbors are considered.
     * @param isViral flag indicating whether the neighbors should be infected if not already.
     * @return a linked list of neighbors within the specified radius.
     */
    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius, boolean isViral){
        LinkedList<Agent> neighbors = new LinkedList<>();
        for (Agent a: agents){
            if ((x0 - radius < a.getX() && a.getX() < x0 + radius) && (y0 - radius < a.getY() && a.getY() < y0 + radius)){
                neighbors.add(a);
                if(isViral && !a.getIsViral()){
                    makeViral(a);
                }
            }
        }
        return neighbors;
    }

    /**
     * Draws the landscape by calling the draw method of each agent.
     * @param g the graphics object for drawing.
     */
    public void draw(Graphics g){
        for (Agent a: agents){
            a.draw(g);
        }
    }

    /**
     * Updates the state of each agent in the landscape.
     * @return the number of agents that moved during the update.
     */
    public int updateAgents(){
        Random rand = new Random();
        int randomInt = rand.nextInt(agents.size());
        Agent randomAgent = agents.remove(randomInt);
        AntiSocialAgent newAgent = new AntiSocialAgent(randomAgent.getX(), randomAgent.getY(), randomAgent.getRadius());
        agents.add(newAgent);
        int numMoved = 0;
        for (Agent agent : agents) {
            agent.updateState(this);
            if(agent.getMoved()){
                numMoved++;
            }
        }
        return numMoved;
    }

    /**
     * Converts a non-viral agent to a viral agent and adds it to the landscape.
     * @param a the agent to be converted and added.
     */
    public void makeViral(Agent a){
        Agent randomAgent = agents.removeItem(a);
        VirusAgent newAgent = new VirusAgent(randomAgent.getX(), randomAgent.getY(), randomAgent.getRadius());
        agents.add(newAgent);
        numViral++;
    }

    /**
     * Gets the number of viral agents in the landscape.
     * @return the number of viral agents.
     */
    public int getViral(){
        return numViral;
    }
}
