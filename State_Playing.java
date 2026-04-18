import greenfoot.*;

public class State_Playing implements GameState
{
    private Mgr_Spawn spawnManager;
    private UIText scoreDisplay;

    public void enter(MyWorld world){
        Mgr_Score.reset(); 
        Mgr_Audio.playLoop("dio_bgm"); 
        
        // Road placement - world.getWidth() is already scaled, so this math stays clean!
        world.addObject(new ScrollingRoad(), world.getWidth() / 2, world.getHeight() / 2);
        world.addObject(new ScrollingRoad(), world.getWidth() + (world.getWidth() / 2), world.getHeight() / 2);

        // DIO starting position must be scaled
        Dio dio = new Dio();
        world.addObject(dio, Config_Game.s(80), Config_Game.s(80));
        
        spawnManager = new Mgr_Spawn();

        // Score UI size and position must be scaled
        scoreDisplay = new UIText("SCORE: 0", Config_Game.s(30), Color.BLACK);
        world.addObject(scoreDisplay, Config_Game.s(100), Config_Game.s(30));
    }
    
    public void update(MyWorld world){
        if("w".equals(Greenfoot.getKey())){
            world.getGSM().pushState(new State_Paused());
        }
        spawnManager.update(world);
        scoreDisplay.setText("SCORE: " + Mgr_Score.getScore());
    }
    
    public void exit(MyWorld world){
        Mgr_Audio.stop("dio_bgm");
        world.removeObjects(world.getObjects(null));
    }
    
    public Mgr_Spawn getSpawnManager() {
        return spawnManager;
    }
}