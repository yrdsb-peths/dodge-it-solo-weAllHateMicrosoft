import greenfoot.*;
/**
 * Write a description of class PausedState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PausedState implements GameState
{
    public void enter(MyWorld world){
        //The game is paused
        //We will show something like a "GAME PAUSED" banner, but currently its empty
    }
    
    public void update(MyWorld world){
        //Handle normal game logic, like spawning obstacles, movement logics etc.
        
        //Sample of swithicng state: use "p" to unpause, but we can also use a button
        if(Greenfoot.isKeyDown("p")){
            //Remove the pause state, resume to normal/whatever state previosly running.
            world.getGSM().popState();
        }
        
        //Some eastereggs?
    }
    
    public void exit(MyWorld world){
        //Clean things up as we leave this state, such as removing the "GAME PAUSED" banner
    }
}
