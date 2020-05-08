import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.util.List;

/**
 * The world where ants live.
 * 
 * @author Michael Kölling  
 * @version 0.2
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }
    
    public void act()
    {
        List<Food> foods = getObjects(Food.class);
        List<Pheromone> pheromones = getObjects(Pheromone.class);
        
        if(foods.size() == 0 && pheromones.size() == 0)
        {
            Greenfoot.stop();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        AntHill antHill = new AntHill();
        addObject(antHill,133,90);
        AntHill secondAntHill = new AntHill();
        addObject(secondAntHill, 420, 410);
        Food food = new Food();
        addObject(food,419,149);
        Food food2 = new Food();
        addObject(food2,155,341);
        Food food3 = new Food();
        addObject(food3,218,543);
    }
}