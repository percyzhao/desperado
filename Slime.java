/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our slime class, the main enemy which extends Entity
*/
import java.awt.Rectangle;

public class Slime extends Entity{
	private static final String SLIME_IDLE = "slime_idle_spritesheet.png";
	private static final String SLIME_RUN = "slime_run_spritesheet.png";
	
	public Slime(int x, int y, int xVelocity, int yVelocity, int sizeX, int sizeY, int hp) {
		super(x,y, xVelocity, yVelocity, SLIME_IDLE, SLIME_RUN, sizeX, sizeY, hp);
	}
	
	
}
