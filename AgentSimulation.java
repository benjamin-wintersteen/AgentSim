/*
  Written by Benjamin Wintersteen

  Creates an Abstract Agent Simulation class.

  This runs simulations of the program.
*/
import java.util.Random;

public class AgentSimulation {
    /**
     * The main method that initializes and runs the Life Simulation.
     *
     * @param args Command-line arguments for configuring the simulation.
     * @throws InterruptedException If the simulation thread is interrupted during sleep.
     */
    public static void main(String[] args) throws InterruptedException {
        // Check and update simulation parameters based on command-line arguments
        checkUsage(args);
        int width = 500;
        int height = 500;
        int agentNum = 50;
        int radius = 25;

        // Uses command line arguments to update rows, columns, and initial chance
        if (args.length >= 1 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 1000) {
            agentNum = Integer.parseInt(args[0]);
        }
        if (args.length >= 2 && Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) < 10000) {
            width = Integer.parseInt(args[1]);
        }
        if (args.length >= 3 && Integer.parseInt(args[2]) > 0 && Integer.parseInt(args[2]) < 10000) {
            height = Integer.parseInt(args[2]);
        }
        if (args.length >= 4 && Integer.parseInt(args[3]) > 0 && Integer.parseInt(args[3]) < 10000) {
            radius = Integer.parseInt(args[3]);
        }

        // Create a new Landscape with the specified parameters
        int totalViral = 0;
        
            int numIterations = 5000;
            Landscape scape = new Landscape(width, height);
            Random rand = new Random();

            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            scape.addAgent(new VirusAgent(x, y, radius));

            for (int i = 1; i < agentNum; i++) {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
                scape.addAgent(new SocialAgent(x, y, radius));
            }
            
            // Create a LandscapeDisplay for visualization
            LandscapeDisplay display = new LandscapeDisplay(scape);

            // Run the simulation for 50 steps
            for (int i = 0; i <= 5000; i++) {
                Thread.sleep(250);

                // Exit loop if the Landscape becomes infinite
                // Advance the Landscape, repaint the display, and save the frame as an image
                int finished = scape.updateAgents();
                if (finished == 0) {
                    numIterations = i;
                    i = 5001;
                }
                display.repaint();
                display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
            }
            totalViral += scape.getViral();
            System.out.println("The number of iterations is " + numIterations + " with " + scape.getViral() + " viral agents.");
        
        System.out.println("The average number of iterations is " + totalViral / 10);
    }

    /**
     * Checks the usage of the program and prints instructions if needed.
     *
     * @param args Command-line arguments provided to the program.
     */
    public static void checkUsage(String[] args) {
        if (args.length > 0) {
            // Print provided command-line arguments
            for (String str : args) {
                System.out.println(str);
            }
        } else {
            // Print usage instructions if no command-line arguments are provided
            System.out.println("Usage: java LifeSimulation [printed args]" +
                    "\n 1st arg: Updates number of Agents [int: 0-1000]" +
                    "\n 2nd arg: Updates width [int: 0-10000]" +
                    "\n 3rd arg: Updates height [int: 0-10000]" +
                    "\n 4th arg: Updates the radius [int: 0-10000]");
        }
    }
}
