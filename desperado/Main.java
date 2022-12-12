package desperado;

import java.awt.*;
import javax.swing.*;


public class Main {
	static Container c;
	static CardLayout cards;
	public static void main(String[] a) {
		  
		
		JFrame f = new JFrame();
		c = f.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);
		
		
		
		//Panel 1, MyPanel, is the home screen.
		MyPanel panel = new MyPanel();
		
		//Panel 2, MyMain(), is currently a knight testing screen.
		MyMain panel2 = new MyMain();
		
		
		c.add(panel);
		c.add(panel2);
		
		f.setSize(700, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}