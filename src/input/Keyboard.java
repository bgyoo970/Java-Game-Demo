package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean [65536];											// 65536 is the max amount of characters
	public boolean up, down, left, right;
	public boolean shift;			// Shift is my addition
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];				// my own addition
		
		// Optional
		/*for (int i = 0; i< keys.length; i++) {
			if (keys[i]) {
				System.out.println("KEY: " + i);
			}
		} */
	}
	
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	public void keyTyped(KeyEvent e) {
		
	}

	
	
	
	
	
	
	
}
