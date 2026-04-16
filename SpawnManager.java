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
    private int spawnRate = 60; //Spanws every 60 seconds
    private int mobCount;
    
    public void update(MyWorld world){
        timer++; 
        //Notice: this timer is exclusive to spawn manager, and only increases
        //when it gets called (which usually means game is runnin)
        
        if(timer >= spawnRate){
            timer = 0;
            spawnObstacle(world);
            
           //Make the game harder by make things spawn more often, but not decreasing every frame of course
           //if(spawnRate > 20) spawnRate--;
        }
        
    }
}
