import javax.swing.JFrame;


public class ColorNameViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(300,400);
		frame.setTitle("Matching Color and Names");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ColorComponent component = new ColorComponent();
		frame.add(component);
		
		frame.setVisible(true);
		

	}

}