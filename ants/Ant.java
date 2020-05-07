import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling and Jordan Miller
 * @version 0.3
 */
public class Ant extends Creature
{
    private boolean carryingFood = false;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private static final int MAX_PH_AVAILABLE = 16;
    private static final int TIME_FOLLOWING_TRAIL = 30;
    private int phAvailable = MAX_PH_AVAILABLE;
    private int followTrialTimeRemaining = 0;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        image1 = getImage();
        image2 = new GreenfootImage("ant-with-food.gif");
    }

    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
    }
    
    private boolean atHome()
    {
        if(getHomeHill() != null) {
            return(Math.abs(getX() - getHomeHill().getX()) < 4) &&
                  (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else {
            return false;
        }
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        
        if (food != null) 
        {
            food.removeCrumb();
            carryingFood = true;
            setImage(image2);
        }
    
    }
    
    private void searchForFood()
    {
        randomWalk();
        checkForFood();
    }
    
    private void status()
    {
        if(carryingFood)
        {
            walkTowardsHome();
            
            if(atHome())
            {
                setImage(image1);
                carryingFood = false;
                getHomeHill().countFood();
            }
        }
        
        if(!carryingFood)
        {
            searchForFood();
        }
    }
    
   
}