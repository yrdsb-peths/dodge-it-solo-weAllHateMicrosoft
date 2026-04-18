import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class State_GameOver implements GameState {
    private List<Actor> uiElements = new ArrayList<>();

    public void enter(MyWorld world) {
        Mgr_Audio.playLoop("lost_bgm"); 
        
        Mgr_Score.updateHighScore();
        int finalScore = Mgr_Score.getScore();
        int bestScore = Mgr_Score.getHighScore();
        
        // Create UI Elements with scaled font sizes
        UIText title = new UIText("RETIRED", Config_Game.s(80), Color.RED);
        UIText scoreTxt = new UIText("Final Score: " + finalScore, Config_Game.s(30), Color.RED);
        UIText bestTxt = new UIText("Best Survival: " + bestScore, Config_Game.s(30), Color.RED);
        UIText restartPrompt = new UIText("Press ENTER to Restart", Config_Game.s(25), Color.BLACK);

        // Position them using scaled Y-coordinates
        int midX = world.getWidth() / 2;
        addUI(world, title, midX, Config_Game.s(150));
        addUI(world, scoreTxt, midX, Config_Game.s(200));
        addUI(world, bestTxt, midX, Config_Game.s(250));
        addUI(world, restartPrompt, midX, Config_Game.s(300));
    }

    public void update(MyWorld world) {
        if ("enter".equals(Greenfoot.getKey())) {
            world.getGSM().changeState(new State_Playing());
        }
    }

    public void exit(MyWorld world) {
        Mgr_Audio.stop("lost_bgm"); 
        world.removeObjects(uiElements);
    }

    private void addUI(MyWorld world, Actor a, int x, int y) {
        world.addObject(a, x, y);
        uiElements.add(a);
    }
}