import greenfoot.*;

public class PlayingState implements GameState
{
    private SpawnManager spawnManager;
    private UIText scoreDisplay;

    public void enter(MyWorld world){
        ScoreManager.reset(); 
        AudioManager.playLoop("dio_bgm"); 
        
        // Road placement - world.getWidth() is already scaled, so this math stays clean!
        world.addObject(new ScrollingRoad(), world.getWidth() / 2, world.getHeight() / 2);
        world.addObject(new ScrollingRoad(), world.getWidth() + (world.getWidth() / 2), world.getHeight() / 2);

        // DIO starting position must be scaled
        Dio dio = new Dio();
        world.addObject(dio, GameConfig.s(80), GameConfig.s(80));
        
        spawnManager = new SpawnManager();

        // Score UI size and position must be scaled
        scoreDisplay = new UIText("SCORE: 0", GameConfig.s(30), Color.BLACK);
        world.addObject(scoreDisplay, GameConfig.s(100), GameConfig.s(30));
    }
    
    public void update(MyWorld world){
        if("w".equals(Greenfoot.getKey())){
            world.getGSM().pushState(new PausedState());
        }
        spawnManager.update(world);
        scoreDisplay.setText("SCORE: " + ScoreManager.getScore());
    }
    
    public void exit(MyWorld world){
        AudioManager.stop("dio_bgm");
        world.removeObjects(world.getObjects(null));
    }
    
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
}