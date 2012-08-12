package edu.uprm.ece.icom4015.p2.photo;

public class PhotoManipulatorFactory {
	public static PhotoManipulator newInstance(){
		return new PhotoManipulatorImplementation();
	}

}
