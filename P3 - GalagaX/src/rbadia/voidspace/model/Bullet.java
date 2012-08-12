package rbadia.voidspace.model;
import java.awt.Rectangle;

/**
 * Represents a bullet fired by a ship.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class Bullet extends Rectangle {
	
	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_WEAPON = 0;
	public static final int LASER_WEAPON = 1;
	public static final int SHOTGUN_WEAPON = 2;
	
	/**
	 * Instance fields for bullets
	 */
	private int bulletWidth = 7;
	private int bulletHeight = 13;
	private int speed = 10;
	int dx;
	
	private int bulletType;

	/**
	 * Creates a new bullet above the ship, centered on it
	 * @param ship
	 */
	public Bullet(Ship ship, int bulletType) {
		if(bulletType == LASER_WEAPON){
			bulletWidth = 12;
			bulletHeight = 25;
			this.setSize(bulletWidth, bulletHeight);
		} else {
			this.setSize(bulletWidth, bulletHeight);
		}
		this.setLocation(ship.x + ship.width/2 - bulletWidth/2,ship.y - bulletHeight);
		this.bulletType = bulletType;
		this.dx = 0;
	}

	/**
	 * Return the bullet's speed.
	 * @return the bullet's speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Set the bullet's speed
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Setter for a width difference.
	 * @param dx width difference
	 */
	public void setDx(int dx){
		this.dx = dx;
	}
	
	/**
	 * Translation method for the bullet. 
	 */
	public void moveBullet(){
		this.translate(this.dx, -this.getSpeed());
	}

	/**
	 * Setter for the type of bullet the ship uses. 
	 * @param bulletType bullet to draw
	 */
	public void setBulletType(int bulletType) {
		this.bulletType = bulletType;
	}

	/**
	 * Getter for the type of bullet the ship is using.
	 * @return an int indicating the type of bullet 
	 */
	public int getBulletType() {
		return bulletType;
	}
}
