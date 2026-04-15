import java.util.ArrayList;
import greenfoot.*;
/**
 * Write a description of class Animator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            //%02d => 2 digits with leading zero
            String suffix = String.format("%03d",i);
            String fileName = "dio/" + folderName +"/" + prefix + "_" + suffix + "png";
            frames[i] = new GreenfootImage(fileName);
        }
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
