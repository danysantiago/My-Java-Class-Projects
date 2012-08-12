package edu.uprm.ece.icom4015.p2.photo;

public interface PhotoManipulator {
	
	
	/**
	 * Creates a new empty photo with  given width and height.
	 * @param width photo width
	 * @param height photo height
	 * @return new empty photo with  given width and height.
	 */
	Photo newPhoto(int width, int height);
	
	/**
	 * Creates a new, independent copy of the parameter photo. This method performs a deep
	 * copy of the photo, meaning that all pixel as independent copies of the pixels in the 
	 * original photo.
	 * @param thePhoto the photo to be copied
	 * @return the new, independent copy of the original.
	 */
	Photo copy(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but with only the red channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the red channel 
	 * visible 
	 */
	Photo isolateRed(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but with only the green channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the green channel 
	 * visible 
	 */
	Photo isolateGreen(Photo thePhoto);

	/**
	 * Returns a new photo that is a copy of the original but with only the blue channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the blue channel 
	 * visible 
	 */
	Photo isolateBlue(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but in gray scale. To set 
	 * gray scale you need to take the average of the red, green, and blue channels of the corresponding pixel in the 
	 * parameter photo, and set all three levels for the pixel in the new photo equal to this value. You need to round down
	 * the averages.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but in gray scale.
	 */
	Photo grayScale(Photo thePhoto);

	/**
	 * Returns a new photo that is a copy of the original but with black lines horizontally interleaved with the 
	 * original image. The first 10 rows look like the original, the next 10 rows are all black, and so on.
	 * To create the black pixels, set all three of the red, green and blue channels to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with horizontal black stripes.
	 */
	Photo horizontalStripes(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but with black lines vertically interleaved with the 
	 * original image. The first 10 columns look like the original, the next 10 columns are all black, and so on.
	 * To create the black pixels, set all three of the red, green and blue channels to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with vertical black stripes.
	 */	
	Photo verticalStripes(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but rotated 90 degrees clockwise.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but rotated 90 degrees clockwise.
	 * 
	 */	
	Photo rotate(Photo thePhoto);

	/**
	 * Returns a new photo that is a copy of the original but with twice the width.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the width.
	 * 
	 */		
	Photo horizontalStretch(Photo thePhoto);

	/**
	 * Returns a new photo that is a copy of the original but with twice the height.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the height.
	 * 
	 */		
	Photo verticalStretch(Photo thePhoto);
	
	/**
	 * Returns a new photo that is a copy of the original but with twice the width and height.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the width and height.
	 * 
	 */		
	Photo enlarge(Photo thePhoto);
}
