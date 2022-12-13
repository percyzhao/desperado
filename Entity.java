

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Entity{
	
	private int x, y;
	private boolean direction[] = new boolean[5];
	
	private int xVelocity, yVelocity;
	
	private SpriteSheet idleSheet;
	private SpriteSheet leftSheet;
	private SpriteSheet rightSheet;
	private SpriteSheet upSheet;
	private SpriteSheet downSheet;
	private BufferedImage entityImg = null;
	private int size_x, size_y;
	
	
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
		setStay();
		
	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setUp() {
		direction[1] = true;
		direction[0] = false;
		direction[3] = false;
		
	}
	public void setRight() {
		direction[2] = true;
		direction[0] = false;
		direction[4] = false;
		
	}
	public void setDown() {
		direction[3] = true;
		direction[0] = false;
		direction[1] = false;
	
	}
	public void setLeft() {
		direction[4] = true;
		direction[0] = false;
		direction[2] = false;
		
	}
	
	public void setStay() {
		direction[0] = true;
		for(int i = 1; i < 5; i++) {
			direction[i] = false;
		}
	}
	
	//setters
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	

	
	public void move() {
		if(direction[1] && direction[2]) {
			x += xVelocity;
			y -= yVelocity;
		}
		
		else if(direction[2] && direction[3]) {
			x += xVelocity;
			y += yVelocity;
		}
		
		else if(direction[3]  && direction[4]) {
			x -= xVelocity;
			y += yVelocity;
		}
		
		else if(direction[1] && direction[4]) {
			x -= xVelocity;
			y -= yVelocity;
		}
		
		else if(direction[1])
			y -= yVelocity;
		
		else if(direction[2])
			x += xVelocity;
		
		else if(direction[3])
			y += yVelocity;
			
		else if(direction[4])
			x -= xVelocity;
		
	}
	
	public void myDraw(Graphics g) {
		//Idle
		if (!direction[0]) {
			entityImg = rightSheet.nextSprite();
			
		}
		//Up
		else
			entityImg = idleSheet.nextSprite();
		
		
		
		
		Image scaledImage = entityImg.getScaledInstance(size_y, size_x, Image.SCALE_DEFAULT);
		
		g.drawImage(scaledImage, this.x, this.y, null);
	}
	
	
	
}