import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
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
	private BufferedImage entityImg = null;
	private int sizeX, sizeY;
	private boolean dmg;

	private Image rock;
	private boolean right, left, up, down, stay;
	private int hp;
	private boolean takenDmg;
	private int iFrames = 0, attackTime = 0;
	private boolean attack, attackBow;
	private boolean alive = true;
	private BufferedImage sword, bow;
	private boolean bow2 = false, drawBow = true;
	private Graphics2D g;






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



		try {
			sword = ImageIO.read(new File("weapon_sword_2.png"));
		} 
		catch (Exception e) {

		} 

		BufferedImage dimg = new BufferedImage(100, 100, sword.getType());  
		g = dimg.createGraphics();  
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g.drawImage(sword, 0, 0, 100, 100, 0, 0, sword.getWidth(), sword.getHeight() , null);  
		g.dispose(); 
		sword = dimg;


		try {
			bow = ImageIO.read(new File("bow.png"));
		} 
		catch (Exception e) {
			System.out.println("bruh");
		} 

		BufferedImage dimg2 = new BufferedImage(100, 120, bow.getType());  
		Graphics2D g2 = dimg2.createGraphics();  
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
		g2.drawImage(bow, 0, 0, 100, 100, 0, 0, bow.getWidth(), bow.getHeight() , null);  
		g2.dispose(); 
		bow = dimg2;






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
	public boolean getBow() {
		return bow2;
	}
	public void setBow() {
		bow2 = true;
		drawBow = true;
	}
	public void setSword() {
		bow2 = false;
	}
	public void setAttackTime(int n) {
		attackTime = n;
	}




	public boolean getAlive() {
		return alive;
	}
	public void drawBow(boolean n) {
		drawBow = n;
	}




	public void attack() {
		attack = true;
		attackTime = 0;

	}



	public void move(boolean canRight, boolean canLeft, boolean canUp, boolean canDown) {

		if(right && canRight)
			x += xVelocity;
		if(left && canLeft)
			x -= xVelocity;
		if(up && canUp)
			y -= yVelocity;
		if(down && canDown)
			y += xVelocity;

	}

	public void dmg() {
		if(takenDmg)
			dmg = false;
		else {
			dmg = true;
			iFrames = 0;
			takenDmg = true;
		}

	}

	public int getattackTime() {
		return attackTime;
	}
	public void dead() {
		alive = false;
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
			if(iFrames % 2==0)
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			else
				((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3));
		}
		else
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));

		if(alive) {
			g.drawImage(scaledImage, x, y, null);
			g.drawRect(x, y, sizeX, sizeY);

		}

		if(attack){
			if(!bow2) {
				// The required drawing location
				int drawLocationX = x + sizeX;
				int drawLocationY = y;

				// Rotation information

				double rotationRequired = Math.toRadians (45);
				double locationX = sword.getWidth() / 2;
				double locationY = sword.getHeight() / 2;
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);


				((Graphics2D) g).drawImage(op.filter(sword, null), drawLocationX + attackTime * 2, drawLocationY, null);




				if(attackTime == 40)
					attack = false;
			}
			else {
				int drawLocationX = x + sizeX;
				int drawLocationY = y - 10;

				// Rotation informati

				double rotationRequired = Math.toRadians (MouseInfo.getPointerInfo().getLocation().getY()/3);
				double locationX = bow.getWidth() / 2;
				double locationY = bow.getHeight() / 2;
				AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
				AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

				if(drawBow)
					((Graphics2D) g).drawImage(op.filter(bow, null), drawLocationX, drawLocationY, null);

			}
		}




		iFrames++;
		attackTime += 20;
		if (iFrames == 20 && takenDmg) {
			takenDmg = false;
		}




	}


}