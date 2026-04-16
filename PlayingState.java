import greenfoot.*;
/**
 * Playing state is the default state of game running.
 * 
 * The GameStateManager can add this state to its stack, and call its methods
 */
public class PlayingState implements GameState
{
    public void enter(MyWorld world){
        //The normal game running: spawns players, reset timers, play music etc.
    }
    
    public void update(MyWorld world){
        //Handle normal game logic, like spawning obstacles, movement logics etc.
        
        //Sample of swithicng state: click "p" to pause. But we can also use a button
        if(Greenfoot.isKeyDown("p")){
            world.getGSM().pushState(new PausedState());
        }
    }
    
    public void exit(MyWorld world){
        //Clean things up as we leave this state.
    }
}
