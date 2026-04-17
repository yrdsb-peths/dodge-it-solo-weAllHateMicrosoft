import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Roadroller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Roadroller extends Obstacles
{
    private int scoreToAdd = 1;
    /**
     * Act - do whatever the Roadroller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean faceLeft = false;
    private boolean resized = false;
    
    //Can customise roadroller so that it doesnt give any score
    public Roadroller(int scoreToAdd) {
        this(); // Calls the original Roadroller() constructor first
        this.scoreToAdd = scoreToAdd;
    }
    public Roadroller(){
        //Resizing and orienting the image
        getImage().mirrorHorizontally();
        getImage().scale(80,80);
        //Moves at 6 pixels per frame
        speed = 6;
    }
    
    public void movementLogic(){
        //Negative speed means moving from right to left
        move(-speed);
    }
    
    public void collisionLogic(){
        //I'm really afraid of that null pointer error so this is a safety check.
        if (getWorld() == null) return; 

        // Get the player directly (no need to use isTouching first, this is faster)
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null && !player.isDead()) {
            if (player.checkCustomHitbox(this, 0.6)) {
                player.die();
            }
        }
    }   
    
    public void checkRemove(){
        //Check remove is checked separately and lastly
        //because this avoids calling world after removing itself from world
        //which results in null pointer error (yikes!)
        if (getX() <= 0) {
            //When it reaches the end, AND if player is alive, add score. 
            java.util.List<Player> players = getWorld().getObjects(Player.class);
            if (!players.isEmpty()) {
                Player player = players.get(0);
                if (!player.isDead()) {
                    ScoreManager.addScore(scoreToAdd);
                }
            }
            
            getWorld().removeObject(this);
            
        }
        return;
    }
}
