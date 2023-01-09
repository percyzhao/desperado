package desperado;

import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")


/*
 * OK SO BASICALLY SOME INFORMATION
 * 
 * 
 * 		1. THE SCALE OF THE TRANSFORMED MAP IS 4 RELATIVE TO THE ORIGINAL MAP. 
 */


public class IntroFloor extends JPanel implements KeyListener, ActionListener, MouseListener{

	private Image map;
	private Player player;
	private Timer timer;
	private Camera camera;
	private int collision[][];
	public static int panelWidth, panelHeight;
	public int playerWidth, playerHeight; 

	public IntroFloor() throws InterruptedException {
		loadMap();
		this.setFocusable(true);
		this.requestFocusInWindow();
		player = new Player(2300,2142,40,40);
		player.setStay();

		playerWidth = player.getSizeX();
		playerHeight = player.getSizeY();

		this.addKeyListener(this);
		timer = new Timer(80, this);
		timer.start();

		this.addMouseListener(this);

		this.getHeight();
		camera = new Camera();

	}


	public void paint(Graphics g){
		g.clearRect(0,0, panelWidth, panelHeight);
		camera.applyTo(g);	
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();
		System.out.println(panelWidth);
		System.out.println(panelHeight);
		

		// Update camera position
		camera.update(player.getX(), player.getY(), player.getSizeX(), player.getSizeY());
		System.out.println();
		System.out.println(camera.getCamX());
		System.out.println(camera.getCamY());
		System.out.println();
		

		//player.setX(panelWidth/2 - playerWidth/2);
		//player.setY(panelHeight/2 - playerHeight/2); //MUST CHANGE HERE

		super.paint(g);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File("_composite.png"));
		} 
		catch (Exception e) {
		} 
		
		map = bufferedImage.getScaledInstance(4224, 3008, Image.SCALE_DEFAULT);

		g.drawImage(map, 0, 0, this);
		
		player.myDraw(g);
		player.loadCollisionMap(collision);
		



	}

	public void loadMap() {
		collision = new int[47][66];

		try {
			File collisionMap = new File("level1.txt");
			Scanner sc = new Scanner(collisionMap);
			while (sc.hasNextLine()) {
				for (int i = 0; i < collision.length; i++) {
					String[] line = sc.nextLine().split(" ");
					for (int k = 0; k < line.length; k++) {
						collision[i][k] = Integer.parseInt(line[k]);
						System.out.print(collision[i][k]);
					}
					System.out.println();
				}
			}
			sc.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void keyTyped(KeyEvent e) {

	}


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





	public void actionPerformed(ActionEvent e) {

		if(player.getHp() == 0) {
			timer.stop();
		}

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


