import greenfoot.*;

public class Config_Game {
    // 1. GLOBAL SCALING: This scales every image. By default, it is 1. To make things larger and occupy more screen, it is currently 1.5 
    public static final float SCALE = 1.5f; 
    
    // 2. WORLD SETTINGS
    public static final int WORLD_WIDTH = s(600);//World widht is 600 pixels
    public static final int WORLD_HEIGHT = s(400);// World height is 400 pixels
    public static final int LANE_HEIGHT = s(80); // Lanes are 80px apart
    
    // 3. PLAYER SETTINGS (DIO)
    public static final int DIO_MOVE_SPEED = s(5);//Dio moves 5 pixels per frame
    public static final double DIO_BASE_SCALE = 0.8 * SCALE; //Dio should be 0.8 its image size

    // 4. ENEMY SETTINGS
    //a. Obstacle Size
    public static final int ROADROLLER_WIDTH = s(80);//Roadroller is 80 pixels wide
    
    public static final int TRAIN_WIDTH = s(130);
        // Train is 130 pixels wide. (Its an Ambulance, but its called a train because it works likes a train and looks like an ambulance).
        //If you dont know why an ambulance is this powerful, you probably have to ask Kira Yoshkage, i think he knows the answer.
    
    //b. Difficulty settings
    public static final int LEVEL_UP_TIME = 300;//Game gets more difficulty per 300 frame
    public static final int ROADROLLER_RATE = 60;// number of frames for a car, decreass with difficulty;
    public static final int ROADROLLER_MIN_RATE= 15;
    public static final int ROADROLLER_SPEED = s(6);//Roadroller moves 6 frames per second
    
    public static final int TRAIN_RATE = 200;// number of frames for a car, decreass with difficulty;
    public static final int TRAIN_MIN_RATE = 50;
    public static final int TRAIN_SPEED = s(25);//Train moves at 25 pixesl per frame by default
    public static final int TRAIN_MAX_SPEED = s(60);//Train can move at 60 pixels per frame at its peak
      
    // Road & Lanes
    public static final int ROAD_SCROLL_SPEED = s(5);//The background "roads" moves at 5 pixels per second
    // We calculate lanes based on the scaled world height
    public static final int[] LANES = { s(40), s(120), s(200), s(280), s(360) };//These are the locations of the "lanes"
    
        
    // UI (Font Size)
    public static final int FONT_SIZE_LARGE = s(80);
    public static final int FONT_SIZE_SMALL = s(30);
    
    // THE HELPER: Scales any pixel value based on the global SCALE
    public static int s(int pixels) {
        return Math.round(pixels * SCALE);
    }
}