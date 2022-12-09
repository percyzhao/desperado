package desperado;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class Main {
	static Container c;
	static CardLayout cards;
	public static void main(String[] a) {
		  
		JFrame f = new JFrame();
		c = f.getContentPane();
		cards = new CardLayout();
		c.setLayout(cards);
		
		MyPanel panel = new MyPanel();
		
		MyMain panel2 = new MyMain();
		
		
		c.add(panel);
		c.add(panel2);
		
		f.setSize(700, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}