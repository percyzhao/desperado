import java.awt.image.BufferedImage;

public class Player extends Entity{
	
	/*
	 * 
	 */
	private static final String KNIGHT_IDLE_PATH = "knight_idle_spritesheet.png";
	private static final String KNIGHT_RUN_PATH = "knight_run_spritesheet.png";
	
	
	
	public Player(int x, int y, int xVelocity, int yVelocity) {
		super(x,y, xVelocity, yVelocity, KNIGHT_IDLE_PATH, KNIGHT_RUN_PATH, 100, 100);
		
	}
	

	

	

}