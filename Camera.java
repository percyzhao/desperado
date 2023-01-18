package desperado;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Camera {
	private int camX, camY;
	public static boolean cutscene = false;
	public static boolean hasHappened = false;



	public Camera() {

	}

	public int getCamX() {
		return camX;
	}

	public int getCamY() {
		return camY;
	}

	public void update(int playerX, int playerY, int playerWidth, int playerHeight, int leftBound, int rightBound, int upBound, int downBound) {


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
			if (playerX >= 2000 && playerX <= 2140 && playerY >= 2170 && playerY <= 2260) {
				cutscene = true;
				hasHappened = true;
			}
		}

	}

	public void applyTo(Graphics g) {

		((Graphics2D) g).translate(-camX, -camY);
	}
}