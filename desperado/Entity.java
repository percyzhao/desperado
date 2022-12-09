package desperado;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	private int x, y;
	private String direction;
	private int velocity = 20;
	private BufferedImage entityImg = null;
	
	public Entity(int x, int y, int velocity) {
		this.x = x;
		this.y = y;
		this.velocity = velocity;
	}
	
	public int getX() {
		return x;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void move() {
		x += velocity;
	}
	
	public void MyDraw(Graphics g) {
		entityImg = SpriteSheet.nextRightSprite("knight_idle_spritesheet.png");
		
		g.drawImage(entityImg,x, y, null);
	}
	
	
	
	
	
	/*
	 * What to implement
	 * 
	 * Check if entity is idle or moving
	 * 
	 * if moving add moving anim, if idle add idle anim all via spritesheet
	 * 
	 */
}
