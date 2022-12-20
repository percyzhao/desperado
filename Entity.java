

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
	
	private int[][] map = {{0, 0, 0, 1, 0, 0, 0, 0},{0, 0, 0, 0, 0, 0, 0, 0}};
	private Rectangle[] obstacles;
	private boolean right, left, up, down, stay;
	
	
	
	//each entity will need a run file, an idle file, and some other animation
	
	public Entity(int x, int y, int xVelocity, int yVelocity, String idleFile, String runFile,  int size_x, int size_y) {
		idleSheet = new SpriteSheet();
		rightSheet = new SpriteSheet();
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.size_x = size_x;
		this.size_y = size_y;
		
		idleSheet.loadImages(idleFile);
		rightSheet.loadImages(runFile);
		int count = 0;
		for(int i =0;i<map.length; i ++) {
			for(int j =0;j<map[0].length; j ++) {
				if(map[i][j] == 1)
					count++;
			}
		}
		obstacles = new Rectangle[1];
		obstacles[0] = new Rectangle(200 - this.xVelocity,200 - this.yVelocity , 100, 100);
		
		}
		
		
	
	
	
	//getters
	public int getX() {
		return x;
	}
	public String isCollide() {
		Rectangle player = new Rectangle(x + xVelocity, y + yVelocity, 80, 80);
		obstacles[0] = new Rectangle(200 - this.xVelocity,200 - this.yVelocity , 100, 100);
		if(player.intersects(obstacles[0])) {
			
			if(right && x +60+ xVelocity < 200 - xVelocity && y + yVelocity > 200 - yVelocity - 70 && y + yVelocity  + 100 < 200 - yVelocity + 190) {
				System.out.println("left");
				return "left";
	
			}
			else if(left && x + xVelocity + 60 > 200 - xVelocity + 100 && y + yVelocity > 200 - yVelocity - 70 && y + yVelocity < 200 - yVelocity + 90) {
				System.out.println("right");
				return "right";
			}
				
			else if(up && y + yVelocity + 60 > 200 - yVelocity + 100 && x + xVelocity > 200 - xVelocity - 70 && x + xVelocity < 200 - xVelocity + 90) {
				System.out.println("below");
				return "below";
			}

			else if(down && y + yVelocity + 60 < 200 - yVelocity && x + xVelocity > 200 - xVelocity - 70 && x + xVelocity + 100 < 200 - xVelocity + 190) {
				System.out.println("above");
				return "above";
			}
				
		
		
		}
		System.out.println("nothing");
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
	
	public void myDraw(Graphics g) {
		//Idle
		if (stay) {
			entityImg = idleSheet.nextSprite();
			
			
		}

		else
			entityImg = rightSheet.nextSprite();
		
		g.drawRect(x + xVelocity, y + yVelocity, 80, 80);
		g.drawRect(200 , 200 , 80, 80);
		
		
		
		
		Image scaledImage = entityImg.getScaledInstance(size_y, size_x, Image.SCALE_DEFAULT);
		
		g.drawImage(scaledImage, this.x, this.y, null);
	}
	
	
	
}