import java.awt.Rectangle;


public class AreaTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Constructs rectangle
		Rectangle r1 = new Rectangle(0,0,60,45);
		
		//Prints expected area and calculated area of rectangle
		System.out.println("The area of the rectangle r1 with width 60 and height 45 is suppose to be 2700.");
		System.out.printf("The calculation of area of the rectangle r1 is: %f\n", r1.getWidth()*r1.getHeight());

	}

}
