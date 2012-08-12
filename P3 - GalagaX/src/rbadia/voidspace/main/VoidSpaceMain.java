package rbadia.voidspace.main;

import javax.swing.JFrame;

/**
 * Main game class. Starts the game.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class VoidSpaceMain {

	public static void main(String[] args) {
		//Initialize main frame
		MainFrame frame = new MainFrame();
		
		//Set to Exit on frame close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Get game screen (from initialized frame)
        GameScreen gameScreen = frame.getGameScreen();
		
		//Initialize game logic handler
		GameLogic gameLogic = new GameLogic(gameScreen);
		
		//Pass some variables to game screen
        gameScreen.setGameLogic(gameLogic);
        
		//Initialize input handler
        InputHandler inputHandler = new InputHandler(gameLogic);
        //Sets a input listener to the frame
        frame.addKeyListener(inputHandler);
        
        //Show main frame
		frame.setVisible(true);
		
		//Initialize main game loop in a new thread
		new Thread(new GameLoop(gameScreen, gameLogic, inputHandler)).start();
	}


}
