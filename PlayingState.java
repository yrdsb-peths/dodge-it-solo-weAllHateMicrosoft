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
        
        ScoreManager.reset();
        AudioManager.playLoop("dio_bgm");
        
        rewindManager = new Time_RewindManager();
        
        world.addObject(new ScrollingRoad(), world.getWidth()/2, world.getHeight()/2);
        world.addObject(new ScrollingRoad(), world.getWidth() + world.getWidth()/2, world.getHeight()/2);
        
        world.addObject(new Dio(), GameConfig.s(80), GameConfig.s(80));
        spawnManager = new SpawnManager();
        
        scoreDisplay = new UIText("SCORE: 0", GameConfig.s(25), Color.WHITE);
        world.addObject(scoreDisplay, GameConfig.s(80), GameConfig.s(20));
        
        rewindBar = new UI_RewindBar(rewindManager);
        world.addObject(rewindBar, world.getWidth() - GameConfig.s(100), GameConfig.s(20));
    }
    
    public void update(MyWorld world) {
        String key = Greenfoot.getKey();

        // Pause
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
        
        // Each frame: either rewind or record
        if (rewindManager.isRewinding()) {
            boolean stillGoing = rewindManager.rewindStep(world, spawnManager);
            if (!stillGoing && rewindOverlay != null) {
                if (rewindOverlay.getWorld() != null) world.removeObject(rewindOverlay);
                rewindOverlay = null;
                AudioManager.setAllSoundsPaused(false);
            }
        } else {
            rewindManager.record(world, spawnManager);
        }
        
        spawnManager.update(world);
        scoreDisplay.setText("SCORE: " + ScoreManager.getScore());
    }
    
    public void exit(MyWorld world) {
        AudioManager.stop("dio_bgm");
        world.removeObjects(world.getObjects(null));
    }
    
    public SpawnManager getSpawnManager() { return spawnManager; }
    public boolean isRewinding() { return rewindManager != null && rewindManager.isRewinding(); }
}