import greenfoot.*;
/**
 * Write a description of class SpawnManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnManager  
{
    private int timer = 0;
    private int spawnRate = 60; //Spanws every 60 frame
    private int mobCount;
    
    private boolean scorelessObstacle = false;
    
    public void update(MyWorld world){
        timer++; 
        //Notice: this timer is exclusive to spawn manager, and only increases
        //when it gets called (which usually means game is runnin)
        
        if(timer >= spawnRate){
            timer = 0;
            
            if(scorelessObstacle){
                spawnObstacle(world, 0);
            }
            else{
            spawnObstacle(world);
            }
           //Make the game harder by make things spawn more often, but not decreasing every frame of course
           //if(spawnRate > 20) spawnRate--;
        }
        
    }
    
    private void spawnObstacle(MyWorld world){
        
        int y = Greenfoot.getRandomNumber(world.getHeight());
        world.addObject(new Roadroller(),world.getWidth(),y);
    }
    
    //Spawning obstalce with custom score
    private void spawnObstacle(MyWorld world, int score){
        
        int y = Greenfoot.getRandomNumber(world.getHeight());
        world.addObject(new Roadroller(score),world.getWidth(),y);
    }
    
    //Spawn many road rollers for cool effect
    public void deathSpawnAnim(MyWorld world){
        spawnRate = 2;
        scorelessObstacle = true;
    }
}
