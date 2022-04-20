/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter  
{
    // instance variables - replace the example below with your own
    private static int stepCounter = 0;

    /**
     * Constructor for objects of class Counter
     */
    public Counter()
    {
    }
    
    
    
    public static int getstepCounter() 
    {
        return stepCounter;        
    }

    public static void setstepCounter(int value) {
        stepCounter = value;
    }
    
    public static void incrementstepCounter() {
        stepCounter++;
    }


    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    // public int sampleMethod(int y)
    // {
    //     // put your code here
    //     return x + y;
    // }
}
