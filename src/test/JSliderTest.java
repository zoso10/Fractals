package test;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderTest {

	public static void main(String[] args){
		javax.swing.JFrame f = new javax.swing.JFrame("JSlider tests");
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setSize(500,500);
		f.setLayout(new GridLayout(2,1));
		final javax.swing.JSlider s = new javax.swing.JSlider();
		final javax.swing.JLabel l = new javax.swing.JLabel("Hey");
		f.add(s);
		f.add(l);
		// Changes immediately
		s.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				l.setText(new Integer(s.getValue()).toString());
			}	
		});
		// Changes when mouse is released
//		s.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mouseReleased(MouseEvent e){
//				l.setText(new Integer(s.getValue()).toString());
//			}
//		});
	}
}