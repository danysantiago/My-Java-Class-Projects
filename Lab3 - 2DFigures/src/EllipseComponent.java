import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class EllipseComponent extends JComponent{
	
	private int width = 0;
	private int height = 0;
	
	//Function to bring the width and height of the JFrame
	public void insertWH(int w, int h){
		width = w;
		height = h;
	}

	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		Ellipse2D.Double outerEllip = new Ellipse2D.Double(0,0,width-17,height-39);
		g2.setColor(Color.BLACK);
		g2.fill(outerEllip);
		
		Ellipse2D.Double innerEllip = new Ellipse2D.Double(10,10,width-37,height-59);
		g2.setColor(Color.CYAN);
		g2.fill(innerEllip);
	}
}
