package desperado;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity{

	private int x, y;

	private int xVelocity, yVelocity;

	private SpriteSheet idleSheet;
	private SpriteSheet leftSheet;
	private SpriteSheet rightSheet;
	private BufferedImage entityImg = null;
	private int sizeX, sizeY;
	private boolean dmg;

	private Image rock;
	private boolean right, left, up, down, stay;
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();// move to somewhere else
	private int hp;
	private boolean takenDmg;
	private int count = 0, count2 = 0;
	private boolean attack;
	private boolean alive = true;





	//each entity will need a run file, an idle file, and some other animation

	public Entity(int x, int y, int xVelocity, int yVelocity, String idleFile, String runFile,  int sizeX, int sizeY, int hp) {
		idleSheet = new SpriteSheet(16);
		rightSheet = new SpriteSheet(16);
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.hp = hp;

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

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
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

	public int getHp() {
		return hp;
	}

	//setters
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	//collision methods
	
	public void loadCollisionMap(int[][] map) {
		obstacles.clear();
		  for (int i = 0; i < map.length; i++) {
		    for (int j = 0; j < map[i].length; j++) {
		      if (map[i][j] == 1) {
		        // 16 is the width and height of each collision block in pixels
		        obstacles.add(new Rectangle(j * 64, i * 64, 64, 64));
		      }
		    }
		  }
		}
	
	public String collideDirection() {
		Rectangle player = new Rectangle(x - xVelocity, y-yVelocity , sizeX + xVelocity*2, sizeY + yVelocity*2);
		for(int i = 0; i < obstacles.size(); i ++) {
			if(player.intersects(obstacles.get(i))) {

				if (right && x + sizeX <= obstacles.get(i).getX() && y > obstacles.get(i).getY() - sizeY && y < obstacles.get(i).getY() + obstacles.get(i).getHeight()) {
					System.out.println("left");
					return "left";
				}
				else if (left && x >= obstacles.get(i).getX() + obstacles.get(i).getWidth() && y > obstacles.get(i).getY() - sizeY && y < obstacles.get(i).getY() + obstacles.get(i).getHeight()) {
					System.out.println("right");
					return "right";
				}

				else if (up && y >= obstacles.get(i).getY() + obstacles.get(i).getHeight() && x > obstacles.get(i).getX() - obstacles.get(i).getWidth() && x < obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					System.out.println("below"); 
					return "below";
				}

				else if (down && y + sizeY <= obstacles.get(i).getY() && x > obstacles.get(i).getX() - obstacles.get(i).getWidth() && x < obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					System.out.println("above");
					return "above";
				}
			}
		}
		System.out.println("nothing");
		return "not";
	}

	public String collideDirection(Entity other) {
		Rectangle player = new Rectangle(x + xVelocity, y + yVelocity, 80, 80);
		Rectangle entity = new Rectangle(other.getX() - this.xVelocity,other.getY() - this.yVelocity , 100, 100);
		Rectangle real = new Rectangle(other.getX(), other.getY(), 80, 80);
		if(!takenDmg) {
			if(player.intersects(entity)) {

				if(x + 60 + xVelocity < other.getX() - xVelocity && y + yVelocity > other.getY() - yVelocity - 70 && y + yVelocity  + 100 < other.getY() - yVelocity + 190) {
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




	public void attack() {
		attack = true;
		count2 = 0;
	}

	public void move() {

		if(right && !collideDirection().equals("left"))
			x += xVelocity;
		if(left && !collideDirection().equals("right"))
			x -= xVelocity;
		if(up && !collideDirection().equals("below"))
			y -= yVelocity;
		if(down && !collideDirection().equals("above"))
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



	public void myDraw(Graphics g) {
		//Idle
		if (stay) {
			entityImg = idleSheet.nextSprite();			
		}

		else {
			entityImg = rightSheet.nextSprite();
		}

		Image scaledImage = entityImg.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);

		if(takenDmg) {
			if(count % 2==0)
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			else
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3));
		}
		else
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));

		if(alive) {
			g.drawImage(scaledImage, x, y, null);
			g.drawRect(x, y , sizeX, sizeY);
		}

		if(attack){
			AffineTransform backup = ((Graphics2D) g).getTransform();
			AffineTransform a = AffineTransform.getRotateInstance(count2/50.0, 200, 300);
			((Graphics2D) g).setTransform(a);
			((Graphics2D) g).setTransform(backup);


			if(count2 == 100)
				attack = false;
		}



		count++;
		count2 += 20;
		if (count == 20 && takenDmg) {
			takenDmg = false;
		}
		for(int i = 0; i < obstacles.size(); i++) {
			g.drawRect((int)obstacles.get(i).getX(), (int)obstacles.get(i).getY(), 64, 64);
		}



	}


}