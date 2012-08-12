import java.awt.Rectangle;


public class IntersectionPrinter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Constructs Rectangles
		Rectangle r1 = new Rectangle (0,0,10,15);
		Rectangle r2 = new Rectangle (5,10,5,8);
		Rectangle r3 = new Rectangle (12,15,10,10);
		
		//Prints rectangles position, width and height
		System.out.println("r1 = " + r1.toString());
		System.out.println("r2 = " + r2.toString());
		System.out.println("r3 = " + r3.toString());
		
		//Check for intersection between rectangles, if so prints .intersection()
		if (r1.intersects(r2) == true){
		System.out.println("The intersection of r1 and r2 is the rectangle: " + r1.intersection(r2).toString());
		} else {
			System.out.println("Rectangle r1 andf r2 do NOT intersect");
		}
		
		if (r1.intersects(r3) == true){
		System.out.println("The intersection of r1 and r3 is the rectangle: " + r1.intersection(r3).toString());
		} else {
			System.out.println("Rectangle r1 andf r3 do NOT intersect");
		}
		
	}
		

}
