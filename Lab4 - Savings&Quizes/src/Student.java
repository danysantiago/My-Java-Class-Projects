
public class Student {
	
	private String name;
	private long score;
	private long quizAmount;
	private double averageScore;

	public Student(String name) {
		this(name, 0);
	}

	
	public Student(String name, long score) {
		this.name = name;
		this.score = score;
		this.quizAmount = 0;
	}


	public String getName() {
		return name;
	}
	
	public long getTotalScore() {
		return score;
	}
	
	public void addQuiz(int score){
		this.score += score;
		quizAmount++;
		averageScore = this.score/quizAmount;
	}
	
	public double getAverageScore(){
		return averageScore;
	}
	
	

}
