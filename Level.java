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
	public final int scale = 4;
	private Image map;
	private Player player;
	private Timer timer;
	private Camera camera;
	private int collisionMap[][];
	private int gridColumns, gridRows;
	private int leftBound, rightBound, upBound, downBound;
	private String mapPath;
	private String gridPath;
	private int width, height;
	private int tileSize;
	public static int panelWidth, panelHeight;
	
	private ArrayList<Entity> enemies = new ArrayList<Entity>();
	private int count = 0;
	
	private Collision collision;

	

	public static int getPanelWidth() {
		return panelWidth;
	}
	public static int getPanelHeight() {
		return panelHeight;
	}
	
	public Level(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows, int leftBound, int rightBound, int upBound, int downBound, int width, int height, int tileSize, int numEnemies) throws InterruptedException {
		
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.upBound = upBound;
		this.downBound = downBound;
		
		this.width = width;
		this.height = height;
		
		this.gridPath = gridPath;
		this.mapPath = mapPath;
		this.gridColumns = gridColumns;
		this.gridRows = gridRows;
		this.tileSize = tileSize;
		loadMap();

		collision = new Collision();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		player = new Player(playerX, playerY, vx, vy);
		player.setStay();

		this.addKeyListener(this);
		timer = new Timer(80, this);
		timer.start();

		this.addMouseListener(this);

		camera = new Camera();
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(mapPath));
		} 
		catch (Exception e) {
		} 
		
		map = bufferedImage.getScaledInstance(width*scale, height*scale, Image.SCALE_DEFAULT);
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

		for (int i = 0; i < gridRows; i++) {
		    for (int j = 0; j < gridColumns; j++) {
		        //calculate the distance between the player and the current tile
		        int distance = (int) Math.sqrt(Math.pow(player.getX() - (j * tileSize*scale), 2) + Math.pow(player.getY() - (i * tileSize*scale), 2));
		        if (distance <= 1380) { //if the distance is less than x pixels
		            //create a source rectangle for the current tile
		            Rectangle sourceRect = new Rectangle(j * tileSize*scale, i * tileSize*scale, tileSize*scale, tileSize*scale);
		            //draw the portion of the background image specified by the source rectangle
		            g.drawImage(map, j * tileSize*scale, i * tileSize*scale, j * tileSize*scale + tileSize*scale, i * tileSize*scale + tileSize*scale, sourceRect.x, sourceRect.y, sourceRect.x + sourceRect.width, sourceRect.y + sourceRect.height, null);
		        }
		    }
		}
		
	
		player.myDraw(g);
		
		levelChange();
		
		player.myDraw(g);
		collision.loadCollisionMap(collisionMap);

		for(int i = 0; i < collision.getMap().size(); i++) {
			g.drawRect((int)collision.getMap().get(i).getX(), (int)collision.getMap().get(i).getY(), 64, 64);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).myDraw(g);
		}

		levelChange();
	}
	
	public void levelChange() {
		
	}

	public void loadMap() {
		System.out.println(gridRows + gridColumns);
		collisionMap = new int[gridRows][gridColumns];
		

		try {
			Scanner sc = new Scanner(new File(this.gridPath));
			while (sc.hasNextLine()) {
				for (int i = 0; i < collisionMap.length; i++) {
					String[] line = sc.nextLine().split(" ");
					for (int k = 0; k < line.length; k++) {
						collisionMap[i][k] = Integer.parseInt(line[k]);
						System.out.print(collisionMap[i][k]);
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
			if(collision.getCanUp())
				player.setUp();
		}

		else if (e.getKeyCode() == KeyEvent.VK_S) {
			if(collision.getCanDown())
				player.setDown();
		} 

		else if (e.getKeyCode() == KeyEvent.VK_A) {
			if(collision.getCanLeft())
				player.setLeft();
		} 

		else if (e.getKeyCode() == KeyEvent.VK_D) {
			if(collision.getCanRight())
				player.setRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(player.getBow()) {
				player.setSword();
				player.setAttackTime(0);
			}
			else {
				player.setBow();
				player.setAttackTime(0);
			}
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
		
		/*
		for(int i = 0; i < enemies.size(); i++) {
			if(!.collideDirection(enemies.get(i), player).equals("not") && enemies.get(i).getAlive()) {
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
		*/
		


		collision.collideDirection(player);
		player.move(collision.getCanRight(),collision.getCanLeft(),collision.getCanUp(),collision.getCanDown());
		repaint();

	}



	public void mouseClicked(MouseEvent e) {
		if(!player.getBow())
			player.attack();
		
		System.out.println("clicked");
	}



	public void mousePressed(MouseEvent e) {
		player.drawBow(true);
		if(player.getBow())
			player.attack();
	}



	public void mouseReleased(MouseEvent e) {
		player.drawBow(false);
	}



	public void mouseEntered(MouseEvent e) {

	}


	public void mouseExited(MouseEvent e) {

	}

}