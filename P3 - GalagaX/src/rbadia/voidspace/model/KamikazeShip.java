package rbadia.voidspace.model;

import java.awt.Rectangle;
import java.util.Random;

import rbadia.voidspace.main.GameScreen;

/**
 * Represents the kamikaze enemy-ship which attacks the player without being destroyed. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class KamikazeShip extends Rectangle {
	
	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 3;
	public static final long DEFAULT_SWAMP_INTERVAL = 20000;
	
	/**
	 * Instance fields for kamikaze ship
	 */
	private int shipWidth = 25;
	private int shipHeight = 25;
	private long swampingInterval = DEFAULT_SWAMP_INTERVAL;
	private int speed = DEFAULT_SPEED;
	private boolean suicidePosition;

	Random rand = new Random();
	
	/**
	 * Constructor for the kamikaze ship.
	 * @param screen the game screen
	 */
	public KamikazeShip(GameScreen screen){
		this.setLocation(rand.nextInt(screen.getWidth() - shipWidth),0);
		this.setSize(shipWidth, shipHeight);
	}
	
	/**
	 * Getter for the kamikaze-ship width
	 * @return an int representing the width
	 */
	public int getShipWidth() {
		return shipWidth;
	}

	/**
	 * Getter for the kamikaze-ship height
	 * @return an in representing the height
	 */
	public int getShipHeight() {
		return shipHeight;
	}

	/**
	 * Getter for the kamikaze-ship speed
	 * @return an int representing the speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Getter for the kamikaze-ship swamping interval.
	 * @return a long representing a swamping interval
	 */
	public long getSwampingInterval() {
		return swampingInterval;
	}

	/**
	 * Setter for the kamikaze-ship swamping interval.
	 * @param swampingInterval a long representing a swamping interval
	 */
	public void setSwampingInterval(long swampingInterval) {
		this.swampingInterval = swampingInterval;
	}
	
	/**
	 * Indicates if there exists a suicide position for the kamikaze-ship.
	 * @return true if it is in a suicide position or false otherwise
	 */
	public boolean isSuicidePosition() {
		return suicidePosition;
	}

	/**
	 * Setter of the suicide position of the kamikaze-ship.
	 * @param suicidePosition a boolean indicating the suicide position
	 */
	public void setSuicidePosition(boolean suicidePosition) {
		this.suicidePosition = suicidePosition;
	}
}
