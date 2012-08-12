package edu.uprm.ece.icom4015.p2.photo;

public interface Pixel {

	/**
	 * Returns the red color component. 
	 * @return the red color component.
	 */
	public int getRed();

	/**
	 * Returns the blue color component. 
	 * @return the blue color component.
	 */

	public int getBlue();

	/**
	 * Returns the green color component. 
	 * @return the green color component.
	 */
	public int getGreen();

	/**
	 * Get the RGB color combination for this pixel.
	 * @return
	 */
	public int getRGB();
	
	/**
	 * Returns true if the argument is a pixel equal to this pixel.
	 * @param obj parameter pixel for comparison
	 * @return true if both pixel have the same RBG values, or false otherwise.
	 */

	public boolean equals(Object obj);

	/**
	 * Returns the pixel as a string of the format: (R, G, B). For example: (3, 12, 0).
	 * @return a string representation of the pixel with the format: (R, G, B).
	 */
	public String toString();

	/**
	 * Creates an independent  copy of this pixel.
	 * @return a new independet copy of the pixel
	 */
	public Pixel copy();
	
	/**
	 * Creates a new Pixel from an int representing the RGB value.
	 * @param rgb - the RGB value
	 * @return a new pixel of based on the rgb value.
	 */
	public Pixel fromRGB(int rgb);

}