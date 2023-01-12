
import java.util.ArrayList;
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
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Level extends JPanel implements KeyListener, ActionListener, MouseListener{
	private Image map;
	private Player player;
	private Timer timer;
	private Camera camera;
	private int collision[][];
	private int gridColumns, gridRows;
	private int leftBound, rightBound, upBound, downBound;
	private String mapPath;
	private String gridPath;
	private static int panelWidth, panelHeight;
	private ArrayList<Entity> enemies = new ArrayList<Entity>();
	private int count = 0;
	

	public static int getPanelWidth() {
		return panelWidth;
	}
	public static int getPanelHeight() {
		return panelHeight;
	}
	public Level(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows, int leftBound, int rightBound, int upBound, int downBound, int numEnemies) throws InterruptedException {
		
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.upBound = upBound;
		this.downBound = downBound;
		
		this.gridPath = gridPath;
		this.mapPath = mapPath;
		this.gridColumns = gridColumns;
		this.gridRows = gridRows;
		loadMap();

		
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		player = new Player(playerX, playerY, vx, vy);
		player.setStay();

		this.addKeyListener(this);
		timer = new Timer(30, this);
		timer.start();

		this.addMouseListener(this);

		camera = new Camera();
		//for(int i = 0; i < numEnemies; i++) {
			//Slime slime = new Slime(2200, 1000 - i * 100, 10, 10);
			//enemies.add(slime);
		//}
		
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void paint(Graphics g){
		g.clearRect(0,0, panelWidth, panelHeight);
		camera.applyTo(g);	
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();

		// Update camera position
		camera.update(player.getX(), player.getY(), player.getSizeX(), player.getSizeY(), leftBound, rightBound, upBound, downBound);

		super.paint(g);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(mapPath));
		} 
		catch (Exception e) {
		} 
		
		map = bufferedImage.getScaledInstance(4224, 3008, Image.SCALE_DEFAULT);

		g.drawImage(map, 0, 0, this);
		
		player.myDraw(g);
		player.loadCollisionMap(collision);
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).myDraw(g);
		}
		levelChange();
	}
	
	public void levelChange() {
		
	}

	public void loadMap() {
		System.out.println(gridRows + gridColumns);
		collision = new int[gridRows][gridColumns];
		

		try {
			File collisionMap = new File(this.gridPath);
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
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(player.getBow()) {
				player.setSword();
			}
			else
				player.setBow();
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


		count ++;
		/*
		for(int i = 0; i < enemies.size(); i++) {
			if(count == 20) {
				enemies.get(i).setStay();
				enemies.get(i).setRight();
				count = -20;
			}
			else if(count == 0) {
				enemies.get(i).setStay();
				enemies.get(i).setUp();
				count = -40;
			}
			else if(count == -20) {
				enemies.get(i).setStay();
				enemies.get(i).setLeft();
				count = -60;
			}
			else if(count == -40) {
				enemies.get(i).setStay();
				enemies.get(i).setDown();
				count = 0;
			}
		}
		*/
		
		for(int i = 0; i < enemies.size(); i++) {
			if(!player.collideDirection(enemies.get(i), player).equals("not") && enemies.get(i).getAlive()) {
				player.dmg();

			}
			if(player.getHp() == 0) {
				timer.stop();
			}

			Entity sword = new Entity(player.getX() + player.getSizeX() + player.getCount2()*2, player.getY() , 0, 0, "knight_idle_spritesheet.png", "knight_idle_spritesheet.png",  100, 100, 0);
			if(sword.collideDirection(sword, enemies.get(i)) != "not") {
				enemies.get(i).dead();
			}

			enemies.get(i).move();
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
