import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our ending level.
*/
public class Ending extends JPanel{

	
	
	public Ending() {
		
		// adding buttons
		// its not 

			

		
		
		JButton exit = new JButton("Exit");
		
		JPanel panelc = new JPanel();
		this.setLayout(new BorderLayout());
		
		JPanel panels = new JPanel();
		
		
		
		this.add(panels, BorderLayout.SOUTH);
		this.add(panelc, BorderLayout.CENTER);
		
		panels.add(exit);
		
	
		repaint();
		
		
		
		

		
		
	}
	

	
	public void paint(Graphics g) {

		 g.setFont(new Font("Arial", Font.BOLD, 30));  // set a new font
		 g.drawString("And so the valiant knight defeated the great slime in a duel for the ages.",200,100);
		 g.drawString("Although an intense fight, good will ultimately triump over evil, right?", 200, 200);
		 g.drawString("The answer however, is something that will be written in the future.", 200, 300);
		 g.drawString("First our hero must rest up, and prepare for the long journey ahead.", 200, 400);
		 repaint();
	}
	
	
	
	
	
}
