import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roadroller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roadroller extends Obstacles
{
    /**
     * Act - do whatever the Roadroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean faceLeft = false;
    private boolean resized = false;
    private MyWorld world;
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
        if(isTouching(Dio.class)){
            Dio.setAnimation("Lose");
            SadFace sadFace = new SadFace();
            getWorld().addObject(sadFace,300,200);
            MyWorld myWorld = (MyWorld) getWorld(); 
            myWorld.getGSM().changeState(new GameOverState());
            return;
        }       
    }
    
}
