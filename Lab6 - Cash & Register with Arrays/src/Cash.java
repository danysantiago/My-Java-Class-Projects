import java.util.ArrayList;

/**
 * Cash class handles money
 * @author 4015_b21
 *
 */

public class Cash {
	
	
	/**
	 * Constant array with the denominations name and values
	 */
	public static final CashDenomination DENOMINATIONS[] = new CashDenomination[]{
		new CashDenomination("ONE_BILL", 100),
		new CashDenomination("QUARTER", 25),
		new CashDenomination("DIME", 10),
		new CashDenomination("NICKEL", 5),
		new CashDenomination("PENNY", 1),
	};
	
	/**
	 * ArrayList of integers that stores the amount of denominations
	 */
	private final ArrayList<Integer> amounts;
	
	/**
	 * Value of the cash in cents
	 */
	private int value = 0;
	
	/**
	 * Default constructor, sets all denominations to 0
	 */
	public Cash(){
		amounts = new ArrayList<Integer>();
		for (int i=0; i < DENOMINATIONS.length; i++){
			amounts.add(0);
		}
	}
	
	/**
	 * Constructor with desired amounts of denomination
	 * @param amounts int array with amount of denominations
	 */
	public Cash(int[] amounts){
		this();
		
		if(amounts != null && amounts.length == DENOMINATIONS.length){
			for(CashDenomination cd: DENOMINATIONS) {
				this.add(cd, amounts[cd.getIndex()]);
			}
		}
	}
	
	/**
	 * Add method for denomination an amount
	 * @param denom denomination to be added
	 * @param amount amount to be added
	 * @return
	 */
	public double add(CashDenomination denom, int amount){
		if(amount > 0 && denom != null){
			int index = denom.getIndex();
			
			amounts.set(index, amounts.get(index) + amount);
			value += amount * denom.getValue();
		}
		return value / 100.0;
	}
	
	/**
	 * Add method for cash
	 * @param c cash to be added
	 * @return returns the new value of the cash
	 */
	public double add(Cash c){
		if (c != null){
			for(CashDenomination cd : DENOMINATIONS)
				this.add(cd, c.getAmount(cd));
		}
		return value / 100.0;
	}
	
	/**
	 * Remove method for denomination and amount
	 * @param denom denomination to be removed
	 * @param amount amount to be removed
	 * @return
	 */
	public int remove(CashDenomination denom, int amount){
		int removed = 0;
		
		if(amount > 0 && denom != null){
			int index = denom.getIndex();
			removed = Math.min(amounts.get(index), amount);
			amounts.set(index, amounts.get(index) - removed);
			value -= amount * denom.getValue();
		}
		
		return removed;
			
	}
	
	/**
	 * Remove method for cash
	 * @param c cash to be removed
	 * @return returns the amount of cash in cents removed
	 */
	public double remove(Cash c){
		if (c != null){
			for(CashDenomination cd : DENOMINATIONS)
				this.remove(cd, c.getAmount(cd));
		}
		return value / 100.0;
	}
	
	/**
	 * Remove method for double amount
	 * @param amount amount in double of the cash to be removed
	 * @return returns the an object cash with the amount removed
	 */
	public Cash remove(double amount){
		Cash c = null;
		
		int toRemove = 0;
		int amountInCents = (int)(amount * 100.0);
		if (amountInCents > 0 && amountInCents <= value){
			c = new Cash();
			
			for(CashDenomination cd : DENOMINATIONS){
				toRemove = amountInCents / cd.getValue();
				c.add(cd, remove(cd, toRemove));
				amountInCents -= toRemove*cd.getValue();
			}
		}
		
		return c;
	}
	
	/**Denomination amount Getter
	 * @param denom denomination to be get
	 * @return amount of such denomination
	 */
	public int getAmount(CashDenomination denom){
		return amounts.get(denom.getIndex());
	}
	
	/**
	 * Value getter
	 * @return returns the value of the cash object
	 */
	public double getValue(){
		return value / 100.0;
	}
	
}
	

