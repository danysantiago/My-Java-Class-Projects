

/**
 * @author Dany
 *
 */
public class RealNumber {
	
	/**
	 * Instance field to store the number
	 */
	private double number;
	
	/**
	 * Default constructor, sets the number to 0
	 */
	public RealNumber() {
		this.number = 0;
	}

	/**
	 * Constructor based on a double number
	 * @param number
	 */
	public RealNumber(double number) {
		this.number = number;
	}
	
	/**
	 * Constructor based on an object type RealNumber
	 * @param R
	 */
	public RealNumber(RealNumber R) {
		this.number = R.getValue();
	}

	/**
	 * Getter
	 * @return
	 */
	public double getValue() {
		return number;
	}
	
	/**
	 * Absolute value method
	 * @return returns the absolute value of the real number
	 */
	public RealNumber absoluteValue(){
		if(this.number < 0){
			return new RealNumber(-this.number);
		} else {
			return new RealNumber(this.number);
		}
		
	}
	
	/**
	 * Add method
	 * @param R real number to be added
	 * @return a new real number with the sum
	 */
	public RealNumber add(RealNumber R){
		return new RealNumber(this.number + R.getValue());
	}
	
	/**
	 * Subtract method
	 * @param R real number to be subtracted
	 * @return a new real number with the subtraction
	 */
	public RealNumber subtract(RealNumber R){
		return new RealNumber(this.number - R.getValue());
	}
	
	/**
	 * Multiply method
	 * @param R real number to be multiply
	 * @return a new real number with the multiplication
	 */
	public RealNumber multiply(RealNumber R){
		return new RealNumber(this.number * R.getValue());
	}
	
	/**
	 * Divide method
	 * @param R real number to be divided
	 * @return a new real number with the division
	 */
	public RealNumber divide(RealNumber R){
		return new RealNumber(this.number / R.getValue());
	}
	
	/**
	 * Square root method
	 * @return a new real number with the applied square root
	 */
	public RealNumber sqrt(){
		return new RealNumber(Math.sqrt(this.number));
	}
	
	/**
	 * Square method
	 * @return a new real number squared
	 */
	public RealNumber sqr(){
		return new RealNumber(this.number*this.number);
	}
	
	/**
	 * Equal method
	 * @param R real number to be compared with
	 * @return boolean saying if real numbers are the same
	 */
	boolean equals(RealNumber R){
		return (this.number == R.getValue());
	}
	
	/**
	 * Compare method 
	 * @param R real number to be compared with
	 * @return 1 if this number is greater, 0 if they are equal and -1 if this number is smaller
	 */
	int compareTo(RealNumber R){
		if(this.number > R.getValue()){
			return 1;
		} else if (this.number == R.getValue()){
			return 0;
		} else {
			return -1;
		}
	}
	

	/* 
	 * toString method that returns a string with the instance field
	 */
	public String toString(){
		return "" + this.number;
	}
	

	

}
