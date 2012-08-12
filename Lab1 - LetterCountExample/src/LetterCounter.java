
public class LetterCounter {
	
	
	public char Letter;
	
	//Constructor
	public LetterCounter(){
		
	}
	
	//Method to count letters, 
	//returns an int with the numbers of times repeated
	public int doCount(String s){
		int count = 0;
		int i;
		for(i = 0; i < s.length(); i++){
			if ( s.charAt(i) == Letter )
				count++;
		}
		
		return count;
		
	}

}
