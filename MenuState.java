import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class MenuState implements GameState {
    private List<Actor> uiElements = new ArrayList<>();
    

    public void enter(MyWorld world) {
        int middle = world.getWidth()/2;
        // 1. Title
        addUI(world, new UIText("DIO-DGE IT", 50, Color.BLACK),middle, 100);

        // 2. The Guide (Instruction block)
        addUI(world, new UIText("--- HOW TO PLAY ---", 25, Color.LIGHT_GRAY),middle, 220);
        addUI(world, new UIText("ARROWS: Move DIO", 20, Color.RED),middle, 260);
        addUI(world, new UIText("W: Pause", 30, Color.RED),middle, 310);
        
        // 3. The Objective
        addUI(world, new UIText("Dodge the Road Rollers to survive!", 22, Color.ORANGE), middle, 350);

        // 4. Start Prompt
        addUI(world, new UIText("Press ENTER to Begin", 20, Color.CYAN), middle, 380);
        
        // Optional: Play intro music
        // AudioManager.play("menu_theme");
    }

    public void update(MyWorld world) {
        // Transition to Playing State
        if ("enter".equals(Greenfoot.getKey())) {
            world.getGSM().changeState(world.playingState);
        }
    }

    public void exit(MyWorld world) {
        // Clean up all UI elements so the screen is empty for PlayingState
        world.removeObjects(uiElements);
        uiElements.clear();
    }

    private void addUI(MyWorld world, Actor a, int x, int y) {
        world.addObject(a, x, y);
        uiElements.add(a);
    }
}