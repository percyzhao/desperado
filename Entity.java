
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Entity{
	
	private int x, y;
	
	private int xVelocity, yVelocity;
	
	private SpriteSheet idleSheet;
	private SpriteSheet leftSheet;
	private SpriteSheet rightSheet;
	private SpriteSheet upSheet;
	private SpriteSheet downSheet;
	private BufferedImage entityImg = null;
	private int size_x, size_y;
	private boolean dmg;
	private int[][] map = {{0, 0, 0, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0}};

	private Image rock, sword;
	private boolean right, left, up, down, stay;
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	private static final String HEART_ANIM_PATH = "heart_animated_2.png";
	private SpriteSheet heartSheet;
	private BufferedImage heart;
	private int hp = 4;
	private boolean takenDmg;
	private int count = 0, count2 = 0;
	private boolean attack;
	private boolean alive = true;

	
	
	
	
	//each entity will need a run file, an idle file, and some other animation
	
	public Entity(int x, int y, int xVelocity, int yVelocity, String idleFile, String runFile,  int size_x, int size_y) {
		idleSheet = new SpriteSheet(16);
		rightSheet = new SpriteSheet(16);
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.size_x = size_x;
		this.size_y = size_y;
		
		idleSheet.loadImages(idleFile);
		rightSheet.loadImages(runFile);
		heartSheet = new SpriteSheet(17);
		heartSheet.loadImages(HEART_ANIM_PATH);
		int count = 0;
		for(int i =0;i<map.length; i ++) {
			for(int j =0;j<map[0].length; j ++) {
				if(map[i][j] == 1)
					count++;
			}
		}
		
		for(int i = 0; i <= 400; i += 100) {
			if(i != 100 && i != 200)
				obstacles.add(new Rectangle(200 - this.xVelocity,i - this.yVelocity , 100, 100));
		}
		
		 
		
		
		heart = heartSheet.nextSprite();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("rock.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		rock = image.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		
		BufferedImage image2 = null;
		
		try {
			image2 = ImageIO.read(new File("weapon_sword_1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		sword = image2.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		
		

		
		
		
		
	}
		
	
	
	
	//getters
	public int getX() {
		return x;
	}
	public String isCollide() {
		Rectangle player = new Rectangle(x + xVelocity, y + yVelocity, 80, 80);
		for(int i = 0; i < obstacles.size(); i ++) {
			if(player.intersects(obstacles.get(i))) {
				
				if (right && x + 60 + xVelocity < obstacles.get(i).getX()
						&& y + yVelocity > obstacles.get(i).getY() - 70
						&& y + yVelocity + 100 < obstacles.get(i).getY() + 190) {
					//System.out.println("left");
					return "left";

				} else if (left && x + xVelocity + 60 > obstacles.get(i).getX() + 100
						&& y + yVelocity > obstacles.get(i).getY() - 70
						&& y + yVelocity < obstacles.get(i).getY() + 90) {
					//System.out.println("right");
					return "right";
				}

				else if (up && y + yVelocity + 60 > obstacles.get(i).getY() + 100
						&& x + xVelocity > obstacles.get(i).getX() - 70
						&& x + xVelocity < obstacles.get(i).getX() + 90) {
					//System.out.println("below");
					return "below";
				}

				else if (down && y + yVelocity + 60 < obstacles.get(i).getY()
						&& x + xVelocity > obstacles.get(i).getX() - 70
						&& x + xVelocity + 100 < obstacles.get(i).getX() + 190) {
					//System.out.println("above");
					return "above";
				}
						
				
				
				}
		}
		//System.out.println("nothing");
		return "not";
	}
	
	public String isCollide(Slime other) {
		Rectangle player = new Rectangle(x + xVelocity, y + yVelocity, 80, 80);
		Rectangle slime = new Rectangle(other.getX() - this.xVelocity,other.getY() - this.yVelocity , 100, 100);
		Rectangle real = new Rectangle(other.getX(), other.getY(), 80, 80);
		if(!takenDmg) {
			if(player.intersects(slime)) {
				
				if(x +60+ xVelocity < other.getX() - xVelocity && y + yVelocity > other.getY() - yVelocity - 70 && y + yVelocity  + 100 < other.getY() - yVelocity + 190) {
					//System.out.println("left");
					return "left";
		
				}
				else if(x + xVelocity + 60 > other.getX() - xVelocity + 100 && y + yVelocity > other.getY() - yVelocity - 70 && y + yVelocity < other.getY() - yVelocity + 90) {
					//System.out.println("right");
					return "right";
				}
					
				else if(y + yVelocity + 60 > other.getY() - yVelocity + 100 && x + xVelocity > other.getX() - xVelocity - 70 && x + xVelocity < other.getX() - xVelocity + 90) {
					//System.out.println("below");
					return "below";
				}
	
				else if(y + yVelocity + 60 < other.getY() - yVelocity && x + xVelocity > other.getX() - xVelocity - 70 && x + xVelocity + 100 < other.getX() - xVelocity + 190) {
					//System.out.println("above");
					return "above";
				}
				else {
					return "inside";
				}
				
				
					
			
			
			}
		}
	
		return "not";
			

		
	}
			

		
	
	
	public int getY() {
		return y;
	}
	
	public void setUp() {
		up = true;
		down = false;
		stay = false;
		
		
	}
	public void setRight() {
		right = true;
		left = false;
		stay = false;
		
		
	}
	public void setDown() {
		down = true;
		up = false;
		stay = false;
		
	
	}
	public void setLeft() {
		left = true;
		right = false;
		stay = false;
		
		
	}
	
	public void setStay() {
		stay = true;

		right = false;
		left = false;
		up = false;
		down = false;
		
		
	}
	
	//setters
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	

	public boolean getUp() {
		return up;
	}
	public boolean getDown() {
		return down;
	}
	public boolean getRight() {
		return right;
	}
	public boolean getLeft() {
		return left;
	}
	public void attack() {
		attack = true;
		count2 = 0;
	}
	public void move() {
		
		
		
		
		if(right && !isCollide().equals("left"))
			x += xVelocity;
		if(left && !isCollide().equals("right"))
			x -= xVelocity;
		if(up && !isCollide().equals("below"))
			y -= yVelocity;
		if(down && !isCollide().equals("above"))
			y += xVelocity;
	
		
	}
	public void dmg() {
		if(takenDmg)
			dmg = false;
		else {
			dmg = true;
			count = 0;
			takenDmg = true;
		}
		
	}
	
	public int getHp() {
		return hp;
	}
	public void myDraw(Graphics g) {
		//Idle
		
		if (stay) {
			entityImg = idleSheet.nextSprite();
			
			
		}

		else
			entityImg = rightSheet.nextSprite();
		
		for(int i =0; i <401; i+= 80) {
			if(i != 80 && i != 160 && i != 240)
				g.drawImage(rock, 200, i,  null);
		}
		

		
		if(dmg && hp != 0) {
			heart = heartSheet.nextSprite();
			dmg = false;
			hp --;
		}
			
		
		
		
		
		Image scaledImage = entityImg.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		
		
		if(takenDmg) {
			if(count % 2==0)
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			else
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3));
		}
		else
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		
		if(alive)
			g.drawImage(scaledImage, x, y, 100, 100, null);

		Image Image2 = heart.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		g.drawImage(Image2, 300, 0, null);
		
		
		
		
		
		if(attack){
			
			AffineTransform backup = ((Graphics2D) g).getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(count2/50.0, 200, 300);
			((Graphics2D) g).setTransform(a);
			((Graphics2D) g).drawImage(sword, 300, 300, null);
			((Graphics2D) g).setTransform(backup);
			 

			if(count2 == 100)
				attack = false;
			

			
		}
		
		
		
		count++;
		count2 += 20;
		if (count == 20 && takenDmg) {
			takenDmg = false;
		}
	

		
	}
	
	
}