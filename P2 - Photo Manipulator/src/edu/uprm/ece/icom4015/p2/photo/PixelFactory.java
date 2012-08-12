package edu.uprm.ece.icom4015.p2.photo;

public class PixelFactory {
	/**
	 * Returns a new pixel set to RGB (0,0,0)
	 * @return
	 */
	public static Pixel newInstance(){
		return new PixelImplementation(0,0,0);
	}

}
