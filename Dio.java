import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap; // Hash map for animation with its animator...
/*
 *   Dio is the player. Although this is a class, this will only be one player 
 *   (only one object) so static methods are used for convenience. 
 *   (a pro programmar would probably spit at me for that statement)
 */
public class Dio extends Player implements Time_Snapshottable
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
    
    private final int moveSpeed = GameConfig.DIO_MOVE_SPEED;// 5 pixels per frame
    
    //Storing death location
    private int dieX, dieY;
    
    //Ability
    private Ability_MadeInHeaven mihAbility = new Ability_MadeInHeaven();
    /*
     * Contructus a DIO by setting up animations.
     * Currently default animation is Dash as a placeholder
     */
    public Dio(){
        //All the names of the animations: action name, folder name, file prefix are the same.
        String[] animNames = {"Idle", "Wry", "Dash", "High", "Intro","Scratch", "Roll","Lose", "WalkLeft", "WalkRight"};
        for (String name : animNames) {
            // Parameters: Folder name
            animations.put(name, new Animator("Dio", name, GameConfig.DIO_BASE_SCALE));
        }
        //Some animations use custom speeds: e.g. scratch is faster
        animations.put("Scratch", new Animator("Dio","Scratch",3, GameConfig.DIO_BASE_SCALE));

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
        if (isDead && !name.equals("Lose")) return;
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
        
        //******Made In Heaven Ability Animation
        mihAbility.update(this, (MyWorld)getWorld());
        
        //Check if we are in a timed, temporary sequence
        if(!isDead &&activeTimer != null){
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
        //TRY TO TRIGGER ABILITY
        if (Greenfoot.isKeyDown(GameConfig.MIH_BUTTON)) {
            mihAbility.activate();
             
        }
        if (isDead) {
            MyWorld world = (MyWorld) getWorld();
            
            // 1. You MUST update the timer every frame so it counts!
            deathTimer.update(world); 
            
            if (!deathTimer.isExpired()) {
                shake();
            }
            // 2. Check if the time is up
            if (deathTimer.isExpired()&& !world.isRewinding()) {
                world.getGSM().changeState(new GameOverState());
            }
        } 
            
        else {
            if(Greenfoot.mouseClicked(null)){
                playRandomAnimation();
            }
            
            // 1. Calculate how fast we are moving
            int currentSpeed = (int)(moveSpeed * mihAbility.getSpeedMultiplier());
            
            // 2. Start with the current position
            int nextX = getX();
            int nextY = getY();
            
            // 3. Update the intended position based on keys
            if (Greenfoot.isKeyDown("up"))    nextY -= currentSpeed;
            if (Greenfoot.isKeyDown("down"))  nextY += currentSpeed;
            if (Greenfoot.isKeyDown("left"))  nextX -= currentSpeed;
            if (Greenfoot.isKeyDown("right")) nextX += currentSpeed;

            // 4. Calculate the "No-Submerge" limits
            int halfWidth = getImage().getWidth() / 2;
            int halfHeight = getImage().getHeight() / 2;
            int worldWidth = getWorld().getWidth();
            int worldHeight = getWorld().getHeight();

            // 5. THE LIMITER (Clamping)
            // If the next position would put the edge off-screen, 
            // set the position to the exact edge instead.
            if (nextX < halfWidth) nextX = halfWidth;
            if (nextX > worldWidth - halfWidth) nextX = worldWidth - halfWidth;
            
            if (nextY < halfHeight) nextY = halfHeight;
            if (nextY > worldHeight - halfHeight) nextY = worldHeight - halfHeight;

            // 6. Final Move (Only happens once, perfectly smooth)
            setLocation(nextX, nextY);
        }
        
    }
    //Die method is public because anyone can tell player to die
    public void die(){
        MyWorld world = (MyWorld) getWorld();
        if (isDead|| world.isRewinding()) return;
        isDead = true;
        SadFace sadFace = new SadFace();
        getWorld().addObject(sadFace,world.getWidth()/2, world.getHeight()/2);
        setAnimation("Lose");
        AudioManager.playPool("dioLostVoices");
        //Set death location
        dieX = getX();
        dieY = getY();
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
            world.addObject(new Banner(BossConfig.DIO), GameConfig.s(1120), GameConfig.s(200));
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
        
        
        double baseWidth = getImage().getWidth();
        double baseHeight = getImage().getHeight();
        double hitboxRadius = ((baseWidth + baseHeight) / 4.0 * padding);
        
        return distance < hitboxRadius;
    }
    
    private void shake (){
        if (!deathTimer.isExpired()) {
            int shakeX = Greenfoot.getRandomNumber(7) - 3; 
            int shakeY = Greenfoot.getRandomNumber(7) - 3;
                
            // 2. ALWAYS add to the baseX/baseY, NOT the current getX()
            setLocation(dieX + shakeX, dieY + shakeY);
        } 
        else {
            // 3. Return to the exact center when finished
            setLocation(dieX, dieY);
        }
    }

    
    //***********************************************************
    //=========Tme Machine Stuff For Time Rewinding==============
        private static class DioData {
        boolean isDead;
        String animName;
        int deathTimerRemaining;
        boolean deathTimerActive;
        int mihFramesRemaining;
        boolean mihActive;

        
        DioData(boolean isDead, String animName, int deathTimerRemaining, boolean active, 
                int mihFrames, boolean mihActive) {
            this.isDead = isDead;
            this.animName = animName;
            this.deathTimerRemaining = deathTimerRemaining;
            this.deathTimerActive = active;
            this.mihFramesRemaining = mihFrames;
            this.mihActive = mihActive;
        }
    }
    
    public Time_ActorMemento captureState() {
        return new Time_ActorMemento(this, getX(), getY(),
            new DioData(isDead, currentAnimName,
                        deathTimer.getRemainingFrames(),
                        deathTimer.isActive(), mihAbility.getRemainingFrames(),// Ask the ability for frames
                        mihAbility.isActive()));          // Ask if it's currently running));
    }
    
    public void restoreState(Time_ActorMemento m) {
        DioData d = (DioData) m.customData;
        
        // Check if we're being revived
        if (this.isDead && !d.isDead) {
            // Remove the sad face that appeared on death
            if (getWorld() != null) {
                getWorld().removeObjects(getWorld().getObjects(SadFace.class));
            }
        }
        
        this.isDead = d.isDead;
        // Don't forcibly switch to death anim if alive
        if (!isDead) setAnimation(d.animName);
        deathTimer.setRemainingFrames(d.deathTimerRemaining);
        if (d.deathTimerActive) deathTimer.start(); else deathTimer.stop();
        
        
        // --- RESTORE MADE IN HEAVEN STATE ---
        mihAbility.setRemainingFrames(d.mihFramesRemaining);
        if (d.mihActive) mihAbility.startTimer(); else mihAbility.stopTimer();
    }
}
