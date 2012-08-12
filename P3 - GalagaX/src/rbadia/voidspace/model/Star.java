package rbadia.voidspace.model;

import java.awt.Point;
import java.util.Random;

import rbadia.voidspace.main.GameScreen;


/**
 * Star Class used to represent a star
 * Star are actually drawn like a like with length 1 but this object
 * stores their speed established randomly on construction
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class Star extends Point{
	
	/**
	 * Constant
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instance field for speed
	 */
	private int speed;
	
	private Random rand = new Random();
	
	/**
	 * Crates a new Star at a given location
	 * @param screen the game screen
	 */
	public Star(GameScreen screen, int x, int y){
		this.setLocation(x,y);
		speed = rand.nextInt(4) + 1;
	}
	
	/**
	 * Getter for the speed.
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Setter for the speed.
	 * @param speed an int representing a speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
