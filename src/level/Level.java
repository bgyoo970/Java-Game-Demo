package level;

import java.util.ArrayList;
import java.util.List;

import level.tile.Tile;
import entity.Entity;
import entity.projectile.Projectile;
import graphics.Screen;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

	
	public static Level spawn = new SpawnLevel("/textures/levels/spawn.png");
	
	// Sets the width and height of the level
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel() {															// Protected means, whatever is executed/written here will execute in the Level Class
		//for (int y = 0; y < 64; y++) {
	//		for (int x = 0; x < 64; x++) {
		//		getTile(x, y);
		//	}
	//	}
	//	tile_size = 16;
	}
	
	protected void loadLevel(String path) {
		
	}
	
	// Stuff for like AI, bots and positions. Entities that might change
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	private void time() {
		
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			// Screw with the numbers after 'size' to help with where collision happens
			int xt = (((int)x + (int)xa) + c % 2 * size / 10 - 5) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size / 8) / 16;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xscroll, int yscroll, Screen screen) {
		screen.setOffset(xscroll, yscroll);
		int x0 = xscroll >> 4;
		int x1 = (xscroll + screen.width + 16) >> 4;
		int y0 = yscroll >> 4;
		int y1 = (yscroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
	}
	
	// Grass = 0xFF00, Flower = 0xFFFF00, Rock = 0x7F7F00
	public Tile getTile (int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		//if (tiles[x + y * width] == 0xFF00FF00) return Tile.grass;
		//if (tiles[x + y * width] == 0xFFFFFF00) return Tile.flower;
		//if (tiles[x + y * width] == 0xFF7F7F00) return Tile.rock;
		
		// Spawn Tiles
		if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if (tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.voidTile;
	}
	
}
