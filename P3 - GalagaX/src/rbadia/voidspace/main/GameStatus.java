package rbadia.voidspace.main;

import rbadia.voidspace.model.Bullet;

/**
 * Container for game flags and/or status variables.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class GameStatus {

	/**
	 * Game flags
	 */
	private boolean gameStarted = false;
	private boolean gameStarting = false;
	private boolean gameOver = false;
	
	/**
	 * Status variables
	 */
	private boolean newShip;
	private boolean newAsteroid;
	private boolean newEnemyShip;
	private boolean enemyShipVisible;
	private boolean newKamikazeShip;
	private boolean kamikazeShipVisible;
	private boolean GodMode;
	private boolean boosterMode;
	private boolean showLevelUpString;
	private boolean showHighScore;
	private boolean newHighScore;
	private boolean showUpdateHighScore;
	private boolean itemVisible;
	private int shipsLeft;
	private long score;
	private int level;
	private int weaponType = Bullet.DEFAULT_WEAPON;
	
	
	/**
	 * Default constructor. 
	 */
	public GameStatus(){
	}
	
	/**
	 * Indicates if the game has already started or not.
	 * @return if the game has already started or not
	 */
	public synchronized boolean isGameStarted() {
		return gameStarted;
	}
	
	/**
	 * Setter for the started game.
	 * @param gameStarted a boolean indicating if the game is started
	 */
	public synchronized void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	
	/**
	 * Indicates if the game is starting ("Get Ready" message is displaying) or not.
	 * @return if the game is starting or not.
	 */
	public synchronized boolean isGameStarting() {
		return gameStarting;
	}
	
	/**
	 * Setter for the starting game.
	 * @param gameStarting a boolean indicating if the game is starting
	 */
	public synchronized void setGameStarting(boolean gameStarting) {
		this.gameStarting = gameStarting;
	}
	
	/**
	 * Indicates if the game has ended and the "Game Over" message is displaying.
	 * @return if the game has ended and the "Game Over" message is displaying.
	 */
	public synchronized boolean isGameOver() {
		return gameOver;
	}
	
	/**
	 * Setter for the game over.
	 * @param gameOver a boolean indicating if the game is over or not
	 */
	public synchronized void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * Indicates if a new ship should be created/drawn.
	 * @return if a new ship should be created/drawn
	 */
	public synchronized boolean isNewShip() {
		return newShip;
	}
	
	/**
	 * Setter to create a new ship
	 * @param newShip a boolean indicating if a ship is created or not
	 */
	public synchronized void setNewShip(boolean newShip) {
		this.newShip = newShip;
	}

	/**
	 * Indicates if a kamikaze-ship is created
	 * @return true if there is a new one or false otherwise
	 */
	public synchronized boolean isNewKamikazeShip() {
		return newKamikazeShip;
	}

	/**
	 * Setter for the kamikaze-ship.
	 * @param newKamikazeShip a boolean indicating if there exists a kamikaze-ship
	 */
	public synchronized void setNewKamikazeShip(boolean newKamikazeShip) {
		this.newKamikazeShip = newKamikazeShip;
	}
	
	/**
	 * Indicates if the kamikaze-ship is visible.
	 * @return true if the kamikaze-ship is visible or false otherwise
	 */
	public synchronized boolean isKamikazeShipVisible() {
		return kamikazeShipVisible;
	}

	/**
	 * Setter for if the kamikaze-ship is visible
	 * @param kamikazeShipVisible a boolean indicating if the kamikaze-ship is visible
	 */
	public synchronized void setKamikazeShipVisible(boolean kamikazeShipVisible) {
		this.kamikazeShipVisible = kamikazeShipVisible;
	}
	
	/**
	 * Indicates if there exists a new enemy-ship
	 * @return true if a new enemy-ship is created
	 */
	public synchronized boolean isNewEnemyShip() {
		return newEnemyShip;
	}
	
	/**
	 * Setter for if a new enemy-ship exists.
	 * @param newEnemyShip a boolean indicating if the enemy-ship is being created
	 */
	public synchronized void setNewEnemyShip(boolean newEnemyShip) {
		this.newEnemyShip = newEnemyShip;
	}
	
	/**
	 * Indicates if the enemy-ship is visible.
	 * @return true if the enemy-ship is visible or false otherwise
	 */
	public synchronized boolean isEnemyShipVisible() {
		return enemyShipVisible;
	}
	
	/**
	 * Setter for if the enemy-ship is visible
	 * @param enemyShipVisible a boolean indicating if the enemy-ship is visible
	 */
	public synchronized void setEnemyShipVisible(boolean enemyShipVisible) {
		this.enemyShipVisible = enemyShipVisible;
	}

	/**
	 * Indicates if a new asteroid should be created/drawn.
	 * @return if a new asteroid should be created/drawn
	 */
	public synchronized boolean isNewAsteroid() {
		return newAsteroid;
	}

	/**
	 * Setter for a new asteroid.
	 * @param newAsteroid a boolean indicating if there is a new asteroid
	 */
	public synchronized void setNewAsteroid(boolean newAsteroid) {
		this.newAsteroid = newAsteroid;
	}

	/**
	 * Returns the number ships/lives left.
	 * @return the number ships left
	 */
	public synchronized int getShipsLeft() {
		return shipsLeft;
	}

	/**
	 * Setter for ships left.
	 * @param shipsLeft an int indicating the ships left
	 */
	public synchronized void setShipsLeft(int shipsLeft) {
		this.shipsLeft = shipsLeft;
	}
	
	/**
	 * Getter for the score.
	 * @return a long indicating the score
	 */
	public synchronized long getScore(){
		return score; 
	}
	
	/**
	 * Setter for the score.
	 * @param score the score accumulated by the player
	 */
	public synchronized void setScore(long score){
		this.score = score;
	}

	/**
	 * Setter for the level.
	 * @param level current level
	 */
	public synchronized void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Getter for the level.
	 * @return an int indicating the current level
	 */
	public synchronized int getLevel() {
		return level;
	}

	/**
	 * Setter for the ghost mode of the ship.
	 * @param godMode a boolean indicating if the ship is in ghost mode
	 */
	public synchronized void setGodMode(boolean godMode) {
		GodMode = godMode;
	}

	/**
	 * Indicates if the ship is in ghost mode.
	 * @return true if the ship is in ghost mode or false otherwise
	 */
	public synchronized boolean isGodMode() {
		return GodMode;
	}

	/**
	 * Setter for the booster mode of the ship.
	 * @param boosterMode a boolean indicating if the ship is in booster mode
	 */
	public synchronized void setBoosterMode(boolean boosterMode) {
		this.boosterMode = boosterMode;
	}

	/**
	 * Indicates if the ship is in booster mode.
	 * @return true if the ship is in booster mode or false otherwise
	 */
	public synchronized boolean isBoosterMode() {
		return boosterMode;
	}

	/**
	 * Setter for level up message. 
	 * @param showLevelUpString a boolean indicating if the string is showed
	 */
	public synchronized void setShowLevelUpString(boolean showLevelUpString) {
		this.showLevelUpString = showLevelUpString;
	}

	/**
	 * Indicates if the level message is showing. 
	 * @return true if the message is show or false otherwise
	 */
	public synchronized boolean isShowLevelUpString() {
		return showLevelUpString;
	}

	/**
	 * Setter to show the high score.
	 * @param showHighScore a boolean indicating if the high score is showing
	 */
	public synchronized void setShowHighScore(boolean showHighScore) {
		this.showHighScore = showHighScore;
	}

	/**
	 * Indicates if the high score is showing.
	 * @return true if the high score is showing or false otherwise 
	 */
	public synchronized boolean isShowHighScore() {
		return showHighScore;
	}

	/**
	 * Setter for a new high score.
	 * @param newHighScore a boolean indicating if there exists a new high score
	 */
	public synchronized void setNewHighScore(boolean newHighScore) {
		this.newHighScore = newHighScore;
	}

	/**
	 * Indicates if there exists a new high score
	 * @return true if there is a new high score or false otherwise
	 */
	public synchronized boolean isNewHighScore() {
		return newHighScore;
	}

	/**
	 * Setter for the message of new high score when it needs to be updated.
	 * @param showUpdateHighScore a boolean indicating if there is a need to update the score
	 */
	public synchronized void setShowUpdateHighScore(boolean showUpdateHighScore) {
		this.showUpdateHighScore = showUpdateHighScore;
	}

	/**
	 * Indicates if there is a need to update the high score table.
	 * @return true if the high score is updated or false otherwise
	 */
	public synchronized boolean isShowUpdateHighScore() {
		return showUpdateHighScore;
	}

	/**
	 * Setter for the visibility of the item. 
	 * @param itemVisible a boolean indicating if the item is visible or not
	 */
	public synchronized void setItemVisible(boolean itemVisible) {
		this.itemVisible = itemVisible;
	}

	/**
	 * Indicates if the item is visible or not.
	 * @return true if the item is visible or false otherwise
	 */
	public synchronized boolean isItemVisible() {
		return itemVisible;
	}

	/**
	 * Setter for the type of weapon the ship owns. 
	 * @param weaponType an int representing the weapon type
	 */
	public void setWeaponType(int weaponType) {
		this.weaponType = weaponType;
	}

	/**
	 * Getter for the weapon type.
	 * @return an int indicating the weapon type
	 */
	public int getWeaponType() {
		return weaponType;
	}

}
