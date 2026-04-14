import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roadroller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roadroller extends Actor
{
    /**
     * Act - do whatever the Roadroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean faceLeft = false;
    public void act()
    {
        if(!faceLeft){
            getImage().mirrorHorizontally();
            faceLeft = true;
        }
        
        getImage().scale(80,80);
        move(-3);
    }
}
