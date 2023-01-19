/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our player class that extends Entity.
*/
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{

	/*
	 * 
	 */
	
	// paths for the spritesheets
	private static final String KNIGHT_IDLE_PATH = "knight_idle_spritesheet.png";
	private static final String KNIGHT_RUN_PATH = "knight_run_spritesheet.png";
	



	public Player(int x, int y, int xVelocity, int yVelocity) {

		super(x, y, xVelocity, yVelocity, KNIGHT_IDLE_PATH, KNIGHT_RUN_PATH, 100, 100, 4);


	}
	





}