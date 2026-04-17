import greenfoot.*;

public class ScrollingRoad extends Actor {
    private static final int SPEED = 5;
    
    // THE LANE MAP: Other classes (like SpawnManager) can use this!
    // For a 400px height with 5 lanes, centers are: 40, 120, 200, 280, 360
    public static final int[] LANES = {40, 120, 200, 280, 360};
    
    private int width;

    public ScrollingRoad() {
        this.width = 600;
        drawPlaceholderRoad();
    }

    private void drawPlaceholderRoad() {
        GreenfootImage img = new GreenfootImage(width, 400);
        
        // 1. Draw Asphalt
        img.setColor(new Color(50, 50, 50));
        img.fill();
        
        // 2. Draw Lane Lines
        img.setColor(Color.YELLOW);
        for (int i = 1; i < LANES.length; i++) {
            int lineY = (LANES[i] + LANES[i-1]) / 2;
            // Draw dashed lines
            for (int x = 0; x < width; x += 40) {
                img.fillRect(x, lineY - 2, 20, 4);
            }
        }
        setImage(img);
    }

    public void act() {
        // Self-management: Only move if the game is actually "playing"
        MyWorld world = (MyWorld) getWorld();
        if (world.getGSM().isState(PlayingState.class)) {
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