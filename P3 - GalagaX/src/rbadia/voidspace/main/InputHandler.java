package rbadia.voidspace.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Ship;

/**
 * Handles user input events.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class InputHandler implements KeyListener{
	/**
	 * Boolean conditions for pressed keys 
	 */
	private boolean leftIsPressed;
	private boolean rightIsPressed;
	private boolean downIsPressed;
	private boolean upIsPressed;
	private boolean spaceIsPressed;
	private boolean shiftIsPressed;
	private boolean backspacePressed;
	private boolean enterPressed;
	
	private long lastBulletTime;
	
	private GameLogic gameLogic;
	
	/**
	 * Create a new input handler
	 * @param gameLogic the game logic handler
	 */
	public InputHandler(GameLogic gameLogic){
		this.gameLogic = gameLogic;
	}
	
	/**
	 * Handle user input after screen update.
	 * @param gameScreen he game screen
	 */
	public void handleInput(GameScreen gameScreen){
		GameStatus status = gameLogic.getStatus();
		if(!status.isGameOver() && !status.isNewShip() && !status.isGameStarting() &&status.isGameStarted()){
			// fire bullet if space is pressed
			if(spaceIsPressed && !status.isGodMode()){
				// fire only up to 5 bullets per second
				long currentTime = System.currentTimeMillis();
				if(status.getWeaponType() == Bullet.LASER_WEAPON){
					if((currentTime - lastBulletTime) > 1000/4){
						lastBulletTime = currentTime;
						gameLogic.fireBullet();
					}
				} else if(status.getWeaponType() == Bullet.SHOTGUN_WEAPON){
					if((currentTime - lastBulletTime) > 1000/3){
						lastBulletTime = currentTime;
						gameLogic.fireBullet();
					}
				} else {
					if((currentTime - lastBulletTime) > 1000/5){
						lastBulletTime = currentTime;
						gameLogic.fireBullet();
					}
				}
			}
			
			//Get ship for movement keys
			Ship ship = gameLogic.getShip();
			
			//Turbo
			if(shiftIsPressed && !status.isGodMode() && status.isGameStarted() && !status.isGameOver() && !status.isGameStarting()){
				ship.setSpeed(ship.getDefaultSpeed());
				ship.setSpeed(ship.getDefaultSpeed() * 2);
			} else {
				status.setBoosterMode(false);
				if(ship != null)
				ship.setSpeed(ship.getDefaultSpeed());
			}

			if(upIsPressed){
				moveShipUp(ship);
			}

			if(downIsPressed){
				moveShipDown(ship, gameScreen.getHeight());
			}

			if(leftIsPressed){
				moveShipLeft(ship);
			}

			if(rightIsPressed){
				moveShipRight(ship, gameScreen.getWidth());
			}	
			
		}
	}

	/**
	 * Move the ship up
	 * @param ship the ship
	 */
	private void moveShipUp(Ship ship){
		if(ship.getY() - ship.getSpeed() >= 0){
			ship.translate(0, -ship.getSpeed());
		}
	}

	/**
	 * Move the ship down
	 * @param ship the ship
	 */
	private void moveShipDown(Ship ship, int screenHeight){
		if(ship.getY() + ship.getSpeed() + ship.height < screenHeight){
			ship.translate(0, ship.getSpeed());
		}
	}
	
	/**
	 * Move the ship left
	 * @param ship the ship
	 */
	private void moveShipLeft(Ship ship){
		if(ship.getX() - ship.getSpeed() >= 0){
			ship.translate(-ship.getSpeed(), 0);
		}
	}
	
	/**
	 * Move the ship right
	 * @param ship the ship
	 */
	private void moveShipRight(Ship ship, int screenWidth){
		if(ship.getX() + ship.getSpeed() + ship.width < screenWidth){
			ship.translate(ship.getSpeed(), 0);
		}
	}
	
	/**
	 * Handle a key input event. By setting the boolean related key as pressed (true)
	 */
	public void keyPressed(KeyEvent e) {
		GameStatus status = gameLogic.getStatus();
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			this.upIsPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			this.downIsPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			this.leftIsPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			this.rightIsPressed = true;
			break;
		case KeyEvent.VK_SPACE: //Space have multiple actions depending of game state
			if(!status.isGameStarted() && !status.isGameOver() && !status.isGameStarting() && !status.isShowHighScore()){
				// new game
				lastBulletTime = System.currentTimeMillis();
				leftIsPressed = false;
				rightIsPressed = false;
				downIsPressed = false;
				upIsPressed = false;
				spaceIsPressed = false;
				
				gameLogic.newGame();
			}
			else if (!status.isGameOver() && status.isShowHighScore() && status.isNewHighScore() && !status.isShowUpdateHighScore()){
				gameLogic.updateHighScore();
			} else {
				spaceIsPressed = true;
			}
			break;
		case KeyEvent.VK_SHIFT:
			this.shiftIsPressed = true;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
		case KeyEvent.VK_BACK_SPACE:
			this.backspacePressed = true;
			break;
		case KeyEvent.VK_ENTER:
			if (!status.isGameOver() && status.isShowHighScore() && status.isNewHighScore() && !status.isShowUpdateHighScore()){
				gameLogic.updateHighScore();
			} else {
				this.enterPressed = true;
			}
			break;
		case KeyEvent.VK_H:
			if(!status.isGameStarted() && !status.isGameStarting() && !status.isGameOver() && !status.isShowHighScore() && !status.isShowUpdateHighScore()){
				status.setShowHighScore(true);
				status.setShowUpdateHighScore(true);
				status.setNewHighScore(false);
			}
		}
		
		e.consume();
	}

	/**
	 * Handle a key release event. By setting the boolean related key as un-pressed (false)
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			this.upIsPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			this.downIsPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			this.leftIsPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			this.rightIsPressed = false;
			break;
		case KeyEvent.VK_SPACE:
			this.spaceIsPressed = false;
			break;
		case KeyEvent.VK_SHIFT:
			this.shiftIsPressed = false;
			break;
		case KeyEvent.VK_BACK_SPACE:
			this.backspacePressed = false;
			break;
		case KeyEvent.VK_ENTER:
			this.enterPressed = false;
			break;
		}

		
		e.consume();
	}
	
	/**
	 * For when the player enter his or her name if a high score is done. 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 * @param e the key event
	 */
	public static char lastChar;
	public void keyTyped(KeyEvent e) {
		GameStatus status = gameLogic.getStatus();
		GameScreen screen = gameLogic.getGameScreen();
		
		if (backspacePressed && status.isShowHighScore() && status.isNewHighScore()){
			String hsName = screen.getHighScoreName();
			if(hsName.length() != 0){
				screen.setHighScoreName(hsName.substring(hsName.length()));
			}
		} else if(status.isShowHighScore() && status.isNewHighScore()){
			String name = screen.getHighScoreName();
			if(name.length() < 8)
			screen.setHighScoreName(name.concat(Character.toString(e.getKeyChar())));
		}

		e.consume();
	}
}
