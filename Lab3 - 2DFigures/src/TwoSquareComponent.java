import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;


public class TwoSquareComponent extends JComponent{
	
	public void paintComponent (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle r = new Rectangle(10,10,50,50);
		g2.setColor(Color.PINK);
		g2.fill(r);
		
		Rectangle r2 = new Rectangle(10,80,50,50);
		Color customColor = new Color(163,74,164);
		g2.setColor(customColor);
		g2.fill(r2);
	}

}
