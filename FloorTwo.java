
@SuppressWarnings("serial")
public class FloorTwo extends Level{
	private static int leftBound = 0, rightBound = 6000, upBound = 0, downBound = 4100;
	private static int width = 1088, height = 1168;
	private static int tileSize = 16;
	private static int[][] enemyXY = {{30,40},{35,40}};

	
	public FloorTwo(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize, enemyXY);

	}

	public void levelChange() {
		if((getPlayer().getY() <= 4380 && getPlayer().getY() >= 4320 ) && (getPlayer().getX() >= 3880 && getPlayer().getX() <= 4010)) {
			Main.cards.next(Main.c);
			System.out.println("hi");
		}
	}
	
}
