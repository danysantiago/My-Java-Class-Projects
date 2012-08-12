
/**
 * @author Dany
 *
 */
public class ComplexNumber {
	
	
	/**
	 * Instance fields based on the objects type RealNumber that will
	 * store the real part and the imaginary part of the complex number
	 */
	private RealNumber realPart;
	private RealNumber imaginaryPart;
	

	/**
	 * Constructor based on real numbers of the real and imaginary part
	 * @param realPart
	 * @param imaginaryPart
	 */
	public ComplexNumber(RealNumber realPart, RealNumber imaginaryPart) {
		super();
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}

	/**
	 * Default constructor that sets both the imaginary and the real part to 0
	 */
	public ComplexNumber() {
		this.realPart = new RealNumber(0);
		this.imaginaryPart = new RealNumber(0);
	}

	/**Getter
	 * @return real part
	 */
	public RealNumber getRealPart() {
		return realPart;
	}

	/**Getter
	 * @return imaginary part
	 */
	public RealNumber getImagnaryPart() {
		return imaginaryPart;
	}
	
	/**
	 * Add method
	 * @param C complex number to be added
	 * @return a new complex number with the addition
	 */
	public ComplexNumber add(ComplexNumber C){
		return new ComplexNumber(this.realPart.add(C.getRealPart()),this.imaginaryPart.add(C.getImagnaryPart()));
	}
	
	/**
	 * Subtract method
	 * @param C complex number to be subtracted
	 * @return a new complex number with the subtraction
	 */
	public ComplexNumber subtract(ComplexNumber C){
		return new ComplexNumber(this.realPart.subtract(C.getRealPart()), this.imaginaryPart.subtract(C.getImagnaryPart()));
	}
	
	/**
	 * Multiply method
	 * @param C complex number to be multiply
	 * @return a new complex number with the multiplication
	 */
	public ComplexNumber multiply(ComplexNumber C){
		RealNumber newRealPart = (this.realPart.multiply(C.getRealPart())).subtract(C.getImagnaryPart().multiply(this.imaginaryPart));
		RealNumber newImaginaryPart = (this.realPart.multiply(C.getImagnaryPart())).add(C.getRealPart().multiply(this.imaginaryPart));
		return new ComplexNumber(newRealPart, newImaginaryPart);
	}
	
	/**
	 * Divide method
	 * @param C complex number to be divided
	 * @return a new complex number with the division
	 */
	public ComplexNumber divide(ComplexNumber C){
		RealNumber denominator = (C.getRealPart().sqr()).add(C.getImagnaryPart().sqr());
		RealNumber newRealPart = (this.realPart.multiply(C.getRealPart())).add(this.imaginaryPart.multiply(C.getImagnaryPart()));
		RealNumber newImaginaryPart = (this.imaginaryPart.multiply(C.getRealPart())).subtract(this.realPart.multiply(C.getImagnaryPart()));
		
		return new ComplexNumber(newRealPart.divide(denominator), newImaginaryPart.divide(denominator));
	}
	
	/**
	 * Norm method
	 * @return a new real number with the norm applied
	 */
	public RealNumber norm(){
		return (this.realPart.sqr().add(this.imaginaryPart.sqr())).sqrt();
	}
	
	/* 
	 * toString method that returns a string with the instance field
	 */
	public String toString(){
		return "" + this.realPart + " + " + this.imaginaryPart + "i";
	}
	
	/**
	 * Equal method
	 * @param C complex number to be compared with
	 * @return boolean saying if complex number are the same
	 */
	public boolean equals(ComplexNumber C){
		return (this.getRealPart().equals(C.getRealPart()) && this.imaginaryPart.equals(C.getImagnaryPart()));
	}
}
