import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class TestBufImage extends Component{
	
	private BufferedImage image;
	private Graphics g;
	
	public Image getImage(){
		return image;
	}
	   public void paint(Graphics g) {
		   Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(this.image, 0, 0, null);
	    }


	   public Dimension getPreferredSize() {
	     
	           return new Dimension(image.getWidth(null), image.getHeight(null));
	   }

	  
	TestBufImage(int width, int height){
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.g = this.image.getGraphics();
	}
	
	
	public void paintPixel2(int x, int y, Color C){
		this.g.setColor(C);
		this.g.fillRect(x, y, 1, 1);
	
	}
	public void paintPixel(int x, int y, int color){
	
		this.image.setRGB(x, y, color);

	}
	
	public  void ready(){
		Graphics g = this.image.getGraphics();
		g.drawImage(this.image, 0, 0, null);
	}
}
