import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exclaimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exclaimation extends Actor
{
    private GameTimer lifeTimer = new GameTimer(1.0, false);
    private Animator exclaimAnim;
    
    public Exclaimation(){
        exclaimAnim = new Animator("symbols", "exclaimation", 0.1 * GameConfig.SCALE);
        lifeTimer.start();
    }
    public void act()
    {
        // Add your action code here.
        setImage(exclaimAnim.getCurrentFrame());
        lifeTimer.update((MyWorld) getWorld());
        if(lifeTimer.isExpired()){
            getWorld().removeObject(this);
        }
    }
}
