import greenfoot.*;
/**
 * Playing state is the default state of game running.
 * 
 * The GameStateManager can add this state to its stack, and call its methods
 */
public class PlayingState implements GameState
{
    //A spawn manager will handle all spawning problems
    private SpawnManager spawnManager;
    public void enter(MyWorld world){
        //The normal game running: spawns players, reset timers, play music etc.
        
        //Sample: this is how you call some basic actors
        Dio dio = new Dio();
        world.addObject(dio,100,100);

        Roadroller roadroller = new Roadroller();
        world.addObject(roadroller,400,100);
        
        spawnManager = new SpawnManager();


        //Sample: this is how you call a banner
        //addObject(new Banner(BossConfig.DIO), 1120, 200);
    }
    
    public void update(MyWorld world){
        //Handle normal game logic, like spawning obstacles, movement logics etc.
        
        //Sample of swithicng state: click "p" to pause. But we can also use a button
        if("p".equals(Greenfoot.getKey())){
            world.getGSM().pushState(new PausedState());
        }
        spawnManager.update(world);
    }
    
    public void exit(MyWorld world){
        //Clean things up as we leave this state.
        world.removeObjects(world.getObjects(null));
    }
}
