import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class GameOverState implements GameState {
    // We use a list to keep track of UI elements for easy cleanup
    private List<Actor> uiElements = new ArrayList<>();

    public void enter(MyWorld world) {
        
        AudioManager.playLoop("lost_bgm"); //Play bgm
        // 1. Process Scores immediately
        ScoreManager.updateHighScore();
        int finalScore = ScoreManager.getScore();
        int bestScore = ScoreManager.getHighScore();
        
        // 2. Create UI Elements
        // "RETIRED" is the classic JoJo defeat text
        UIText title = new UIText("RETIRED", 80, Color.RED);
        UIText scoreTxt = new UIText("Final Score: " + finalScore, 30, Color.RED);
        UIText bestTxt = new UIText("Best Survival: " + bestScore, 30, Color.RED);
        UIText restartPrompt = new UIText("Press ENTER to Restart", 25, Color.BLACK);

        // 3. Add to World and track them for cleanup
        addUI(world, title, world.getWidth()/2, 150);
        addUI(world, scoreTxt, world.getWidth()/2, 200);
        addUI(world, bestTxt, world.getWidth()/2, 250);
        addUI(world, restartPrompt, world.getWidth()/2, 300);
        
        // Professional Touch: Play the defeat sound via our Manager
        // AudioManager.playVoice("dio_lost");
    }

    public void update(MyWorld world) {
        // Check for restart
        if ("enter".equals(Greenfoot.getKey())) {
            // changeState deletes this state and starts a fresh PlayingState
            world.getGSM().changeState(new PlayingState());
        }
    }

    public void exit(MyWorld world) {
        // Remove all the UIs
        //Potentially clean up anythign if needed
        AudioManager.stop("lost_bgm"); //Play bgm
        world.removeObjects(uiElements);
    }

    /**
     * Helper to add UI and keep track of it automatically
     */
    private void addUI(MyWorld world, Actor a, int x, int y) {
        world.addObject(a, x, y);
        uiElements.add(a);
    }
}