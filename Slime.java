package desperado;

public class Slime extends Entity{
	private static final String SLIME_IDLE = "slime_idle_spritesheet.png";
	private static final String SLIME_RUN = "slime_run_spritesheet.png";
	
	public Slime(int x, int y, int xVelocity, int yVelocity, int sizeX, int sizeY, int hp) {
		super(x,y, xVelocity, yVelocity, SLIME_IDLE, SLIME_RUN, sizeX, sizeY, hp);
	}
}