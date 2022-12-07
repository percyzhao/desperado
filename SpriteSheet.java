package desperado;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class SpriteSheet {
	
	
	private static BufferedImage spriteSheet;
	private static final int TILE_SIZE = 16;
	private static BufferedImage right[];
	private static int rightCount = 0;
	
	public static BufferedImage loadSprite(String file) {
		BufferedImage sprite = null;
		
		try {
			sprite = ImageIO.read(new File(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		return sprite;
	}
	
	public static BufferedImage nextRightSprite() {
		rightCount++;
		if (rightCount > right.length) {
			rightCount = 0;
		}
		
		if (spriteSheet == null) {
			spriteSheet = loadSprite("knight_idle_spritesheet.png");
		}
		
		return spriteSheet.getSubimage(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}	
	
	/*
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	 
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32)- 32, width, height);
		return img;
	}
	*/
	
}