import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class myMain extends JPanel implements ActionListener{
	private JButton start, exit, instruct;
	private Image image;
	private int x = 0;
	
	public myMain() {
		
	
		
		
		
	}
	

	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	
	
	public void paint(Graphics g){

		

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("amongus.png"));
		} 
		catch (Exception e) {
	
		} 
       
		image = bufferedImage.getScaledInstance(700, 600, Image.SCALE_DEFAULT);
		
		
		g.drawImage(image, x, 0, this);
	
        x += 2;
        if (x > 600)
        	x = 0;
	}
	
	
	
}