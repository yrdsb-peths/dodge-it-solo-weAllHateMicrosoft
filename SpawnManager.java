import greenfoot.*;
/**
 * Write a description of class SpawnManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnManager  
{
    private int spawnTimer = 0;
    private final int defaultSpawnRate = 60;
    private int minSpawnRate = 6;//Spawning 10 road rollers is very manageable with time stop and a small hitbox.
    private int spawnRate = defaultSpawnRate; //Spanws every 60 frame
    
    private int levelUpTime = 40;//Decrease spawn rate by 1 every 40 frames/ 0.6 second
    private int mobCount;
    private int difficultyTimer = 0;
    private boolean scorelessObstacle = false;
    
    
    public void update(MyWorld world){
        spawnTimer++; 
        difficultyTimer++;
        //Notice: this timer is exclusive to spawn manager, and only increases
        //when it gets called (which usually means game is runnin)
        
        if(spawnTimer >= spawnRate){
            spawnTimer = 0;
            
            if(scorelessObstacle){
                spawnObstacle(world, 0);
            }
            else{
            spawnObstacle(world);
            }
        }
        if(difficultyTimer > levelUpTime){
            if(spawnRate > minSpawnRate){
               spawnRate--;
            }
               difficultyTimer = 0;
        }
        
    }
    
    private void reset(MyWorld world){
        spawnRate = defaultSpawnRate;
        scorelessObstacle = false;
        difficultyTimer = 0;
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
