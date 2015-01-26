package entity.mob;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.Sprite;

// Cannot be instantiated if abstract
public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;								// Stands for direction. 0-north, 1-east, 2-south, 3-west
	protected boolean moving = false;
	protected boolean walking = false;
		
	public void move(int xa, int ya) {
		// When moving on two axis, allows for sliding movement against walls
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		// x or y = -1, 0, 1. Three possible value/movements
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
		
	}
	
	public void update() {
		
	}
	
	protected void shoot(int x, int y, double dir) {		// Need to add a projectile and then render it
		//dir = dir * (180 / Math.PI);
		//this.mDirection = dir;
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12 - 5) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 1) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
		
	}
}
