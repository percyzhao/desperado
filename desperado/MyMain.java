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
		SpriteSheet.loadImages("knight_idle_spritesheet.png");
		player = SpriteSheet.nextRightSprite("knight_idle_spritesheet.png");
	
	}
	

	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		System.out.println(player);
		Image image = player.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		g.drawImage(image, 0, 0, this);
	
        x += 2;
        if (x > 600)
        	x = 0;
        	
	}
	
	 
	
}