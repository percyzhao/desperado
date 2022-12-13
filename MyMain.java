

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyMain extends JPanel implements KeyListener, ActionListener{
	
	/*
	 * Load Images onto screen
	 * press d
	 * sprite moves by x velocity,
	 * sprite image changes after movement 
	 */

	private Timer timer;
	private Player player;
	private Image background;
	private boolean collide;

	public MyMain() throws InterruptedException {
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		player = new Player(0,0,30,30);
		this.addKeyListener(this);
		timer = new Timer(80,this);
		timer.start();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("rock.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		background = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		
		
	

		
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 100, 100, this);
		player.myDraw(g);
		this.setFocusable(true);
		this.requestFocusInWindow();
		
	


	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp();
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown();

		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft();
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight();
		
		}

	



		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.setStay();
	
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
				
		player.move();
		repaint();
		
	}

}