/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our camera system to display what is shown to the player.
*/
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Camera {
	private boolean boss = false;
	private int camX, camY;
	public static boolean cutscene = false;
	public static boolean hasHappened = false;



	// getters
	public Camera(boolean n) {
		boss = n;
	}
	public static boolean getCutscene() {
		return cutscene;
	}
	public static boolean getHasHappened() {
		return hasHappened;
	}

	public int getCamX() {
		return camX;
	}

	public int getCamY() {
		return camY;
	}

	public void update(int playerX, int playerY, int playerWidth, int playerHeight, int leftBound, int rightBound, int upBound, int downBound) {

		// changing where camera is depending on player
		camX = playerX + playerWidth/2 - Level.getPanelWidth()/2;
		camY = playerY + playerHeight/2 - Level.getPanelHeight()/2;

		if(camX <= leftBound) {
			camX = leftBound;
		}

		if(camX >= rightBound) {
			camX = rightBound;
		}

		if(camY <= upBound) {
			camY = upBound;
		}

		if(camY >= downBound) {
			camY = downBound;
		} 	

		if(!hasHappened) {
			if (playerY >= 2170 && boss) {
				cutscene = true;
				hasHappened = true;
			}
		}

	}

	public void applyTo(Graphics g) {

		((Graphics2D) g).translate(-camX, -camY);
	}
}