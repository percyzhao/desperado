/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our main class, where everything is created and ran
*/
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


public class Main {
	static Container c;
	static CardLayout cards;
	static KeyEvent e;



	public static void main(String[] a) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {


		JFrame f = new JFrame();
		c = f.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);

		
		// creating panels and adding them

		//Panel 1, MyPanel, is the home screen.
		//MyPanel panel = new MyPanel();

		//MyMain panel2 = new MyMain(2062, 2640, 30, 30, "white.png", "test.txt", 66, 47);
		

		IntroFloor introFloor = new IntroFloor(2062, 2640, 30, 30, "introFloor.png", "introFloor.txt", 66, 47);
		FloorOne floorOne = new FloorOne(1350, 570, 30, 30, "floorOne.png", "floor1.txt" , 119, 68);
		FloorTwo floorTwo = new FloorTwo(1090, 450, 30, 30, "floorTwo.png", "floor2.txt" , 68, 73);
		BossFloor bossFloor = new BossFloor(2060, 340, 30, 30, "bossFloor.png", "bossFloor.txt" , 66, 43);

		//c.add(panel);
		//c.add(panel2);
		
		c.add(introFloor);
		c.add(floorOne);
		c.add(floorTwo);
		c.add(bossFloor);
		
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
		f.getContentPane().setBackground(Color.black);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




	}
}