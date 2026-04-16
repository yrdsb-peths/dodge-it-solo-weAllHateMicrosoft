import greenfoot.*;
/**
 * Write a description of class PausedState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverState implements GameState
{
    public void enter(MyWorld world){
        //The game is over
        //We will show something like a "GAME OVER" banner, but currently its empty
    }
    
    public void update(MyWorld world){
        //Wait for the user to restart ( i mean, what else?)
        
        //Sample of swithicng state: click enter to swurch
        if("enter".equals(Greenfoot.getKey())){
            world.getGSM().changeState(new PlayingState());
        }
    }
    
    public void exit(MyWorld world){
        //Clean things up as we leave this state, such as removing the "GAME PAUSED" banner
    }
}
