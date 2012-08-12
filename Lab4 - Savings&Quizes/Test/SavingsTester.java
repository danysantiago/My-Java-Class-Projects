import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author juansuris
 *
 */
public class SavingsTester {

	/**
	 * Test method for {@link SavingsAccount#SavingsAccount(double, double)}.
	 */
	@Test
	public void testSavingsAccountDoubleDouble() {
		SavingsAccount sa = new SavingsAccount(100.0, 0.12);
		
		assertTrue(
				sa.getBalance() == 100.0 &&
				sa.getInterest() == 0.12
				);
	}

	/**
	 * Test method for {@link SavingsAccount#SavingsAccount(double)}.
	 */
	@Test
	public void testSavingsAccountDouble() {
		SavingsAccount sa = new SavingsAccount(0.12);
		
		assertTrue(
				sa.getBalance() == 0.0 &&
				sa.getInterest() == 0.12
				);
	}

	/**
	 * Test method for {@link SavingsAccount#deposit(double)}.
	 */
	@Test
	public void testDeposit() {
		SavingsAccount sa = new SavingsAccount(100.0, 0.12);
		
		double bal = sa.deposit(50.50);
		
		assertTrue(
				sa.getBalance() == 150.5 &&
				bal == 150.50 &&
				sa.getInterest() == 0.12
				);
		}

	/**
	 * Test method for {@link SavingsAccount#withdraw(double)}.
	 */
	@Test
	public void testWithdraw() {
		SavingsAccount sa = new SavingsAccount(100.0, 0.12);
		
		double bal = sa.withdraw(50.50);
		
		assertTrue(
				sa.getBalance() == 100.0 - 50.5 &&
				bal == 100.0 - 50.5 &&
				sa.getInterest() == 0.12
				);
		}

	/**
	 * Test method for {@link SavingsAccount#addInterest()}.
	 */
	@Test
	public void testAddInterest() {
		SavingsAccount sa = new SavingsAccount(100.0, 0.12);
		
		double interest = sa.addInterest();
		
		assertTrue(
				sa.getBalance() == 101.0 &&
				interest == 1.0 &&
				sa.getInterest() == 0.12
				);
	}
}
