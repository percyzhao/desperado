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
	
	public void collideDirection(Entity self) {
		Rectangle hitbox = new Rectangle(self.getX(), self.getY(), self.getSizeX(),  self.getSizeY());
		
		for(int i = 0; i < obstacles.size(); i ++) {
			if(hitbox.intersects(obstacles.get(i))) {
				System.out.println("bruh");
				canRight = false;
				canLeft = false;
				canUp = false;
				canDown = false;
				
				if (self.getRight() && self.getX() + self.getSizeX() <= obstacles.get(i).getX() && self.getY() > obstacles.get(i).getY() - self.getSizeY() && self.getY() < obstacles.get(i).getY() + obstacles.get(i).getHeight()) {
					
					System.out.println("left");
					canLeft = false;
					canRight = true;
				}
				else if (self.getLeft() && self.getX() >= obstacles.get(i).getX() + obstacles.get(i).getWidth() && self.getY() > obstacles.get(i).getY() - self.getSizeY() && self.getY() < obstacles.get(i).getY() + obstacles.get(i).getHeight()) {
					System.out.println("right");
					canRight = false;
					canLeft = true;
				}

				else if (self.getUp() && self.getY()>= obstacles.get(i).getY() + obstacles.get(i).getHeight() && self.getX() > obstacles.get(i).getX() - obstacles.get(i).getWidth() && self.getX() < obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					System.out.println("below"); 
					canUp = false;
					canDown = true;
				}

				else if (self.getDown() && self.getY() + self.getSizeY() <= obstacles.get(i).getY() && self.getX() > obstacles.get(i).getX() - obstacles.get(i).getWidth() && self.getX() < obstacles.get(i).getX() + obstacles.get(i).getWidth()) {
					System.out.println("above"); 
					canDown = false;
					canUp = true;
				}
				
			}
			
		}
		//System.out.println("nothing");

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
