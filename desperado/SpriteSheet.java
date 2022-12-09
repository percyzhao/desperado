package desperado;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

public class SpriteSheet {
	
	
	private static BufferedImage spriteSheet;
	private static final int TILE_SIZE = 16;
	private static ArrayList<BufferedImage> right = new ArrayList<BufferedImage>();
	private static int rightCount = 0;
	private static int x = 16;

	
	public static void loadImages(String file) {
		BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(new File(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < sprite.getWidth(); i += TILE_SIZE) {
			right.add(sprite.getSubimage(i, 0, TILE_SIZE, TILE_SIZE));
		}
	}
		
	
	public static BufferedImage nextRightSprite(String file) {
		rightCount++;
		if (rightCount > right.size()) {
			rightCount = 0;
		}
		
		if (spriteSheet == null) {
			loadImages(file);
		}
		
		return right.get(rightCount);
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