import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CashTest {
	private Cash cash;
	
	private double value;
	
	@Before
	public void setUp() throws Exception {
		int[] amounts = new int[Cash.DENOMINATIONS.length];

		this.value = 0;
		for(CashDenomination cd : Cash.DENOMINATIONS) {
		    amounts[cd.getIndex()] = 1;
			value += cd.getValue() / 100.0;	
		}
		
		cash = new Cash(amounts);
	}

	@Test
	public void testCash() {
		Cash c = new Cash();
		
		boolean pass = true;
		for (CashDenomination cd : Cash.DENOMINATIONS) {
			if (c.getAmount(cd) != 0) {
				pass = false;
				break;
			}
		}
		pass = pass && Math.abs(c.getValue() - 0) < 1e-8;
		
		assertTrue(pass);
	}

	@Test
	public void testCashIntArray() {
		boolean pass = true;
	
		for (CashDenomination cd : Cash.DENOMINATIONS) {
			if (cash.getAmount(cd) != 1) {
				pass = false;
				break;
			}
		}
		pass = pass && Math.abs(cash.getValue() - this.value) < 1e-8;
		
		assertTrue(pass);
	}

	@Test
	public void testAddCashDenominationInt() {
		boolean pass = true;
		
		for (CashDenomination cd : Cash.DENOMINATIONS) {
			int amount = cash.getAmount(cd);
			double cvalue = cash.add(cd, 1);
			
			this.value += cd.getValue() / 100.0;
			
			if (Math.abs(cvalue - this.value) > 1e-8 || cash.getAmount(cd) != amount + 1) {
				pass = false;
				break;
			}
		}
		
		assertTrue(pass);
	}

	@Test
	public void testAddCash() {
		int newAmounts[] = new int[Cash.DENOMINATIONS.length];
		int amounts[] = new int[Cash.DENOMINATIONS.length];
		double newValue = this.value;
		int amountToAdd = 2;

		for (CashDenomination cd : Cash.DENOMINATIONS) {
			amounts[cd.getIndex()] = amountToAdd;
			newAmounts[cd.getIndex()] = amountToAdd + cash.getAmount(cd);
			newValue += amountToAdd * cd.getValue() / 100.0;
		}
		
		Cash c = new Cash(amounts);
		double cvalue = cash.add(c);
		
		boolean pass = true;
		for (CashDenomination cd : Cash.DENOMINATIONS) {				
			if (cash.getAmount(cd) != newAmounts[cd.getIndex()]) {
				pass = false;
				break;
			}	
		}
		
		pass = pass && Math.abs(cvalue - newValue) < 1e-8;
		
		assertTrue(pass);
	}

	@Test
	public void testRemoveCashDenominationInt() {
		boolean pass = true;
		
		for (CashDenomination cd : Cash.DENOMINATIONS) {
			int amount = cash.getAmount(cd);
			double cvalue = cash.remove(cd, 1);
			this.value -= cd.getValue() / 100.0;
			
			if (Math.abs(cvalue - this.value) < 1e-8 || cash.getAmount(cd) != amount - 1) {
				pass = false;
				break;
			}
		}
		
		assertTrue(pass);
	}

	@Test
	public void testRemoveDouble() {
		boolean pass = true;
		
		Cash c = cash.remove(0.36);
		this.value -= 0.36;
		if (Math.abs(c.getValue() - 0.36) > 1e-8 || 
			Math.abs(cash.getValue() - this.value) > 1e-8)
			pass = false;
		
		c = cash.remove(this.value + 10);
		if (c != null || Math.abs(cash.getValue() - this.value) > 1e-8)
			pass = false;
		
		c = cash.remove(-1);
		if (c != null || Math.abs(cash.getValue() - this.value) > 1e-8)
			pass = false;
		
		c = cash.remove(4);
		if (c != null || Math.abs(cash.getValue() - this.value) > 1e-8)
			pass = false;
		
		assertTrue(pass);
	}
}
