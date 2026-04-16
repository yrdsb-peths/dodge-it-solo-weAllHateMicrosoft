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
    //A hashmap(dictionary) of animations with key: name and value: the corresponding animator
    private static HashMap<String, Animator> animations = new HashMap<>();
    //The current animationor and action name it takes in
    private static Animator currentAnimator;
    private static String currentAnimName = "";


    /*
     * Contructus a DIO by setting up animations.
     * Currently default animation is Dash as a placeholder
     */
    public Dio(){
        //All the names of the animations: action name, folder name, file prefix are the same.
        String[] animNames = {"Idle", "Wry", "Dash", "High", "Intro","Scratch", "Roll","Lose", "WalkLeft", "WalkRight"};
        for (String name : animNames) {
            // Parameters: Folder name
            animations.put(name, new Animator(name));
        }
        //Some animations use custom speeds
        animations.put("Scratch", new Animator("Scratch",3));

        // 2. Set the starting animation
        setAnimation("Dash");
    }
    
    /*
     * The main loop that gets called like (60?) times per second:
     * Updates movement and logic.
     */
    public void act()
    {
        movementLogic();    
        animationLogic();
    }
    
    /*
     * Sets animatoin based on the name input.
     */
    public static void setAnimation(String name){
        //Prevents repetitive calling
        if (!currentAnimName.equals(name) && animations.containsKey(name)) {
            currentAnimName = name;
            currentAnimator = animations.get(name);
            currentAnimator.reset();
        }
    }
    
    /*
     * Overloaded version that accepts a speed
     */
    public void setAnimation(String name, int speed) {
    if (animations.containsKey(name)) {
        animations.get(name).setSpeed(speed); // Update the speed
        setAnimation(name);                   // Call the original logic to switch
    }
    }
    
    /*
     * Sets actor image as the correct frame. 
     */
    private void animationLogic(){
        setImage(currentAnimator.getCurrentFrame());
    }
    /*
     * Current, trashy movement logic that should be worked on
     */
    private void movementLogic(){
        if(Greenfoot.mouseClicked(null)){
            playRandomAnimation();
        }
        
        if (Greenfoot.isKeyDown("up")) 
        {
            setLocation(getX(), getY() - 5);
        }
        
        if (Greenfoot.isKeyDown("down")) 
        {
            setLocation(getX(), getY() + 5);
        }
        
    }
    
    /*
     * A funny method that... calls a random animation (obviously)
     */
     private void playRandomAnimation(){
        Object[] keys = animations.keySet().toArray();
        int randomIndex = Greenfoot.getRandomNumber(keys.length);
        String randomAnimName = (String) keys[randomIndex];
        setAnimation(randomAnimName);
    }
}
