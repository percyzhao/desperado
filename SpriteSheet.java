

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

public class SpriteSheet {
	
	private final int TILE_SIZE = 16;
	private ArrayList<BufferedImage> Sprites = new ArrayList<BufferedImage>();
	private int rightCount = 0;
	
	public void loadImages(String file) {
		
		BufferedImage sheet = null;
		
		try {
			sheet = ImageIO.read(new File(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < sheet.getWidth(); i += TILE_SIZE) {
			Sprites.add(sheet.getSubimage(i, 0, TILE_SIZE, TILE_SIZE));
		}
		
	}
		
	public BufferedImage nextSprite() {
		
		rightCount++;
		
		if (rightCount >= Sprites.size()) {
			rightCount = 0;
		}
		
		return Sprites.get(rightCount);
	}	
	
	
	
	
}