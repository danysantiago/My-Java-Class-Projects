package edu.uprm.ece.icom4015.p2.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageEnvelope extends Component {
	
	private static final long serialVersionUID = -6939938599705262099L;
	
	private BufferedImage img;

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	public ImageEnvelope(BufferedImage img) {
		this.img = img;
	}
	
	public void reset(BufferedImage img){
		this.img = img;
	}
	
	public Dimension getPreferredSize() {
		if (img == null) {
			return new Dimension(100,100);
		} else {
			return new Dimension(img.getWidth(null), img.getHeight(null));
		}
	}
	
	public int getWidth(){
		return img.getWidth();
	}
	
	public int getHeight(){
		return img.getHeight();
	}

	public BufferedImage getImage() {
		return img;
	}
}

