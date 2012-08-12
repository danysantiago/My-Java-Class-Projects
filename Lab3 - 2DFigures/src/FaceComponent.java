import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;


public class FaceComponent extends JComponent{

	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLACK);
		
		Ellipse2D.Double head = new Ellipse2D.Double(20,20,50,50);
		g2.draw(head);
		
		Ellipse2D.Double eye1 = new Ellipse2D.Double(33,36,5,5);
		g2.draw(eye1);
		
		Ellipse2D.Double eye2 = new Ellipse2D.Double(51,36,5,5);
		g2.draw(eye2);
		
		Line2D.Double mouth = new Line2D.Double(31, 55, 58, 55);
		g2.draw(mouth);
	}
}
