import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap; // Hash map for animation with its animator...
/*
 *   Dio is the player. Although this is a class, this will only be one player 
 *   (only one object) so static methods are used for convenience. 
 *   (a pro programmar would probably spit at me for that statement)
 */
public class Dio extends Player
{
    //A hashmap(dictionary) of animations with key: name and value: the corresponding animator
    private HashMap<String, Animator> animations = new HashMap<>();
    //The current animationor and action name it takes in
    private Animator currentAnimator;
    private String currentAnimName = "";


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
     * Sets animatoin based on the name input.
     */
    public void setAnimation(String name){
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
    protected void animationLogic(){
        setImage(currentAnimator.getCurrentFrame());
    }
    /*
     * Current, trashy movement logic that should be worked on
     */
    protected void movementLogic(){
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
    //Die method is public because anyone can tell player to die
    public void die(){
        isDead = true;
        setAnimation("Lose");
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
