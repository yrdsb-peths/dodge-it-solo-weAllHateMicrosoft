import greenfoot.*;

public class ScrollingRoad extends Actor {
    private static final int SPEED = Config_Game.ROAD_SCROLL_SPEED;//Moves 5 pixels per frame
    private int width = Config_Game.WORLD_WIDTH;
    // THE LANE MAP: Other classes (like SpawnManager) can use this!
    // For a 400px height with 5 lanes, centers are: 40, 120, 200, 280, 360

    public ScrollingRoad() {
        drawPlaceholderRoad();
    }

    private void drawPlaceholderRoad() {
        GreenfootImage img = new GreenfootImage(Config_Game.WORLD_WIDTH, Config_Game.WORLD_HEIGHT);
        
        // 1. Draw Asphalt
        img.setColor(new Color(50, 50, 50));
        img.fill();
        
        // 2. Draw Lane Lines
        img.setColor(Color.YELLOW);
        for (int i = 1; i < Config_Game.LANES.length; i++) {
            //Lane locations are stored in game config
            int lineY = (Config_Game.LANES[i] + Config_Game.LANES[i-1]) / 2;
            // Draw dashed lines(they are scaled by game config too)
            for (int x = 0; x < width; x += Config_Game.s(40)) {
                img.fillRect(x, lineY - 2, Config_Game.s(20), Config_Game.s(4));
            }
        }
        setImage(img);
    }

    public void act() {
        // Self-management: Only move if the game is actually "playing"
        MyWorld world = (MyWorld) getWorld();
        if (world.getGSM().isState(State_Playing.class)) {
            scroll();
        }
    }

    private void scroll() {
        setLocation(getX() - SPEED, getY());
        
        // If we moved off screen, teleport to the back of the "twin"
        if (getX() <= -width / 2) {
            setLocation(getX() + width * 2, getY());
        }
    }
}