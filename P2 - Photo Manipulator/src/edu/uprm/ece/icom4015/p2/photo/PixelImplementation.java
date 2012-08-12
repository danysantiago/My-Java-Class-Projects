package edu.uprm.ece.icom4015.p2.photo;

public class PixelImplementation implements Pixel {
	
	//Instance fields
	private int red;
	private int green;
	private int blue;
	
	/**
	 * Constructor based on the 3 instance fields
	 * @param red
	 * @param green
	 * @param blue
	 */
	
	public PixelImplementation(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 *Getter
	 *@return red
	 */
	@Override
	public int getRed() {
		return red;
	}

	/**
	 *Getter
	 *@return blue
	 */
	@Override
	public int getBlue() {
		return blue;
	}

	/**
	 *Getter
	 *@return green
	 */
	@Override
	public int getGreen() {
		return green;
	}

	/**
	 * Get the RGB color combination for this pixel.
	 * @return
	 */
	@Override
	public int getRGB() {
		int rgb;
		rgb = red;
		rgb = rgb << 8;
		rgb = rgb | green;
		rgb = rgb << 8;
		rgb = rgb | blue;
		
		return rgb;
	}

	/**
	 * Creates an independent  copy of this pixel.
	 * @return a new independet copy of the pixel
	 */
	@Override
	public Pixel copy() {
		return new PixelImplementation(red, green, blue);
	}
	
	/**
	 * Returns the pixel as a string of the format: (R, G, B). For example: (3, 12, 0).
	 * @return a string representation of the pixel with the format: (R, G, B).
	 */
	public String toString(){
		return "(" + red + "," + green + "," + blue + ")";
	}

	/**
	 * Creates a new Pixel from an int representing the RGB value.
	 * @param rgb - the RGB value
	 * @return a new pixel of based on the rgb value.
	 */
	@Override
	public Pixel fromRGB(int rgb) {
		int red = (rgb & 0xff0000) >> 16;
		int green = (rgb & 0xff00) >> 8;
		int blue = (rgb & 0xff);
		return new PixelImplementation(red, green, blue);
	}
	
	/**
	 * Returns true if the argument is a pixel equal to this pixel.
	 * @param obj parameter pixel for comparison
	 * @return true if both pixel have the same RBG values, or false otherwise.
	 */
	public boolean equals(Object obj){
		if (obj instanceof PixelImplementation){
			PixelImplementation pixel = (PixelImplementation) obj;
			if(red == pixel.getRed() && green == pixel.getGreen() && blue == pixel.getBlue()){
				return true;
			}
		}
		
		return false;
	}

}
