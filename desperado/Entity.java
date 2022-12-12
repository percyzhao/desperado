package desperado;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Entity {
	
	private int x = 0, y = 0;
	private int direction = 0;
	private int xVelocity = 20, yVelocity = 10;
	
	private SpriteSheet idleSheet;
	private SpriteSheet leftSheet;
	private SpriteSheet rightSheet;
	private SpriteSheet upSheet;
	private SpriteSheet downSheet;
	private BufferedImage entityImg = null;
	
	//each entity will need a run file, an idle file, and some other animation
	
	public Entity(int x, int y, int xVelocity, int yVelocity, String idleFile, String runFile) {
		idleSheet = new SpriteSheet();
		rightSheet = new SpriteSheet();
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		
		idleSheet.loadImages(idleFile);
		rightSheet.loadImages(runFile);
	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDirection() {
		return direction;
	}
	
	//setters
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move() {
		
		if (direction == 1) {
			y -= yVelocity;
		}
		else if (direction == 2) {
			y += yVelocity;
		}
		else if (direction == 3) {
			x -= xVelocity;
		}
		else if (direction == 4) {
			x += xVelocity;
		}
		
		
	}
	
	public void myDraw(Graphics g) {
		//Idle
		if (direction == 0) {
			entityImg = idleSheet.nextSprite();
		}
		//Up
		else if (direction == 1) {
			entityImg = rightSheet.nextSprite();
		}
		//Down
		else if (direction == 2) {
			entityImg = rightSheet.nextSprite();
		}
		//Left
		else if (direction == 3) { 
			entityImg = rightSheet.nextSprite();
		}
		//Right
		else if (direction == 4) {
			entityImg = rightSheet.nextSprite();
		}
		
		
		g.drawImage(entityImg, this.x, this.y, null);
	}
	
	
	
}