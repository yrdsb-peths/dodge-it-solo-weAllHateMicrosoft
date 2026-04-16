import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roadroller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roadroller extends Actor
{
    /**
     * Act - do whatever the Roadroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean faceLeft = false;
    private boolean resized = false;
    public Roadroller(){
        //Resizing and orienting the image
        if(!faceLeft){
            getImage().mirrorHorizontally();
            faceLeft = true;
        }
        getImage().scale(80,80);
    }
    public void act(){
        move(-6);
        if(getX()<=0){
            resetRoadroller();
        }
        
        if(isTouching(Dio.class)){
            Dio.setAnimation("Lose");
            SadFace sadFace = new SadFace();
            getWorld().addObject(sadFace,300,200);
            getWorld().removeObject(this);
        }
    }
    
    public void resetRoadroller(){
        int num = Greenfoot.getRandomNumber(2);
        if(num == 1){
            setLocation(600,100);
        }
        else{
            setLocation(600,300);
        }
    }
}
