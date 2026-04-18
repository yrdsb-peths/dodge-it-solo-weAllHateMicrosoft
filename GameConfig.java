import greenfoot.*;

public class GameConfig {
    // 1. GLOBAL SCALING
    public static final float SCALE = 2.0f; 
    
    // 2. WORLD SETTINGS
    public static final int WORLD_WIDTH = s(600);
    public static final int WORLD_HEIGHT = s(400);
    public static final int LANE_HEIGHT = s(80); // Lanes are 80px apart
    
    // 3. PLAYER SETTINGS (DIO)
    public static final int DIO_MOVE_SPEED = s(5);
    public static final double DIO_BASE_SCALE = 0.8 * SCALE; //Dio should be 0.8 its image size

    // 4. ENEMY SETTINGS
    //a. Obstacle Size
    public static final int ROADROLLER_WIDTH = s(80);
    public static final int TRAIN_WIDTH = s(130);
    
    //b. Difficulty settings
    public static final int LEVEL_UP_TIME = 300;
    public static final int ROADROLLER_RATE = 60;// number of frames for a car, decreass with difficulty;
    public static final int ROADROLLER_MIN_RATE= 15;
    public static final int ROADROLLER_SPEED = s(6);
    
    public static final int TRAIN_RATE = 200;// number of frames for a car, decreass with difficulty;
    public static final int TRAIN_MIN_RATE = 50;
    public static final int TRAIN_SPEED = s(25);
    public static final int TRAIN_MAX_SPEED = s(60);
    public static final int SPAWN_INTERVAL_START = 60;
    
        
    // Road & Lanes
    public static final int ROAD_SCROLL_SPEED = s(5);
    // We calculate lanes based on the scaled world height
    public static final int[] LANES = { s(40), s(120), s(200), s(280), s(360) };
    
        
    // UI (Word Size, these are just placeholder values as for now)
    public static final int FONT_SIZE_LARGE = s(80);
    public static final int FONT_SIZE_SMALL = s(30);
    
    // THE HELPER: Scales any pixel value based on the global SCALE
    public static int s(int pixels) {
        return Math.round(pixels * SCALE);
    }
}