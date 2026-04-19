import greenfoot.*;

public class FX_RewindOverlay extends Actor {
    private int frame = 0;
    private GreenfootImage screen1;
    private GreenfootImage screen2;

    public FX_RewindOverlay() {
        // Create the two animation frames ONCE when the effect starts
        screen1 = createOverlay(false);
        screen2 = createOverlay(true);
        setImage(screen1);
    }

    public void act() {
        frame++;
        // Just swap the image, don't DRAW anything new
        if (frame % 4 < 2) {
            setImage(screen1);
        } else {
            setImage(screen2);
        }
    }

    private GreenfootImage createOverlay(boolean scanlineOffset) {
        int w = GameConfig.WORLD_WIDTH;
        int h = GameConfig.WORLD_HEIGHT;
        GreenfootImage img = new GreenfootImage(w, h);
        
        // Blue tint
        img.setColor(new Color(60, 100, 200, 60));
        img.fill();
        
        // Scanlines
        img.setColor(new Color(0, 0, 0, 50));
        int startY = scanlineOffset ? GameConfig.s(2) : 0;
        for (int y = startY; y < h; y += GameConfig.s(4)) {
            img.fillRect(0, y, w, GameConfig.s(2));
        }
        return img;
    }
}