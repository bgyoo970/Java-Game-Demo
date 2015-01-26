package graphics;

import java.util.Random;

import level.tile.Tile;
import entity.projectile.Projectile;

public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();																// A random number generator
	
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);														// This will randomly generate colors for that tile index array
		}
	}
	
	public void clear(){
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	// xp = xposition. xa = xabsolute.
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -1*tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;				// Helps with border rendering
				if (xa < 0) 
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];						// What pixels on the screen & what pixels get rendered
			}
		}
	}
	
	public void renderTile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -1*sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;				// Helps with border rendering
				if (xa < 0) 
					xa = 0;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];						// What pixels on the screen & what pixels get rendered
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -1*p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;				// Helps with border rendering
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;																// What pixels on the screen & what pixels get rendered
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;				// Helps with border rendering
				if (xa < 0) 
					xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0x000000) {
					pixels[xa + ya * width] = col;						// What pixels on the screen & what pixels get rendered
				}
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}
