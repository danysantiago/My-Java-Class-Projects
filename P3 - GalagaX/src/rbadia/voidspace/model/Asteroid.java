package rbadia.voidspace.model;

import java.awt.Rectangle;
import java.util.Random;

import rbadia.voidspace.main.GameScreen;

/**
 * Represents every asteroid that fall in the screen. 
 * Includes small, medium, and large size asteroids. 
 * Also, includes a indestructible metal that we call diamond. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class Asteroid extends Rectangle {
	
	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 4;
	
	public static final int VERTICAL_MOV = 0;
	public static final int DIAGONAL_L_MOV = 1;
	public static final int DIAGONAL_R_MOV = 2;
	
	public static final int SMALL_MAGNITUD = 5;
	public static final int MEDIUM_MAGNITUD = 6;
	public static final int BIG_MAGNITUD = 7;
	
	public static final int DIAMOND_MAGNITUD = 8;
	
	/**
	 * Instance fields for asteroids.
	 */
	private boolean asteroidDestroyed;

	private int asteroidWidth = 32;
	private int asteroidHeight = 32;
	private int speed = DEFAULT_SPEED;
	private int healthPoints;
	
	private long lastDamage = 0;
	
	private double lastX;
	private double lastY;
	
	private int asteroidMagnitud;
	
	private int movementType = 0 ;
	
	private int moveCounter = 0;
	
	private Rectangle explosionRect;

	private Random rand = new Random();
	
	/**
	 * Creates a new asteroid at a random x location at the top of the screen.
	 * @param screen the game screen
	 * @param asteroidWidth asteroid width
	 * @param asteroidHeight asteroid height
	 * @param speed asteroid speed
	 * @param healthPoints life of the asteroid
	 * @param magnitud the magnitud of the asteroid 
	 */
	public Asteroid(GameScreen screen, int asteroidWidth, int asteroidHeight, int speed, int healthPoints, int magnitud){
		this.asteroidDestroyed = false;
		this.setLocation(rand.nextInt(screen.getWidth() - asteroidWidth),0);
		this.setSize(asteroidWidth, asteroidHeight);
		this.speed = speed;
		this.asteroidMagnitud = magnitud;
		this.healthPoints = healthPoints;
		setMovementType();
	}
	
	/**
	 * Creates a new asteroid of medium size at a random x location at the top of the screen 
	 * @param screen the game screen
	 */
	public Asteroid(GameScreen screen){
		this.asteroidDestroyed = false;
		this.setLocation(rand.nextInt(screen.getWidth() - asteroidWidth),0);
		this.setSize(asteroidWidth, asteroidHeight);
		this.asteroidMagnitud = MEDIUM_MAGNITUD;
		if(rand.nextInt(10) > 6){
			this.healthPoints = 2;
		} else {
			this.healthPoints = 1;
		}
		setMovementType();
	}
	
	
	/**
	 * Draws a big asteroid.
	 * @param screen the game screen
	 * @return an asteroid which is of big magnitude
	 */
	public static Asteroid createBigAsteroid(GameScreen screen){
		return new Asteroid(screen, 64, 64, 2, 3, BIG_MAGNITUD);
	}
	
	/**
	 * Draws a small asteroid.
	 * @param screen the game screen
	 * @return an asteroid which is of small magnitude
	 */
	public static Asteroid createSmallAsteroid(GameScreen screen){
		return new Asteroid(screen, 16, 16, 5, 1, SMALL_MAGNITUD);
	}
	
	/**
	 * Draws an indestructible metal.
	 * @param screen the game screen
	 * @return an asteroid which is the diamond
	 */
	public static Asteroid createDiamondAsteroid(GameScreen screen){
		return new Asteroid(screen, 48, 48, 3, 100, DIAMOND_MAGNITUD);
	}
	
	/**
	 * Indicates if the asteroid is being destroyed.
	 * @return true if the asteroid is destroyed or false otherwise
	 */
	public boolean isAsteroidDestroyed() {
		return asteroidDestroyed;
	}
	
	/**
	 * Setter for the last location of the asteroid.
	 */
	public void setLastLocation(){
		lastX = this.x;
		lastY = this.y;
	}
	
	/**
	 * Getter for the last x coordinate position of the asteroid.
	 * @return a double indicating the last x coordinate
	 */
	public double getLastX() {
		return lastX;
	}

	/**
	 * Getter for the last y coordinate position of the asteroid. 
	 * @return a double indicating the last y coordinate
	 */
	public double getLastY() {
		return lastY;
	}

	/**
	 * Setter for the asteroid location.
	 * @param x an int for the x coordinate
	 * @param y an int for the y coordiante
	 */
	public void setAsteroidLocation(int x, int y){
		this.setLocation(x, y);
	}

	/**
	 * Setter for the destruction of the asteroid.
	 * @param asteroidDestroyed a boolean indicating true or false for if the asteroid is being destroyed or not
	 */
	public void setAsteroidDestroyed(boolean asteroidDestroyed) {
		this.asteroidDestroyed = asteroidDestroyed;
	}

	/**
	 * Getter for the asteroid width.
	 * @return an int indicating the asteroid width
	 */
	public int getAsteroidWidth() {
		return asteroidWidth;
	}
	/**
	 * Getter for the asteroid height
	 * @return an int indicating the asteroid height
	 */
	public int getAsteroidHeight() {
		return asteroidHeight;
	}
	
	/**
	 * Getter for the asteroid magnitude.
	 * @return an int indicatin the magnitude for the asteroid
	 */
	public int getAsteroidMagnitud(){
		return this.asteroidMagnitud;
	}

	/**
	 * Returns the current asteroid speed
	 * @return the current asteroid speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set the current asteroid speed
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Returns the default asteroid speed.
	 * @return the default asteroid speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}

	/**
	 * Translation method for the asteroid. 
	 */
	public void move() {
		int dx = 0;
		int dy = this.speed;
		
		if(this.movementType == DIAGONAL_L_MOV){
			if(moveCounter > rand.nextInt(4) + 1){
				dx = -rand.nextInt(8) + 1;
				moveCounter = 0;
			} else {
				moveCounter++;
			}
		} else if (this.movementType == DIAGONAL_R_MOV){
			if(moveCounter > rand.nextInt(4) + 1){
				dx = rand.nextInt(8) + 1;
				moveCounter = 0;
			} else {
				moveCounter ++;
			}
		}
		                                               
		this.translate(dx, dy);
	}
	
	/**
	 * Setter for the random move type of the asteroid. 
	 */
	public void setMovementType(){
		this.movementType = rand.nextInt(3);
	}

	/**
	 * Decreases the life of the asteroid once hit. 
	 */
	public void substractHealthPoints() {
		healthPoints -= 1;
		lastDamage = System.currentTimeMillis();
	}

	/**
	 * Getter for the score of the asteroid by life. 
	 * @return an int indicating the heald points
	 */
	public int getHealthPoints() {
		return healthPoints;
	}
	
	/**
	 * Getter for the last damage asteroid. 
	 * @return a long indicating te last damage asteroid.
	 */
	public long getLastDamage(){
		return lastDamage;
	}

	/**
	 * Translation method for the boost feature. 
	 * Works once shift-key is pressed. 
	 */
	public void moveBoosted() {
		int dx = 0;
		int dy = this.speed*2;
		
		if(this.movementType == DIAGONAL_L_MOV){
			if(moveCounter > rand.nextInt(4) + 1){
				dx = -rand.nextInt(8) + 1;
				moveCounter = 0;
			} else {
				moveCounter++;
			}
		} else if (this.movementType == DIAGONAL_R_MOV){
			if(moveCounter > rand.nextInt(4) + 1){
				dx = rand.nextInt(8) + 1;
				moveCounter = 0;
			} else {
				moveCounter ++;
			}
		}
		                                               
		this.translate(dx, dy);
	}

	/**
	 * Setter for the explosion of the asteroid. 
	 * @param explosionRect 
	 */
	public void setExplosionRect(Rectangle explosionRect) {
		this.explosionRect = explosionRect;
	}

	/**
	 * Getter for the explosion of the asteroid. 
	 * @return
	 */
	public Rectangle getExplosionRect() {
		return explosionRect;
	}
}
