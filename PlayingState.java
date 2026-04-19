import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class PlayingState implements GameState {
    private SpawnManager spawnManager;
    private UIText scoreDisplay;
    private Time_RewindManager rewindManager;
    private UI_RewindBar rewindBar;
    private FX_RewindOverlay rewindOverlay; // visual effect during rewind
    
    public void enter(MyWorld world) {
        
        GameRNG.randomize();//Sets a random seed for the game: its consistent
        
        ScoreManager.reset();//Resets score.
        AudioManager.playLoop("dio_bgm");//Use Audio MGR to call the background music
        
        rewindManager = new Time_RewindManager();//Initiate time rewind manager
        
        //Two images of roads are added: each of the side of the screen, they move side by side
        //And go back to start after disappearing from screen to create "infinte scrolling"
        world.addObject(new ScrollingRoad(), world.getWidth()/2, world.getHeight()/2);
        world.addObject(new ScrollingRoad(), world.getWidth() + world.getWidth()/2, world.getHeight()/2);
        
        //Add the player.
        world.addObject(new Dio(), GameConfig.s(80), GameConfig.s(80));
        //Initiate Spawning Manager: Handles all obstacle spawning
        spawnManager = new SpawnManager();
        
        //Score label
        scoreDisplay = new UIText("SCORE: 0", GameConfig.s(25), Color.WHITE);
        world.addObject(scoreDisplay, GameConfig.s(80), GameConfig.s(20));
        
        //Time rewind bar: shows how much "reversible time" has been accumulated
        rewindBar = new UI_RewindBar(rewindManager);
        world.addObject(rewindBar, world.getWidth() - GameConfig.s(100), GameConfig.s(20));
    }
    
    public void update(MyWorld world) {
        //Key needs to be stored because Greenfoot.getKey() somehow can only be compared once,
        //So I can't compare it with "w" and "r" at the same time. 
        String key = Greenfoot.getKey();

        // Pause if w is clicked
        if ("w".equals(key)) {
            world.getGSM().pushState(new PausedState());
        }
        
        // Trigger rewind on R press
        if ("r".equals(key) && rewindManager.canRewind()) {
            AudioManager.setAllSoundsPaused(true);
            AudioManager.playPool("rewind");
            rewindManager.startRewind();
            rewindOverlay = new FX_RewindOverlay();
            world.addObject(rewindOverlay, world.getWidth()/2, world.getHeight()/2);
        }
        
        //
        /* Each frame: either rewind or record
         * Think of the following code like a DVD player. 
         * Every frame, the game checks its mode: 
         * if Rewind Mode is on, it plays the game backward 
         * and removes the 'Rewind Overlay' once it reaches the start. 
         * If Rewind Mode is off, it acts like a Record button,
         * saving everything happening on screen so it can be reversed later.
         */
        if (rewindManager.isRewinding()) {
            //Rewind if rewinding: still going rewinds, and returns if rewining is still going
            boolean stillGoing = rewindManager.rewindStep(world, spawnManager);
            //Check if rewinding has finished 
            if (!stillGoing && rewindOverlay != null) {
                if (rewindOverlay.getWorld() != null) world.removeObject(rewindOverlay);
                rewindOverlay = null;//Clean up visuals
                AudioManager.setAllSoundsPaused(false);//Reusme normal game music, sound effect is a bit tricky, ill just let it be
            }
        } else {
            //Normal gameplay(recording mode): record the current object states to the history stack.
            rewindManager.record(world, spawnManager);
        }
        
        //Update spawning manager and score. 
        spawnManager.update(world);
        scoreDisplay.setText("SCORE: " + ScoreManager.getScore());
    }
    
    public void exit(MyWorld world) {
        //Clean up as we end playing state. 
        //Note: normally we don't "end" the playing state, we simply put a new state on top of it
        //So exit is only called when the entire playing state is over (aka game is lost)
        AudioManager.stop("dio_bgm");
        world.removeObjects(world.getObjects(null));
    }
    
    //Getter methods 
    public SpawnManager getSpawnManager() { return spawnManager; }
    public boolean isRewinding() { return rewindManager != null && rewindManager.isRewinding(); }
}