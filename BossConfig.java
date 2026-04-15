import greenfoot.*;

/**
 * Write a description of class BossConfig here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum BossConfig  
{
   DIO(
        Color.BLACK, 
        new String[]{"wry.mp3", "muda_muda.mp3", "kono_dio_da.mp3"}, 
        new Banner.SpriteOverlay[] {
            // file name, width, heigh, x offset, y offset
            new Banner.SpriteOverlay("dio_full.png", 150, 150, -200, 0),
            new Banner.SpriteOverlay("dio_label.png", 300, 100, 0, 0)
        }
    );
    
    public final Color bgColor;
    public final String[] sounds;
    public final Banner.SpriteOverlay[] overlays;

    private BossConfig(Color color, String[] sounds, Banner.SpriteOverlay[] overlays) {
        this.bgColor = color;
        this.sounds = sounds;
        this.overlays = overlays;
    }
}
