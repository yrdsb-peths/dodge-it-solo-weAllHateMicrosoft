import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//I imported arraylist for the list of overlay items
import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class BanBanner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banner extends Actor{

    /**
     * Act - do whatever the BanBanner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // 0: entering 1: in the middle 2: exiting 
    private int state = 0;
    //How long the banner has been displayed
    private int timer = 0;
    //Storing the images
    private GreenfootImage baseImage;
    //Control the speed
    private double speed = -12.0;
    //Control play sound so that only once is played
    private boolean playedSound = false;
    //Boss configurations
    private BossConfig config;
    //a private class to store overlay items on the banner (like figure or text)
    
    public Banner(BossConfig config){
        this.config = config;
        
        baseImage = new GreenfootImage(810,150);
        baseImage.setColor(config.bgColor);
        baseImage.fill();
        
        for (SpriteOverlay s: config.overlays){
            sprites.add(s);
        }
        
        render(150);
    }
    
    public static class SpriteOverlay{
        GreenfootImage image;
        int offsetX;
        int offsetY;
        
        public SpriteOverlay(String fileName, int w, int h,int x,int y){
            this.image = new GreenfootImage(fileName);
            this.image.scale(w,h);
            this.offsetX = x;
            this.offsetY = y;
        }
    }
    //Make an arraylist of overlay items as they may be mutilples of them
    private List<SpriteOverlay> sprites = new ArrayList<>();
    
    private void render (int currentHeight){
        render(currentHeight,255);
    }
    private void render (int currentHeight,int alpha){
        //Set base image height
        GreenfootImage canvas = new GreenfootImage(1000,400);
        //trying transparency
        canvas.setColor(new Color(0,0,0,0));
        canvas.fill();
        
        //Set background
        Color c = config.bgColor;
        int bgH = Math.max(1, currentHeight);
        int bgW = 900;
        int bgX = (canvas.getWidth() - bgW) / 2;
        int bgY = (canvas.getHeight() - bgH) / 2;
        canvas.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        canvas.fillRect(bgX, bgY, bgW, bgH);
        // Set sprite
        for (SpriteOverlay s : sprites) {
            int drawX = canvas.getWidth()/2 - s.image.getWidth()/2 + s.offsetX;
            int drawY = canvas.getHeight()/2 - s.image.getHeight()/2 + s.offsetY;
            canvas.drawImage(s.image, drawX, drawY);
        }

        setImage(canvas);
    }
    public void act(){

        if(state ==0){
            slideIn();
        }
        else if(state == 1){
            hold();
        }
        else if(state == 2){
            slideOut();
        }
    }
    private void slideIn(){
        if (!playedSound){
            playRandomSound();
            playedSound = true;
        }
        int dist = 400-getX();
        if(Math.abs(dist)<=4){
            setLocation(400,getY());
            state = 1;
        }
        else{
            int step = (int)(dist*0.12);
            if(step == 0){
                if(dist>0){
                    step = 1;
                }
                else{
                    step = -1;
                }
            }
            setLocation(getX()+step,getY());
        }
    }
    
    private void hold(){
        //Slowing down in the negative direction
        speed += 0.4;
        setLocation(getX()+((int)speed),getY());
        //Resize the image
        int d = getX()-200;
        double ratio = d/200.0;
        int newHeight = 30 + (int)(120*ratio);
        render(newHeight);
        if(speed >= 0){
            state = 2;
            speed = 0;
        }
        
    }
    private void slideOut(){
        //Increasing speed in the positive direction
        speed += 1;
        setLocation(getX()+(int)speed,getY());
        //Resize the image
        int d = getX() -200;
        double ratio = d/200.0;
        int newHeight = 30 + (int)(120*ratio);
        int alpha = (int)(255 * (1.0 - ratio/4));
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;
        render(newHeight,alpha);
    }
    public void playRandomSound() {
    String[] sounds = config.sounds;
    if(sounds != null && sounds.length > 0){
        int index = Greenfoot.getRandomNumber(sounds.length);
 
        Greenfoot.playSound(sounds[index]); 
    }
    }
}
