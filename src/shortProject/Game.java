package shortProject;

import graphics.Screen;
import input.Keyboard;
import input.Mouse;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.TileCoordinate;
import entity.mob.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;								// A convention of Java
	
	private static int width = 300;
	private static int height = width / 16*9;
	private static int scale = 3;
	private static String title = "Rain";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		frame = new JFrame();
		screen = new Screen(width, height);
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(19, 4);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);											// Helps set position of player
		player.init(level);
		
		Mouse mouse = new Mouse();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");												// Refers to Run()
		thread.start();
	}
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("updates: " + updates + "," + "fps: " + frames);
				frame.setTitle(title + " | " + "updates: " + updates + "," + "fps: " + frames);
				frames = 0;
				updates = 0;
			}
				
		}
		stop();
	}
	
	private void update() {
		key.update();
		player.update();
		level.update();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image,  0 , 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 50));
		//g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		g.drawString("Button: " + Mouse.getButton(), 80, 80);										// Tells you what mouse button is being used
		g.dispose();																				// Disposes of the graphics eventually
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
	
}