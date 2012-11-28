package julia.fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JuliaFractal {
	// Just copied all this over, I'm sure there's some garbage I won't use
	// Width and Height of our Window
	private static final int W = 500;
	private static final int H = 500;
	// Zoom factor
	private static final int ZOOM = 1;
	// Constant c in  f(z+1) = z^2+c
	private static double cRe = -0.7;
	private static double cIm = 0.27015;
	// Image where we will draw the fractal
	private BufferedImage image = null;;
	private int maxIterations = 300;
	// Frame for displaying pretty pictures
	private JFrame f;
	
	public JuliaFractal(){
		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
		double newRe, newIm, oldRe, oldIm;
		
		for(int i = 0; i < W; ++i){
			for(int j = 0; j < H; ++j){
				newRe = 1.5 * (i-W/2) / (0.5 * ZOOM * W);
				newIm = (j-H/2) / (0.5 * ZOOM * H);
				//newNum = new ComplexNumber(newRe, newIm);
				
				int n;
				for(n = 0; n < maxIterations; ++n){
					oldRe = newRe;
					oldIm = newIm;
					
					newRe = oldRe * oldRe - oldIm * oldIm + cRe;
					newIm = 2 * oldRe * oldIm + cIm;
					
					if((newRe * newRe + newIm * newIm ) > 4) break;
				}
				
				// Color based on number of iterations completed
				//System.out.println(n);
				Color c = new Color(n%256, n%256, n%256); // Hmm...
				image.setRGB(i, j, c.getRGB());
			}
		}
		
		// make the window
		f = new JFrame("Julia Fractal"){
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(java.awt.Graphics g){
				g.drawImage(image, 0, 0, null);
			}
		};
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(W, H);
		f.repaint();
		f.setVisible(true);
	}
	
	private static void launch(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new JuliaFractal();
			}
		});
	}
	
	public static void main(String[] args){
		launch();
	}
}
