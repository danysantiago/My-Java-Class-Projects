package edu.uprm.ece.icom4015.p2.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import edu.uprm.ece.icom4015.p2.photo.Photo;
import edu.uprm.ece.icom4015.p2.photo.PhotoManipulator;
import edu.uprm.ece.icom4015.p2.photo.Pixel;
import edu.uprm.ece.icom4015.p2.photo.PixelFactory;


public class PhotoManager extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String COPY = "Copy";
	private static final String ENLARGE = "Enlarge";
	private static final String GRAY_SCALE = "Gray Scale";
	private static final String HORIZONTAL_STRETCH = "Horizontal Stretch";
	private static final String VERTICAL_STRETCH = "Vertical Strech";
	private static final String ONLY_RED = "Only Red";
	private static final String ONLY_GREEN = "Only Green";
	private static final String ONLY_BLUE= "Only Blue";
	private static final String ROTATE = "Rotate";
	private static final String VERTICAL_STRIPES = "Vertical Stripes";
	private static final String HORIZONTAL_STRIPES = "Horizontal Stripes";
	private static final String LOAD = "Load";
	
	
	private PhotoManipulator manipulator;
	private Photo currPhoto;
	private Photo workPhoto;
	private BufferedImage bufImage;
	private ImageEnvelope imgEnvelope;
	private JFileChooser fc;
	
	public PhotoManager(PhotoManipulator manipulator) throws IOException{
		this.manipulator = manipulator;
		// set this layout
		this.setLayout(new BorderLayout());
		
		// Add the image
		
		bufImage= ImageIO.read(new File("./MD.JPG"));

		this.imgEnvelope = new ImageEnvelope(bufImage);
		
		//this.add(new JPanel().add(this.imgEnvelope), BorderLayout.PAGE_START);
		JScrollPane scroll = new JScrollPane(this.imgEnvelope);
		scroll.setPreferredSize(this.imgEnvelope.getPreferredSize());
		   
		this.add(new JPanel().add(scroll), BorderLayout.PAGE_START);
	
		// set the panel for the radio buttons
		JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(4,3));

        // Button Groupo
        ButtonGroup group = new ButtonGroup();
        //add the buttons 
		JRadioButton button = new JRadioButton(COPY);
		button.setActionCommand(COPY);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);
	
		// rotate button
		button = new JRadioButton(ROTATE);
		button.setActionCommand(ROTATE);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);
		// only red button
		button = new JRadioButton(ONLY_RED);
		button.setActionCommand(ONLY_RED);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		// only green button
		button = new JRadioButton(ONLY_GREEN);
		button.setActionCommand(ONLY_GREEN);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		// only blue button
		button = new JRadioButton(ONLY_BLUE);
		button.setActionCommand(ONLY_BLUE);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		// gray scale button
		button = new JRadioButton(GRAY_SCALE);
		button.setActionCommand(GRAY_SCALE);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		button = new JRadioButton(VERTICAL_STRIPES);
		button.setActionCommand(VERTICAL_STRIPES);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		button = new JRadioButton(HORIZONTAL_STRIPES);
		button.setActionCommand(HORIZONTAL_STRIPES);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		button = new JRadioButton(HORIZONTAL_STRETCH);
		button.setActionCommand(HORIZONTAL_STRETCH);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		button = new JRadioButton(VERTICAL_STRETCH);
		button.setActionCommand(VERTICAL_STRETCH);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		button = new JRadioButton(ENLARGE);
		button.setActionCommand(ENLARGE);
		button.addActionListener(this);
		group.add(button);
		controls.add(button);

		// add the controls
		this.add(new JPanel().add(controls), BorderLayout.CENTER);
		fc = new JFileChooser();
		JButton button2= new JButton("Load JPG Image");
		button2.setActionCommand(LOAD);
		button2.addActionListener(this);
		JPanel temp = new JPanel();
		temp.setSize(20, 20);
		temp.add(button2);
		this.add(temp, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(COPY)){
			// DEBUG
			//System.err.println("COPY");
			this.imgEnvelope.reset(this.bufImage);
			repaint();
			
		}
		else if (cmd.equals(ENLARGE)){
			//DEBUG
			//System.err.println("ENLARGE");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.enlarge(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();

			
		}
		else if (cmd.equals(GRAY_SCALE)){
			// DEBUG
			//System.err.println("GRAY_SCALE");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.grayScale(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();

			
		}
		else if (cmd.equals(HORIZONTAL_STRETCH)){
			// DEBUG
			//System.err.println("HORIZONTAL_STRETCH");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.horizontalStretch(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
			
		}
		else if (cmd.equals(VERTICAL_STRETCH)){
			// DEBUG
			//System.err.println("VERTICAL_STRETCH");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.verticalStretch(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
		}
		else if (cmd.equals(ONLY_RED)){
			// DEBUG
			//System.err.println("ONLY_RED");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.isolateRed(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();

		}
		else if (cmd.equals(ONLY_GREEN)){
			// DEBUG
			//System.err.println("ONLY_GREEN");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.isolateGreen(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
			
		}
		else if (cmd.equals(ONLY_BLUE)){
			// DEBUG
			//System.err.println("ONLY_BLUE");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.isolateBlue(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
			
		}
		else if (cmd.equals(ROTATE)){
			// DEBUG
			//System.err.println("ROTATE");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			// DEBUG
			//System.out.printf("Img-> w: %d, h: %d\n",imgEnvelope.getWidth(), imgEnvelope.getHeight());
			//System.out.printf("Before Photo-> w: %d, h: %d\n",workPhoto.getWidth(), workPhoto.getHeight());
			
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.rotate(workPhoto);
			// DEBUG
			//System.out.printf("Img-> w: %d, h: %d\n",imgEnvelope.getWidth(), imgEnvelope.getHeight());
			//System.out.printf("After Photo-> w: %d, h: %d\n",workPhoto.getWidth(), workPhoto.getHeight());
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
			
		}
		else if (cmd.equals(VERTICAL_STRIPES)){
			// DEBUG
			//System.err.println("VERTICAL_STRIPES");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.verticalStripes(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
		
		}
		else if (cmd.equals(HORIZONTAL_STRIPES)){
			// DEBUG
			//System.err.println("HORIZONTAL_STRIPES");
			workPhoto = null;
			workPhoto = manipulator.newPhoto(imgEnvelope.getWidth(), imgEnvelope.getHeight());
			this.copyPhoto(workPhoto, imgEnvelope.getImage());
			workPhoto = manipulator.horizontalStripes(workPhoto);
			this.imgEnvelope.reset(new BufferedImage(workPhoto.getWidth(), workPhoto.getHeight(), BufferedImage.TYPE_INT_RGB ));
			copyBufferedImg(this.imgEnvelope.getImage(), workPhoto);
			repaint();
		
		}
		else if (cmd.equals(LOAD)){
			// DEBUG
			//System.err.println("LOAD");
	        int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                	this.bufImage= ImageIO.read(file);
                	this.imgEnvelope.reset(this.bufImage);
                	repaint();
                }
                catch(IOException ex){
                	throw new IllegalArgumentException("Illegal File Type.", ex);
                }
            }
		}
	}

	public void copyPhoto(Photo dst, BufferedImage src){
		Pixel P = null;
		Pixel P2 = PixelFactory.newInstance();
		for (int i=0; i < dst.getWidth(); ++i){
			for (int j=0; j < dst.getHeight(); ++j){
				P = P2.fromRGB(src.getRGB(i, j));
				dst.setPixel(i, j, P);
			}
		}
	}
	
	public void copyBufferedImg(BufferedImage dst, Photo src){
		for (int i=0; i < dst.getWidth(); ++i){
			for (int j=0; j < dst.getHeight(); ++j){
				dst.setRGB(i, j, src.getPixel(i, j).getRGB());
			}
		}
	}
}
