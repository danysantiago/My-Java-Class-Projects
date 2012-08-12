import static org.junit.Assert.*;

import org.junit.Test;


public class LetterCounterTest {

	@Test
	public void testDoCount() {
		
		LetterCounter a = new LetterCounter();
		a.Letter = 'a';
		assertTrue(a.doCount("abracadabra") == 5);
	}

}
