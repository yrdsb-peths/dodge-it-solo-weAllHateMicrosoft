import greenfoot.*;

public class Ability_MadeInHeaven {
    private GameTimer durationTimer = new GameTimer(6.5, false); // Lasts 6.5 seconds to match the sound effect
    private int afterimageCounter = 0;
    private final double speedMultiplier = 5; // How much faster?

    public void activate() {
        if (durationTimer.isExpired() || !durationTimer.isActive()) {
            
            durationTimer.reset();
            durationTimer.start();
            AudioManager.play("speed_up_time");
        }
    }

    public void update(Player p, MyWorld world) {
        if (!durationTimer.isActive()) return;
        
        durationTimer.update(world);

        // Spawn afterimage every frame
        afterimageCounter++;
        if (afterimageCounter % 1 == 0) {
            world.addObject(new FX_Afterimage(p.getImage()), p.getX(), p.getY());
        }

        if (durationTimer.isExpired()) {
            durationTimer.stop();
        }
    }

    public double getSpeedMultiplier() {
        return durationTimer.isActive() && !durationTimer.isExpired() ? speedMultiplier : 1.0;
    }
    
    // For the Time Machine snapshots
    // These allow the Time Machine to save and load the ability's progress
    public int getRemainingFrames() { return durationTimer.getRemainingFrames(); }
    public void setRemainingFrames(int f) { durationTimer.setRemainingFrames(f); }
    
    public boolean isActive() { return durationTimer.isActive(); }
    public void startTimer() { durationTimer.start(); }
    public void stopTimer() { durationTimer.stop(); }
}