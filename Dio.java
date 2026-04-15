import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dio extends Actor
{
    private Animator idleAnim;
    private Animator wryAnim;
    private Animator walkLeftAnim;
    private Animator walkRightAnim;
    private Animator dashAnim;
    private Animator highAnim;

    
    /**
     * Act - do whatever the Dio wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean atTop = false;
    public void act()
    {
        if(Greenfoot.mouseClicked(null)){
            atTop = !atTop;
        }
        
        if(atTop){
            setLocation(100,100);
        }
        else{
            setLocation(100,300);
        }
        if (Greenfoot.isKeyDown("left")){
            
        }
    }
}
