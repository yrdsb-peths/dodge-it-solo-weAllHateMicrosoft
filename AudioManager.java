import greenfoot.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/*
 * The voice manager can play sounds in two ways:
 * 1. Play a single sound.music
 * 2. Play randomly from a list of sounds
 * 
 * Sample: 
 * AudioManager.play("some_key_of_single_sound"); 
 * AudioManager.playPool"some_key_of_souns_pool); 
 * 
 * 
 * To add new voices:
 * 
 * 1. Add the mp3 file to the sound file, make sure to organise properly
 * 2.a)  loadSound(String key, String file name, int volume) for single sounds
 * 2.b)  loadVoicePool(String key, String array file names, int volume)
 */
public class AudioManager {
    // Stores single sounds (BGM, UI clicks)
    private static HashMap<String, GreenfootSound> sounds = new HashMap<>();
    
    // Stores groups of sounds (Voice lines for random selection)
    private static HashMap<String, List<GreenfootSound>> voicePools = new HashMap<>();

    // Setup method that gets called once the game starts
    //Loads the files from hard drive into RAM permanently
    public static void init() {
        // Pre-load background music
        loadSound("dio_bgm","eye_of_heaven_dio_bgm.mp3", 10);
        
        
        loadSound("lost_bgm","brawl_stars_lost_bgm.mp3", 15);
        
        
        // Example of a single sound 
        // loadSound("punch", "sounds/punch.mp3", 70);
        
        // Pre-load voice pools
        String[] loseFiles = {"dio_voiceline/dio_lost.mp3", "dio_voiceline/dio_lost2.mp3"};
        loadVoicePool("dioLostVoices", loseFiles, 15);
        
        String[] dioBattleCry = {"dio_voiceline/wry.mp3","dio_voiceline/high.mp3", "dio_voiceline/muda_muda.mp3","dio_voiceline/Voicy_Timestop DiegoBrando.mp3"};
        loadVoicePool("dioBattleCry", dioBattleCry,20);
    }

    //Helper method to load single sounds
    private static void loadSound(String key, String file, int volume) {
        GreenfootSound s = new GreenfootSound(file);
        s.setVolume(volume);
        sounds.put(key, s);
    }
    
    //Helper method to load multiple sounds
    private static void loadVoicePool(String key, String[] files, int volume) {
        List<GreenfootSound> pool = new ArrayList<>();
        for (String f : files) {
            GreenfootSound s = new GreenfootSound(f);
            s.setVolume(volume);
            pool.add(s);
        }
        voicePools.put(key, pool);
    }

    //Methods to play sounds
    public static void play(String key) {
        if (sounds.containsKey(key)) {
            GreenfootSound s = sounds.get(key);
            if (s.isPlaying()) s.stop(); // Restart if already playing
            s.play();
        }
    }

    public static void playPool(String poolKey) {
        if (voicePools.containsKey(poolKey)) {
            List<GreenfootSound> pool = voicePools.get(poolKey);
            int index = Greenfoot.getRandomNumber(pool.size());
            GreenfootSound s = pool.get(index);
            // This prevents the "Cut-off" because 's' is stored in our Map 
            // and won't be garbage collected!
            s.play(); 
        }
    }
    
    //Loop music (used for bgm)
    public static void playLoop(String key) {
        if (sounds.containsKey(key)) {
            GreenfootSound s = sounds.get(key);
            if (!s.isPlaying()) {
                s.playLoop();
            }
        }
    }
    
    //Stops music
    public static void stop(String key) {
        if (sounds.containsKey(key)) {
            sounds.get(key).stop();
        }
    }
}