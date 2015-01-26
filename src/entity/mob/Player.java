package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import shortProject.Game;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	
	Projectile p;
	private int fireRate = 0;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}
	
	public Player (int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update() {
		if (fireRate > 0) fireRate--;
		
		int xa = 0, ya = 0;
		if(anim < 7500) anim++;
		else anim = 0;																					// Help safeguard against crashing (if left over night)
		// Helps with the speed of the character. Shift adds running
		if (input.up) {
			if(input.shift)
				ya = ya - 2;
			else 
				ya = ya - 1;
		}
		if (input.down) {
			if(input.shift)
				ya = ya + 2;
			else 
				ya = ya + 1;
		}
		if (input.left) {
			if(input.shift)
				xa = xa - 2;
			else 
				xa = xa - 1;
		}
		if (input.right) {
			if(input.shift)
				xa = xa + 2;
			else 
				xa = xa + 1;
		}
		updateShooting();
		
		// Do an action with the mouse
		if (Mouse.getButton() == 1) {
			//System.out.println("dir: " + dir);
			//shoot(x, y, dir);
		}
		
		// As long as one of the directional keys are being pressed, move (use move animation)
		if (xa != 0 || ya !=0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
		updateShooting();
		clear();
	}
	
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
			
		}
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			// Determines the angle to help give the direction of where the projectile should go.
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
		
	}

	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 < 10) {
					sprite = Sprite.player_forward_1;
				}
				else
					sprite = Sprite.player_forward_2;
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 < 10) {
					sprite = Sprite.player_right_1;
				}
				else
					sprite = Sprite.player_right_2;
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 < 10) {
					sprite = Sprite.player_back_1;
				}
				else
					sprite = Sprite.player_back_2;
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 < 10) {
					sprite = Sprite.player_left_1;
				}
				else
					sprite = Sprite.player_left_2;
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite);
	}
}