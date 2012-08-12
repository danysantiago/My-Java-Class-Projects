package edu.uprm.ece.icom4015.p2.gui;

import javax.swing.JFrame;

import edu.uprm.ece.icom4015.p2.photo.PhotoManipulatorFactory;

public class PhotoSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame("Photo Manipulation System");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(new PhotoManager(PhotoManipulatorFactory.newInstance()));
			//Display the window.
			frame.pack();
			frame.setVisible(true);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}

	}

}
