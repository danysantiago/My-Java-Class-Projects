package rbadia.voidspace.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

import rbadia.voidspace.main.GameScreen;

/**
 * Manages and plays the game's sounds.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class SoundManager {
	/**
	 * Constants
	 */
	private static final boolean SOUND_ON = true;
	private static final boolean SOUND_OFF = false;
	
	/**
	 * Instance fields for music
	 */
	private boolean musicStatus;
	private boolean soundStatus;
	
	/**
	 * Instance fields for audio clips
	 */
    private AudioClip shipExplosionSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/shipExplosion.wav"));
    private AudioClip asteroidExplosion = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/asteroidExplosion.wav"));
    private AudioClip bigAsteroidExplosion = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/bigAsteroidExplosion.wav"));
    private AudioClip enemyShipExplosion = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/enemyShipExplosion.wav"));
    private AudioClip shield = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/shield.wav"));
    private AudioClip bulletSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/bullet.wav"));
    private AudioClip laserSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/laser.wav"));
    private AudioClip diamondSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/diamondHit.wav"));
    private AudioClip lifeSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/life.wav"));
    private AudioClip bombSound = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/bomb.wav"));
    private AudioClip gameSong = Applet.newAudioClip(GameScreen.class.getResource(
    "/rbadia/voidspace/sounds/GameSong.mid"));
    
    /**
     * Turns on the sounds. 
     */
    public SoundManager() {
    	musicStatus = SOUND_ON;
    	soundStatus = SOUND_ON;
    }
    
    /**
     * Play game's music.
     */
    public void playGameMusic(){
		// play sound for asteroid explosions
    	if(musicStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				gameSong.loop();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for bullets fired by the ship.
     */
    public void playBulletSound(){
    	if(soundStatus){ //If sound enabled play the file
    		new Thread(new Runnable(){ //Sounds are played in a different thread
    			public void run() {
    				bulletSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for laser gun fired by the ship.
     */
    public void playLaserSound(){
    	if(soundStatus){ //If sound enabled play the file
    		new Thread(new Runnable(){ //Sounds are played in a different thread
    			public void run() {
    				laserSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sound for when the metal is hit.
     */
    public void playDiamondHitSound(){
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				diamondSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for ship explosions.
     */
    public void playShipExplosionSound(){
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				shipExplosionSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Plays sound for asteroid explosions.
     */
    public void playAsteroidExplosionSound(){
		// play sound for asteroid explosions
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				asteroidExplosion.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for the big asteroid explosion. 
     */
    public void playBigAsteroidExplosionSound(){
		// play sound for asteroid explosions
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				bigAsteroidExplosion.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for the enemy ship explosion. 
     */
    public void playEnemyShipExplosionSound(){
		// play sound for asteroid explosions
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				enemyShipExplosion.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for when the kamikaze ship covers when shot. 
     */
    public void playShield(){
		// play sound for asteroid explosions
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				shield.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for when the ship gain extra life. 
     */
    public void playLife(){
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				lifeSound.play();
    			}
    		}).start();
    	}
    }
    
    /**
     * Play sounds for when the bomb clears the screen. 
     */
    public void playBomb(){
    	if(soundStatus){
    		new Thread(new Runnable(){
    			public void run() {
    				bombSound.play();
    			}
    		}).start();
    	}
    }
}
