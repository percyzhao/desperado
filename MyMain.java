package desperado;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;


public class MyMain extends JPanel implements ActionListener{
	private JButton start, exit, instruct;
	private Image image;
	private int x = 0;
	private BufferedImage spriteSheet = null;
	private BufferedImage player;
	 
	public MyMain() {
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		System.out.println(player);
		g.drawImage(player, 0, 0, this);
	
        x += 2;
        if (x > 600)
        	x = 0;
        	
	}
	
	 
	
}