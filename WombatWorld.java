import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
import java.lang.Math.*;
import java.util.Random;
/**
 * Wombat World.
 * Spawns a random amount of Wombats and Leaves. Change the variables at the top.
 * 
 * @author Leon Namowitz
 * Original code by: Michael Kölling
 * @version 3.0
 */

public class WombatWorld extends World
{       
    public static int maxLeaveAmount = 21;
    public static int maxWombatAmount = 5;
    
    public static int chosenWombatAmount;
    public static int chosenLeaveAmount;
    public static int leavesEaten;
    // public static int stepCounter;

    /**
     * Create a new world with 10x10 cells and
     * with a cell size of 60x60 pixels.
     */
    public WombatWorld() 
    {
        super(10, 10, 60);        
        setBackground("cell.jpg");
        setPaintOrder(Wombat.class, Leaf.class);  // draw wombat on top of leaf
        prepare();
    }
    
    /**
     * Random Number Generator for different cases: (n-1)
     *
     */
    public int generator(int type)
    {
        
        if (type == 1)  {
            //int rdmNumber = rnd.nextInt(4);
            return(Greenfoot.getRandomNumber(4)*90);
        }
        else if (type == 2) {
            //int rdmNumber = rnd.nextInt(11);
            return(Greenfoot.getRandomNumber(11));
        }
        else if (type == 3) {
            //int rdmNumber = rnd.nextInt(11);
            chosenWombatAmount = Greenfoot.getRandomNumber(maxWombatAmount);
            chosenWombatAmount++;
            return(chosenWombatAmount);
        }
        else if (type == 4) {
            //int rdmNumber = rnd.nextInt(11);
            chosenLeaveAmount = Greenfoot.getRandomNumber(maxLeaveAmount);
            chosenLeaveAmount++;
            return(chosenLeaveAmount);
        }
        else    {
            return(0);
        }
    }
        
    /**
     * Populate the world with a fixed scenario of wombats and leaves.
     * "1" random value: 0,90,180,270
     * "2" random value: 0-10
     * "3" random value: 0-maxWombatAmount
     * "4" random value: 0-maxLeaveAmount 
     */    
    public void populate()
    {
        int current = generator(3);
         for (int i = 0; i < current; i++)    {
        
             addObject(new Wombat(generator(1),i), generator(2), generator(2));
            //System.out.print(numberOfObjects());
        }
        //addObject(new Wombat(0,42),4,4);
        randomLeaves(generator(4));
    }
    
    /**
     * Place a number of leaves into the world at random places.
     * The number of leaves can be specified.
     */
    public void randomLeaves(int howMany)
    {
        for (int i=0; i<howMany; i++) {
            Leaf leaf = new Leaf();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(leaf, x, y);
        }
    }
    
    /**
     * Not currently in use!
     */
    public static void finished()
    {
        System.out.println("Steps taken: " + Counter.getstepCounter() + "\nAll " + WombatWorld.chosenLeaveAmount + " leaves have been eaten :)");
        
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //Wombat wombat = new Wombat(0);
        Counter.setstepCounter(0);
        populate();
        //wombat.initMove();
    }
}