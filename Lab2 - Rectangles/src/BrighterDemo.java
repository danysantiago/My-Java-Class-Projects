import java.awt.Color;


public class BrighterDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Constructs Color
		Color c1 = new Color (50, 100, 150);
		//Apply .brighter() method and prints new color
		System.out.println("c1.brighter: " + c1.brighter().toString());

	}

}
