import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class State_Menu implements GameState {
    private List<Actor> uiElements = new ArrayList<>();

    public void enter(MyWorld world) {
        int middle = world.getWidth() / 2;
        
        // 1. Title - Using Large Font from Config
        addUI(world, new UIText("DIO-DGE IT", Config_Game.s(50), Color.BLACK), middle, Config_Game.s(100));

        // 2. The Guide (Instruction block)
        addUI(world, new UIText("--- HOW TO PLAY ---", Config_Game.s(25), Color.LIGHT_GRAY), middle, Config_Game.s(220));
        addUI(world, new UIText("ARROWS: Move DIO", Config_Game.s(20), Color.RED), middle, Config_Game.s(260));
        addUI(world, new UIText("W: Pause", Config_Game.s(30), Color.RED), middle, Config_Game.s(310));
        
        // 3. The Objective
        addUI(world, new UIText("Dodge the Road Rollers to survive!", Config_Game.s(22), Color.ORANGE), middle, Config_Game.s(350));

        // 4. Start Prompt
        addUI(world, new UIText("Press ENTER to Begin", Config_Game.s(20), Color.CYAN), middle, Config_Game.s(380));
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