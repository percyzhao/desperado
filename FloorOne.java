
package desperado;

@SuppressWarnings("serial")
public class FloorOne extends Level{
	private static int leftBound = 0, rightBound = 6000, upBound = 0, downBound = 4100;
	private static int width = 1904, height = 1088;
	private static int tileSize = 16;
	
	public FloorOne(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize);

	}



}