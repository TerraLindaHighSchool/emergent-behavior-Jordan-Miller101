import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Places and removes crumbs, which are food for the ants.
 * 
 * @author Jordan Miller
 * @version 5/8/20
 */
public class Food extends Actor
{
    private GreenfootImage image;
    private int crumbs = 100;
    private static final int size = 50;
    
    public Food()
    {
        image = new GreenfootImage(size, size);
        updateImage();
    }
    
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //do what food's gotta do
    } 
    
    private void updateImage()
    {
        Random random = new Random();
        
        for(int i = 0; i < crumbs; i++)
        {   
            int stDev = size/6;
            int x = (int) (stDev * random.nextGaussian() + 3 * stDev);
            int y = (int) (stDev * random.nextGaussian() + 3 * stDev);
            
            //keep crumbs in image
            if(x < 0)
            {
                x = 0;
            }
            
            if(x >= size)
            {
                x = size - 1;
            }
            
            if(y < 0)
            {
                y = 0;
            }
            
            if(y >= size)
            {
                y = size - 1;
            }
            
            Color color = new Color(50, 20, 20);
            image.setColorAt(x, y, color);
        }
        
        setImage(image);
    }
    
    public void removeCrumb()
    {
        crumbs = crumbs - 1;
        
        image.clear();
        updateImage();
        
        if(crumbs == 0)
        {
            getWorld().removeObject(this);
        }
    }
}
