import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Wombat. Multiple Wombats look for the closest leaf, move towards it and eat it. This process is repeated until all leaves are gone.
 * Amount of Wombats and Leaves and starting locations and orientations are randomized.
 * 
 * @author Leon Namowitz
 * Original code by: Michael Kölling
 * @version 3.0
 */
public class Wombat extends Actor
{
    //private int leavesEaten;
    //private int stepCounter = 0; 
    private int j = 0;
    private int i = 0;
    private int wombatIndex;
    private int radius;

    public Wombat(int rotation,int index)
    {
        wombatIndex = index;
        WombatWorld.leavesEaten = 0;
        setRotation(rotation);

    }
    
    /**
     * Do whatever the wombat likes to do just now.
     */
    public void act()
    {
        moveAlg();
        
    }
    
    /**
     * Algorithm for leaf finding and navigation.
     */
    private void moveAlg()
    { 
        if (getLeavesEaten() == WombatWorld.chosenLeaveAmount)  {
            System.out.println("\nAll leaves have been eaten.");
            Greenfoot.stop();
        }
        else if (isLeafInRange(radius) == false) {
            radius++;
            //System.out.print(radius);
        }
        else if(foundLeaf() == true)    {
            eatLeaf();
        }
        else if (isLeafInRange(radius) == true) {
            if ((getLeafX(radius) - getWombatX()) < 0)  {
                int diffX = getLeafX(radius) - getWombatX();
                //System.out.print(diffX);
                moveLeft();
                }
            else if ((getLeafX(radius) - getWombatX()) > 0)  {
                int diffX = getLeafX(radius) - getWombatX();
                //System.out.print(diffX);
                moveRight();
            }
            else if ((getLeafY(radius) - getWombatY()) < 0) {
                int diffY = getLeafY(radius) - getWombatY();
                moveUp();
            }
            else if ((getLeafY(radius) - getWombatY()) > 0) {
                int diffY = getLeafY(radius) - getWombatY();
                moveDown();



            }
        }
    }    



    public int getLeafX(int radius)
    {
        // for Redundancy
        if (isLeafInRange(radius) == false)    {
            return(42);
        }
        else    {
            List localleafsInRange = getObjectsInRange(radius, Leaf.class);
            Object under = localleafsInRange.get(0);
            
            Actor leaf = (Actor)under; 
            int leafXlocal = leaf.getX();
           
            // System.out.println(leafX);
            // System.out.println(leafY);
    
            return(leafXlocal);
        }
    }

   public int getLeafY(int radius)
   {    
       // for Redundancy
        if (isLeafInRange(radius) == false)    {
            return(42);
        }
        else    {
            List localleafsInRange = getObjectsInRange(radius, Leaf.class);
            Object under = localleafsInRange.get(0);

            Actor leaf = (Actor)under;
            int leafYlocal = leaf.getY();
            return (leafYlocal);
        }
   }

   public int getWombatX()
   {
        int currentX = getX();
        return(currentX);
   }

   public int getWombatY()
   {
        int currentY = getY();
        return(currentY);
   }


   
   public boolean isLeafInRange(int radius)
   {
       if (getObjectsInRange(radius, Leaf.class).isEmpty())    {
           return(false);
        }
        else{
            return(true);
        }        
    }
    

    public int getWombatIndex()
    {
        return(wombatIndex);
    }
    

    // Movement Methods //
    public void moveLeft()
    {
        setRotation(180);
        move(1);
    }

    public void moveRight()
    {
        setRotation(0);
        move(1);
    }

    public void moveUp()
    {
        setRotation(270);
        move(1);
    }

    public void moveDown()
    {
        setRotation(90);
        move(1);
    }
    // Movement Methods //

    /**
     * Legacy Movement
     * Deprecated.
     */
    private void legacyMove()
    {
        if (foundLeaf() == true && canMove() == true && WombatWorld.leavesEaten < (WombatWorld.chosenLeaveAmount)) {
            eatLeaf();
            move();
            //stepCounter++;
            //System.out.print(stepCounter);

        }
        else if (canMove() == true && WombatWorld.leavesEaten < (WombatWorld.chosenLeaveAmount)) {
            move();  
            // stepCounter++;
            // System.out.print(WombatWorld.chosenLeaveAmount);
            // System.out.print(getX());
            // System.out.print(stepCounter);

        }
        else if (canMove() == false && foundLeaf() == true && WombatWorld.leavesEaten < (WombatWorld.chosenLeaveAmount)){
            eatLeaf();
            nextRow();
            //stepCounter++;
            //System.out.print(stepCounter);
        }
        else if (canMove() == false && WombatWorld.leavesEaten < (WombatWorld.chosenLeaveAmount)){
            nextRow();
            //System.out.print(stepCounter);
        }
        else    {
            //WombatWorld.finished();
            //System.out.println("Steps taken: " + stepCounter + "\nAll " + WombatWorld.chosenLeaveAmount + " leaves have been eaten :)");
            Greenfoot.stop();
        }
        
        Counter.incrementstepCounter();
    }

    /**  
     * Initial movement of all wombats to the bottom left corner
     * Deprecated.
     */
    private void initMove()
    { 
        if (j < 1)  {
            setDirection(2);
            move(9);
            setDirection(1);
            move(9);
            setDirection(0);
            j=j+1;
            //System.out.print(j);
        }
    }
    
    /**
     * Advances 1 row and orients itself, depending on where wombat is
     * Deprecated.
     */
    private void nextRow()
    {
        int rotation = getRotation();
        if (rotation == 0)  {
            setDirection(3);
            move(1);
            setDirection(2);
        }
        else if (rotation == 180)  {
            setDirection(3);
            move(1);
            setDirection(0);
        }    
        else {
            setDirection(0);
        } 
        i++; 
    }
    
    /**
     * Move one step forward.
     */
    private void move()
    {
        move(1);
    }
    
    /**
     * Turn left by 90 degrees.
     * Deprecated.
     */
    private void turnLeft()
    {
        turn(-90);
    }
       
    /**
     * Check whether there is a leaf in the same cell as we are.
     * Return true, if there is, false otherwise.
     */
    public boolean foundLeaf()
    {
        Actor leaf = getOneObjectAtOffset(0, 0, Leaf.class);
        return leaf != null;
    }
    
    /**
     * Eat a leaf (if there is one in our cell).
     */
    private void eatLeaf()
    {
        Actor leaf = getOneObjectAtOffset(0, 0, Leaf.class);
        if (leaf != null) {
            // eat the leaf...
            getWorld().removeObject(leaf);
            WombatWorld.leavesEaten++;
            //System.out.print(leavesEaten);
        }
    }
    
    // public void printObject()
    // {
        // System.out.print(getOneObjectAtOffset(0,0,Leaf.class));
    
    // }
    
    /**
     * Set the direction we're facing. The 'direction' parameter must
     * be in the range [0..3].
     */
    public void setDirection(int direction)
    {
        if ((direction >= 0) && (direction <= 3)) {
            setRotation(direction * 90);
        }
    }
    
    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     * Deprecated.
     */
    private boolean canMove()
    {
        int rotation = getRotation();
        int x = getX();
        int y = getY();
        boolean facingEdge = false;
        
        switch (rotation) {
            case 0:
                facingEdge = (x == getWorld().getWidth() - 1);
                break;
            case 90:
                facingEdge = (y == getWorld().getHeight() - 1);
                break;
            case 180:
                facingEdge = (x == 0);
                break;
            case 270:
                facingEdge = (y == 0);
                break;
        }
        
        return !facingEdge;
    }

    /**
     * Tell how many leaves we have eaten.
     */
    public int getLeavesEaten()
    {
        return WombatWorld.leavesEaten;
    }
}
