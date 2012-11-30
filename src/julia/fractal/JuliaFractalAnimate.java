package julia.fractal;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JuliaFractalAnimate extends JFrame{

	private static final long serialVersionUID = 1L;
	// Needs a 4:3 ratio
	private static final int W = 667;
	private static final int H = 500;
	private static int Z = 1; // Zoom factor
	private static double xShift = 0;
	private static double yShift = 0;
	private double cRe = -0.7;
	private double cIm = 0.27015;
	private BufferedImage image;
	private int maxIt = 300;
	private double newRe, newIm, oldRe, oldIm;
	
	
	public JuliaFractalAnimate(){
		super("Animated Julia Fractal");
		init();
		updateImage();
	}
	
	private void init(){
		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(W, H);
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar() == '+') ++Z;
				if(e.getKeyChar() == '-' && Z > 1) --Z;
				if(e.getKeyCode() == KeyEvent.VK_LEFT) xShift -= .5 * 1/Z;
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) xShift += .5 * 1/Z;
				if(e.getKeyCode() == KeyEvent.VK_DOWN) yShift += .5 * 1/Z;
				if(e.getKeyCode() == KeyEvent.VK_UP) yShift -= .5 * 1/Z;
				updateImage();
				repaint();
			}
		});
	}
	
	private void updateImage(){
		for(int x = 0; x < W; ++x){
			for(int y = 0; y < H; ++y){
				newRe = 1.5 * (x-W/2) / (0.5 * Z * W) + xShift;
				newIm = (y-H/2) / (0.5 * Z * H) + yShift;
				
				int i; // Iteration counter
				for(i = 0; i < maxIt; ++i){
					// Previous iteration
					oldRe = newRe;
					oldIm = newIm;
					
					// Next iteration
					newRe = oldRe * oldRe - oldIm * oldIm + cRe;
					newIm = 2 * oldRe * oldIm + cIm;
					
					// If the point is outside a circle of radius sqrt(2)
					if((newRe * newRe + newIm * newIm) > 2) break;
				}
				
				// I still can't get this to give me pretty colors >:|
				//Color c = new Color(i%256, i%256, 255 * ((i < maxIt) ? 1 : 0));
				//Color c = new Color(i%256, i%256, i%256);
				//Color c = Color.getHSBColor(i*6, i%255, i%255);
				
				// I am so determined...
				int n = 765*i/maxIt;
				Color c;
				if(n < 254) c = new Color(n%256,0,0);
				else if (n < 509) c = new Color(0, n%256, 0);
				else c = new Color(0, 0, n%256);
				
				
				image.setRGB(x, y, c.getRGB());
			}
		}
	}
	
	@Override
	public void paint(java.awt.Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
	public static void launch(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new JuliaFractalAnimate().setVisible(true);
			}
		});
	}
	
	public static void main(String[] args){
		launch();
	}
}
