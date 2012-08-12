import javax.swing.JFrame;


public class EllipseViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(400,300);
		frame.setTitle("Look at the rounded thingy!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		EllipseComponent component = new EllipseComponent();
		
		//Decided to use a loop for window refreshing. Try resizing the windows!
		while(0==0){
			component.insertWH(frame.getWidth(), frame.getHeight()); //Inserts JFrame size to component
			frame.add(component); //Draws component
			frame.setVisible(true); //Makes windows visible
		}
		
	
		

	}

}
