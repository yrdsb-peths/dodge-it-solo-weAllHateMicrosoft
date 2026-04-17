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
    
    //UI Components
    private UIText scoreDisplay;
    public void enter(MyWorld world){
        //The normal game running: spawns players, reset timers, play music etc.
        
        ScoreManager.reset(); //Reset score
        AudioManager.playLoop("dio_bgm"); //Play bgm
        //Sample: this is how you call some basic actors
        
        Dio dio = new Dio();
        world.addObject(dio,80,80);
        
        spawnManager = new SpawnManager();

        scoreDisplay = new UIText("SCORE: 0", 30, Color.BLACK);
        world.addObject(scoreDisplay, 100, 30);
        //Sample: this is how you call a banner
        //addObject(new Banner(BossConfig.DIO), 1120, 200);
    }
    
    public void update(MyWorld world){
        //Handle normal game logic, like spawning obstacles, movement logics etc.
        
        //Sample of swithicng state: click "p" to pause. But we can also use a button
        if("w".equals(Greenfoot.getKey())){
            world.getGSM().pushState(new PausedState());
        }
        spawnManager.update(world);
        //Update score display
        scoreDisplay.setText("SCORE: " + ScoreManager.getScore());

    }
    
    public void exit(MyWorld world){
        //Clean things up as we leave this state.
        AudioManager.stop("dio_bgm"); //Stop bgm
        world.removeObjects(world.getObjects(null));
    }
    
    //Getter method for spawn manager
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
}
