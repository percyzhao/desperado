

@SuppressWarnings("serial")


/*
 * OK SO BASICALLY SOME INFORMATION
 * 
 * 
 * 		1. THE SCALE OF THE TRANSFORMED MAP IS 4 RELATIVE TO THE ORIGINAL MAP. 
 */


public class IntroFloor extends Level{

	public IntroFloor(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
		super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, 0, 2300, 0, 1944, 1056, 752);
	}

	public void levelChange() {
		if((getPlayer().getY() <= 422 && getPlayer().getY() >= 182) && (getPlayer().getX() >= 2020 && getPlayer().getX() <= 2100)) {
			Main.cards.next(Main.c);
		}
	}
}