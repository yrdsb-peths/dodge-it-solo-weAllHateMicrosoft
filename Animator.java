import java.util.ArrayList;
import greenfoot.*;
import java.io.File;
/**
 * Write a description of class Animator here.
 * 
 * Animator takes in the time and tracks the correct image from the animation
 * Call animator with
 * 
 */


/**
 * ANIMATION LIST FOR DIO
 * ---------------------
 * Dash         - Runs
 * High         - brain massage
 * Idle         - Standing still
 * Intro        - Cool intro
 * Lose         - Defeat state
 * Roll         - Dodge move
 * Sratch       - Brain massag + wry
 * Up           - Up slash
 * WalkLeft     - Moving left
 * WalkRight    - Moving right
 * Wry          - WRYYYYYYY
 * 
 * (special: to be implemented later)
 * Description: Dio teleports backwards as his Stand appears to punch.
 * Requires two animations play simultaneously, and locatio change.
 * WorldPunch   - Special attack
 * Teleport     - pairs with WorldPunch
 * 
 */
public class Animator  
{
    private GreenfootImage[] frames;
    private int currentFrame = 0;
    private int timer = 0;
    private int speed;//Higher = slower (frames between images)
    
    public Animator(String folderName,String prefix, int frameCount, int speed){
        this.speed = speed;
        frames = new GreenfootImage[frameCount]; 
        
        for (int i = 0 ; i < frameCount; i++){
            //%03d => 3 digits with leading zero
            String suffix = String.format("%03d",i);
            String fileName = "dio/" + folderName +"/" + prefix + "_" + suffix + ".png";
            frames[i] = new GreenfootImage(fileName);
        }
    }
    public Animator(String folderName){
        this(folderName,folderName,0,10);
        int frameCount = countFrames(folderName);
        this.speed = 10;
        this.frames = new GreenfootImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            String suffix = String.format("%03d",i);
            String fileName = "dio/" + folderName + "/" + folderName + "_" + suffix + ".png";
            frames[i] = new GreenfootImage(fileName);
        }
        
    }
    private int countFrames(String folderName) {
    // Greenfoot looks for images in the "images" folder of your project
    File dir = new File("images/dio/" + folderName);
    
    // Check if the directory exists
    if (dir.exists() && dir.isDirectory()) {
        // Filter to only count .png files
        String[] files = dir.list((d, name) -> name.toLowerCase().endsWith(".png"));
        return (files != null) ? files.length : 0;
    }
    return 0;
    }
    
    public GreenfootImage getCurrentFrame(){
        timer++;
        if(timer >= speed){
            timer = 0;
            currentFrame = (currentFrame +1)%frames.length; //loop back to start
        }
        return frames[currentFrame];
    }
    
    public void reset(){
        currentFrame = 0; 
        timer = 0;
    }
}
