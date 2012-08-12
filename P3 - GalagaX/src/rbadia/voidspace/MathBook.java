package rbadia.voidspace;

import java.awt.Rectangle;

/**
 * MathBook class owns mathematical function implemented in the enemy-ship
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class MathBook {
	
	/**
	 *Instance Fields 
	 */
	private double value1;
	private double value2;
	
	/**
	 * Calculates the difference in the x axis and in the y axis 
	 * @param obj1 a rectangle
	 * @param obj2 a rectangle
	 */
	public void calcDxDy(Rectangle obj1, Rectangle obj2){
		double bulletX = obj1.getX();
		double bulletY = obj1.getY();
		double shipX = obj2.getX() + obj2.getWidth()/2;
		double shipY = obj2.getY() + obj2.getHeight()/2;
		
		value1 = shipX - bulletX;
		value2 = shipY - bulletY;
		
		int temp1 = 1;
		
		if(value1 < 0)
			temp1 = -1;
		
		double theta;
		theta = Math.atan(value2/value1);
		
		value1 = 100*Math.cos(theta)*temp1;
		value2 = 100*Math.sin(theta)*temp1;

	}

	/**
	 * @return value corresponding to the x coordinate
	 */
	public double getValue1() {
		return value1;
	}

	/**
	 * @return value corresponding to the y coordinate
	 */
	public double getValue2() {
		return value2;
	}
}
