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
    private int minRoadrollerSpawnRate = 15;//Spawning 4 road rollers per second is very manageable with time stop and a small hitbox.
    private int roadrollerSpawnRate = defaultSpawnRate; //Spanws every 60 frame
    
    private int mobCount;
    private int difficultyTimer = 0;
    private boolean scorelessObstacle = false;
    
    
    public void update(MyWorld world){
        spawnTimer++; 
        difficultyTimer++;
        //Notice: this timer is exclusive to spawn manager, and only increases
        //when it gets called (which usually means game is runnin)
        
        if(spawnTimer >= roadrollerSpawnRate){
            spawnTimer = 0;
            
            if(scorelessObstacle){
                spawnRoadroller(world, 0);
            }
            else{
            spawnRoadroller(world);
            //Justtesting
            spawnTrain(world);
            }
        }
        if(difficultyTimer > levelUpTime){
            if(roadrollerSpawnRate > minRoadrollerSpawnRate){
               roadrollerSpawnRate--;
            }
               difficultyTimer = 0;
        }
        
    }
    
    private void reset(MyWorld world){
        roadrollerSpawnRate = defaultSpawnRate;
        scorelessObstacle = false;
        difficultyTimer = 0;
    }
    
    private void spawnRoadroller(MyWorld world){
        int randomLane = Greenfoot.getRandomNumber(ScrollingRoad.LANES.length);
        int spawnY = ScrollingRoad.LANES[randomLane];
        world.addObject(new Roadroller(),world.getWidth(),spawnY);
    }
    
    //Spawning obstalce with custom score
    private void spawnRoadroller(MyWorld world, int score){
        int randomLane = Greenfoot.getRandomNumber(ScrollingRoad.LANES.length);
        int spawnY = ScrollingRoad.LANES[randomLane];
        world.addObject(new Roadroller(score),world.getWidth(),spawnY);
    }
    
    private void spawnTrain(MyWorld world){
        int randomLane = Greenfoot.getRandomNumber(ScrollingRoad.LANES.length);
        int spawnY = ScrollingRoad.LANES[randomLane];
        world.addObject(new Exclaimation(),world.getWidth()-20,spawnY );
        world.addObject(new PathWarning(world.getWidth(), 40), world.getWidth()/2, spawnY);
        world.addObject(new Train(), world.getWidth()+40, spawnY);
    }

}
