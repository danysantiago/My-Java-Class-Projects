package rbadia.voidspace.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.Item;
import rbadia.voidspace.model.KamikazeShip;
import rbadia.voidspace.model.Ship;
import rbadia.voidspace.model.Star;
import rbadia.voidspace.sounds.SoundManager;

/**
 * Main game screen. Handles all game graphics updates and some of the game logic.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class GameScreen extends JPanel {
	
	/**
	 * Constants 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int NEW_SHIP_DELAY = 500;
	private static final int NEW_ENEMY_SHIP_DELAY = 500;
	private static final int NEW_ASTEROID_DELAY = 300;
	
	
	//Game Screen buffered image and graphics
	private BufferedImage backBuffer;
	private Graphics2D g2d;

	/**
	 * Instance fields for time management.
	 */
	private long lastShipTime;
	private long lastAsteroidTime;
	private long lastEnemyShipTime;
	private long lastKamikazeShipTime;
	private long kamikazeReadyTime;
	private long lastBigAsteroidTime;
	private long lastKamikazeShipShieldedTime;
	private long lastLaserItemTime;
	private long lastLifeItemTime;
	private long lastShotgunItemTime;
	private long lastBombItemTime;
	
	/**
	 * Instance fields for explosion management.
	 */
	private boolean drawExplosion;
	private Rectangle shipExplosion;
	private Rectangle enemyShipExplosion;
	private Rectangle kamikazeShipExplosion;
	
	/**
	 * Instance fields for label management.
	 */
	private JLabel shipsValueLabel;
	private JLabel scoreValueLabel;
	private JLabel levelValueLabel;

	//Randomizer
	private Random rand;
	
	/**
	 * Instance fields for font management.
	 */
	private Font originalFont;
	private Font bigFont;
	private Font biggestFont;
	
	/**
	 * Instance fields for game components.  
	 */
	private GameStatus status;
	private SoundManager soundMan;
	private GraphicsManager graphicsMan;
	private GameLogic gameLogic;
	private GameHighScore highScore;
	private List<Star> stars = new ArrayList<Star>();

	/**
	 * Constructor for the GameScreen
	 */
	public GameScreen() {
		super();
		// initialize random number generator
		rand = new Random();
		
		initialize();
		
		// Initialize graphics manager
		graphicsMan = new GraphicsManager();
		
		// Initialize back buffer image
		backBuffer = new BufferedImage(500, 400, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		
		//Creates 150 random stars
		for(int i = 0; i <= 150; i++){
			stars.add(new Star(this, rand.nextInt(this.getWidth()),rand.nextInt(this.getHeight())));
		}
		
	}

	/**
	 * Initialization method (for VE compatibility).
	 */
	private void initialize() {
		// set panel properties
        this.setSize(new Dimension(500, 400));
        this.setPreferredSize(new Dimension(500, 400));
        this.setBackground(Color.BLACK);
	}

	/**
	 * Update the game screen.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw current backbuffer to the actual game screen
		g.drawImage(backBuffer, 0, 0, this);
	}
	
	/**
	 * Update the game screen's backbuffer image.
	 * Variables used in this method are declared below
	 */
	
	private Ship ship;
	private Item item;
	private EnemyShip enemy;
	private KamikazeShip kamikaze;
	private ArrayList<Asteroid> asteroidArray;
	private List<Bullet> bullets;
	private List<EnemyBullet> enemybullets;
	
	/**
	 * Update the screen every time the game is running.
	 */
	public void updateScreen(){
		//Get variables from game logic
		ship = gameLogic.getShip();
		item = gameLogic.getItem();
		enemy = gameLogic.getEnemyShip();
		kamikaze = gameLogic.getKamikazeShip();
		asteroidArray = gameLogic.getAsteroid();
		bullets = gameLogic.getBullets();
		enemybullets = gameLogic.getEnemyBullets();
		
		// set original font - for later use
		if(this.originalFont == null){
			this.originalFont = g2d.getFont();
			this.bigFont = originalFont;
		}
		
		// Erase screen by drawing a black fill
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		
		//Draw stars
		drawStars();

		// if the game is starting, draw "Get Ready" message
		if(status.isGameStarting()){
			drawGetReady();
			return;
		}
		
		// if the game is over, draw the "Game Over" message
		if(status.isGameOver()){
			// draw the message
			drawGameOver();
			
			long currentTime = System.currentTimeMillis();
			// draw the explosions until their time passes
			if((currentTime - lastShipTime) < NEW_SHIP_DELAY){
				graphicsMan.drawShipExplosion(shipExplosion, g2d, this);
			}
			return;
		}
		
		if(status.isShowHighScore()){
			drawHighScore();
			return;
		}
		
		// the game has not started yet
		if(!status.isGameStarted()){
			// draw game title screen
			initialMessage();
			return;
		}
		
		if(status.isShowLevelUpString()){
			drawLevelUpMessage();
		}
		
		//Draws enemy ship
		drawEnemyShip();
		
		//Draws enemy ship
		drawKamikazeShip();
		
		//Draw asteroids
		drawAsteroids();
		
		//Draw Ship
		drawShip();
		
		//Draw Bullets
		drawBullets();
		
		//Draw enemy bullets
		drawEnemyBullets();
		
		//Draw items
		drawItem();
		
		//Draws explosion
		if(drawExplosion){
			drawExplosion();
		}
		
		//Check EnemyShip-Bullet collision
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(enemy.intersects(bullet)){
				handleCollision(ENEMYSHIPandBULLET_COLLISION, i, null);
				break;
			}
		}
		
		//Check Asteroid-Bullet collision
		for (int i = 0; i < asteroidArray.size(); i++){
			for(int j=0; j<bullets.size(); j++){
				Asteroid asteroid = asteroidArray.get(i);
				Bullet bullet = bullets.get(j);
				
				if(asteroid.intersects(bullet)){
					handleCollision(ASTEROIDandBULLET_COLLISION, i, j);
					break;
				}
			}
			if(status.isNewAsteroid())
				break;
		}
		
		//Check EnemyShip-Ship collision
		if(enemy.intersects(ship)){
			if(!status.isGodMode())
				handleCollision(ENEMYSHIPandSHIP_COLLISION, null, null);
		}
		
		//Check KamikazeShip-Ship collision
		if(kamikaze.intersects(ship))
			if(!status.isGodMode()){
				handleCollision(KAMIKAZEandSHIP_COLLISION, null, null);
		}
		
		//Check KamikazeShip-Bullet collision
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(kamikaze.intersects(bullet)){
				handleCollision(KAMIKAZEandBULLET_COLLISION, i, null);
			}
		}
		
		//Check EnemyBullet-Ship Collision
		for(int i=0; i<enemybullets.size(); i++){
			EnemyBullet enemybullet = enemybullets.get(i);
			if(enemybullet.intersects(ship)){
				if(!status.isGodMode())
					handleCollision(ENEMYBULLETandSHIP_COLLISION, i, null);
				break;
			}
		}
		
		//Check Asteroid-Ship Collision
		for (int i = 0; i < asteroidArray.size(); i++){
			Asteroid asteroid = asteroidArray.get(i);
			if(asteroid.intersects(ship)){
				if(!status.isGodMode())
					handleCollision(ASTEROIDandSHIP_COLLISION, i, null);
				break;
			}
		}
		
		//Check for Item-Ship Collision
		if(item.intersects(ship)){
			handleCollision(ITEMandSHIP_COLLISION, null, null);
		}
		
		// update score label
		scoreValueLabel.setText(Long.toString(status.getScore()));
		
		// update ships left label
		shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		
		// update level label
		levelValueLabel.setText(Integer.toString(status.getLevel()));
	}

	
	/**
	 * Constants to define the kind of collision to handle
	 */
	public static final int ENEMYSHIPandBULLET_COLLISION = 0;
	public static final int ASTEROIDandBULLET_COLLISION = 1;
	public static final int ENEMYSHIPandSHIP_COLLISION = 2;
	public static final int ENEMYBULLETandSHIP_COLLISION = 3;
	public static final int ASTEROIDandSHIP_COLLISION = 4;
	public static final int KAMIKAZEandSHIP_COLLISION = 5;
	private static final int KAMIKAZEandBULLET_COLLISION = 6;
	private static final int ITEMandSHIP_COLLISION = 7;
	
	
	/**
	 *  Handles objects collisions
	 * @param code of the type of collision being handled
	 * @param i iteration 1 at which collision occurred (set to null if no iteration)
	 * @param j iteration 2 at which collision occurred (set to null if no iteration)
	 */
	private void handleCollision(int code, Object iter1, Object iter2){
		if(code == ENEMYSHIPandBULLET_COLLISION){
			int i = (Integer) iter1;
			Bullet bullet = bullets.get(i);
			if(enemy.intersects(bullet)){
				// increase asteroids destroyed count
				
				status.setScore(status.getScore() + 500);

				// "remove" asteroid
		        enemyShipExplosion = new Rectangle(
		        		enemy.x,
		        		enemy.y,
		        		enemy.width,
		        		enemy.height);
				enemy.setLocation(-enemy.width, -enemy.height);
				status.setNewEnemyShip(true);
				status.setEnemyShipVisible(false);
				lastEnemyShipTime = System.currentTimeMillis();
				
				soundMan.playEnemyShipExplosionSound();

				// remove bullet
				if(status.getWeaponType() != Bullet.LASER_WEAPON){
					bullets.remove(i);
				}
			}
		} else if(code == ASTEROIDandBULLET_COLLISION){
			int i = (Integer) iter1;
			int j = (Integer) iter2;
			Asteroid asteroid = asteroidArray.get(i);
			
			asteroid.substractHealthPoints();
			
			if(asteroid.getHealthPoints() == 0){
				// increase asteroids destroyed count
				if(asteroid.getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
					status.setScore(status.getScore() + 300);
				} else if(asteroid.getAsteroidMagnitud() == Asteroid.SMALL_MAGNITUD){
					status.setScore(status.getScore() + 100);
				} else {
					status.setScore(status.getScore() + 50);
				}


				// "remove" asteroid
				asteroid.setExplosionRect(new Rectangle(
						asteroid.x,
						asteroid.y,
						asteroid.width,
						asteroid.height)
				);
				asteroid.setLastLocation();
				asteroid.setLocation(-asteroid.width, -asteroid.height);
				asteroid.setAsteroidDestroyed(true);
				lastAsteroidTime = System.currentTimeMillis();

				// play asteroid explosion sound
				if(asteroid.getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
					soundMan.playBigAsteroidExplosionSound();
				} else {
					soundMan.playAsteroidExplosionSound();
				}
			}
			
			if(asteroid.getAsteroidMagnitud() == Asteroid.DIAMOND_MAGNITUD){
				soundMan.playDiamondHitSound();
			}
		
			if(status.getWeaponType() != Bullet.LASER_WEAPON){
				bullets.remove(j);
			}
		} else if(code == ENEMYSHIPandSHIP_COLLISION){
			
			shipDied();

			// "remove" enemy ship
			enemyShipExplosion = new Rectangle(
					enemy.x,
					enemy.y,
					enemy.width,
					enemy.height);
			enemy.setLocation(-enemy.width, -enemy.height);
			status.setNewEnemyShip(true);
			status.setEnemyShipVisible(false);
			lastEnemyShipTime = System.currentTimeMillis();

			// "remove" ship
			shipExplosion = new Rectangle(
					ship.x,
					ship.y,
					ship.width,
					ship.height);
			ship.setLocation(this.getWidth() + ship.width, -ship.height);
			status.setNewShip(true);
			lastShipTime = System.currentTimeMillis();

			// play ship explosion sound
			soundMan.playShipExplosionSound();

			// play asteroid explosion sound
			soundMan.playEnemyShipExplosionSound();
		} else if(code == ENEMYBULLETandSHIP_COLLISION){
			int i = (Integer) iter1;
			
			shipDied();
			
			// "remove" ship
	        shipExplosion = new Rectangle(
	        		ship.x,
	        		ship.y,
	        		ship.width,
	        		ship.height);
			ship.setLocation(this.getWidth() + ship.width, -ship.height);
			status.setNewShip(true);
			lastShipTime = System.currentTimeMillis();
			
			// play ship explosion sound
			soundMan.playShipExplosionSound();
			enemybullets.remove(i);
		} else if(code == ASTEROIDandSHIP_COLLISION){
			int i = (Integer) iter1;
			Asteroid asteroid = asteroidArray.get(i);
			
			shipDied();
			
			// "remove" asteroid
			asteroid.setExplosionRect(new Rectangle(
	        		asteroid.x,
	        		asteroid.y,
	        		asteroid.width,
	        		asteroid.height)
			);
			asteroid.setLocation(-asteroid.width, -asteroid.height);
			asteroidArray.get(i).setAsteroidDestroyed(true);
			lastAsteroidTime = System.currentTimeMillis();
			
			// "remove" ship
	        shipExplosion = new Rectangle(
	        		ship.x,
	        		ship.y,
	        		ship.width,
	        		ship.height);
			ship.setLocation(this.getWidth() + ship.width, -ship.height);
			status.setNewShip(true);
			lastShipTime = System.currentTimeMillis();
			
			// play ship explosion sound
			soundMan.playShipExplosionSound();
			// play asteroid explosion sound
			if( asteroid.getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
				soundMan.playBigAsteroidExplosionSound();
			} else {
				soundMan.playAsteroidExplosionSound();
			}
		} else if(code == KAMIKAZEandSHIP_COLLISION){
			shipDied();

			// "remove" kamikaze ship
			kamikazeShipExplosion = new Rectangle(
					kamikaze.x,
					kamikaze.y,
					kamikaze.width,
					kamikaze.height);
			kamikaze.setLocation(-kamikaze.width, -kamikaze.height);
			status.setNewKamikazeShip(true);
			status.setKamikazeShipVisible(false);
			lastKamikazeShipTime = System.currentTimeMillis();

			// "remove" ship
			shipExplosion = new Rectangle(
					ship.x,
					ship.y,
					ship.width,
					ship.height);
			ship.setLocation(this.getWidth() + ship.width, -ship.height);
			status.setNewShip(true);
			lastShipTime = System.currentTimeMillis();

			// play ship explosion sound
			soundMan.playShipExplosionSound();

			soundMan.playEnemyShipExplosionSound();
		} else if(code == KAMIKAZEandBULLET_COLLISION){
			int i = (Integer) iter1;
			Bullet bullet = bullets.get(i);
			if(kamikaze.intersects(bullet)){
				soundMan.playShield();
				lastKamikazeShipShieldedTime = System.currentTimeMillis();
				
				if(status.getWeaponType() != Bullet.LASER_WEAPON){
					bullets.get(i).setDx( ((int)Math.pow(-1, rand.nextInt(10)))*(rand.nextInt(5) + 5));
				} 

			}
		} else if(code == ITEMandSHIP_COLLISION){
			if(item.getItemType() == Item.BOMB_ITEM){
				drawExplosion = true;
				soundMan.playBomb();
			}
			
			if(item.getItemType() == Item.LASER_ITEM){
				status.setWeaponType(Bullet.LASER_WEAPON);
			} else if((item.getItemType() == Item.SHOTGUN_ITEM)){
				status.setWeaponType(Bullet.SHOTGUN_WEAPON);
			}
			
			if(item.getItemType() == Item.LIFE_ITEM){
				status.setShipsLeft(status.getShipsLeft() + 1);
				soundMan.playLife();
			}
			
			status.setItemVisible(false);
		}
	}
	
	/**
	 * For when the ship is dead. 
	 */
	private void shipDied() {
		status.setShipsLeft(status.getShipsLeft() - 1);
		
		status.setBoosterMode(false);
		status.setWeaponType(Bullet.DEFAULT_WEAPON);
		
	}

	/**
	 * Draws the enemy bullets. 
	 */
	private void drawEnemyBullets() {
		for(int i=0; i<enemybullets.size(); i++){
			EnemyBullet enemybullet = enemybullets.get(i);
			graphicsMan.drawEnemyBullet(enemybullet, g2d, this);
			
			boolean remove = gameLogic.moveEnemyBullet(enemybullet);
			if(remove){
				enemybullets.remove(i);
				i--;
			}
		}
	}

	/**
	 * Draws the ship bullets. 
	 */
	private void drawBullets() {
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			if(bullet.getBulletType() == Bullet.LASER_WEAPON){
				graphicsMan.drawLaserBullet(bullet, g2d, this);
			} else {
				graphicsMan.drawBullet(bullet, g2d, this);
			}
			
			boolean remove = gameLogic.moveBullet(bullet);
			if(remove){
				bullets.remove(i);
				i--;
			}
		}
	}

	/**
	 * Draws the ship. 
	 */
	private void drawShip() {
		if(!status.isNewShip() && !status.isGodMode()){
			long currentTime = System.currentTimeMillis();
			// draw it in its current location
			if(ship.getSpeed() == Ship.DEFAULT_SPEED){
				status.setBoosterMode(false);
				graphicsMan.drawShip(ship, g2d, this);
			} else {
				status.setBoosterMode(true);
				if (currentTime%200 < 100){
					status.setScore(status.getScore() + 1);
					graphicsMan.drawShipwithBoost1(ship, g2d, this);
				} else {
					graphicsMan.drawShipwithBoost2(ship, g2d, this);
				}
			}
		}
		else{
			// draw a new one
			long currentTime = System.currentTimeMillis();
			if(status.isGodMode()){
				
				if((currentTime - lastShipTime) > 3000){
					status.setGodMode(false);
				} else if (currentTime%500 < 250){
					graphicsMan.drawGodShip(ship, g2d, this);
				} else {
					graphicsMan.drawShip(ship, g2d, this);
				}
			} else if((currentTime - lastShipTime) > NEW_SHIP_DELAY){
				lastShipTime = currentTime;
				status.setNewShip(false);
				status.setGodMode(true);
				ship = gameLogic.newShip(this);
			}
			else{
				// draw explosion
				graphicsMan.drawShipExplosion(shipExplosion, g2d, this);
			}
		}
	}

	/**
	 * Draw asteroids
	 */
	private void drawAsteroids() {
		for (int i = 0; i  < asteroidArray.size(); i++){
			if(!asteroidArray.get(i).isAsteroidDestroyed()){
				long currentTime = System.currentTimeMillis();
				// draw the asteroid until it reaches the bottom of the screen
				if(asteroidArray.get(i).getY() + asteroidArray.get(i).getSpeed() < this.getHeight() && asteroidArray.get(i).getX() + asteroidArray.get(i).getWidth() > 0 && asteroidArray.get(i).getX() < this.getWidth()){
					if(status.isBoosterMode()){
						asteroidArray.get(i).moveBoosted();
					} else {
						asteroidArray.get(i).move();
					}
					
					if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
						if((currentTime - asteroidArray.get(i).getLastDamage()) > 300 ){
							graphicsMan.drawBigAsteroid(asteroidArray.get(i), g2d, this);
						} else {
							graphicsMan.drawBigAsteroidHit(asteroidArray.get(i), g2d, this);
						}
					} else if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.SMALL_MAGNITUD){
						graphicsMan.drawSmallAsteroid(asteroidArray.get(i), g2d, this);
					} else if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.DIAMOND_MAGNITUD){
						graphicsMan.drawDiamondAsteroid(asteroidArray.get(i), g2d, this);
					} else {
						if((currentTime - asteroidArray.get(i).getLastDamage()) > 300 ){
							graphicsMan.drawAsteroid(asteroidArray.get(i), g2d, this);
						} else {
							graphicsMan.drawAsteroidHit(asteroidArray.get(i), g2d, this);
						}
					}
				}

				else{
					if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.DIAMOND_MAGNITUD){
						asteroidArray.remove(i);
						asteroidArray.add(new Asteroid(this));
					} else {
						asteroidArray.get(i).setMovementType();
						asteroidArray.get(i).setLocation(rand.nextInt(getWidth() - asteroidArray.get(i).width), 0);
					}
				}
			}
			else{
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){
					
					// draw a new asteroid
					lastAsteroidTime = currentTime;
					if(asteroidArray.size() < gameLogic.getLevel().getMaxAsteroidInLvl()){
						if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
							Asteroid temp;
							double lastAsteroidX = asteroidArray.get(i).getLastX();
							double lastAsteroidY = asteroidArray.get(i).getLastY();
							asteroidArray.add(new Asteroid(this));
							temp = asteroidArray.get(asteroidArray.size()-1);
							temp.setAsteroidLocation((int) lastAsteroidX, (int) lastAsteroidY);
							
							//Create second asteroid if no item is created
							if(!createItem((int)lastAsteroidX, (int)lastAsteroidY)){
								asteroidArray.add(new Asteroid(this));
								temp = asteroidArray.get(asteroidArray.size()-1);
								temp.setAsteroidLocation((int) lastAsteroidX, (int) lastAsteroidY);
							}
							
						} else if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.MEDIUM_MAGNITUD && rand.nextInt(20) > 16){
							Asteroid temp;
							double lastAsteroidX = asteroidArray.get(i).getLastX();
							double lastAsteroidY = asteroidArray.get(i).getLastY();
							asteroidArray.add(Asteroid.createSmallAsteroid(this));
							temp = asteroidArray.get(asteroidArray.size()-1);
							temp.setAsteroidLocation((int) lastAsteroidX, (int) lastAsteroidY);
							asteroidArray.add(Asteroid.createSmallAsteroid(this));
							temp = asteroidArray.get(asteroidArray.size()-1);
							temp.setAsteroidLocation((int) lastAsteroidX, (int) lastAsteroidY);
						} else if(rand.nextInt(20) > 15){
							asteroidArray.add(Asteroid.createDiamondAsteroid(this));
						} else if((currentTime - lastBigAsteroidTime) > gameLogic.getGameLevels().getBigAsteroidTimeInterval()){
							asteroidArray.add(Asteroid.createBigAsteroid(this));
							lastBigAsteroidTime = currentTime;
						} else{
							asteroidArray.add(new Asteroid(this));
						}
							
						Asteroid temp = asteroidArray.get(asteroidArray.size()-1);
						temp.setLocation(rand.nextInt(getWidth() - temp.width), 0);
					}
					
					//Remove destroyed asteroid
					asteroidArray.remove(i);

				}
				else{
					// draw explosion
					if(asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.BIG_MAGNITUD){
						graphicsMan.drawBigAsteroidExplosion(asteroidArray.get(i).getExplosionRect(), g2d, this);
					} else if (asteroidArray.get(i).getAsteroidMagnitud() == Asteroid.SMALL_MAGNITUD){
						graphicsMan.drawSmallAsteroidExplosion(asteroidArray.get(i).getExplosionRect(), g2d, this);
					} else {
						graphicsMan.drawAsteroidExplosion(asteroidArray.get(i).getExplosionRect(), g2d, this);
					}
				}
			}
		}
	}

	/**
	 * Draws enemy ship
	 */
	private void drawEnemyShip() {
		if(!status.isNewEnemyShip()){
			long currentTime = System.currentTimeMillis();
			if(enemy.getX() >= 0 - enemy.getWidth() && enemy.getX() <= this.getWidth() && status.isEnemyShipVisible()){
				status.setEnemyShipVisible(true);
				lastEnemyShipTime = currentTime;
				
				if(enemy.getSwampingLocation() == EnemyShip.LEFT){
					enemy.translate(-enemy.getSpeed(), 0);
					graphicsMan.drawEnemyShip(enemy, g2d, this);
				} else if(enemy.getSwampingLocation() == EnemyShip.RIGHT){
					enemy.translate(enemy.getSpeed(), 0);
					graphicsMan.drawRotatedEnemyShip(enemy, g2d, this);
				}				
				
			} else {
				status.setEnemyShipVisible(false);
				
				if(currentTime - lastEnemyShipTime > enemy.getSwampingInterval()){
					if(enemy.getSwampingLocation() == EnemyShip.LEFT){
						enemy.setLocation(this.getWidth(), this.getHeight()/2 - rand.nextInt(this.getHeight()/2));
					} else if(enemy.getSwampingLocation() == EnemyShip.RIGHT){
						enemy.setLocation((int) (0 - enemy.getWidth()), this.getHeight()/2 - rand.nextInt(this.getHeight()/2));
					}
					enemy.setSwampingLocation();
					status.setEnemyShipVisible(true);
				}
			}
		} else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastEnemyShipTime) > NEW_ENEMY_SHIP_DELAY){
				lastEnemyShipTime = currentTime;
				status.setNewEnemyShip(false);
				status.setEnemyShipVisible(false);
				enemy.setLocation(this.getWidth(), this.getHeight()/2 - rand.nextInt(this.getHeight()/2));
			} else {
				graphicsMan.drawEnemyShipExplosion(enemyShipExplosion, g2d, this);
			}
		}	
	}
	
	/**
	 * Draws kamikaze-ship.
	 */
	private void drawKamikazeShip() {
		if(!status.isNewKamikazeShip()){
			long currentTime = System.currentTimeMillis();

			double dx = ship.getX() - kamikaze.getX();
			double dy = 1;

			if(dx > 0){
				dx = 1;
			} else {
				dx = -1;
			}

			if(kamikaze.getY() < this.getHeight() && status.isKamikazeShipVisible()){
				lastKamikazeShipTime = currentTime;

				if(!kamikaze.isSuicidePosition()){

					kamikaze.translate((int) dx*kamikaze.getSpeed(), (int) dy*kamikaze.getSpeed());

					if(Math.abs(ship.getY() - kamikaze.getY()) < 150 && Math.abs(ship.getX() - kamikaze.getX()) < 20){
						kamikaze.setSuicidePosition(true);
						kamikazeReadyTime = currentTime;
					}
				} else {
					if ((currentTime - kamikazeReadyTime) > 3000){
						kamikaze.translate(0, kamikaze.getSpeed() + 2);
					} else if (Math.abs(ship.getY() - kamikaze.getY()) < 50){
						kamikaze.translate((int) dx*kamikaze.getSpeed(), -1*ship.getSpeed());
					} else if (Math.abs(ship.getY() - kamikaze.getY()) > 150){
						kamikaze.translate((int) dx*kamikaze.getSpeed(), 1*ship.getSpeed());
					} else {
						kamikaze.translate((int) dx*kamikaze.getSpeed(), 0);
					}
				}

				if(currentTime - lastKamikazeShipShieldedTime > 300){
					graphicsMan.drawKamikazeShip(kamikaze, g2d, this);
				} else {
					graphicsMan.drawKamikazeShipShielded(kamikaze, g2d, this);
				}

			} else {
				if(currentTime - lastKamikazeShipTime > kamikaze.getSwampingInterval()){
					kamikaze.setSuicidePosition(false);
					status.setKamikazeShipVisible(true);
					kamikaze.setLocation(rand.nextInt((int) (this.getWidth() - kamikaze.getWidth())),0);
				}
			}

		} else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastKamikazeShipTime) > NEW_ENEMY_SHIP_DELAY){
				lastKamikazeShipTime = currentTime;
				status.setNewKamikazeShip(false);
				kamikaze.setSuicidePosition(false);
				kamikaze.setLocation(rand.nextInt((int) (this.getWidth() - kamikaze.getWidth())),0);
			} else {
				graphicsMan.drawKamikazeShipExplosion(kamikazeShipExplosion, g2d, this);
			}
		}

	}
	
	/**
	 * Draws items falling. 
	 */
	private void drawItem(){
		
		if(status.isItemVisible() && item.y < this.getHeight()){
			item.translate(0, item.getSpeed());
			
			if(item.getItemType() == Item.LASER_ITEM){
				graphicsMan.drawLaserItem(item, g2d, this);
			} else if(item.getItemType() == Item.SHOTGUN_ITEM){
				graphicsMan.drawShotgunItem(item, g2d, this);
			} else if(item.getItemType() == Item.LIFE_ITEM){
				graphicsMan.drawLifeItem(item, g2d, this);
			} else if(item.getItemType() == Item.BOMB_ITEM){
				graphicsMan.drawBombItem(item, g2d, this);
			}
		} else {
			item.setLocation(-item.getItemWidth(), -item.getItemHeight());
			status.setItemVisible(false);
		}
	}

	/**
	 * Moves stars
	 */
	private void drawStars() {
		g2d.setColor(Color.WHITE); //Stars color
		//Iterate trough every star
		for(int i=0; i<stars.size(); i++){
			//Translate star (Star are moved vertically based on their random speed)
			if(status.isBoosterMode()){
				stars.get(i).translate(0, stars.get(i).getSpeed()*2);
			} else {
				stars.get(i).translate(0, stars.get(i).getSpeed());
			}
			//Get start position
			int x = stars.get(i).x;
			int y = stars.get(i).y;
			//Check if star is at bottom, star is deleted and a new one is created at top
			if(y > this.getHeight()){
				stars.remove(i);
				stars.add(new Star(this, rand.nextInt(this.getWidth()),0));
			}
			//Draw the star (Stars are really just a line with lenght of 1)
			if(status.isBoosterMode()){
				g2d.drawLine(x, y, x, y+5);
			} else {
				g2d.drawLine(x, y, x, y);
			}
		}
	}

	/**
	 * Draws the "Game Over" message.
	 */
	private void drawGameOver() {
		String gameOverStr = "GAME OVER";
		Font currentFont = biggestFont == null? bigFont : biggestFont;
		float fontSize = currentFont.getSize2D();
		bigFont = currentFont.deriveFont(fontSize + 1).deriveFont(Font.BOLD);
		FontMetrics fm = g2d.getFontMetrics(bigFont);
		int strWidth = fm.stringWidth(gameOverStr);
		if(strWidth > this.getWidth() - 10){
			biggestFont = currentFont;
			bigFont = biggestFont;
			fm = g2d.getFontMetrics(bigFont);
			strWidth = fm.stringWidth(gameOverStr);
		}
		int ascent = fm.getAscent();
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2;
		g2d.setFont(bigFont);
		g2d.setPaint(Color.WHITE);
		g2d.drawString(gameOverStr, strX, strY);
	}

	/**
	 * Draws the initial "Get Ready!" message.
	 */
	private void drawGetReady() {
		String readyStr = "Get Ready!"; //String to be drawn
		//Some drawing properties
		g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 1));
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();
		int strWidth = fm.stringWidth(readyStr);
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2;
		g2d.setPaint(Color.WHITE); //Color
		
		g2d.drawString(readyStr, strX, strY); //Draw
	}
	
	/**
	 * Draws explosions.
	 */
	private double circleSize = 0;
	Shape circle = new Ellipse2D.Double(0,0,0,0);
	private void drawExplosion() {
		if(circleSize < 800){
			status.setGodMode(true);
			double shipX = ship.getX();
			double shipY = ship.getY();
			circle = new Ellipse2D.Double(shipX - circleSize/2, shipY - circleSize/2, circleSize, circleSize);
			g2d.draw(circle);
			g2d.fill(circle);
			circleSize += 10;
		} else if (circleSize < 1500){
			gameLogic.explosion();
			double shipX = ship.getX();
			double shipY = ship.getY();
			circle = new Ellipse2D.Double(shipX - circleSize/2, shipY - circleSize/2, circleSize, circleSize);
			g2d.draw(circle);
			if(System.currentTimeMillis()%100 > 50){
				g2d.fill(circle);
			}
			circleSize += 10;
		} else {
			status.setGodMode(false);
			circleSize = 0;
			drawExplosion = false;
			gameLogic.addAsteroids(status.getLevel());
		}
	}


	/**
	 * Display initial game title screen.
	 */
	private int titleX = 300;
	private void initialMessage() {
		String gameTitleStr = "Galaga X";
		Font currentFont = biggestFont == null? bigFont : biggestFont;
		float fontSize = currentFont.getSize2D();
		bigFont = currentFont.deriveFont(fontSize + 1).deriveFont(Font.BOLD).deriveFont(Font.ITALIC);
		FontMetrics fm = g2d.getFontMetrics(bigFont);
		int strWidth = fm.stringWidth(gameTitleStr);
		if(strWidth > this.getWidth() - 10){
			bigFont = currentFont;
			biggestFont = currentFont;
			fm = g2d.getFontMetrics(currentFont);
			strWidth = fm.stringWidth(gameTitleStr);
		}
		g2d.setFont(bigFont);
		int ascent = fm.getAscent();
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (3*this.getHeight() - 2*ascent)/10;
		if(titleX != 10){
			titleX -= 2;
			graphicsMan.drawGameTitle(new Rectangle(titleX,strY,1,1), g2d, this);
		} else {
			graphicsMan.drawGameTitle(new Rectangle(titleX,strY,1,1), g2d, this);
		}
		
		g2d.setFont(originalFont);
		fm = g2d.getFontMetrics();
		String newGameStr = "Press <Space> to Start a New Game.";
		strWidth = fm.stringWidth(newGameStr);
		strX = (this.getWidth() - strWidth)/2;
		strY = (this.getHeight() + fm.getAscent())/2 + ascent + 16;
		g2d.setPaint(Color.WHITE);
		g2d.drawString(newGameStr, strX, strY);
		
		fm = g2d.getFontMetrics();
		String viewScoresString = "Press <H> to View High Scores.";
		strWidth = fm.stringWidth(viewScoresString);
		strX = (this.getWidth() - strWidth)/2;
		strY = strY + 16;
		g2d.drawString(viewScoresString, strX, strY);
		
		fm = g2d.getFontMetrics();
		String exitGameStr = "Press <Esc> to Exit the Game.";
		strWidth = fm.stringWidth(exitGameStr);
		strX = (this.getWidth() - strWidth)/2;
		strY = strY + 16;
		g2d.drawString(exitGameStr, strX, strY);
	}
	
	/**
	 * Draws the "Level Up" message.
	 */
	private void drawLevelUpMessage() {
		String readyStr = "Level Up!"; //String to be drawn
		//Some drawing properties
		g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 25));
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();
		int strWidth = fm.stringWidth(readyStr);
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2;
		g2d.setPaint(Color.WHITE); //Color
		
		if(System.currentTimeMillis()%400 > 200)
			g2d.drawString(readyStr, strX, strY); //Draw
	}
	
	/**
	 * Draws high score table.
	 */
	private String highScoreName;
	private void drawHighScore() {
		String readyStr = "High Scores"; //String to be drawn
		//Some drawing properties
		g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 25));
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();
		int strWidth = fm.stringWidth(readyStr);
		int strX = (this.getWidth() - strWidth)/2;
		int strY = (this.getHeight() + ascent)/2 - 150;
		g2d.setPaint(Color.YELLOW); //Color
		g2d.drawString(readyStr, strX, strY);
		
		//Draws the scores
		g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 15));
		for(int i = 0; i < 5; i++){
			String name = "" + highScore.getName(i);
			String score = "" + highScore.getScore(i);
			fm = g2d.getFontMetrics();
			ascent = fm.getAscent();
			strWidth = fm.stringWidth(name);
			strX = this.getWidth()/2;
			strY = this.getHeight()/2 -60 + i*30;
			g2d.setPaint(Color.WHITE); //Color
			g2d.drawString(name, strX - strWidth - 25, strY);
			g2d.drawString(score, strX + 25, strY);
		}
		
		if(status.isNewHighScore() && !status.isShowUpdateHighScore()){
			String newHighScoreString = "New High Score!";
			g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 18));
			fm = g2d.getFontMetrics();
			ascent = fm.getAscent();
			strWidth = fm.stringWidth(newHighScoreString);
			strX = (this.getWidth() - strWidth)/2;
			strY = (this.getHeight() + ascent)/2 + 100;
			if(System.currentTimeMillis()%1000 > 800) {
				g2d.setPaint(Color.RED);
			}else if(System.currentTimeMillis()%1000 > 600) {
				g2d.setPaint(Color.YELLOW);
			}else if(System.currentTimeMillis()%1000 > 400) {
				g2d.setPaint(Color.GREEN);
			}else if(System.currentTimeMillis()%1000 > 200) {
				g2d.setPaint(Color.BLUE);
			}else if(System.currentTimeMillis()%1000 > 0) {
				g2d.setPaint(Color.MAGENTA);
			}
			g2d.drawString(newHighScoreString, strX, strY);

			g2d.setPaint(Color.WHITE);
			g2d.drawLine(this.getWidth()/2 - 100, this.getHeight() - 30, this.getWidth()/2 + 100, this.getHeight() - 30);

			g2d.setFont(originalFont.deriveFont(originalFont.getSize2D() + 18));
			fm = g2d.getFontMetrics();
			ascent = fm.getAscent();
			strWidth = fm.stringWidth(highScoreName);
			strX = (this.getWidth() - strWidth)/2;
			strY = (this.getHeight() + ascent)/2 + 150;
			g2d.setPaint(Color.WHITE); //Color
			g2d.drawString(highScoreName, strX, strY);
		}
	}
	
	/**
	 * Creates an item.
	 * @param lastAsteroidX last x coordinate of the asteroid
	 * @param lastAsteroidArrayY last y coordinate of the asteroid
	 * @return true if the item is created or false otherwise
	 */
	private boolean createItem(int lastAsteroidX, int lastAsteroidArrayY) {
		if(status.getWeaponType() != Bullet.LASER_WEAPON && (System.currentTimeMillis() - lastLaserItemTime) > 10000 && rand.nextInt(20) > 15){
			lastLaserItemTime = System.currentTimeMillis();
			item.createLaserItem(lastAsteroidX,lastAsteroidArrayY);
			status.setItemVisible(true);
			return true;
		} else if(status.getWeaponType() != Bullet.SHOTGUN_WEAPON && (System.currentTimeMillis() - lastShotgunItemTime) > 10000 && rand.nextInt(20) > 15){
			lastShotgunItemTime = System.currentTimeMillis();
			item.createShotgunItem(lastAsteroidX,lastAsteroidArrayY);
			status.setItemVisible(true);
			return true;
		} else if((System.currentTimeMillis() - lastBombItemTime) > 15000 && rand.nextInt(20) > 13){
			lastBombItemTime = System.currentTimeMillis();
			item.createBombItem(lastAsteroidX,lastAsteroidArrayY);
			status.setItemVisible(true);
			return true;
		} else if(status.getShipsLeft() < 3 && (System.currentTimeMillis() - lastLifeItemTime) > 12000 && rand.nextInt(10) > 5){
			lastLifeItemTime = System.currentTimeMillis();
			item.createLifeItem(lastAsteroidX,lastAsteroidArrayY);
			status.setItemVisible(true);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Prepare screen for game over.
	 */
	public void doGameOver(){
		shipsValueLabel.setForeground(new Color(128, 0, 0));
	}
	
	/**
	 * Prepare screen for a new game.
	 */
	public void doNewGame(){		
		lastAsteroidTime = -NEW_ASTEROID_DELAY;
		lastShipTime = -NEW_SHIP_DELAY;
				
		bigFont = originalFont;
		biggestFont = null;
				
        // set labels' text
		shipsValueLabel.setForeground(Color.BLACK);
		shipsValueLabel.setText(Integer.toString(status.getShipsLeft()));
		scoreValueLabel.setText(Long.toString(status.getScore()));
		levelValueLabel.setText(Integer.toString(status.getLevel()));
	}

	/**
	 * Sets the game graphics manager.
	 * @param graphicsMan the graphics manager
	 */
	public void setGraphicsMan(GraphicsManager graphicsMan) {
		this.graphicsMan = graphicsMan;
	}

	/**
	 * Sets the game logic handler
	 * @param gameLogic the game logic handler
	 */
	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		this.status = gameLogic.getStatus();
		this.soundMan = gameLogic.getSoundMan();
		this.highScore = gameLogic.getHighScore();
	}

	/**
	 * Sets the label that displays the value for asteroids destroyed.
	 * @param scoreValueLabel the label to set
	 */
	public void setScoreValueLabel(JLabel scoreValueLabel) {
		this.scoreValueLabel = scoreValueLabel;
	}
	
	/**
	 * Sets the label that displays the value for ship (lives) left
	 * @param shipsValueLabel the label to set
	 */
	public void setShipsValueLabel(JLabel shipsValueLabel) {
		this.shipsValueLabel = shipsValueLabel;
	}
	
	/**
	 * Setter for the level value label.
	 * @param levelValueLabel shows current level
	 */
	public void setLevelValueLabel(JLabel levelValueLabel) {
		this.levelValueLabel = levelValueLabel;
	}

	/**
	 * Setter for high score name
	 * @param name player's name
	 */
	public void setHighScoreName(String name) {
		this.highScoreName = name;
		
	}
	
	/**
	 * Getter for high score name
	 * @return a string of the player's name
	 */
	public String getHighScoreName() {
		return highScoreName;
	}
	
	/**
	 * Reset the time of the game. 
	 */
	public void reset() {
		long currentTime = System.currentTimeMillis();
		lastEnemyShipTime = currentTime;
		lastKamikazeShipTime = currentTime;
		
	}
}
