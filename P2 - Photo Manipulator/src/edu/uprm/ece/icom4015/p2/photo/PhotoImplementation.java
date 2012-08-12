package edu.uprm.ece.icom4015.p2.photo;

public class PhotoImplementation implements Photo {
	
	//Instance fields
	private int width;
	private int height;
	private Pixel[][] pixelMatrix;
	
	
	/**
	 * Constructor based on width & height
	 * @param width
	 * @param height
	 */
	public PhotoImplementation(int width, int height) {
		this.width = width;
		this.height = height;
		
		if(width > 0 && height > 0){
			pixelMatrix = new Pixel[width][height];
		}
	}
	
	/**
	 * Returns the pixel at position (x,y) in the photo.
	 * @param x - horizontal coordinate
	 * @param y - vertical coordinate
	 * @return the pixel at position (x,y) in the photo
	 */
	@Override
	public Pixel getPixel(int x, int y) {
		assert(x > 0 && x < width && y > 0 && y < height);
		return pixelMatrix[x][y];

	}
	
	/**
	 * Sets the pixel at position (x,y)
	 * @param x - horizontal coordinate
	 * @param y - vertical coordinate
	 * @param P - the pixel to st
	 */
	@Override
	public void setPixel(int x, int y, Pixel P) {
		assert(x > 0 && x < width && y > 0 && y < height && P != null);
		pixelMatrix[x][y] = P;
	}
	
	/**
	 * Getter
	 * @return width
	 */
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	/**
	 * Getter
	 * @return height
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
