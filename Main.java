
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
		

		IntroFloor intro = new IntroFloor(2200, 2142, 30, 30, "introFloor.png", "level1.txt", 66, 47);
		FloorOne floorOne = new FloorOne(0, 0, 30, 30, "floorOne.png", "level2.txt" , 129, 84);

		//c.add(panel);
		//c.add(panel2);
		c.add(intro);
		c.add(floorOne);

		f.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}
}