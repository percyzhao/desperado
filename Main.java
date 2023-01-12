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
		MyMain panel2 = new MyMain();

		//IntroFloor intro = new IntroFloor(2200, 2142, 30, 30, "composite.png", "level1.txt", 66, 47);

		//c.add(panel);
		c.add(panel2);
		//FloorOne floorOne = new FloorOne(0, 0, 30, 30, "floor1Comp.png", "" , 0, 0);
		//c.add(intro);
		//c.add(floorOne);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}
}