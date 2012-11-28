package simple.julia.fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import useful.classes.ComplexNumber;

/*
 * This is a simple Julia Fractal generator
 * I just check every pixel within a given width and height,
 *  iterate it a fixed amount of times, then check to see if it is still in the set
 *  if it is I assign that pixel a random Color else the pixel is black
 */
public class JuliaFractal {
	// Width and Height of our Window
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	// Constant c in  f(z+1) = z^2+c
	//private static ComplexNumber c1 = new ComplexNumber(-0.223, 0.745); // Works nice with 100 iterations
	private static ComplexNumber c1 = new ComplexNumber(-0.7, 0.27015); // Looks pretty with 250 iterations
	// boolean values which represent pixels
	private boolean[][] values = null;
	// Constant for our bounds of the plane
	private static final double COORD = 1.5;
	// Bounds of the Complex Plane
	private double minX = -COORD;
	private double maxX = COORD;
	private double minY = -COORD;
	private double maxY = COORD;
	// Image where we will draw the fractal
	private BufferedImage image = null;
	// threshold and iterations
	private double threshold = 1;
	private int iterations = 250;
	// Used for random colors
	private java.util.Random r;
	// Frame for displaying pretty pictures
	private JFrame f;
	
	
	public JuliaFractal(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		r = new java.util.Random();
		
		// Fill bool array
		getValues();		
		// Now draw our image
		drawImage();
		// Make and draw the Frame
		makeFrame();
	}
	
	private void getValues(){
		values = new boolean[WIDTH][HEIGHT];
		// Check each pixel to see if it is in the set
		for(int i = 0; i < WIDTH; ++i){
			for(int j = 0; j < HEIGHT; ++j){
				double a = (double)i*(maxX-minX)/(double)WIDTH+minX;
				double b = (double)j*(maxY-minY)/(double)HEIGHT+minY;
				values[i][j] = isInSet(new ComplexNumber(a, b));
			}
		}
	}
	
	private void drawImage(){
		// Loop through the values and draw the image
		for(int i = 0; i < WIDTH; ++i){
			for(int j = 0; j < HEIGHT; ++j){
				// Probably should've made this readable...
				image.setRGB(i, j, (values[i][j] ? randColor() : Color.BLACK).getRGB());
			}
		}
	}
	
	private void makeFrame(){
		f = new JFrame("Julia Fractal"){
			private static final long serialVersionUID = 1L;
			public void paint(java.awt.Graphics g){
				g.drawImage(image, 0, 0, null);
			}
		};
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.repaint();
		f.setVisible(true);
	}
	
	/*
	 * Determine if the ComplexNumber we pass is in the set
	 * f(z+1) = z^2+c
	 * We do 100 iterations and see if it is less than the threshold
	 */
	private boolean isInSet(ComplexNumber c){
		for(int i = 0; i < iterations; ++i)
			c = c.square().add(c1);
		return c.magnitude() < threshold*threshold;
	}
	
	private Color randColor(){
		return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
	}
	
	public static void launch(){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new JuliaFractal();
			}
		});
	}
	
	public static void main(String[] args){
		launch();
	}
}