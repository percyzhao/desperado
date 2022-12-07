package desperado;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener{
	private JButton start, exit, instruct;
	private Image image;
	private ImageIcon icon;
	
	public MyPanel() {
		 
		
		start = new JButton("Start");
		exit = new JButton("Exit");
		instruct = new JButton("Instructions");
		
		
		JPanel panelc = new JPanel();
		this.setLayout(new BorderLayout());
		
		JPanel panels = new JPanel();
		
		this.add(panels, BorderLayout.SOUTH);
		this.add(panelc, BorderLayout.CENTER);
		
		panels.setLayout(new FlowLayout());
		
		
		
		panels.add(start);
		panels.add(exit);
		panels.add(instruct);
		
		start.addActionListener(this);
		exit.addActionListener(this);
		instruct.addActionListener(this);
		
		
		
		
		
		

		
		
	}
	

	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == start) {
			Main.cards.next(Main.c);
		}
		
	}
	
	public void paint(Graphics g){

		super.paint(g);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("title_screen.png"));
		} 
		catch (Exception e) {
	
		} 
       
		image = bufferedImage.getScaledInstance(700, 600, Image.SCALE_DEFAULT);
		
		g.drawImage(image, 0, 0, this);
		
		
	
        
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
