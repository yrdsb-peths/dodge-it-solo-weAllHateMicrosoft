import greenfoot.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AudioManager {
    private static HashMap<String, GreenfootSound> sounds = new HashMap<>();
    private static HashMap<String, List<GreenfootSound>> voicePools = new HashMap<>();
    
    // New: Storage for "Original" volumes
    private static HashMap<String, Integer> baseVolumes = new HashMap<>();
    private static HashMap<String, Integer> basePoolVolumes = new HashMap<>();
    
    // This remembers which sounds were active before we hit pause
    private static List<GreenfootSound> activeBeforePause = new ArrayList<>();

    private static int masterVolume = 100; // 0 to 100

    public static void init() {
        // Pre-load background music (using 100 as base, we scale it later)
        loadSound("dio_bgm", "eye_of_heaven_dio_bgm.mp3", 60);
        loadSound("lost_bgm", "brawl_stars_lost_bgm.mp3", 50);
        loadSound("car_crash", "car_crash.mp3", 70);
        
        // Pre-load voice pools
        
        String[] rewind = {
            "rewind1.mp3"
        };
        loadVoicePool("rewind", rewind, 60);
        
        String[] loseFiles = {"dio_voiceline/dio_lost.mp3", "dio_voiceline/dio_lost2.mp3"};
        loadVoicePool("dioLostVoices", loseFiles, 60);
        
        String[] dioBattleCry = {
            "dio_voiceline/wry.mp3", "dio_voiceline/high.mp3", 
            "dio_voiceline/muda_muda.mp3", "dio_voiceline/Voicy_Timestop DiegoBrando.mp3"
        };
        loadVoicePool("dioBattleCry", dioBattleCry, 100);
        
        updateAllVolumes(); // Set initial volumes based on master
    }

    private static void loadSound(String key, String file, int volume) {
        GreenfootSound s = new GreenfootSound(file);
        sounds.put(key, s);
        baseVolumes.put(key, volume);
    }
    
    private static void loadVoicePool(String key, String[] files, int volume) {
        List<GreenfootSound> pool = new ArrayList<>();
        for (String f : files) {
            GreenfootSound s = new GreenfootSound(f);
            pool.add(s);
        }
        voicePools.put(key, pool);
        basePoolVolumes.put(key, volume);
    }

    // --- Master Volume Logic ---
    
    public static void setMasterVolume(int level) {
        masterVolume = level;
        if (masterVolume < 0) masterVolume = 0;
        if (masterVolume > 100) masterVolume = 100;
        updateAllVolumes();
    }

    private static void updateAllVolumes() {
        // Update single sounds
        for (String key : sounds.keySet()) {
            int base = baseVolumes.get(key);
            sounds.get(key).setVolume((base * masterVolume) / 100);
        }
        // Update pools
        for (String key : voicePools.keySet()) {
            int base = basePoolVolumes.get(key);
            for (GreenfootSound s : voicePools.get(key)) {
                s.setVolume((base * masterVolume) / 100);
            }
        }
    }

    // --- Special Control Logic ---

    /**
     * Stops everything currently playing.
     */
    public static void stopAll() {
        for (GreenfootSound s : sounds.values()) s.stop();
        for (List<GreenfootSound> pool : voicePools.values()) {
            for (GreenfootSound s : pool) s.stop();
        }
    }

    // --- Standard Methods ---

    public static void play(String key) {
        if (sounds.containsKey(key)) {
            GreenfootSound s = sounds.get(key);
            if (s.isPlaying()) s.stop();
            s.play();
        }
    }

    public static void playPool(String poolKey) {
        if (voicePools.containsKey(poolKey)) {
            List<GreenfootSound> pool = voicePools.get(poolKey);
            int index = Greenfoot.getRandomNumber(pool.size());
            pool.get(index).play(); 
        }
    }
    
    public static void playLoop(String key) {
        if (sounds.containsKey(key)) {
            GreenfootSound s = sounds.get(key);
            if (!s.isPlaying()) s.playLoop();
        }
    }
    
    public static void stop(String key) {
        if (sounds.containsKey(key)) sounds.get(key).stop();
    }
    
     /**
     * Pauses a specific looping sound.
     */
    public static void pause(String key) {
        if (sounds.containsKey(key)) {
            sounds.get(key).pause();
        }
    }

    /**
     * Resumes or Starts a looping sound.
     */
    public static void resume(String key) {
        if (sounds.containsKey(key)) {
            sounds.get(key).playLoop();
        }
    }
    
    /**
     * UNIVERSAL SOUND CONTROL (With Memory)
     * @param pause true to pause active sounds, false to resume ONLY those sounds.
     */
    public static void setAllSoundsPaused(boolean pause) {
        if (pause) {
            // 1. CLEAR memory first (just in case)
            activeBeforePause.clear();

            // 2. Scan all single sounds (BGM, etc.)
            for (GreenfootSound s : sounds.values()) {
                if (s.isPlaying()) {
                    activeBeforePause.add(s);
                    s.pause();
                }
            }

            // 3. Scan all pools
            for (List<GreenfootSound> pool : voicePools.values()) {
                for (GreenfootSound s : pool) {
                    if (s.isPlaying()) {
                        activeBeforePause.add(s);
                        s.pause();
                    }
                }
            }
        } 
        else {
            // 4. RESUME: Only play the ones we actually paused
            for (GreenfootSound s : activeBeforePause) {
                s.play(); 
            }
            // 5. Clear the memory so we don't accidentally resume them again later
            activeBeforePause.clear();
        }
    }
}