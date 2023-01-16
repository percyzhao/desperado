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
		
		
		
		JPanel panelc = new JPanel();
		this.setLayout(new BorderLayout());
		
		JPanel panels = new JPanel();
		
		
		
		this.add(panels, BorderLayout.SOUTH);
		this.add(panelc, BorderLayout.CENTER);
		
		panels.setLayout(new BorderLayout());
		
		
		
		panels.add(start, BorderLayout.CENTER);
	
		
		start.addActionListener(this);
		repaint();
		
		
		
		
		
		

		
		
	}
	

	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == start) {
			Main.cards.next(Main.c);
		}
		
	}
	
	
	
	public void paint(Graphics g){

		

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("title_screen.png"));
		} 
		catch (Exception e) {
	
		} 
       
		image = bufferedImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		
		g.drawImage(image, 0, 0, this);
		
		
		BufferedImage bufferedImage2 = null;
		try {
			bufferedImage2 = ImageIO.read(new File("start.png"));
		} 
		catch (Exception e) {
	
		} 
		image = bufferedImage2.getScaledInstance(200, 100, Image.SCALE_DEFAULT);
		
		g.drawImage(image, getWidth()/2-100, getHeight()	-80, this);
		repaint();
		
		
	
        
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