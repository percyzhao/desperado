package desperado;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

@SuppressWarnings("serial")
public class MyPanel extends JPanel implements ActionListener{
	private JButton start, exit, instruct;
	private Image image;
	
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
	
	
	
	
	
}