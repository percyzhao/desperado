

public class Player extends Entity{
	
	/*
	 * load images from spritesheet class, move, change image, and display
	 */
	private static final String KNIGHT_IDLE_PATH = "knight_idle_spritesheet.png";
	private static final String KNIGHT_RUN_PATH = "knight_run_spritesheet.png";

	
	public Player(int x, int y, int xVelocity, int yVelocity) {
		super(x,y, xVelocity, yVelocity, KNIGHT_IDLE_PATH, KNIGHT_RUN_PATH, 100, 100);
	}


	

}