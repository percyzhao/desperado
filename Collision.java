import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision {
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();

	
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
	public ArrayList<Rectangle> getMap() {
		return obstacles;
	}
	
	/*
	public String collideDirection(Entity other, Entity self) {
		Rectangle selfHit = new Rectangle(self.getX() + self.getXVelocity(), self.getY() + self.getXVelocity(), 80, 80);
		Rectangle entity = new Rectangle(other.getX() - this.xVelocity,other.getY() - this.yVelocity , 100, 100);
		Rectangle real = new Rectangle(other.getX(), other.getY(), 80, 80);
		if(!takenDmg) {
			if(selfHit.intersects(entity)) {

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
	*/

	
	public boolean[] collideDirection(Entity self) {
		boolean[] canMove = {true, true, true, true};
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		int xVelocity = self.getXVelocity();
		int yVelocity = self.getYVelocity();
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		
		for(int i = 0; i < obstacles.size(); i ++) {
			
			if(selfRect.intersects(obstacles.get(i))) {
				Rectangle rightHitbox = new Rectangle(x + sizeX/5+64,y + sizeY/6, ((sizeX - 64)/2), 64);
				Rectangle leftHitbox = new Rectangle(x + sizeX/5- ((sizeX - 64)/2),y + sizeY/6, ((sizeX - 64)/2), 64);
				Rectangle topHitbox = new Rectangle(x + sizeX/5, y + sizeY/6 - ((sizeX - 64)/2), 64, ((sizeX - 64)/2));
				Rectangle bottomHitbox = new Rectangle(x + sizeX/5, y + sizeY/6 +64, 64, ((sizeX - 64)/2));
				
				if(self.getRight() && obstacles.get(i).intersects(rightHitbox)) {
					System.out.println("collide left");
					canMove[0] = false;
				}
				if(self.getLeft() && obstacles.get(i).intersects(leftHitbox)) {
					System.out.println("collide right");
					canMove[1] = false;
				}
				if(self.getUp() && obstacles.get(i).intersects(topHitbox)) {
					System.out.println("collide bottom");
					canMove[2] = false;
				}
				if(self.getDown() && obstacles.get(i).intersects(bottomHitbox)) {
					System.out.println("collide top");
					canMove[3] = false;
				}
			}
		}
		return canMove;
		/*
		//top
		g.drawRect(x + sizeX/5, y + sizeY/6 - ((sizeX - 64)/2), 64, ((sizeX - 64)/2));
			
		//bottom
		g.drawRect(x + sizeX/5, y + sizeY/6 +64, 64, ((sizeX - 64)/2));
			
		//left
		g.drawRect(x + sizeX/5- ((sizeX - 64)/2),y + sizeY/6, ((sizeX - 64)/2), 64);
			
		//right
		g.drawRect(x + sizeX/5+64,y + sizeY/6, ((sizeX - 64)/2), 64);
		*/
	}
	
	
	
	public boolean canRight(Entity self) {
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		int xVelocity = self.getXVelocity();
		
		
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		for(int i = 0; i < obstacles.size(); i ++) {
			int obX = (int) obstacles.get(i).getX();
			int obY = (int) obstacles.get(i).getY();
			int obHeight = (int) obstacles.get(i).getHeight();
			
			if(selfRect.intersects(obstacles.get(i)) && self.getRight()) {
				System.out.println("collide left");
				if(x + sizeX - xVelocity <= obX && y >= obY - sizeY + xVelocity && y <= obY + obHeight) {
					System.out.println("collide left");
					return false;
				}
				
				
			}
			
		}
		return true;
		
	}
	
	public boolean canLeft(Entity self) {
		
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		int xVelocity = self.getXVelocity();
		
		
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		for(int i = 0; i < obstacles.size(); i ++) {
			int obX = (int) obstacles.get(i).getX();
			int obY = (int) obstacles.get(i).getY();
			int obWidth = (int) obstacles.get(i).getWidth();
			int obHeight = (int) obstacles.get(i).getHeight();
			
			if(selfRect.intersects(obstacles.get(i)) && self.getLeft()) {
				System.out.println("collide right");
				if(x >= obX + obWidth - xVelocity && y >= obY - sizeY + xVelocity && y <= obY + obHeight)
					System.out.println("collide right");
					return false;
				
				
			}
			
		}
		return true;
		
	}
	public boolean canUp(Entity self) {
		
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		int xVelocity = self.getXVelocity();
		int yVelocity = self.getYVelocity();
		
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		for(int i = 0; i < obstacles.size(); i ++) {
			int obX = (int) obstacles.get(i).getX();
			int obY = (int) obstacles.get(i).getY();
			int obWidth = (int) obstacles.get(i).getWidth();
			int obHeight = (int) obstacles.get(i).getHeight();
			
			if(selfRect.intersects(obstacles.get(i)) && self.getUp()) {
				System.out.println("collide below");
				if(y >= obY + obHeight - yVelocity && x >= obY - sizeX + xVelocity && y <= obX + obWidth)
					System.out.println("collide below");
					return false;
				
				
			}
			
		}
		return true;
		
	}
	public boolean canDown(Entity self) {
		
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		int xVelocity = self.getXVelocity();
		int yVelocity = self.getYVelocity();
		
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		for(int i = 0; i < obstacles.size(); i ++) {
			int obX = (int) obstacles.get(i).getX();
			int obY = (int) obstacles.get(i).getY();
			int obWidth = (int) obstacles.get(i).getWidth();

			
			if(selfRect.intersects(obstacles.get(i)) && self.getDown()) {
				System.out.println("collide above");
				if(y + sizeY - yVelocity <= obY && x >= obY - sizeX + xVelocity && y <= obX + obWidth)
					System.out.println("collide above");
					return false;
			
				
			}
			
		}
		return true;
		
	}
	
}
