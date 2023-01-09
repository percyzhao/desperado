package desperado;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Camera {
	private int camX, camY;
	
	public Camera() {
		
	}
	
	public int getCamX() {
		return camX;
	}
	
	public int getCamY() {
		return camY;
	}
	
	public void update(int playerX, int playerY, int playerWidth, int playerHeight) {
		
		camX = playerX + playerWidth/2 - IntroFloor.panelWidth/2;
		camY = playerY + playerHeight/2 - IntroFloor.panelHeight/2;
		
		if(camX <= 0) {
			camX = 0;
		}
		
		if(camX >= 2300) {
			camX = 2300;
		}
		
		if(camY >= 1944) {
			camY = 1944;
		}
		
		if(camY <= 0) {
			camY = 0;
		}
		
		
	}
	
	public void applyTo(Graphics g) {
		((Graphics2D) g).translate(-camX, -camY);
	}
}

