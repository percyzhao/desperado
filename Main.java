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
		

		IntroFloor introFloor = new IntroFloor(2062, 2640, 30, 30, "introFloor.png", "introFloor.txt", 66, 47);
		FloorOne floorOne = new FloorOne(1350, 570, 70, 70, "floorOne.png", "floor1.txt" , 119, 68);
		FloorTwo floorTwo = new FloorTwo(1090, 450, 30, 30, "floorTwo.png", "floor2.txt" , 68, 73);
		BossFloor bossFloor = new BossFloor(2060, 340, 30, 30, "bossFloor.png", "bossFloor.txt" , 66, 43);

		//c.add(panel);
		//c.add(panel2);
		//c.add(introFloor);
		//c.add(floorOne);
		//c.add(floorTwo);
		c.add(bossFloor);
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		f.setVisible(true);
		f.getContentPane().setBackground(Color.black);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}
}