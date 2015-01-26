package level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import level.tile.Tile;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();																// same thing as w = width; w = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}
	
	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for(int x = 0; x < 64; x++) {
				getTile(x, y);
			}
		}
	}
	
}
