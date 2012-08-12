
public class LetterCountExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	//Initialize Class
	LetterCounter a = new LetterCounter();
	//Sets letter to be compared
	a.Letter = 'a';
	//Prints the comparison 
	System.out.printf("Times the letter '%c' is repeated in the string is: %s", a.Letter, a.doCount("abracadabra") );

	}

}
