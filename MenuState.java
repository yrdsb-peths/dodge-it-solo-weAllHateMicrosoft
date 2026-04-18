import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class MenuState implements GameState {
    private List<Actor> uiElements = new ArrayList<>();

    public void enter(MyWorld world) {
        int middle = world.getWidth() / 2;
        
        // 1. Title - Using Large Font from Config
        addUI(world, new UIText("DIO-DGE IT", GameConfig.s(50), Color.BLACK), middle, GameConfig.s(100));

        // 2. The Guide (Instruction block)
        addUI(world, new UIText("--- HOW TO PLAY ---", GameConfig.s(25), Color.LIGHT_GRAY), middle, GameConfig.s(220));
        addUI(world, new UIText("ARROWS: Move DIO", GameConfig.s(20), Color.RED), middle, GameConfig.s(260));
        addUI(world, new UIText("W: Pause", GameConfig.s(30), Color.RED), middle, GameConfig.s(310));
        
        // 3. The Objective
        addUI(world, new UIText("Dodge the Road Rollers to survive!", GameConfig.s(22), Color.ORANGE), middle, GameConfig.s(350));

        // 4. Start Prompt
        addUI(world, new UIText("Press ENTER to Begin", GameConfig.s(20), Color.CYAN), middle, GameConfig.s(380));
    }

    public void update(MyWorld world) {
        if ("enter".equals(Greenfoot.getKey())) {
            world.getGSM().changeState(world.playingState);
        }
    }

    public void exit(MyWorld world) {
        world.removeObjects(uiElements);
        uiElements.clear();
    }

    private void addUI(MyWorld world, Actor a, int x, int y) {
        world.addObject(a, x, y);
        uiElements.add(a);
    }
}