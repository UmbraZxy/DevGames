package com.umbradev.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemysheet {
private BufferedImage enemysheet;
	
	public Enemysheet(String path) {
		
		try {
			enemysheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return enemysheet.getSubimage(x, y, width, height);
	}
}
