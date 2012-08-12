package rbadia.voidspace.model;

import java.awt.Rectangle;

import rbadia.voidspace.MathBook;

/**
 * Represents the bullets fired by the enemy-ship. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class EnemyBullet extends Rectangle{
	/**
	 * Constant 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instance fields for the enemy bullet
	 */
	private int bulletWidth = 10;
	private int bulletHeight = 10;
	private int invSpeed = 20 ; //(Higher means slower, must be 1 < s < 100)
	private int dy;
	private int dx;
	
	/**
	 * Draws the enemy-ship bullet at the ship position.
	 * @param enemyship the ship to draw that will attack
	 * @param ship the ship to draw that will be attacked
	 */
	public EnemyBullet(EnemyShip enemyship, Ship ship) {
		this.setLocation(enemyship.x + enemyship.width/2 - bulletWidth/2,
				enemyship.y - bulletHeight);
		
		MathBook trayectoryCalc = new MathBook();
		trayectoryCalc.calcDxDy(this, ship);
		this.dx = (int) (trayectoryCalc.getValue1()/invSpeed);
		this.dy = (int) (trayectoryCalc.getValue2()/invSpeed);
		this.setSize(bulletWidth, bulletHeight);
	}
	
	/**
	 * Getter for the difference in height between the enemy-ship and the ship.
	 * @return an int indicating the height difference
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * Getter for the difference in width between the enemy-ship and the ship.
	 * @return an int indicating the width difference
	 */
	public int getDx() {
		return dx;
	}

}
