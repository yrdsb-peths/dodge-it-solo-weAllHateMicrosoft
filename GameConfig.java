import greenfoot.*;

public class GameConfig {
    // 1. GLOBAL SCALING
    public static final float SCALE = 1.0f; 
    
    // 2. WORLD SETTINGS
    public static final int WORLD_WIDTH = 600;
    public static final int WORLD_HEIGHT = 400;
    public static final int LANE_HEIGHT = s(80); // Lanes are 100px apart
    
    // 3. PLAYER SETTINGS (DIO)
    public static final int DIO_MOVE_SPEED = s(5);
    
    // 4. ENEMY SETTINGS
    public static final int LEVEL_UP_TIME = 300;
    public static final int ROADROLLER_RATE = 60;// number of frames for a car, decreass with difficulty;
    public static final int ROADROLLER_MIN_RATE= 15;
    public static final int ROADROLLER_SPEED = s(6);
    
    public static final int TRAIN_RATE = 200;// number of frames for a car, decreass with difficulty;
    public static final int TRAIN_MIN_RATE = 50;
    public static final int TRAIN_MAX_SPEED = s(25);
    public static final int SPAWN_INTERVAL_START = 60;
    
    // 5. COLORS (Consistency!)
    public static final Color UI_RED = new Color(255, 0, 0);
    public static final Color JOJO_GOLD = new Color(255, 215, 0);

    // THE HELPER: Scales any pixel value based on the global SCALE
    public static int s(int pixels) {
        return Math.round(pixels * SCALE);
    }
}