import java.awt.Color;


/**
 * @author Dany
 *
 */
public class Mandelbrot {
	
	/**
	 * Divergence test method to check if complex number C diverges in the Mandelbrot Sequence
	 * if not they belong to the Mandelbrot Set
	 * @param C complex number to be tested
	 * @return boolean saying if complex number diverges
	 */
	public static boolean diverges(ComplexNumber C){
		ComplexNumber ithNumber = (C.multiply(C)).add(C);
		for(int i = 1; i <= 256; i++){
			if (ithNumber.norm().getValue() < 2){
			ithNumber = (ithNumber.multiply(ithNumber)).add(C);
			} else {
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Divergence index getter method, if the complex number C does diverges then this method calculates
	 * at which iteration it diverges
	 * @param C complex number to calculate its divergence index
	 * @return
	 */
	public static int divergenceIndex(ComplexNumber C){
		ComplexNumber ithNumber = (C.multiply(C)).add(C);
		for(int i = 1; i <= 256; i++){
			if (ithNumber.norm().getValue() < 2){
				ithNumber = (ithNumber.multiply(ithNumber)).add(C);
			} else {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Color getter method to get which color will the point of a complex number be painted, that is
	 * if the complex number diverges in the Mandelbrot sequence
	 * @param colorIndex
	 * @return
	 */

	public static Color getColor(int colorIndex){
		
		//Constant array of Colors holding the available colors to paint the points
		final Color[] COLOR_ARRAY = {
			Color.blue.brighter(),
			Color.blue,
			Color.cyan,
			Color.green.brighter(),
			Color.green,
			Color.yellow.brighter(),
			Color.yellow,
			Color.orange.brighter(),
			Color.orange,
			Color.pink,
			Color.pink.darker(),
			Color.magenta,
			Color.red.brighter(),
			Color.red
			};
		
		if (colorIndex == -1){
			return Color.black;
		} else {
			return COLOR_ARRAY[colorIndex%14];
		}
	}
}
