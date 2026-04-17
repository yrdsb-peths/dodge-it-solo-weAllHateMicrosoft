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

    
    //A 2 second timer before removing object from gae after dying
    private GameTimer deathTimer = new GameTimer(4.0, false);
    //Play some animation for 3 seconds only
    private GameTimer highTimer = new GameTimer(3.0, false);
    //A timer that allows Dio to play some temporary animation.
    //Such as death anim, or high animiation
    private GameTimer activeTimer = null;
    private String defaultAnim = "Dash";
    /*
     * Contructus a DIO by setting up animations.
     * Currently default animation is Dash as a placeholder
     */
    public Dio(){
        //All the names of the animations: action name, folder name, file prefix are the same.
        String[] animNames = {"Idle", "Wry", "Dash", "High", "Intro","Scratch", "Roll","Lose", "WalkLeft", "WalkRight"};
        for (String name : animNames) {
            // Parameters: Folder name
            animations.put(name, new Animator("Dio", name));
        }
        //Some animations use custom speeds: e.g. scratch is faster
        animations.put("Scratch", new Animator("Dio","Scratch",3));

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
    if (getWorld() == null) return; 
    if (animations.containsKey(name)) {
        animations.get(name).setSpeed(speed); // Update the speed
        setAnimation(name);                   // Call the original logic to switch
    }
    }
    
    public void playTimedAnimation(String name, GameTimer timer){
        setAnimation(name);
        this.activeTimer = timer;
        this.activeTimer.reset();
        this.activeTimer.start();
    }
    
    /*
     * Sets actor image as the correct frame. 
     */
    protected void animationLogic(){
        if (getWorld() == null) return; 
        setImage(currentAnimator.getCurrentFrame());
        
        //Check if we are in a timed, temporary sequence
        if(activeTimer != null){
            activeTimer.update((MyWorld)getWorld());
            if(activeTimer.isExpired()){
                activeTimer = null;
                setAnimation(defaultAnim);
            }
        }
    }
    /*
     * Current, trashy movement logic that should be worked on
     */
    protected void movementLogic(){
        if (getWorld() == null) return; 
        if (isDead) {
            MyWorld world = (MyWorld) getWorld();
            
            // 1. You MUST update the timer every frame so it counts!
            deathTimer.update(world); 
            
            // 2. Check if the time is up
            if (deathTimer.isExpired()) {
                world.getGSM().changeState(new GameOverState());
            }
        } 
            
        else{
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
            if (getY() < 0) setLocation(getX(), 0);
            if (getY() > getWorld().getHeight() - 1) setLocation(getX(), getWorld().getHeight() - 1);
            
            // Prevents moving off left/right (if i add horizontal movement later)
            if (getX() < 0) setLocation(0, getY());
            if (getX() > getWorld().getWidth() - 1) setLocation(getWorld().getWidth() - 1, getY());
        }
        
    }
    //Die method is public because anyone can tell player to die
    public void die(){
        MyWorld world = (MyWorld) getWorld();
        if (isDead) return;
        isDead = true;
        SadFace sadFace = new SadFace();
        getWorld().addObject(sadFace,world.getWidth()/2, world.getHeight()/2);
        setAnimation("Lose");
        AudioManager.playPool("dioLostVoices");

        //Start counting down
        //This buys time for voice and animation
        //After timer, the state changes and world resets
        deathTimer.start();
    }
    
    /*
     * A funny method that... calls a random animation (obviously)
     * However, it is buggy so im not using it. 
     */
     private void playRandomAnimation(){
        Object[] keys = animations.keySet().toArray();
        int randomIndex = Greenfoot.getRandomNumber(keys.length);
        String randomAnimName = (String) keys[randomIndex];
        setAnimation(randomAnimName);
    }
    
    /*
     * Dio's method overrides the method in player:
     * normally player can't do anything
     * but dio can move normally and display a cool banner
     * bannerSpawned makes sure banner only gets played once, 
     * and it gets set back to true in player update method.
     */
    protected void onPauseUpdate(MyWorld world) {
        if (!bannerSpawned) {
            world.addObject(new Banner(BossConfig.DIO), 1120, 200);
            bannerSpawned = true;
            playTimedAnimation("Wry", highTimer);
        }
        movementLogic(); // Dio moves during pause!
        animationLogic();
    }
    
    /*
     * When playtesting I think Dio's hitbox is too big, so I decided to decrease it here.
     * 
     * Padding = 1 => fll image is hitbox
     * Padding = 0.5 => half image is hitbox
     */

    public boolean checkCustomHitbox(Actor attacker, double padding) {
        double distance = Math.hypot(getX() - attacker.getX(), getY() - attacker.getY());
        
        double hitboxRadius = ((getImage().getWidth() + getImage().getHeight()) / 4.0) * padding;
        
        return distance < hitboxRadius;
    }
}
