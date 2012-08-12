import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class NameComponent extends JComponent{
	
	public void paintComponent (Graphics g){
		//Recovers Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		//Name Drawing
		g2.setColor(Color.RED);
		g2.drawString("Daniel is my name", 85, 175);
		
		//Rectangle drawing
		Rectangle r = new Rectangle(60,150,160,40);
		g2.setColor(Color.BLUE);
		g2.draw(r);
	}

}
