import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Player extends Actor
{
    protected boolean isDead = false;
    //Every player class must have die, movement and animation  logic.
    //Currently we only have DIO, but new playable characters can be added!
    
    //Die method is public because anyone can tell player to die
    public abstract void die();
    //Other methods are protected, meaning subclasses can access it but not the rest of the world
    protected abstract void movementLogic();
    protected abstract void animationLogic();
    //Keep track of banner
    protected boolean bannerSpawned = false; 
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(world == null) return;
        if(!world.getGSM().isState(PlayingState.class)){
            onPauseUpdate(world);
            return;
        }
        //This banner is for DIO only. I need to tell him he can play banenr again.
        bannerSpawned = false; 
        movementLogic();    
        animationLogic();
    }
    
    public boolean isDead() {
        return isDead;
    }
    
    //Normally, players do nothing while being paused
    protected void onPauseUpdate(MyWorld world) {}
    
}
