import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap; // Hash map for animation with its animator...
/*
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dio extends Actor
{
    private HashMap<String, Animator> animations = new HashMap<>();
    //The current animation
    private Animator currentAnimator;
    private String currentAnimName = "";
    /**
     * Act - do whatever the Dio wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean atTop = false;
    public Dio(){
        String[] animNames = {"Idle", "Wry", "Dash", "High", "Intro", "Roll", "WalkLeft", "WalkRight"};
        for (String name : animNames) {
            // Parameters: Folder name, Animation speed (lower is faster)
            animations.put(name, new Animator(name));
        }

        // 2. Set the starting animation
        setAnimation("Dash");
    }
    public void act()
    {
        movementLogic();    
        animationLogic();
    }
    
    public void setAnimation(String name){
        if (!currentAnimName.equals(name) && animations.containsKey(name)) {
            currentAnimName = name;
            currentAnimator = animations.get(name);
            currentAnimator.reset();
        }
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
