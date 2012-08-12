package rbadia.voidspace.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the top high scores that will be displayed in a screen when the game isn't running or 
 * by pressing the H-key at the main menu. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class GameHighScore {
	
	
	/**
	 * Instance fields
	 */
	File file;
	private String[] nameArray = new String[5];
	private long[] scoreArray = new long[5];
	
	
	
	/**
	 * Creates the file that contains the scores.
	 */
	public GameHighScore(){
		
		try {
			FileReader reader = new FileReader("HighScoreTable.txt");
			Scanner fileIn = new Scanner(reader);
			System.out.println("HighScore File found, going to read it.");
			try {
				for(int i = 0; i < nameArray.length; i++){
					nameArray[i] = fileIn.nextLine();
				}
				
				for(int i = 0; i < scoreArray.length; i++){
					scoreArray[i] = fileIn.nextLong();
				}
			}catch(InputMismatchException e){
				System.out.println("Error reading found file, going to create a new one");
				
				createNewFile();
			} catch (NoSuchElementException e){
				System.out.println("Error reading found file, going to create a new one");
				
				createNewFile();
			}
			
			System.out.println("Done");
			
		} catch (FileNotFoundException e) {
			System.out.println("HighScore File not found, going to create it.");
			file = new File("HighScoreTable.txt");
			
			createNewFile();
			
		}
	}
	
	/**
	 * Private method used to create a new HighScore file
	 */
	private void createNewFile() {
		file = new File("HighScoreTable.txt");
		
		try {
			PrintWriter fileOut = new PrintWriter(file);
			for(int i = 0; i < nameArray.length; i++){
				nameArray[i] = "---";
				fileOut.println(nameArray[i]);
			}
			for(int i = 0; i < scoreArray.length; i++){
				scoreArray[i] = 0;
				fileOut.println(scoreArray[i]);
			}
			
			fileOut.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Error creating a new file!");
		}
	}

	/**
	 * Insert the scores in the top scores.
	 * @param name the name of the player
	 * @param score the score accumulated
	 */
	public void insertScore(String name, long score){
		if(compareTopScores(score)){
			if(name.length() == 0)
				name = "NoName";
			int index;
	
			for(int i =0; i < nameArray.length; i ++){
				if(score > scoreArray[i]){
					index = i;
					for(int j = nameArray.length-1; j > index; j--){
						scoreArray[j] = scoreArray[j-1];
						nameArray[j] = nameArray[j-1];
					}
					scoreArray[index] = score;
					nameArray[index] = name;
					break;
				}
			}
		}
	}
	
	/**
	 * Compare the score gained once finished the game with the top ones. 
	 * @param score the score accumulated
	 * @return true if the score is bigger than one of the top scores or false otherwise
	 */
	public boolean compareTopScores(long score){
		
		for(long topScore : scoreArray){
			if(score > topScore){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Register the score and name in the file. 
	 */
	public void writeScores(){
		try {
			PrintWriter fileOut = new PrintWriter("HighScoreTable.txt");
			for(int i = 0; i < nameArray.length; i++){
				fileOut.println(nameArray[i]);
			}
			for(int i = 0; i < scoreArray.length; i++){
				fileOut.println(scoreArray[i]);
			}
			
			fileOut.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Error writing to file");
		}
	}
	
	/**
	 * Getter for the name.
	 * @param i a position in the array of names
	 * @return a string corresponding to the position i
	 */
	public String getName(int i){
		return nameArray[i];
	}
	
	/**
	 * Getter for the score. 
	 * @param i a position in the array of scores
	 * @return a long corresponding to the position i
	 */
	public long getScore(int i){
		return scoreArray[i];
	}

}
