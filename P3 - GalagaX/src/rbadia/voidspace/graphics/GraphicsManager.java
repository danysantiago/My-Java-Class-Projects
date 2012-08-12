package rbadia.voidspace.graphics;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.EnemyShip;
import rbadia.voidspace.model.Item;
import rbadia.voidspace.model.KamikazeShip;
import rbadia.voidspace.model.Ship;

/**
 * @author Daniel Santiago & Nataira Pag‡n
 * Manages and draws game graphics and images.
 */
public class GraphicsManager {
	
	private BufferedImage gameTitle;
	
	/**
	 * Instance fields for the ship
	 */
	private BufferedImage shipImg;
	private BufferedImage godShipImg;
	private BufferedImage shipWithBoost1Img;
	private BufferedImage shipWithBoost2Img;
	private BufferedImage shipExplosionImg;
	
	/**
	 * Instance fields for the enemy ship
	 */
	private BufferedImage enemyshipImg;
	private BufferedImage enemyshiprotatedImg;
	private BufferedImage enemyShipExplosionImg;
	
	/**
	 * Instance fields for bullets
	 */
	private BufferedImage bulletImg;
	private BufferedImage laserBulletImg;
	private BufferedImage enemyBulletImg;
	
	/**
	 * Instance fields for asteroids
	 */
	private BufferedImage smallasteroidImg;
	private BufferedImage smallasteroidExplosionImg;
	private BufferedImage asteroidImg;
	private BufferedImage asteroidHitImg;
	private BufferedImage asteroidExplosionImg;
	private BufferedImage diamondAsteroidImg;
	private BufferedImage bigasteroidImg;
	private BufferedImage bigasteroidHitImg;
	private BufferedImage bigasteroidexplosionImg;
	
	/**
	 * Instance fields for kamikaze ship
	 */
	private BufferedImage kamikazeShipImg;
	private BufferedImage kamikazeShipShieldedImg;
	private BufferedImage kamikazeShipExplosionImg;
	
	private BufferedImage laserItemImg;
	private BufferedImage shotgunItemImg;
	private BufferedImage lifeImg;
	private BufferedImage bombImg;
	
	/**
	 * Creates a new graphics manager and loads the game images.
	 */
	public GraphicsManager(){
    	// load images
		try {
			this.gameTitle = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/GameTitle.png"));
			
			this.shipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/ship.png"));
			this.shipWithBoost1Img = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/shipwithBoost.png"));
			this.shipWithBoost2Img = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/shipwithBoost2.png"));
			this.godShipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/godShip.png"));
			this.shipExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/shipExplosion.png"));
			
			this.enemyshipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/enemyship.png"));
			this.enemyshiprotatedImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/enemyshiprotated.png"));
			this.enemyShipExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/enemyShipExplosion.png"));
			
			this.smallasteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/smallasteroid.png"));
			this.smallasteroidExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/smallasteroidExplosion.png"));
			this.asteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroid.png"));
			this.asteroidHitImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroidhit.png"));
			this.asteroidExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/asteroidExplosion.png"));
			this.bigasteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigasteroid.png"));
			this.bigasteroidHitImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigasteroidhit.png"));
			this.bigasteroidexplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bigasteroidExplosion.png"));
			this.diamondAsteroidImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/diamondAsteroid.png"));
			
			
			this.bulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bullet.png"));
			this.enemyBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/enemyBullet.png"));
			this.laserBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/laserBullet.png"));
			
			this.kamikazeShipImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/kamikazeShip.png"));
			this.kamikazeShipShieldedImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/kamikazeShipShielded.png"));
			this.kamikazeShipExplosionImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/kamikazeShipExplosion.png"));
			
			this.laserItemImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/laserItem.png"));
			this.shotgunItemImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/shotgunItem.png"));
			this.lifeImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/1up.png"));
			this.bombImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/bomb.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
					"VoidSpace - Fatal Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Draws the title of the game 
	 * @param rectangle the window that displays
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawGameTitle(Rectangle rectangle, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(gameTitle, rectangle.x, rectangle.y, observer);
	}

	/**
	 * Draws a ship image to the specified graphics canvas.
	 * @param ship the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShip(Ship ship, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipImg, ship.x, ship.y, observer);
	}
	
	/**
	 * Draws the ghost ship that appears after a collision with the ship occurs  
	 * @param ship the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawGodShip(Ship ship, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(godShipImg, ship.x, ship.y, observer);
	}
	
	/**
	 * Draws the first part of the ship once shift-key is pressed, often knows as a boost.
	 * Draws the ship once it is in a boost. 
	 * @param ship the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShipwithBoost1(Ship ship, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipWithBoost1Img, ship.x, ship.y, observer);
	}
	
	/**
	 * Draws the second part of the ship once shift-key is pressed, often knows as a boost.
	 * @param ship the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShipwithBoost2(Ship ship, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipWithBoost2Img, ship.x, ship.y, observer);
	}
	
	/**
	 * Draws the kamikaze ship that attacks.
	 * @param kamikazeShip the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawKamikazeShip(KamikazeShip kamikazeShip, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(kamikazeShipImg, kamikazeShip.x, kamikazeShip.y, observer);
	}
	
	/**
	 * Draws a shield that protects the kamikaze ship once shot by the ship.
	 * @param kamikazeShip the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawKamikazeShipShielded(KamikazeShip kamikazeShip, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(kamikazeShipShieldedImg, kamikazeShip.x-7, kamikazeShip.y-7, observer);
	}
	
	/**
	 * Draws the explosion when a kamizaze ship collides. 
	 * @param kamikazeShipExplosion the explosion to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawKamikazeShipExplosion(Rectangle kamikazeShipExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(kamikazeShipExplosionImg, kamikazeShipExplosion.x, kamikazeShipExplosion.y, observer);
	}
	
	/**
	 * Draws an enemy ship in the right direction. 
	 * @param enemyShip the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyShip(EnemyShip enemyShip, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(enemyshipImg, enemyShip.x, enemyShip.y, observer);
	}
	
	/**
	 * Draws an enemy ship in an inverse direction.
	 * @param enemyShip the ship to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawRotatedEnemyShip(EnemyShip enemyShip, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(enemyshiprotatedImg, enemyShip.x, enemyShip.y, observer);
	}
	
	/**
	 * Draws the explosion when an enemy ship collides. 
	 * @param enemyShipExplosion the explosion to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyShipExplosion(Rectangle enemyShipExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(enemyShipExplosionImg, enemyShipExplosion.x, enemyShipExplosion.y, observer);
	}

	/**
	 * Draws a bullet image to the specified graphics canvas.
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bulletImg, bullet.x, bullet.y, observer);
	}
	
	/**
	 * Draws a laser.
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawLaserBullet(Bullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(laserBulletImg, bullet.x, bullet.y, observer);
	}
	
	/**
	 * Draws the bullets shots by the enemy ship. 
	 * @param bullet the bullet to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawEnemyBullet(EnemyBullet bullet, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(enemyBulletImg, bullet.x, bullet.y, observer);
	}
	/**
	 * Draws small asteroids.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawSmallAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(smallasteroidImg, asteroid.x, asteroid.y, observer);
	}
	
	/**
	 * Draws the explosion for when a small asteroid collides. 
	 * @param asteroidExplosion the explosion to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawSmallAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(smallasteroidExplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}

	/**
	 * Draws an asteroid image to the specified graphics canvas.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidImg, asteroid.x, asteroid.y, observer);
	}
	
	/**
	 * Draws when the asteroid is hit. It turns red. 
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroidHit(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidHitImg, asteroid.x, asteroid.y, observer);
	}
	
	/**
	 * Draws big asteroids.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBigAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigasteroidImg, asteroid.x, asteroid.y, observer);
	}
	
	/**
	 * Draws when the asteroid is hit. It turns red.
	 * @param asteroid the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBigAsteroidHit(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigasteroidHitImg, asteroid.x, asteroid.y, observer);
	}
	
	/**
	 * Draws the explosion when a big asteroid collides. 
	 * @param asteroidExplosion the asteroid to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBigAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bigasteroidexplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}
	
	/**
	 * Draws an indestructible metal.
	 * @param asteroid the metal to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawDiamondAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(diamondAsteroidImg, asteroid.x, asteroid.y, observer);
	}

	/**
	 * Draws a ship explosion image to the specified graphics canvas.
	 * @param shipExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShipExplosion(Rectangle shipExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shipExplosionImg, shipExplosion.x, shipExplosion.y, observer);
	}

	/**
	 * Draws an asteroid explosion image to the specified graphics canvas.
	 * @param asteroidExplosion the bounding rectangle of the explosion
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawAsteroidExplosion(Rectangle asteroidExplosion, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(asteroidExplosionImg, asteroidExplosion.x, asteroidExplosion.y, observer);
	}
	
	/**
	 * Draws the icon for the laser gun.
	 * @param item the icon to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawLaserItem(Item item, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(laserItemImg, item.x, item.y, observer);
	}
	
	/**
	 * Draws the icon for the one-up life.
	 * @param item the icon to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawLifeItem(Item item, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(lifeImg, item.x, item.y, observer);
	}
	
	/**
	 * Draws the icon that increases the number of bullets from one to three. 
	 * @param item the icon to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawShotgunItem(Item item, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(shotgunItemImg, item.x, item.y, observer);
	}
	
	/**
	 * Draws the icon that clear the screen.
	 * @param item the icon to draw
	 * @param g2d the graphics canvas
	 * @param observer object to be notified
	 */
	public void drawBombItem(Item item, Graphics2D g2d, ImageObserver observer) {
		g2d.drawImage(bombImg, item.x, item.y, observer);
	}
}
