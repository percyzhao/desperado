package desperado;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class Main {
	static Container c;
	static CardLayout cards;
	static KeyEvent e;



	public static void main(String[] a) throws InterruptedException {


		JFrame f = new JFrame();
		c = f.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);



		//Panel 1, MyPanel, is the home screen.
		//MyPanel panel = new MyPanel();

		//Panel 2, MyMain(), is currently a knight testing screen.
		//MyMain panel2 = new MyMain();

		IntroFloor intro = new IntroFloor();

		//c.add(panel);
		//c.add(panel2);
		c.add(intro);

		f.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}
}