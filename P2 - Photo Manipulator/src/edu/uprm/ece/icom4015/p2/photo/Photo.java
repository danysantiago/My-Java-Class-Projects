package edu.uprm.ece.icom4015.p2.photo;

public interface Photo {
	
	/**
	 * Returns the pixel at position (x,y) in the photo.
	 * @param x - horizontal coordinate
	 * @param y - vertical coordinate
	 * @return the pixel at position (x,y) in the photo
	 */
	Pixel getPixel(int x, int y);
	
	/**
	 * Sets the pixel at position (x,y)
	 * @param x - horizontal coordinate
	 * @param y - vertical coordinate
	 * @param P - the pixel to st
	 */
	void setPixel(int x, int y, Pixel P);

	/**
	 * Returns the width of the image.
	 * @return width of the image.
	 */
	int getWidth();
	
	/**
	 * Returns the height of the image.
	 * @return height of the image.
	 */
	int getHeight();
	
}
