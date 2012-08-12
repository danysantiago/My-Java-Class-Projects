package edu.uprm.ece.icom4015.p2.photo;

public class PhotoManipulatorImplementation implements PhotoManipulator {
	
	/**
	 * Creates a new empty photo with  given width and height.
	 * @param width photo width
	 * @param height photo height
	 * @return new empty photo with  given width and height.
	 */
	@Override
	public Photo newPhoto(int width, int height) {
		return new PhotoImplementation(width, height);
	}

	/**
	 * Creates a new, independent copy of the parameter photo. This method performs a deep
	 * copy of the photo, meaning that all pixel as independent copies of the pixels in the 
	 * original photo.
	 * @param thePhoto the photo to be copied
	 * @return the new, independent copy of the original.
	 */
	@Override
	public Photo copy(Photo thePhoto) {
		Photo copiedPhoto = thePhoto;
		
		for(int i = 0; i < copiedPhoto.getWidth(); i++){
			for(int j = 0; j < copiedPhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				copiedPhoto.setPixel(i, j, new PixelImplementation(copiedPixel.getRed(),copiedPixel.getGreen(),copiedPixel.getBlue()));
			}
		}
		
		return copiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with only the red channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the red channel 
	 * visible 
	 */
	@Override
	public Photo isolateRed(Photo thePhoto) {
		Photo modifiedPhoto = thePhoto;
		
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			for(int j = 0; j < modifiedPhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				modifiedPhoto.setPixel(i, j, new PixelImplementation(copiedPixel.getRed(),0,0));
			}
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with only the green channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the green channel 
	 * visible 
	 */
	@Override
	public Photo isolateGreen(Photo thePhoto) {
		Photo modifiedPhoto = thePhoto;
		
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			for(int j = 0; j < modifiedPhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				modifiedPhoto.setPixel(i, j, new PixelImplementation(0,copiedPixel.getGreen(),0));
			}
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with only the blue channel 
	 * visible in all pixels. The remaining channels are set to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with only the blue channel 
	 * visible 
	 */
	@Override
	public Photo isolateBlue(Photo thePhoto) {
		Photo modifiedPhoto = thePhoto;
		
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			for(int j = 0; j < modifiedPhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				modifiedPhoto.setPixel(i, j, new PixelImplementation(0,0,copiedPixel.getBlue()));
			}
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but in gray scale. To set 
	 * gray scale you need to take the average of the red, green, and blue channels of the corresponding pixel in the 
	 * parameter photo, and set all three levels for the pixel in the new photo equal to this value. You need to round down
	 * the averages.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but in gray scale.
	 */
	@Override
	public Photo grayScale(Photo thePhoto) {
		
		Photo modifiedPhoto = thePhoto;
		
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			for(int j = 0; j < modifiedPhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				int pixelAvg = (int) Math.floor((copiedPixel.getRed() + copiedPixel.getGreen() + copiedPixel.getBlue())/3);
				modifiedPhoto.setPixel(i, j, new PixelImplementation(pixelAvg,pixelAvg,pixelAvg));
			}
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with black lines horizontally interleaved with the 
	 * original image. The first 10 rows look like the original, the next 10 rows are all black, and so on.
	 * To create the black pixels, set all three of the red, green and blue channels to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with horizontal black stripes.
	 */
	@Override
	public Photo horizontalStripes(Photo thePhoto) {
		Photo modifiedPhoto = thePhoto;
		int stripeCount = 0;
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			for(int j = 0; j < modifiedPhoto.getHeight(); j++){
				if (j % 10 == 0)
					stripeCount++;
				
				if(stripeCount % 2 == 0){
					modifiedPhoto.setPixel(i, j, new PixelImplementation(0,0,0));
				} else {
					Pixel copiedPixel = thePhoto.getPixel(i, j);
					modifiedPhoto.setPixel(i, j, new PixelImplementation(copiedPixel.getRed(),copiedPixel.getGreen(),copiedPixel.getBlue()));
				}
			}
			
			stripeCount = 0;
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with black lines vertically interleaved with the 
	 * original image. The first 10 columns look like the original, the next 10 columns are all black, and so on.
	 * To create the black pixels, set all three of the red, green and blue channels to 0.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with vertical black stripes.
	 */	
	@Override
	public Photo verticalStripes(Photo thePhoto) {
		Photo modifiedPhoto = thePhoto;
		int stripeCount = 0;
		for(int i = 0; i < modifiedPhoto.getWidth(); i++){
			if (i % 10 == 0)
				stripeCount++;

			for(int j = 0; j < modifiedPhoto.getHeight(); j++){

				if(stripeCount % 2 == 0){
					modifiedPhoto.setPixel(i, j, new PixelImplementation(0,0,0));
				} else {
					Pixel copiedPixel = thePhoto.getPixel(i, j);
					modifiedPhoto.setPixel(i, j, new PixelImplementation(copiedPixel.getRed(),copiedPixel.getGreen(),copiedPixel.getBlue()));
				}
			}
		}
		
		return modifiedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but rotated 90 degrees clockwise.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but rotated 90 degrees clockwise.
	 * 
	 */	
	@Override
	public Photo rotate(Photo thePhoto) {
		Photo rotatedPhoto = new PhotoImplementation(thePhoto.getHeight(), thePhoto.getWidth());
		for(int i = 0; i < thePhoto.getWidth(); i++){
			for(int j = 0; j < thePhoto.getHeight(); j++){
				Pixel copiedPixel = thePhoto.getPixel(i, j);
				rotatedPhoto.setPixel( (rotatedPhoto.getWidth() - 1) - j, i, copiedPixel);
			}
		}
		return rotatedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with twice the width.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the width.
	 * 
	 */	
	@Override
	public Photo horizontalStretch(Photo thePhoto) {
		Photo enlargedPhoto = new PhotoImplementation(thePhoto.getWidth()*2,thePhoto.getHeight());

		for(int i = 0; i < thePhoto.getWidth(); i++){
			for(int j = 0; j < thePhoto.getHeight(); j++){

				Pixel copiedPixel = thePhoto.getPixel(i, j);

				for(int k = i*2; k < i*2 + 2; k++){
					enlargedPhoto.setPixel(k,j, copiedPixel);
				}
			}
		}

		return enlargedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with twice the height.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the height.
	 * 
	 */	
	@Override
	public Photo verticalStretch(Photo thePhoto) {
		Photo enlargedPhoto = new PhotoImplementation(thePhoto.getWidth(),thePhoto.getHeight()*2);

		for(int i = 0; i < thePhoto.getWidth(); i++){
			for(int j = 0; j < thePhoto.getHeight(); j++){

				Pixel copiedPixel = thePhoto.getPixel(i, j);

				for(int l = j*2; l < j*2 + 2; l++){
					enlargedPhoto.setPixel(i,l, copiedPixel);
				}

			}
		}

		return enlargedPhoto;
	}

	/**
	 * Returns a new photo that is a copy of the original but with twice the width and height.
	 * @param thePhoto the photo to be copied.
	 * @return new photo that is a copy of the original but with twice the width and height.
	 * 
	 */	
	@Override
	public Photo enlarge(Photo thePhoto) {
		Photo enlargedPhoto = new PhotoImplementation(thePhoto.getWidth()*2,thePhoto.getHeight()*2);

		for(int i = 0; i < thePhoto.getWidth(); i++){
			for(int j = 0; j < thePhoto.getHeight(); j++){

				Pixel copiedPixel = thePhoto.getPixel(i, j);

				for(int k = i*2; k < i*2 + 2; k++){
					for(int l = j*2; l < j*2 + 2; l++){
						enlargedPhoto.setPixel(k,l, copiedPixel);
					}
				}
			}
		}

		return enlargedPhoto;
	}

}
