/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our collision detection system.
*/
import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision {
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();

	// loading the obstacles from the int array
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
	
	public void collideDirection(Entity self, Entity other) {
		// function for slime to figure out where player is
		
		
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		
		int x2 = other.getX();
		int y2 = other.getY();
	
	
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		Rectangle otherRect = new Rectangle(x-sizeX*5/2, y-sizeY*5/2, sizeX*5, sizeY*5);

			
			if(selfRect.intersects(otherRect)) {
				if(x2 < x)
					other.setRight();
				if(x2 > x)
					other.setLeft();
				if(y2 < y)
					other.setDown();
				if(y2 > y)
					other.setUp();
			}
			else {
				other.setStay();
			}
				
		

		
	}

	public boolean collideEnemy(Entity self, Entity enemy) {
		// detect whether player is touching an enemy
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		
		int x2 = enemy.getX();
		int y2 = enemy.getY();
		int sizeX2 = enemy.getSizeX();
		int sizeY2 = enemy.getSizeY();
		
		Rectangle enemyHit = new Rectangle(x2, y2, sizeX2, sizeY2);
		if(selfRect.intersects(enemyHit)) {
			return true;
		}
	
		return false;
		
	}
	public boolean[] collideDirection(Entity self) {
		// collision system for obstacles
		boolean[] canMove = {true, true, true, true};
		int x = self.getX();
		int y = self.getY();
		int sizeX = self.getSizeX();
		int sizeY = self.getSizeY();
	
		Rectangle selfRect = new Rectangle(x, y, sizeX, sizeY);
		
		for(int i = 0; i < obstacles.size(); i ++) {
			
			if(selfRect.intersects(obstacles.get(i))) {
				
				//checking which
				Rectangle rightHitbox = new Rectangle(x + sizeX/5+64,y + sizeY/4, ((sizeX - 64)/2), (int) (64/1.5));
				Rectangle leftHitbox = new Rectangle(x + sizeX/5- ((sizeX - 64)/2),y + sizeY/4, ((sizeX - 64)/2), (int) (64/1.5));
				Rectangle topHitbox = new Rectangle(x + sizeX/3, y + sizeY/6 - ((sizeX - 64)/2), (int)(64/1.5), ((sizeX - 64)/2));
				Rectangle bottomHitbox = new Rectangle(x + sizeX/3, y + sizeY/6 +64, (int) (64/1.5), ((sizeX - 64)/2));
				
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
		
	}
	
	
	
	
}
