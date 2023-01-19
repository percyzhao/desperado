/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our general level class.
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Level extends JPanel implements KeyListener, ActionListener, MouseListener{
	public final int scale = 4;
	public static int panelWidth, panelHeight;
	private int collisionMap[][];
	private int gridColumns, gridRows;
	private int leftBound, rightBound, upBound, downBound;
	private int tileSize;
	private int distance;
	private boolean drawPlayer = true;

	private Image map;
	private Player player;
	private Player dummy;
	private Timer timer;
	private Camera camera;
	private String gridPath;
	private Slime slime;
	private ArrayList<Slime> enemies = new ArrayList<Slime>();
	private Collision collision;
	private SpriteSheet heartSheet;
	private BufferedImage[] hearts = new BufferedImage[4];
	private BufferedImage heart;
	private static final String HEART_PATH = "heart_animated_2.png";
	private Clip clip;
	private boolean clear = false;





	public boolean getClear() {
		return clear;
	}
	public static int getPanelWidth() {
		return panelWidth;
	}
	public static int getPanelHeight() {
		return panelHeight;
	}

	public Level(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows, int leftBound, int rightBound, int upBound, int downBound, int width, int height, int tileSize, int[][] enemyXY, boolean boss) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		heartSheet = new SpriteSheet(17);
		heartSheet.loadImages(HEART_PATH);
		
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.upBound = upBound;
		this.downBound = downBound;

		this.gridPath = gridPath;
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

		camera = new Camera(boss);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(mapPath));
		} 
		catch (Exception e) {
		} 

		// creating map
		map = bufferedImage.getScaledInstance(width*scale, height*scale, Image.SCALE_DEFAULT);

		if (enemyXY != null) {
				for (int i = 0; i < enemyXY.length; i++) {
					slime = new Slime(enemyXY[i][0], enemyXY[i][1], 15, 15, 100, 100, 3);
					enemies.add(slime);
					
			}
		}
		heart = heartSheet.nextSprite();
		for(int i = 0; i < hearts.length; i ++) {
			hearts[i] = heart;
		}
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("floorOne.wav").getAbsoluteFile());
          
        // create clip reference
        clip = AudioSystem.getClip();
          
        // open audioInputStream to the clip
       // clip.open(audioInputStream);
          
        //clip.loop(Clip.LOOP_CONTINUOUSLY);
        




	}

	public Player getPlayer() {
		return player;
	}
	

	public void paint(Graphics g){
		
		
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		g.clearRect(0,0, panelWidth, panelHeight);
		camera.applyTo(g);	
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();

		// Update camera position
		if(Camera.cutscene) {
			if (slime == null) {
				slime = new Slime(1910, 730, 10, 10, 400, 400, 10);
				enemies.add(slime);
			}
			
			if (dummy == null) {
				dummy = new Player(player.getX(),player.getY(),0,0);
			}
			camera.update(dummy.getX(), dummy.getY(), dummy.getSizeX(), dummy.getSizeY(), leftBound, rightBound, upBound, downBound);
		}

		else {
			camera.update(player.getX(), player.getY(), player.getSizeX(), player.getSizeY(), leftBound, rightBound, upBound, downBound);
		}

		super.paint(g);

		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				//calculate the distance between the player and the current tile
				distance = (int) Math.sqrt(Math.pow(player.getX() - (j * tileSize*scale), 2) + Math.pow(player.getY() - (i * tileSize*scale), 2));

				if(Camera.cutscene) {
					if (distance <= 2500) { //if the distance is less than x pixels
						//create a source rectangle for the current tile
						Rectangle sourceRect = new Rectangle(j * tileSize*scale, i * tileSize*scale, tileSize*scale, tileSize*scale);
						//draw the portion of the background image specified by the source rectangle
						g.drawImage(map, j * tileSize*scale, i * tileSize*scale, j * tileSize*scale + tileSize*scale, i * tileSize*scale + tileSize*scale, sourceRect.x, sourceRect.y, sourceRect.x + sourceRect.width, sourceRect.y + sourceRect.height, null);
					}
				}
				else {
					if (distance <= 1380) { //if the distance is less than x pixels
						//create a source rectangle for the current tile
						Rectangle sourceRect = new Rectangle(j * tileSize*scale, i * tileSize*scale, tileSize*scale, tileSize*scale);
						//draw the portion of the background image specified by the source rectangle
						g.drawImage(map, j * tileSize*scale, i * tileSize*scale, j * tileSize*scale + tileSize*scale, i * tileSize*scale + tileSize*scale, sourceRect.x, sourceRect.y, sourceRect.x + sourceRect.width, sourceRect.y + sourceRect.height, null);
					}
				}

			}
		}
		// boss cutscene
		if (Camera.cutscene) {
			if(dummy != null) {
				drawPlayer = false;
				if (!(dummy.getY() <= 800)) {
					dummy.setY(dummy.getY()-15);
				}
				else{
					try {
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Camera.cutscene = false;
					drawPlayer = true;
					enemies.get(0).setY(enemies.get(0).getY() + 500);
				}
			}

		}

		if(drawPlayer) {
			player.myDraw(g);
		}

		// loading collision map
		collision.loadCollisionMap(collisionMap);

		/*
		// rectangles for obstacles drawn if you want
		for(int i = 0; i < collision.getMap().size(); i++) {
			g.drawRect((int)collision.getMap().get(i).getX(), (int)collision.getMap().get(i).getY(), 64, 64);
		}
		*/

		// drawing enemies
		for(int i = 0; i < enemies.size(); i++) {

			enemies.get(i).myDraw(g);
		}
		
		
		levelChange();
		
		
        
		// drawing hearts
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		
		for(int i = 4 - player.getHp(); i < hearts.length; i ++) {
			Image image = hearts[i].getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			g.drawImage(image, player.getX()- 600 + i*100, camera.getCamY() + 100, null);
		}
		
	
	
		
    	
    		
    		
        
		
	}

	public void levelChange() {

	}

	public void loadMap() {
		collisionMap = new int[gridRows][gridColumns];

		// loads collsion map
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
		if(!Camera.cutscene) {
			
			// moves player with wasd
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
					player.setAttackTime(0);
				}
				else {
					player.setBow();
					player.setAttackTime(0);
				}
			}
		}


	}


	public void keyReleased(KeyEvent e) {
		// stopping from moving when key is released
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
		
		// checks if player is dead
		if(player.getHp() == 0)
			System.exit(0);


		for(int i = 0; i < enemies.size(); i++) {
			
			// checks for player and enemy dmg
            if(collision.collideEnemy(player, enemies.get(i)) && enemies.get(i).getAlive()) {
                player.dmg();
            }
            
            Entity sword = new Entity(player.getX() + player.getSizeX() + player.getattackTime()*2, player.getY() , 0, 0, "knight_idle_spritesheet.png", "knight_idle_spritesheet.png",  100, 100, 0);
            if(player.getattackTime() >= 60) {
            	sword.dead();
            }
            if(collision.collideEnemy(sword, enemies.get(i)) && sword.getAlive()) {
            	enemies.get(i).dmg();
            	if(!enemies.get(i).getAlive())
            		clear = true;
            }

            
            
            if(!camera.cutscene)
            	collision.collideDirection(player, enemies.get(i));
            
            // makes sure enemies cant walk through walls
            boolean[] canMove = collision.collideDirection(enemies.get(i));
            enemies.get(i).move(canMove[0],canMove[1],canMove[2],canMove[3]);
   
        }
		
		//if(p && camera.getHasHappened())
			//clear = true;
        
        



		// makes sure player cant walk through walls

		boolean[] canMove = collision.collideDirection(player);
		player.move(canMove[0],canMove[1],canMove[2],canMove[3]);
		repaint();

	}



	public void mouseClicked(MouseEvent e) {
		if(!player.getBow()) {
			if(player.getattackTime() >= 120)
				player.attack();
		}

		System.out.println("clicked");
	}



	public void mousePressed(MouseEvent e) {
		/*
		player.drawBow(true);
		if(player.getBow())
			player.attack();
		*/
	}



	public void mouseReleased(MouseEvent e) {
		player.drawBow(false);
	}



	public void mouseEntered(MouseEvent e) {

	}


	public void mouseExited(MouseEvent e) {

	}

}