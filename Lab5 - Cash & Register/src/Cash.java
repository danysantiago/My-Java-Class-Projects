/**
 * Cash class handles money
 * @author 4015_b21
 *
 */

public class Cash {
	
	/**
	 * Constants representing the value of the money
	 */
	
	public static final int ONE_BILL_VALUE = 100;
	public static final int QUARTER_VALUE = 25;
	public static final int DIME_VALUE = 10;
	public static final int NICKEL_VALUE = 5;
	public static final int PENNY_VALUE = 1;
	
	/**
	 * Variables which store the amount of each money
	 */
	
	private int bills = 0;
	private int quarters = 0;
	private int dimes = 0;
	private int nickels = 0;
	private int pennies = 0;
	
	/**
	 * Variable that stores the amount of money in pennies
	 */
	
	private int value = 0;
	
	/**
	 * Default constructor, every amount of money is 0
	 */
	public Cash(){
	}

	/**
	 * Constructor specifying amounts of money
	 * @param bills amount of bills to be added
	 * @param quarters amount of quarters to be added
	 * @param dimes amount of dimes to be added
	 * @param nickels amount of nickels to be added
	 * @param pennies amount of pennies to be added
	 */
	public Cash(int bills, int quarters, int dimes, int nickels, int pennies){
		addBills(bills);
		addQuarters(quarters);
		addDimes(dimes);
		addNickels(nickels);
		addPennies(pennies);
	}
	
	/**Getter
	 * @return the bills
	 */
	public int getBills() {
		return bills;
	}

	/**Getter
	 * @return the quarters
	 */
	public int getQuarters() {
		return quarters;
	}

	/**Getter
	 * @return the dimes
	 */
	public int getDimes() {
		return dimes;
	}

	/**Getter
	 * @return the nickels
	 */
	public int getNickels() {
		return nickels;
	}

	/**Getter
	 * @return the pennies
	 */
	public int getPennies() {
		return pennies;
	}
	
	/**Getter
	 * @return value
	 */
	public double getValue(){
		return value/100.0;
	}
	
	/**Adds amount of bills and value
	 * @param amount bills to be added
	 * @return value
	 */
	public double addBills(int amount){
		if(amount > 0){
			bills += amount;
			value += amount*ONE_BILL_VALUE;
		}
		return value/100.0;
	}
	
	/**Adds amount of quarters and value
	 * @param amount quarters to be added
	 * @return value
	 */
	public double addQuarters(int amount){
		if(amount > 0){
			quarters += amount;
			value += amount*QUARTER_VALUE;
		}
		return value/100.0;
		
	}
	
	/**Adds amount of dimes and value
	 * @param amount dimes to be added
	 * @return value
	 */
	public double addDimes(int amount){
		if(amount > 0){
			dimes += amount;
			value += amount*DIME_VALUE;
		}
		return value/100.0;
	}
	
	/**Adds amount of nickels and value
	 * @param amount nickels to be added
	 * @return value
	 */
	public double addNickels(int amount){
		if(amount > 0){
			nickels += amount;
			value += amount*NICKEL_VALUE;
		}
		return value/100.0;
	}
	
	/**Adds amount of pennies and value
	 * @param amount pennies to be added
	 * @return value
	 */
	public double addPennies(int amount){
		if(amount > 0){
			pennies += amount;
			value += amount*PENNY_VALUE;
		}
		return value/100.0;
	}
	
	/**Removes amount of bills and value
	 * @param amount bills to be removed
	 * @return bills removed
	 */
	public int removeBills(int amount){
		int removed = 0;
		
		if(amount > 0){
			removed = Math.min(amount, bills);
			bills -= removed;
			value -= removed*ONE_BILL_VALUE;
		}
		return removed;
	}
	
	/**Removes amount of quarters and value
	 * @param amount quarters to be removed
	 * @return quarters removed
	 */
	public int removeQuarters(int amount){
		int removed = 0;
		
		if(amount > 0){
			removed = Math.min(amount, quarters);
			quarters -= removed;
			value -= removed*QUARTER_VALUE;
		}
		return removed;
	}
	
	/**Removes amount of dimes and value
	 * @param amount dimes to be removed
	 * @return dimes removed
	 */
	public int removeDimes(int amount){
		int removed = 0;
		
		if(amount > 0){
			removed = Math.min(amount, dimes);
			dimes -= removed;
			value -= removed*DIME_VALUE;
		}
		return removed;
	}
	
	/**Removes amount of nickels and value
	 * @param amount nickels to be removed
	 * @return nickels removed
	 */
	public int removeNickels(int amount){
		int removed = 0;
		
		if(amount > 0){
			removed = Math.min(amount, nickels);
			nickels -= removed;
			value -= removed*NICKEL_VALUE;
		}
		return removed;
	}
	
	/**Removes amount of pennies and value
	 * @param amount pennies to be removed
	 * @return pennies removed
	 */
	public int removePennies(int amount){
		int removed = 0;
		
		if(amount > 0){
			removed = Math.min(amount, pennies);
			pennies -= removed;
			value -= removed*PENNY_VALUE;
		}
		return removed;
	}
	
	/**Adds cash
	 * @param c the object Cash to be added
	 * @return value
	 */
	public double add(Cash c){
		this.addBills(c.getBills());
		this.addQuarters(c.getQuarters());
		this.addDimes(c.getDimes());
		this.addNickels(c.getNickels());
		this.addPennies(c.getPennies());
		
		return value/100.0;
	}
	
	/**Removes a value of cash
	 * @param amount value of cash to be removed
	 * @return object Cash with the amount of money an value
	 */
	public Cash remove(double amount){
		Cash c = null;
		
		int toRemove = 0;
		int amountInCents = (int) (amount * 100.0);
		if (amountInCents > 0 && amountInCents <= value){
			c = new Cash();
			
			toRemove = amountInCents / ONE_BILL_VALUE;
			c.addBills(removeBills(toRemove));
			amountInCents -= c.getBills()*ONE_BILL_VALUE;
			
			toRemove = amountInCents / QUARTER_VALUE;
			c.addQuarters(removeQuarters(toRemove));
			amountInCents -= c.getQuarters()*QUARTER_VALUE;
			
			toRemove = amountInCents / DIME_VALUE;
			c.addDimes(removeDimes(toRemove));
			amountInCents -= c.getDimes()*DIME_VALUE;
			
			toRemove = amountInCents / NICKEL_VALUE;
			c.addNickels(removeNickels(toRemove));
			amountInCents -= c.getNickels()*NICKEL_VALUE;
			
			toRemove = amountInCents / PENNY_VALUE;
			c.addPennies(removePennies(toRemove));
			amountInCents -= c.getPennies()*PENNY_VALUE;
		}
		
		return c;
	}
	
	
}
