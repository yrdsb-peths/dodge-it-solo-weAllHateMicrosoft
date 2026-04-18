import greenfoot.*;
/**
 * Write a description of class SpawnManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnManager  
{
  
    
    private int globalTimer = 0;
    
    //== Difficulty Settings ==
    private int difficultyLevel = 0;
    private int levelUpTime = GameConfig.LEVEL_UP_TIME;//Level up every 5 seconds
    
    //Control Roadroller rate
    private int roadrollerRate = GameConfig.ROADROLLER_RATE;// number of frames for a car
    private final int roadrollerMin = GameConfig.ROADROLLER_MIN_RATE;
    
    //Control train rate
    private int trainRate = GameConfig.TRAIN_RATE;// number of frames for a car, decreass with difficulty
    private final int trainMin = GameConfig.TRAIN_MIN_RATE;
    private int trainSpeed = 25;//Increases with difficulty
    private final int trainSpeedMax = GameConfig.TRAIN_MAX_SPEED;
    
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
        // The Train logic is kept in one place
        world.addObject(new Exclaimation(), world.getWidth() - 20, spawnY);
        world.addObject(new PathWarning(world.getWidth(), 40), world.getWidth() / 2, spawnY);
        world.addObject(new Train(speed), world.getWidth() + 50, spawnY);
    }

    private int getRandomLane() {
        return ScrollingRoad.LANES[Greenfoot.getRandomNumber(ScrollingRoad.LANES.length)];
    }

}
