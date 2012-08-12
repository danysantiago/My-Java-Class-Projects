import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author juansuris
 *
 */
public class CashTester {

	/**
	 * Test method for {@link Cash#Cash(int, int, int, int, int)}.
	 */
	@Test
	public void testCashIntIntIntIntInt() {
		Cash c = new Cash(1,1,1,1,1);
		
		assertTrue(
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.41) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#addBills(int)}.
	 */
	@Test
	public void testAddBills() {
		Cash c = new Cash(1,1,1,1,1);
		
		c.addBills(3);
		
		assertTrue(
				c.getBills() == 4 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 4.41) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#addQuarters(int)}.
	 */
	@Test
	public void testAddQuarters() {
		Cash c = new Cash(1,1,1,1,1);
		
		c.addQuarters(3);
		
		assertTrue(
				c.getBills() == 1 &&
				c.getQuarters() == 4 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 2.16) < 1e-8
		);

	}

	/**
	 * Test method for {@link Cash#addDimes(int)}.
	 */
	@Test
	public void testAddDimes() {
		Cash c = new Cash(1,1,1,1,1);
		
		c.addDimes(3);
		
		assertTrue(
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 4 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.71) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#addNickels(int)}.
	 */
	@Test
	public void testAddNickels() {
		Cash c = new Cash(1,1,1,1,1);
		
		c.addNickels(3);
		
		assertTrue(
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 4 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.56) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#addPennies(int)}.
	 */
	@Test
	public void testAddPennies() {
		Cash c = new Cash(1,1,1,1,1);
		
		c.addPennies(3);
		
		assertTrue(
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 4 &&
				Math.abs(c.getValue() - 1.44) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#removeBills(int)}.
	 */
	@Test
	public void testRemoveBills() {
		Cash c = new Cash(2,1,1,1,1);
		
		int b1 = c.removeBills(1);
		int b2 = c.removeBills(2);
		
		assertTrue(
				b1 == 1 &&
				b2 == 1 &&
				c.getBills() == 0 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 0.41) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#removeQuarters(int)}.
	 */
	@Test
	public void testRemoveQuarters() {
		Cash c = new Cash(1,2,1,1,1);
		
		int b1 = c.removeQuarters(1);
		int b2 = c.removeQuarters(2);
		
		assertTrue(
				b1 == 1 &&
				b2 == 1 &&
				c.getBills() == 1 &&
				c.getQuarters() == 0 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.16) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#removeDimes(int)}.
	 */
	@Test
	public void testRemoveDimes() {
		Cash c = new Cash(1,1,2,1,1);
		
		int b1 = c.removeDimes(1);
		int b2 = c.removeDimes(2);
		
		assertTrue(
				b1 == 1 &&
				b2 == 1 &&
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 0 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.31) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#removeNickels(int)}.
	 */
	@Test
	public void testRemoveNickels() {
		Cash c = new Cash(1,1,1,2,1);
		
		int b1 = c.removeNickels(1);
		int b2 = c.removeNickels(2);
		
		assertTrue(
				b1 == 1 &&
				b2 == 1 &&
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 0 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 1.36) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#removePennies(int)}.
	 */
	@Test
	public void testRemovePennies() {
		Cash c = new Cash(1,1,1,1,2);
		
		int b1 = c.removePennies(1);
		int b2 = c.removePennies(2);
		
		assertTrue(
				b1 == 1 &&
				b2 == 1 &&
				c.getBills() == 1 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 1 &&
				c.getPennies() == 0 &&
				Math.abs(c.getValue() - 1.40) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#remove(double)}.
	 */
	@Test
	public void testRemove() {
		Cash c = new Cash(1,1,1,1,1);
		
		Cash c2 = c.remove(1.10);
		
		assertTrue(
				c.getBills() == 0 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 0 &&
				c.getNickels() == 1 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 0.31) < 1e-8 &&
				c2.getBills() == 1 &&
				c2.getQuarters() == 0 &&
				c2.getDimes() == 1 &&
				c2.getNickels() == 0 &&
				c2.getPennies() == 0 &&
				Math.abs(c2.getValue() - 1.10) < 1e-8
		);
	}

	/**
	 * Test method for {@link Cash#add(Cash)}.
	 */
	@Test
	public void testAdd() {
		Cash c = new Cash(1,1,1,1,1);
		Cash c2 = new Cash(1,0,0,1,0);
		
		double value = c.add(c2);
		
		assertTrue(
				c.getBills() == 2 &&
				c.getQuarters() == 1 &&
				c.getDimes() == 1 &&
				c.getNickels() == 2 &&
				c.getPennies() == 1 &&
				Math.abs(c.getValue() - 2.46) < 1e-8 &&
				Math.abs(value - 2.46) < 1e-8
		);
	}
}
