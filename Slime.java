

public class Slime extends Entity{
	private static final String slime_idle = "slime_idle_spritesheet.png";
	private static final String slime_run = "slime_run_spritesheet.png";
	
	public Slime(int x, int y, int xVelocity, int yVelocity) {
		super(x,y, xVelocity, yVelocity, slime_idle, slime_run, 100, 100, 4);
	}
}