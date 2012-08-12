package rbadia.voidspace.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.Item;
import rbadia.voidspace.model.KamikazeShip;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.sounds.SoundManager;


/**
 * Handles general game logic and status.
 * @author Daniel Santiago & Nataira Pag‡n
 */
public class GameLogic {
	
	/**
	 * Instance fields for manage the logic of the game 
	 */
	private GameScreen gameScreen;
	private GameStatus status;
	private SoundManager soundMan;
	private GameLevels level;
	private GameHighScore highScore;
	private Ship ship;
	private ArrayList<Asteroid> asteroid;
	private EnemyShip enemy;
	private KamikazeShip kamikaze;
	private Item item;
	private List<Bullet> bullets;
	private List<EnemyBullet> enemybullets;
	private long lastEnemyBulletTime = 0;
	
	Random rand = new Random();
	
	/**
	 * Creates a new game logic handler
	 * @param gameScreen the game screen
	 */
	public GameLogic(GameScreen gameScreen){
		this.gameScreen = gameScreen;
		
		// initialize game status information
		status = new GameStatus();
		// initialize game high scire
		highScore = new GameHighScore();
		// initialize the sound manager
		soundMan = new SoundManager();
		// initialize the Game Levels Manager
		level = new GameLevels(this, status);
		
		// initialize some variables
		bullets = new ArrayList<Bullet>();
		
		soundMan.playGameMusic();
	}
	
	/**
	 * Getter for the current game level.
	 * @return the level on which the player is
	 */
	public GameLevels getLevel() {
		return level;
	}

	/**
	 * Getter for game status.
	 * @return game components
	 */
	public GameStatus getStatus() {
		return status;
	}

	/**
	 * Getter for sound manager.
	 * @return sounds
	 */
	public SoundManager getSoundMan() {
		return soundMan;
	}

	/**
	 * Getter for game screen.
	 * @return game screen
	 */
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	/**
	 * Getter for game levels.
	 * @return current level
	 */
	public GameLevels getGameLevels(){
		return level;
	}
	
	/**
	 * Getter for the high score.
	 * @return high score
	 */
	public GameHighScore getHighScore() {
		return highScore;
	}

	/**
	 * Prepare for a new game.
	 */
	public void newGame(){
		status.setGameStarting(true);
		
		if(bullets != null)
			bullets = null;
		if(enemybullets != null)
			enemybullets = null;
		if(enemy != null)
			enemy = null;
		if(kamikaze != null)
			kamikaze = null;
		if(asteroid != null)
			asteroid = null;
		
		// init game variables
		bullets = new ArrayList<Bullet>();
		enemybullets = new ArrayList<EnemyBullet>();
		enemy = new EnemyShip(gameScreen);
		kamikaze = new KamikazeShip(gameScreen);
		asteroid = new ArrayList<Asteroid>();
		item = new Item();
		
		level.reset();
		gameScreen.reset();

		status.setShipsLeft(3);
		status.setGameOver(false);
		status.setScore(0);
		status.setLevel(1);
		status.setNewAsteroid(false);
		status.setNewEnemyShip(false);
		status.setNewKamikazeShip(false);
		status.setKamikazeShipVisible(false);
		status.setItemVisible(false);
		
		addAsteroids(1);
				
		// init the ship and the asteroid
        newShip(gameScreen);
        
        // prepare game screen
        gameScreen.doNewGame();
        
        // delay to display "Get Ready" message for 1.5 seconds
		Timer timer = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameStarting(false);
				status.setGameStarted(true);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Check game or level ending conditions.
	 */
	public void checkConditions(){
		
		// check game over conditions
		if(!status.isGameOver() && status.isGameStarted()){
			if(status.getShipsLeft() == 0){
				gameOver();
			}
		}
		
		//check to see if a enemy ship is on screen to fire a bullet
		if(status.isGameStarted()){
			if(!status.isNewEnemyShip()){
				if(System.currentTimeMillis() - lastEnemyBulletTime > 1000){
					lastEnemyBulletTime = System.currentTimeMillis();
					fireEnemyBullet();
				}
			}
		}
		
		//check if go to next level or not
		level.checkLevel(status.getScore());
		
		if(status.isShowLevelUpString()){
			levelUp();
		}
		
		if(status.isShowUpdateHighScore()){
			showUpdateScores();
		}
	}
	
	/**
	 * Actions to take when the game is over.
	 */
	public void gameOver(){
		status.setGameStarted(false);
		status.setGameOver(true);
		gameScreen.doGameOver();
		
        // delay to display "Game Over" message for 3 seconds
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setGameOver(false);
				status.setShowHighScore(true);
				if(highScore.compareTopScores(status.getScore())){
					gameScreen.setHighScoreName("");
					status.setNewHighScore(true);
				} else {
					status.setNewHighScore(false);
					status.setShowUpdateHighScore(true);
				}
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Display the scores updated at the high score table. 
	 */
	public void showUpdateScores(){
		Timer timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setShowHighScore(false);
				status.setNewHighScore(false);
				status.setShowUpdateHighScore(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Indicator in the game screen for when increasing the level.
	 */
	public void levelUp(){
		Timer timer = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				status.setShowLevelUpString(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	/**
	 * Fire a bullet from ship.
	 */
	public void fireBullet(){
		Bullet bullet;
		if(status.getWeaponType() == Bullet.LASER_WEAPON){
			bullet = new Bullet(ship, Bullet.LASER_WEAPON);
			soundMan.playLaserSound();
			bullets.add(bullet);
		} else if(status.getWeaponType() == Bullet.SHOTGUN_WEAPON){	
			Bullet bullet1 = new Bullet(ship, Bullet.SHOTGUN_WEAPON);
			Bullet bullet2 = new Bullet(ship, Bullet.SHOTGUN_WEAPON);
			bullet2.setDx(-3);
			Bullet bullet3 = new Bullet(ship, Bullet.SHOTGUN_WEAPON);
			bullet3.setDx(3);
			soundMan.playBulletSound();
			bullets.add(bullet1);
			bullets.add(bullet2);
			bullets.add(bullet3);
		} else {
			bullet = new Bullet(ship, Bullet.DEFAULT_WEAPON);
			soundMan.playBulletSound();
			bullets.add(bullet);
		}
		
	}
	
	/**
	 * Move a bullet once fired.
	 * @param bullet the bullet to move
	 * @return if the bullet should be removed from screen
	 */
	public boolean moveBullet(Bullet bullet){
		if(bullet.getY() - bullet.getSpeed() >= 0){
			bullet.moveBullet();
			return false;
		}
		else{
			return true;
		}
	}
	
	
	/**
	 * Enemy bullet fired.
	 */
	public void fireEnemyBullet(){
		if(status.isEnemyShipVisible()){
			EnemyBullet enemybullet = new EnemyBullet(enemy, ship);
			enemybullets.add(enemybullet);
			soundMan.playBulletSound();
		}
	}
	
	/**
	 * Translation method for the enemy bullet
	 * @param enemybullet the bullet shot
	 * @return a boolean
	 */
	public boolean moveEnemyBullet(EnemyBullet enemybullet){
		if(enemybullet.getY() <= gameScreen.getHeight()){
			enemybullet.translate(enemybullet.getDx(), enemybullet.getDy());
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Create a new ship (and replace current one).
	 */
	public Ship newShip(GameScreen screen){
		this.ship = new Ship(screen);
		return ship;
	}
	
	/**
	 * Returns the ship.
	 * @return the ship
	 */
	public Ship getShip() {
		return ship;
	}
	
	/**
	 * Getter for the enemy-ship
	 * @return the enemy-ship
	 */
	public EnemyShip getEnemyShip() {
		return enemy;
	}
	
	/**
	 * Getter for the kamikaze-ship
	 * @return the kamikaze-ship
	 */
	public KamikazeShip getKamikazeShip() {
		return kamikaze;
	}
	
	/**
	 * Getter for the item.
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Returns the list of asteroids.
	 * @return the list of asteroids
	 */
	public ArrayList<Asteroid> getAsteroid() {
		return asteroid;
	}

	/**
	 * Returns the list of bullets.
	 * @return the list of bullets
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	/**
	 * Getter for the bullets of the enemy-ship.
	 * @return the list of enemy bullets.
	 */
	public List<EnemyBullet> getEnemyBullets() {
		return enemybullets;
	}
	
	/**
	 * Add asteroids to the screen.
	 * @param amountOfAsteroids quantity of asteroids
	 */
	public void addAsteroids(int amountOfAsteroids){
		//adds Asteroid based on lvl
		for(int i = 0; i < amountOfAsteroids; i++){
			asteroid.add(new Asteroid(gameScreen));
		}
	}

	/**
	 * Update the high score table.
	 */
	public void updateHighScore() {
		String highScoreName = gameScreen.getHighScoreName();
		Long highScoreValue = status.getScore();
		
		this.highScore.insertScore(highScoreName, highScoreValue);
		this.highScore.writeScores();
		status.setShowUpdateHighScore(true);
	}

	/**
	 * Set the game for when an explosion occurs. 
	 */
	public void explosion() {
		status.setScore(status.getScore() + asteroid.size()*120);
		
		if(bullets != null)
			bullets = null;
		if(enemybullets != null)
			enemybullets = null;
		if(enemy != null)
			enemy = null;
		if(kamikaze != null)
			kamikaze = null;
		if(asteroid != null)
			asteroid = null;
		
		// init game variables
		bullets = new ArrayList<Bullet>();
		enemybullets = new ArrayList<EnemyBullet>();
		enemy = new EnemyShip(gameScreen);
		kamikaze = new KamikazeShip(gameScreen);
		asteroid = new ArrayList<Asteroid>();
		item = new Item();
		
		gameScreen.reset();
		
		status.setNewAsteroid(false);
		status.setNewEnemyShip(false);
		status.setNewKamikazeShip(false);
		status.setKamikazeShipVisible(false);
		status.setItemVisible(false);
	}

	
}
