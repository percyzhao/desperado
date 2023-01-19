import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our boss floor of the game.
*/
@SuppressWarnings("serial")
public class BossFloor extends Level{
	private static int leftBound = 0, rightBound = 6000, upBound = 0, downBound = 4100;
	private static int width = 1056, height = 688;
	private static int tileSize = 16;
	private static int[][] enemyXY;
	
	// calling super constructor
	public BossFloor(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize, enemyXY, true);

	}
	

	
	public void levelChange() {
		
	}

}