package mandlebrot.fractal;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Mandlebrot extends JFrame{
	
	private static final long serialVersionUID = 1L;
	// Needs a 4:3 ratio?
	private static final int W = 667;
	private static final int H = 500;
	private static int Z = 1;
	private double pRe, pIm; // Real and Imaginary part of the pixel
	private double newRe, newIm, oldRe, oldIm;
	private static int maxIt = 300;
	private BufferedImage image;
	
	
	public Mandlebrot(){
		super("Mandlebrot Set Fractal");
		init();
		updateImage();
		repaint();
	}
	
	private void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(W, H);
		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
	}
	
	private void updateImage(){
		for(int x = 0; x < W; ++x){
			for(int y = 0; y < H; ++y){
				pRe = 1.5 * (x-W/2) / (0.5 * Z * W) - 0.5;
				pIm = (y-H/2) / (0.5 * Z * H);
				newRe = newIm = oldRe = oldIm = 0;
				
				int i;
				for(i = 0; i < maxIt; ++i){
					oldRe = newRe;
					oldIm = newIm;
					
					newRe = oldRe * oldRe - oldIm * oldIm + pRe;
					newIm = 2 * oldRe * oldIm + pIm;
					
					if((newRe * newRe + newIm * newIm) > 4) break;
				}
				
				Color c = new Color(i%256, i%256, i%256);
				image.setRGB(x, y, c.getRGB());
			}
		}
	}
	
	@Override
	public void paint(java.awt.Graphics g){
		g.drawImage(image,0,0,null);
	}

	public static void launch(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Mandlebrot().setVisible(true);
			}
		});
	}
	
	public static void main(String[] args){
		launch();
	}
}
