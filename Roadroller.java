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
        
        speed = 6;
    }
    
    public void movementLogic(){
        //Negative speed means moving from right to left
        move(-speed);
    }
    
    public void collisionLogic(){
        if(isTouching(Dio.class)){
            Dio player = (Dio) getOneIntersectingObject(Dio.class);
            if (player != null && !player.isDead()) {
            player.die();
            
            SadFace sadFace = new SadFace();
            getWorld().addObject(sadFace,300,200);
            
            MyWorld myWorld = (MyWorld) getWorld(); 
            myWorld.getGSM().changeState(new GameOverState());
            }       
        }
        return;
    
    }
    
    public void checkRemove(){
        if (getX() <= 0) {
            getWorld().removeObject(this);
        }
        return;
    }
}
