import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacles extends Actor
{
    protected int speed;
    
    /**
     * Act - do whatever the Obstacles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (((MyWorld)getWorld()).getGSM().isState(PlayingState.class)) {
            setLocation(getX() - speed, getY());
            if (getX() <= 0) getWorld().removeObject(this);
        }
    }
}
