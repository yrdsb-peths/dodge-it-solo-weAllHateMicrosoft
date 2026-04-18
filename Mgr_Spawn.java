import greenfoot.*;
/**
 * Write a description of class SpawnManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mgr_Spawn  
{
  
    
    private int globalTimer = 0;
    
    //== Difficulty Settings ==
    private int difficultyLevel = 0;
    private int levelUpTime = Config_Game.LEVEL_UP_TIME;//Level up every 5 seconds
    
    //Control Roadroller rate
    private int roadrollerRate = Config_Game.ROADROLLER_RATE;// number of frames for a car
    private final int roadrollerMin = Config_Game.ROADROLLER_MIN_RATE;
    
    //Control train rate
    private int trainRate = Config_Game.TRAIN_RATE;// number of frames for a car, decreass with difficulty
    private final int trainMin = Config_Game.TRAIN_MIN_RATE;
    private int trainSpeed = Config_Game.TRAIN_MAX_SPEED;//Increases with difficulty
    private final int trainSpeedMax = Config_Game.TRAIN_MAX_SPEED;
    
    //Unnecessary boolean 
    private boolean scorelessObstacle = false;
    
    public void update(MyWorld world){
        globalTimer++; 
        //Notice: this timer is exclusive to spawn manager, and only increases
        //when it gets called (which usually means game is runnin)
        
        if(globalTimer % levelUpTime == 0){
            increaseDifficulty();
        }
        
        if(globalTimer % roadrollerRate == 0){
            spawnRoadroller(world);
        }
        
        if(globalTimer % trainRate == 0){
            spawnTrain(world, trainSpeed);
        }
        
    }
    
    private void increaseDifficulty(){
        if(roadrollerRate > roadrollerMin) roadrollerRate -= 5;
        
        if(trainRate > trainMin) trainRate -= 5;
        
        if(trainSpeed > trainSpeedMax) trainSpeed -= 5;
    }
    
    private void spawnRoadroller(MyWorld world) {
        int spawnY = getRandomLane();
        world.addObject(new Roadroller(), world.getWidth(), spawnY);
    }

    private void spawnTrain(MyWorld world, int speed) {
        int spawnY = getRandomLane();
        
        int pathHeight = Config_Game.LANE_HEIGHT; 
        int exclaimXOffset = Config_Game.s(20);
        int trainXOffset = Config_Game.s(50);
        // The Train spawning logic is kept in one place:
        //The !, the path warning, and the actual train 
        world.addObject(new Exclaimation(), world.getWidth() - exclaimXOffset, spawnY);
        world.addObject(new PathWarning(world.getWidth(), pathHeight), world.getWidth() / 2, spawnY);
        world.addObject(new Train(speed), world.getWidth() + trainXOffset, spawnY);
    }

    private int getRandomLane() {
        return Config_Game.LANES[Greenfoot.getRandomNumber(Config_Game.LANES.length)];
    }

}
