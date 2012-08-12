import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author juansuris
 *
 */
public class StudentTester {

	/**
	 * Test method for {@link Student#Student(java.lang.String)}.
	 */
	@Test
	public void testStudent() {
		Student s = new Student("Juane");
		
		assertTrue(
				s.getName().compareTo("Juane") == 0 &&
				s.getTotalScore() == 0 &&
				s.getAverageScore() == 0.0
		);
	}

	/**
	 * Test method for {@link Student#addQuiz(int)}.
	 */
	@Test
	public void testAddQuiz() {
		Student s = new Student("Juane");
		
		s.addQuiz(5);
		
		assertTrue(
				s.getName().compareTo("Juane") == 0 &&
				s.getTotalScore() == 5 &&
				s.getAverageScore() == 5.0
		);
	}

	/**
	 * Test method for {@link Student#getTotalScore()}.
	 */
	@Test
	public void testGetTotalScore() {
		Student s = new Student("Juane");
		
		s.addQuiz(5);
		s.addQuiz(20);
		
		assertTrue(
				s.getTotalScore() == 25
		);
	}

	/**
	 * Test method for {@link Student#getAverageScore()}.
	 */
	@Test
	public void testGetAverageScore() {
		Student s = new Student("Juane");
		
		s.addQuiz(5);
		s.addQuiz(25);
		
		assertTrue(
				s.getAverageScore() == 15.0
		);	
	}
}
