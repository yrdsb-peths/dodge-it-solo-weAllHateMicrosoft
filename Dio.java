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
    private Animator currentAnimator;
    
    /**
     * Act - do whatever the Dio wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean atTop = false;
    public Dio(){
        idleAnim = new Animator("Idle");
        wryAnim = new Animator("Wry");
        dashAnim = new Animator("Dash");
        currentAnimator = dashAnim;
        setImage(currentAnimator.getCurrentFrame());
    }
    public void act()
    {
        movementLogic();    
        animationLogic();
    }
    
    private void animationLogic(){
        setImage(currentAnimator.getCurrentFrame());
    }
    private void movementLogic(){
        if(Greenfoot.mouseClicked(null)){
            atTop = !atTop;
        }
        
        if(atTop){
            setLocation(100,100);
        }
        else{
            setLocation(100,300);
        }
    }
}
