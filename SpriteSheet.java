/*
Author: Percy Zhao, Haodong Wang
Date:  Jan 18 2022
Class Code: ICS3U7-1
Teacher: H. Strelkovska
Program: Our class that loads spritehseets into images.
*/

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

public class SpriteSheet {
	
	private int tileSize;
	private ArrayList<BufferedImage> Sprites = new ArrayList<BufferedImage>();
	private int rightCount = -1;
	
	public SpriteSheet(int tileSize) {
		this.tileSize = tileSize;
	}
	
	// loading the spritesheet
	public void loadImages(String file) {
		
		BufferedImage sheet = null;
		
		try {
			sheet = ImageIO.read(new File(file));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < sheet.getWidth(); i += tileSize) {
			Sprites.add(sheet.getSubimage(i, 0, tileSize, tileSize));
		}
		
	}
		
	// getting the next image in the spritesheet
	public BufferedImage nextSprite() {
		
		rightCount++;
		
		if (rightCount >= Sprites.size()) {
			rightCount = 0;
		}
		return Sprites.get(rightCount);
	}	
	
	
	
	
}