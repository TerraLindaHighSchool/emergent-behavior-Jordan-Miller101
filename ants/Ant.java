import greenfoot.*;
import java.util.List;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling and Jordan Miller
 * @version 0.5
 */
public class Ant extends Creature
{
    private boolean carryingFood = false;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private static final int MAX_PH_AVAILABLE = 16;
    private static final int TIME_FOLLOWING_TRAIL = 30;
    private int phAvailable = MAX_PH_AVAILABLE;
    private int followTrailTimeRemaining = 0;
    
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
        if(followTrailTimeRemaining == 0)
        {
            if(smellPheromone()) 
            {
               walkTowardsPheromoneCenter();
            }
            else
            {
                randomWalk();
            }
        }
        else
        {
            followTrailTimeRemaining--;
            walkAwayFromHome();
        }
        
        checkForFood();
    }
    
    private void status()
    {
        if(carryingFood)
        {
            walkTowardsHome();
            handlePheromoneDrop();
            
            if(atHome())
            {
                setImage(image1);
                carryingFood = false;
                getHomeHill().countFood();
            }
        }
        else
        {
            searchForFood();
        }
    }
    
     private void handlePheromoneDrop()
    {
        if(phAvailable == MAX_PH_AVAILABLE)
        {
            getWorld().addObject(new Pheromone(), getX(), getY());
            phAvailable = 0;
        }
        else
        {
            phAvailable++;
        }
    }
    
    private boolean smellPheromone()
    {
        return isTouching(Pheromone.class);
    }
    
    private void walkTowardsPheromoneCenter()
    {
        Pheromone pheromone = (Pheromone) getOneIntersectingObject(Pheromone.class);
        
        if(pheromone != null)
        {
            headTowards(pheromone);
            if(pheromone.getX() == getX() && pheromone.getY() == getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            }
        }
    }
}