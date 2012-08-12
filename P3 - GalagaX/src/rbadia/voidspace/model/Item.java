package rbadia.voidspace.model;

import java.awt.Rectangle;

/**
 * Represents the items that will fall from when an multiple-life asteroid is destroyed. 
 * Includes a laser gun, a shotgun with multiple bullets, extra life. 
 * @author Daniel Santigao & Nataira Pag‡n 
 *
 */
public class Item extends Rectangle{
	
	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int SHOTGUN_ITEM = 3;
	public static final int LASER_ITEM = 2;
	public static final int BOMB_ITEM = 1;
	public static final int LIFE_ITEM = 0;
	
	/**
	 * Instance fields for items.
	 */
	private int itemType;
	private int speed;
	private int itemWidth;
	private int itemHeight;
	
	/**
	 * Constructor for a new item
	 * @param x coordinate
	 * @param y coordinate
	 * @param itemWidth the width of the item
	 * @param itemHeight the height of the item
	 * @param speed speed it will be falling
	 * @param itemType which item will fall
	 */
	private void newItem(int x, int y, int itemWidth, int itemHeight, int speed, int itemType){
		this.itemWidth = itemWidth;
		this.itemHeight = itemHeight;
		this.speed = speed;
		this.itemType = itemType;
		this.setSize(itemWidth, itemHeight);
		this.setLocation(x,y);
	}
	
	/**
	 * Laser item.
	 * The ship can acquires it.
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void createLaserItem(int x, int y){
		newItem(x, y, 26,26, 4, LASER_ITEM);
	}
	
	/**
	 * Shotgun item.
	 * Multiple bullets gun the ship can acquires. 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void createShotgunItem(int x, int y){
		newItem(x, y, 26,26, 4, SHOTGUN_ITEM);
	}
	
	/**
	 * Bomb item.
	 * Clears the screen once acquired. 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void createBombItem(int x, int y){
		newItem(x, y, 22,28, 4, BOMB_ITEM);
	}
	
	/**
	 * Life item.
	 * Increase the ship life by one. 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void createLifeItem(int x, int y){
		newItem(x, y, 26,26, 5, LIFE_ITEM);
	}
	
	/**
	 * Getter for the width of the item.
	 * @return an int indicating the width of the item
	 */
	public int getItemWidth() {
		return itemWidth;
	}

	/**
	 * Getter for the height of the item.  
	 * @return an int indicating the height of the item 
	 */
	public int getItemHeight() {
		return itemHeight;
	}

	/**
	 * Getter for the item type.
	 * @return an int representing the item
	 */
	public int getItemType() {
		return itemType;
	}
	
	/**
	 * Getter for the speed of the item.
	 * @return an int representing the speed
	 */
	public int getSpeed() {
		return speed;
	}

}
