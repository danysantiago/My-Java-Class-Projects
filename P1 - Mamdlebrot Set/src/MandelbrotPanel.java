import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MandelbrotPanel extends JPanel implements ActionListener, MouseListener{

	private static final double W_X_MIN = -2.5;
	private static final double W_Y_MIN = -1.5;
	private static final double W_X_MAX = 1;
	private static final double W_Y_MAX = 1.5;
	
	private TestBufImage img;
	private int width;
	private int height;
	
	private double currWXMin;
	private double currWYMin;
	private double currWXMax;
	private double currWYMax;
	
	private double currSXMin;
	private double currSYMin;
	private double currSXMax;
	private double currSYMax;

	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	/**
	 * Constructor based on width and height for the screen.
	 * @param width
	 * @param height
	 */
	
	public MandelbrotPanel(int width, int height){
		if ((width < 1) || (height < 1)){
			throw new IllegalArgumentException("Width or height are illegal.");
		}
		this.width = width;
		this.height = height;
		this.currWXMin = W_X_MIN;
		this.currWYMin = W_Y_MIN;
		this.currWXMax = W_X_MAX;
		this.currWYMax = W_Y_MAX;
		
		this.currSXMin = 0;
		this.currSYMin = 0;
		this.currSXMax = width -1;
		this.currSYMax = height -1;
		
		


		img = new TestBufImage(width, height);
		redrawImage();
		this.add(img);
		this.addMouseListener(this);
		this.setVisible(true);
	}
	
	/**
	 * Returns the complex plane currently being used.
	 * @return complex plan currently being used.
	 */
	private String complexPlane(){
		ComplexNumber C1 = new ComplexNumber(new RealNumber(currWXMin), new RealNumber(currWYMin));
		ComplexNumber C2 = new ComplexNumber(new RealNumber(currWXMax), new RealNumber(currWYMax));
		return "< " + C1.toString() + ", " + C2.toString() + " >";
	}
	
	/**
	 * Repaints the image on the screen.
	 */
    public void paintComponent(Graphics g) {    	
    	g.drawImage(this.img.getImage(), 0, 0, null);
    	System.out.println("Complex Plane: " + complexPlane());
   }

    /**
     * Redraws the image.
     */
	private void redrawImage() {
		ScreenToWorldPointConverter converter = null;
    	try {
    		 converter = new ScreenToWorldPointConverter(currWXMin, currWYMin, currWXMax,currWYMax, 0, 0, width - 1, height - 1);
    	}
    	catch(Exception e){
    		throw new IllegalStateException(e);
    	}
    	
    	Point2D P = null;
    	Point2D.Double screenPoint = new Point2D.Double();
    	ComplexNumber C = null;
    	Color theColor = null;
    	int colorIndex = 0;
    	for (int i=0; i < width; ++i){
    		for (int j=0; j < height; ++j){
    			screenPoint.setLocation(i, j);
    			P = converter.getWorldCoordinates(screenPoint);
    			C = new ComplexNumber(new RealNumber(P.getX()), new RealNumber(P.getY()));
    			colorIndex = Mandelbrot.divergenceIndex(C);
    			if (colorIndex < 0){
    				theColor = Color.black;
    			}
    			else {
    				theColor = Mandelbrot.getColor(colorIndex);
    			}
    			img.paintPixel(i, j, theColor.getRGB());
    		}
    	}
	}
    
	@Override
	/**
	 * Detects button being pressed.
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Reset")){
			this.currWXMin = W_X_MIN;
			this.currWYMin = W_Y_MIN;
			this.currWXMax = W_X_MAX;
			this.currWYMax = W_Y_MAX;
			redrawImage();
			repaint();
		}
		else {
			this.currWXMin = -1.493;
			this.currWYMin = -0.2333;
			this.currWXMax = -0.1;
			this.currWYMax = -0.1;
			repaint();
		}
		
	}
	
	/**
	 * Main program.
	 * @param args - arguments
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Mandelbrot Set");
		MandelbrotPanel M = new MandelbrotPanel(800,600);

		f.getContentPane().add(M, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setBackground(Color.gray);
		JButton startButton = new JButton("Reset");
		startButton.addActionListener(M);
		bottom.add(startButton);

		f.getContentPane().add(bottom, BorderLayout.SOUTH);

		f.pack();
		f.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Not needed
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//Not needed
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//Not needed
		
	}

	/**
	 * Detects mouse being clicked to start selecting a rectangle.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		/*
		currSXMin = e.getX();
		currSYMin = e.getY();
		System.out.printf("currSXMin: %f, currSYMin: %f\n ",currSXMin,currSYMin);
		*/
		
		startX = e.getX();
		startY = e.getY();
		// DEBUG
		//System.out.printf("startX: %f, startY: %f\n ",startX,startY);
		
	}

	/**
	 * Detects mouse being release. This completes the rectangle representing the new complex plane. 
	 * It draws the selected rectangle first, redraws the image and repaints the screen. 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		currSXMax = e.getX();
		currSYMax = e.getY();
		
		System.out.printf("currSXMax: %f, currSYMax: %f\n ",currSXMax,currSYMax);
		*/

		endX = e.getX();
		endY = e.getY();
		// DEBUG
		//System.out.printf("endX: %f, endY: %f\n ",endX,endY);

		currSXMin = Math.min(startX, endX);
		currSYMin = Math.min(startY, endY);
		currSXMax = Math.max(startX, endX);
		currSYMax = Math.max(startY, endY);

		
		//Graphics g = this.getGraphics();
		Graphics g = this.img.getGraphics();
		g.setColor(new Color(1, 1, 1, 0.7f));
		// DEBUG
		//System.out.printf("Recgtangle(%d, %d, %d, %d)\n", (int) currSXMin, (int) currSYMin, (int) (currSXMax - currSXMin), (int) (currSYMax - currSYMin));

		g.fillRect((int) currSXMin, (int) currSYMin, (int) (currSXMax - currSXMin), (int) (currSYMax - currSYMin));

	   	Point2D.Double screenPoint = new Point2D.Double();
	   	Point2D P = null;
	    
	  	ScreenToWorldPointConverter converter = null;
    	try {
    		 converter = new ScreenToWorldPointConverter(currWXMin, currWYMin, currWXMax,currWYMax, 0, 0, width - 1, height - 1);
    	}
    	catch(Exception ex){
    		throw new IllegalStateException(ex);
    	}
		screenPoint.setLocation(currSXMin, currSYMin);
		P = converter.getWorldCoordinates(screenPoint);
		currWXMin = P.getX();
		currWYMin = P.getY();
		
		screenPoint.setLocation(currSXMax, currSYMax);
		P = converter.getWorldCoordinates(screenPoint);
		currWXMax = P.getX();
		currWYMax = P.getY();
		redrawImage();
		repaint();
		
	}
}
