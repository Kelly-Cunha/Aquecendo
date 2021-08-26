package Homenagem2;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage sob;
	
	public Spritesheet(String path) {
		try {
			sob = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x,int y, int width,int height) {
		return sob.getSubimage(x, y, width,height);
	}
}