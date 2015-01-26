package entity;
import graphics.Screen;
import java.util.Random;
import level.Level;

// Abstract Class
public abstract class Entity {
	
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		
	}
	public void remove() {
		// Remove from level
		removed = true;
	}
	
	// Check if entity is removed or not
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
