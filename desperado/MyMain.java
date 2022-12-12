package desperado;

import java.awt.*;
import java.awt.event.*;
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

	public MyMain() {
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		player = new Player(0,0,10,5);
		this.addKeyListener(this);
		timer = new Timer(120,this);
		timer.start();
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.myDraw(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.setDirection(1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.setDirection(2);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.setDirection(3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			player.setDirection(4);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.setDirection(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		player.move();
		repaint();
		
	}

}