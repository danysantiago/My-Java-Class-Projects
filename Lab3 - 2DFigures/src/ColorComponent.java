import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class ColorComponent extends JComponent{
	
	public void paintComponent(Graphics g){
	
	Graphics2D g2 = (Graphics2D) g;
	
	g2.setColor(Color.BLACK);
	g2.drawString("BLACK", 5, 10);
	g2.setColor(Color.BLUE);
	g2.drawString("BLUE",5, 20);
	g2.setColor(Color.CYAN);
	g2.drawString("CYAN", 5, 30);
	g2.setColor(Color.GRAY);
	g2.drawString("GRAY",5,40);
	g2.setColor(Color.DARK_GRAY);
	g2.drawString("DARK_GRAY", 5, 50);
	g2.setColor(Color.LIGHT_GRAY);
	g2.drawString("LIGHT_GRAY",5,60);
	g2.setColor(Color.GREEN);
	g2.drawString("GREEN", 5, 70);
	g2.setColor(Color.MAGENTA);
	g2.drawString("MAGENTA",5,80);
	g2.setColor(Color.ORANGE);
	g2.drawString("ORANGE", 5, 90);
	g2.setColor(Color.PINK);
	g2.drawString("PINK",5,100);
	g2.setColor(Color.RED);
	g2.drawString("RED", 5, 110);
	g2.setColor(Color.YELLOW);
	g2.drawString("YELLOW", 5, 120);
	
	}

}
