
public class CashDenomination {
	//Private static variable to keep count of the number of denominations used so far
	private static int COUNT = 0;
	
	// Label for the denomination
	private final String label;
	
	// Value of the denomination
	private final int value;

	// Index used of the denomination
	private final int index;
	
	/**
	 * Constructor that takes the label of the denomination and 
	 * the value in cents.
	 * @param name The label
	 * @param value The value in cents
	 */
	public CashDenomination(String label, int value) {
		this.label = label;
		this.value = value;
		
		// Assign the index to the denomination and increment
		// so that the next denomination added has the next index.
		this.index = COUNT++;
	}

	/**
	 * Return the label.
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Return the value in cents.
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns the index.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	
}
