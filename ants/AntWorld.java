import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
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
        addObject(food,450,178);
        Food food2 = new Food();
        addObject(food2,105,327);
        Food food3 = new Food();
        addObject(food3,241,494);
        Food food4 = new Food();
        addObject(food4,557,546);
        Food food5 = new Food();
        addObject(food5,218,77);
        removeObject(food5);
        removeObject(food);
        removeObject(food4);
        food3.setLocation(243,497);
        removeObject(food3);
        removeObject(food2);
    }
}