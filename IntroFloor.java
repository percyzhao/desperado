package desperado;

@SuppressWarnings("serial")


/*
 * OK SO BASICALLY SOME INFORMATION
 * 
 * 
 * 		1. THE SCALE OF THE TRANSFORMED MAP IS 4 RELATIVE TO THE ORIGINAL MAP. 
 */


public class IntroFloor extends Level{
	private static int leftBound = 0, rightBound = 2300, upBound = 0, downBound = 1944;
	private static int width = 1056, height = 752;
	private static int tileSize = 64;

	public IntroFloor(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize);
	}

	public void levelChange() {
		if((getPlayer().getY() <= 422 && getPlayer().getY() >= 182) && (getPlayer().getX() >= 2020 && getPlayer().getX() <= 2100)) {
			Main.cards.next(Main.c);
		}
	}
}