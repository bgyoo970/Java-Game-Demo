package level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
		
	}
	
	protected void generateLevel() {
		// Cycles through every index to fill the Random Tiles array
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4);							// random.nextInt(4) will produce 0,1,2, or 3.
			}
		}
	}
}
