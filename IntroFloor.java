import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class IntroFloor extends Level{
	private static int leftBound = 0, rightBound = 2300, upBound = 0, downBound = 1944;
	private static int width = 1056, height = 752;
	private static int tileSize = 64;
	private static int[][] enemyXY = {{1944, 1000},{2144, 1000}};
	
	public IntroFloor(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize, enemyXY, false);
		
		
	}

	public void levelChange() {
		if((getPlayer().getY() <= 422 && getPlayer().getY() >= 182) && (getPlayer().getX() >= 2020 && getPlayer().getX() <= 2100)) {
			Main.cards.next(Main.c);
		
			
			this.requestFocusInWindow();
		}
	}
}