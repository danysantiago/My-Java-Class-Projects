package rbadia.voidspace.model;

import java.awt.Rectangle;
import java.util.Random;

import rbadia.voidspace.main.GameScreen;

/**
 * Represents an enemy-ship that attacks to the point where the ship is located in the instant it appears. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class EnemyShip extends Rectangle{
	
	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 5;
	public static final long DEFAULT_SWAMP_INTERVAL = 10000;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	/**
	 * Instance fields for the enemy ship 
	 */
	private int shipWidth = 25;
	private int shipHeight = 25;
	private int swampingLocation = 0;
	private long swampingInterval = DEFAULT_SWAMP_INTERVAL;
	private int speed = DEFAULT_SPEED;
	
	
	Random rand = new Random();
	
	/**
	 * Draws an enemy ship. 
	 * @param screen where it will be displayed
	 */
	public EnemyShip(GameScreen screen){
		this.setLocation(screen.getWidth(),screen.getHeight()/2 - rand.nextInt(screen.getHeight()/2));
		this.setSize(shipWidth, shipHeight);
		this.swampingLocation = rand.nextInt(2);
	}

	/**
	 * Getter for the width of the enemy ship
	 * @return an int representing the width
	 */
	public int getShipWidth() {
		return shipWidth;
	}

	/**
	 * Getter for the height of the enemy ship
	 * @return an int representing the height
	 */
	public int getShipHeight() {
		return shipHeight;
	}

	/**
	 * Getter for the speed of the enemy ship
	 * @return an int representing the speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Getter for the swamping location of the enemy ship
	 * @return an int representing the swamping location 
	 */
	public int getSwampingLocation(){
		return swampingLocation;
	}
	
	/**
	 * Setter for the swamping location. 
	 */
	public void setSwampingLocation(){
		this.swampingLocation = rand.nextInt(2);
	}
	
	/**
	 * Getter for the swamping location interval.
	 * @return a long representing the swamping location interval
	 */
	public long getSwampingInterval() {
		return swampingInterval;
	}

	/**
	 * Setter for the swamping location interval.
	 * @param swampingInterval a long representing a new swamping location interval 
	 */
	public void setSwampingInterval(long swampingInterval) {
		this.swampingInterval = swampingInterval;
	}
	
	/**
	 * Getter for default speed. 
	 * @return an int representing a default speed
	 */
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
}
