package rbadia.voidspace.main;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * The game's main frame. Contains all the game's labels, file menu, and game screen.
 * @author Daniel Santiago & Nataira Pag‡n 
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private GameScreen gameScreen = null;
	
	//Labels of strings and numbers (values)
	private JLabel scoreLabel;
	private JLabel scoreValueLabel;
	private JLabel shipsLabel;
	private JLabel shipsValueLabel;
	private JLabel levelLabel;
	private JLabel levelValueLabel;
	
	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 */
	private void initialize() {
		this.setSize(550, 480); //Frame size
		
		//Calls the getContent method to construct the frame
		this.setContentPane(getJContentPane());
		
		this.setTitle("Galaga X"); //Title of frame
		this.setResizable(false); //Makes it un-resizable
		
		Dimension dim = this.getToolkit().getScreenSize(); //This gets the PC's screen resolution
		Rectangle bounds = this.getBounds(); //Returns the frame as a rectangle
		this.setLocation( //Sets the frame to be in the center of the screen
			(dim.width - bounds.width) / 2,
			(dim.height - bounds.height) / 2);
	}

	/**
	 * This method initializes jContentPane
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			//Sets some constrains for the components to be added to the frame
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.weightx = 1.D;
			gridBagConstraints6.gridx = 5;
			
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.weightx = 1.D;
			gridBagConstraints5.gridx = 4;
			
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.weightx = 1.D;
			gridBagConstraints4.gridx = 3;
			
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.weightx = 1.D;
			gridBagConstraints3.gridx = 2;
			
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.weightx = 1.D;
			gridBagConstraints2.gridx = 1;
			
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.weightx = 1.D;
			gridBagConstraints1.gridx = 0;
			
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridwidth = 6;
			
			//Construct Labels
			shipsLabel = new JLabel("Ships Left: ");
			shipsValueLabel = new JLabel("3");
			scoreLabel = new JLabel("Score: ");
			scoreValueLabel = new JLabel("0");
			levelLabel = new JLabel("Level: ");
			levelValueLabel = new JLabel("1");
			
			//Constructs JPanel to be returned
			jContentPane = new JPanel(); 
			jContentPane.setLayout(new GridBagLayout());
			//Adds the game screen, it is done so by calling the getGameScreen method
			jContentPane.add(getGameScreen(), gridBagConstraints);
			//Adds the labels
			jContentPane.add(shipsLabel, gridBagConstraints1);
			jContentPane.add(shipsValueLabel, gridBagConstraints2);
			jContentPane.add(levelLabel, gridBagConstraints3);
			jContentPane.add(levelValueLabel, gridBagConstraints4);
			jContentPane.add(scoreLabel, gridBagConstraints5);
			jContentPane.add(scoreValueLabel, gridBagConstraints6);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes gameScreen	
	 * @return GameScreen
	 */
	public GameScreen getGameScreen() {
		if (gameScreen == null) {
			//Crates the GameScreen
			gameScreen = new GameScreen();
			//Pass created JLabel values to the game screen instance fields
			gameScreen.setShipsValueLabel(shipsValueLabel);
			gameScreen.setScoreValueLabel(scoreValueLabel);
			gameScreen.setLevelValueLabel(levelValueLabel);
		}
		return gameScreen;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
