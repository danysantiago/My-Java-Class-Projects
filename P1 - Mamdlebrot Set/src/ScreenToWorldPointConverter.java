import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * This class converts from World coordinates to screen coordinates.
 * In Computer Graphics, this done by an affine transformation that 
 * translate from one coordinate system to another system.
 * @author manuel
 *
 */
public class ScreenToWorldPointConverter {
	
	private AffineTransform translate;
	
	/**
	 * Constructor based on the rectangles convering the world coordinates and the screen coordinates.
	 */
	public ScreenToWorldPointConverter(double wXMin, double wYMin, double wXMax, double wYMax, 
			double sXMin, double sYMin, double sXMax, double sYMax) throws NoninvertibleTransformException{
		double m00, m10, m01, m11, m02, m12;
		
		// REDO
		// set up the elements of the matrix
		m00 = (sXMax - sXMin)/(wXMax - wXMin);
		m01 = 0;
		m02 = (-1*wXMin*m00) + sXMin;
		m10 = 0;
		m11 = (sYMax - sYMin)/(wYMax - wYMin);
		m12 = (-1*wYMin*m11) + sYMin;
		// set up the affine transformation
		AffineTransform temp = new AffineTransform(m00, m10, m01, m11, m02, m12);
		// create the translation matrix
		translate = temp.createInverse();
	}
	
	
	/**
	 * Map a point in the screen to a point in the world.
	 * @param screenPoint point in the screen
	 * @return corresponding point in the world.
	 */
	public Point2D getWorldCoordinates(Point2D screenPoint){
		return translate.transform(screenPoint, null);
	}
	
	
}
