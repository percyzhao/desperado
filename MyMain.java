

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyMain extends JPanel implements KeyListener, ActionListener, MouseListener{

	private Timer timer;
	private Player player;
	private Slime slime;
	private int count = 0;
	private boolean right = false, left = false, up = false, down = false;



	public MyMain() throws InterruptedException {

		this.setFocusable(true);
		this.requestFocusInWindow();

		player = new Player(0,0,10,10);
		slime = new Slime(300,300,10,10);

		this.addKeyListener(this);
		timer = new Timer(80,this);
		timer.start();

		player.setStay();

		this.addMouseListener(this);




	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		slime.myDraw(g);
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


	public void keyReleased(KeyEvent e) {
		if(player.getUp() && e.getKeyCode() == KeyEvent.VK_W) {
			if(!player.getLeft() || !player.getDown() || !player.getRight())	
				player.setStay();
		}
		else if(player.getDown() && e.getKeyCode() == KeyEvent.VK_S) {
			if(!player.getLeft() || !player.getUp() || !player.getRight())	
				player.setStay();
		}
		else if(player.getRight() && e.getKeyCode() == KeyEvent.VK_D) {
			if(!player.getLeft() || !player.getUp() || !player.getDown())	
				player.setStay();
		}
		else if(player.getLeft() && e.getKeyCode() == KeyEvent.VK_A) {
			if(!player.getDown() || !player.getUp() || !player.getRight())	
				player.setStay();
		}
	}




	@Override
	public void actionPerformed(ActionEvent e) {


		count ++;
		if(count == 20) {
			slime.setStay();
			slime.setRight();
			count = -20;
		}
		else if(count == 0) {
			slime.setStay();
			slime.setUp();
			count = -40;
		}
		else if(count == -20) {
			slime.setStay();
			slime.setLeft();
			count = -60;
		}
		else if(count == -40) {
			slime.setStay();
			slime.setDown();
			count = 0;
		}


		if(player.collideDirection(slime).equals("left")) {
			player.dmg();

		}
		else if(player.collideDirection(slime).equals("right")) {
			player.dmg();

		}
		else if(player.collideDirection(slime).equals("above")) {
			player.dmg();

		}
		else if(player.collideDirection(slime).equals("below")) {
			player.dmg();

		}
		else if(player.collideDirection(slime).equals("inside")) {
			/*
			if(slime.getUp())
				player.setY(slime.getY() - 50);
			else if(slime.getDown())
				player.setY(slime.getY() + 50);
			else if(slime.getRight())
				player.setY(slime.getX() + 50);
			else if(slime.getLeft())
				player.setY(slime.getX() - 50);
			 */
			player.dmg();

		}
		if(player.getHp() == 0) {
			timer.stop();
		}


		slime.move();
		player.move();
		repaint();

	}



	public void mouseClicked(MouseEvent e) {
		player.attack();
		System.out.println("clicked");
	}



	public void mousePressed(MouseEvent e) {

	}



	public void mouseReleased(MouseEvent e) {

	}



	public void mouseEntered(MouseEvent e) {

	}


	public void mouseExited(MouseEvent e) {

	}

}