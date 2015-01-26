package graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;													// Representing coordinates for pixels
	public int[] pixels;
	private SpriteSheet sheet;
	
	// Tiles
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);									// Want to set a sprite to have a "void" sprite
	
	//Spawn Level Sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	
	//Player Sprites
	public static Sprite player_forward = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_forward_1 = new Sprite(32, 2, 4, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_left_1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_right_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	// Projectile Sprites
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite (int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	
	private void setColor(int color) {
		for(int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;
		}
	}

	// Extracts a single sprite out of a spritesheet.
	// Accesses the entire sprite sheet and extracts corresponding pixels, pixel by pixel
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels [x+y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}