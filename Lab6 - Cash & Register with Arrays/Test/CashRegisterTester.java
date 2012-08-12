import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author juansuris
 *
 */
public class CashRegisterTester {

	/**
	 * Test method for {@link CashRegister#CashRegister(Cash)}.
	 */
	@Test
	public void testCashRegisterCash() {
		CashRegister cr = new CashRegister(new Cash(new int[] {10, 10, 10, 10, 10}));
		
		assertTrue(
				Math.abs(cr.getPayment().getValue()) < 1e-8 &&
				Math.abs(cr.getPurchaseTotal())  < 1e-8 &&
				Math.abs(cr.getRegisterTotal().getValue() - 14.10) < 1e-8
		);
	}

	/**
	 * Test method for {@link CashRegister#CashRegister()}.
	 */
	@Test
	public void testCashRegister() {
		CashRegister cr = new CashRegister();
		
		assertTrue(
				Math.abs(cr.getPayment().getValue()) < 1e-8 &&
				Math.abs(cr.getPurchaseTotal())  < 1e-8 &&
				Math.abs(cr.getRegisterTotal().getValue() - 141.0) < 1e-8
		);
	}

	/**
	 * Test method for {@link CashRegister#purchase(java.lang.String, double)}.
	 */
	@Test
	public void testPurchase() {
		CashRegister cr = new CashRegister();
		
		cr.purchase(2.50);
		cr.purchase(2.75);
		
		assertTrue(
				Math.abs(cr.getPayment().getValue()) < 1e-8 &&
				Math.abs(cr.getPurchaseTotal() - 5.25)  < 1e-8 &&
				Math.abs(cr.getRegisterTotal().getValue() - 141.0) < 1e-8
		);
	}

	/**
	 * Test method for {@link CashRegister#payment(Cash)}.
	 */
	@Test
	public void testPayment() {
		CashRegister cr = new CashRegister();
		
		cr.purchase(2.50);
		cr.purchase(2.75);
	
		cr.payment(new Cash(new int[] {4, 0, 0, 0, 0}));
		cr.payment(new Cash(new int[] {0, 4, 9, 2, 0}));
		
		assertTrue(
				Math.abs(cr.getPayment().getValue() - 6.0) < 1e-8 &&
				Math.abs(cr.getPurchaseTotal() - 5.25)  < 1e-8 &&
				Math.abs(cr.getRegisterTotal().getValue() - 141.0) < 1e-8
		);
	}

	/**
	 * Test method for {@link CashRegister#giveChange()}.
	 */
	@Test
	public void testGiveChange() {
		CashRegister cr = new CashRegister();
		
		cr.purchase(2.50);
		cr.purchase(2.75);
	
		cr.payment(new Cash(new int[] {4, 0, 0, 0, 0}));
		cr.payment(new Cash(new int[] {0, 4, 9, 2, 0}));
	
		Cash c = cr.giveChange();
		Cash r = cr.getRegisterTotal();
		
		assertTrue(
				Math.abs(cr.getPayment().getValue()) < 1e-8 &&
				Math.abs(cr.getPurchaseTotal())  < 1e-8 &&
				Math.abs(r.getValue() - 146.25) < 1e-8 &&
				Math.abs(c.getValue() - 0.75) < 1e-8
		);
	}

}
