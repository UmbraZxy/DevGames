package com.umbradev.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Playersheet {
private BufferedImage playersheet;
	
	public Playersheet(String path) {
		
		try {
			playersheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return playersheet.getSubimage(x, y, width, height);
	}
}
