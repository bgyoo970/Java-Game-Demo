package entity.projectile;

import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile{
	
	// Higher the rate of fire, slower it fires. Think of as seconds in between each shot
	public static final int FIRE_RATE = 10;
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(50) + 100;			// range will be between 100 and 150
		speed = 2;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		
		// Controlling the speed of the projectile
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		
	}
	
	public void update() {
		if (level.tileCollision(x, y, nx, ny, 64)) remove();
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() >  range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));
		return dist;
	}

	// Subtracting, or adding the displacement here, changes where the projectile will fire
	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8, (int)y - 4, this);
	}
}
