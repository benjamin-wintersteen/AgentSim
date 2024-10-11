/*
  Written by Benjamin Wintersteen

  Creates an Abstract Agent class.

  This is the framework given for creating agents who will interact within a landscape.
*/
import java.awt.Graphics;

// Abstract Agent class representing entities in a simulation
public abstract class Agent {
    private double xPos;
    private double yPos;
    protected int radius;
    protected boolean moved;
    protected boolean isViral = false;

    // Constructor that sets the initial position
    public Agent(double x0, double y0) {
        this(x0, y0, 0);
    }

    // Constructor that sets the initial position and radius
    public Agent(double x0, double y0, int radius) {
        this.xPos = x0;
        this.yPos = y0;
        this.radius = radius;
    }

    // Getter method for x position
    public double getX() {
        return xPos;
    }

    // Getter method for y position
    public double getY() {
        return yPos;
    }

    // Getter method for viral status
    public boolean getIsViral() {
        return isViral;
    }

    // Getter method for radius
    public int getRadius() {
        return radius;
    }

    // Getter method for moved field
    public boolean getMoved() {
        return moved;
    }

    // Setter method for x position
    public void setX(double newX) {
        this.xPos = newX;
    }

    // Setter method for y position
    public void setY(double newY) {
        this.yPos = newY;
    }

    // Setter method for radius
    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    // Setter method for viral status
    public void setIsViral(boolean b) {
        this.isViral = b;
    }

    // Setter method for moved field
    public void setMoved(boolean b) {
        this.moved = b;
    }

    // String representation of the Agent's position
    public String toString() {
        return "(" + xPos + "," + yPos + ")";
    }

    // Abstract method to be implemented by subclasses for updating the state in the landscape
    public abstract void updateState(Landscape scape);

    // Abstract method to be implemented by subclasses for drawing the Agent
    public abstract void draw(Graphics g);
}

