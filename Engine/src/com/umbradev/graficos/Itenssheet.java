package com.umbradev.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Itenssheet {
private BufferedImage itenssheet;
	
	public Itenssheet(String path) {
		
		try {
			itenssheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return itenssheet.getSubimage(x, y, width, height);
	}
}
