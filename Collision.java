package desperado;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision {
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	public boolean canRight = true, canLeft = true, canUp =true, canDown = true;
	
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
		
		Rectangle player = new Rectangle(x, y-yVelocity , sizeX + xVelocity, sizeY + yVelocity);
		for(int i = 0; i < obstacles.size(); i ++) {
			if(player.intersects(obstacles.get(i))) {

				
				if (self.getRight() && x + sizeX <= obstacles.get(i).getX() && y >= obstacles.get(i).getY() - sizeY && y <= obstacles.get(i).getY() + obstacles.get(i).getHeight()) {
					//System.out.println("left");
					canMove[0] = false;
				}
				else if (self.getLeft() && x >= obstacles.get(i).getX()) {
					//System.out.println("right");
					canMove[1] = false;
				}

				else if (self.getUp() && y >= obstacles.get(i).getY() && x >= obstacles.get(i).getX() - sizeX && x <= obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					//System.out.println("below"); 
					canMove[2] = false;
				}

				else if(self.getUp() && y <= obstacles.get(i).getY() + obstacles.get(i).getHeight() && x >= obstacles.get(i).getX() - sizeX && x <= obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					//System.out.println("above");
					canMove[3] = false;
				}
			}
		}

		return canMove;
	}
	public boolean getCanUp() {
		return canUp;
	}

	public boolean getCanDown() {
		return canDown;
	}

	public boolean getCanRight() {
		return canRight;
	}

	public boolean getCanLeft() {
		return canLeft;
	}

}