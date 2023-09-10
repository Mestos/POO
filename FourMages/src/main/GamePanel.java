package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import characters.Player;
import object.SuperObject;
import tile.TileManager;

@SuppressWarnings("serial") // Write it to avoid version notification in the code.
public class GamePanel extends JPanel implements Runnable{ // Import the JPanel in the GamePanel; Runnable = gameThread.
	//	SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile. 
	final int scale = 3; // Increase the size by 3 of images.
	public final int tileSize = originalTileSize * scale; // 48x48 tile. Put it as public to Player class access it.
	public final int maxScreenCol = 16; // 16 "tiles" of images. Put it as public to TileManager class access it.
	public final int maxScreenRow = 12; // 12 "tiles" of images. Put it as public to TileManager class access it.
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels. Put it as public to TileManager class access it.
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels. Put it as public to TileManager class access it.
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50; // Size of the column in the world map.
	public final int maxWorldRow = 50; // Size of the row in the world map.
	public final int worldWidth = tileSize * maxWorldCol; // Adjust pixels of the map in the width.
	public final int worldHeight = tileSize * maxWorldRow; // Adjust pixels of the map in the Height.
	
	// FPS
	int FPS = 60; // Use 60 FPS to run the game.
	
	// SYSTEM
	TileManager tileM = new TileManager(this); // Instantiate TileManager and pass this GamePanel class.
	KeyHandler keyH = new KeyHandler(); // Instantiate the KeyHandler.
	Thread gameThread; // Run a repetition of frame sets instantiating the Thread.
	public CollisionChecker cChecker = new CollisionChecker(this); // Instantiate coLlision checker and pass this GamePanel.
	public UI ui = new UI(this); // Instantiate the UI class and pass this game panel.
	public SuperObject obj[] = new SuperObject[5]; // We prepare 5 slots for objects.
	
	// ENTITY
	public Player player = new Player(this, keyH); // Instantiate the Player class with GamePanel and Key Handler.
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the dimensions of window.
		this.setBackground(Color.black); // Define black as background.
		this.setDoubleBuffered(true); // Reduce flickering when the content is updated in GamePanel.
		this.addKeyListener(keyH); // Add the KeyHandler to GamePanel to recognize the key inputs.
		this.setFocusable(true); // GamePanel is able to receive inputs now!
	}

	public void startGameThread() {
		gameThread = new Thread(this); // Pass GamePanel to this Thread. It instantiate a Thread.
		gameThread.start(); // Start the thread. It cores the run method.
	}
	
	// DELTA/ACCUMULATOR METHOD
	@Override // With override line that child class can give its own implementation.
	public void run() { // The gameThread automatically requires the run method. 
		double drawInterval = 1000000000 / FPS; // ~0.017 seconds
		double delta = 0; // Create a variable called delta.
		long lastTime = System.nanoTime();/* System.nanoTime(): Returns the current value of the running
		 * Java Virtual Machine's high-resolution time source, in nanoseconds.*/
		long currentTime; // It will be used to know the actual time and use it.
		long timer = 0; // Timer of FPS starting from 0s;
		int drawCount = 0; // Initial amount of drawing per second.
		
		while (gameThread != null) { // Run the loop indefinitely until it sets gameThread to null stopping the loop.
			currentTime = System.nanoTime(); // The current time in nanoseconds.
			delta += (currentTime - lastTime) / drawInterval; // current - last (time passed) / interval passing.
			timer += (currentTime - lastTime); // Every loop is add the past time to this timer.
			lastTime = currentTime; // Then, current time becomes last time.
			
			if (delta >= 1) { // When delta reach the draw interval...
				update(); // Then, we update...
				repaint(); // and repaint.
				delta--; // So, we reset this delta.	
				drawCount++; // Increase drawing count by 1;
			}
			
			if (timer >= 1000000000) { // When this timer reach 1 second...
				System.out.println("FPS: " + drawCount); // Show how many drawing is showing in a second.
				drawCount = 0; // Reset the number of drawing.
				timer = 0; // Reset the second in FPS.
			}	
		}
	}
	
	public void update() { // 1 - UPDATE: update information such as character positions.
		player.update(); // Call the method from Player class.
	}
	
	public void paintComponent(Graphics g) { // 2 - DRAW: draw the screen with the updated information.
		super.paintComponent(g); /* It needs to use painComponent; "super" means the parent class 
		* of the JPanel in this case.*/	
		Graphics2D g2 = (Graphics2D)g; /* Graphics2D class extends the Graphics class to provide more sophisticated 
		* control over geometry, coordinate transformations, color management, and text layout.*/
		
		// DEBUG
		long  drawStart = 0; // Initializes the time.
		
		if (keyH.checkDrawTime == true) { // If debug mode is active.
			drawStart = System.nanoTime(); // System.nanoTime checks the time.
		}
		
		// TILE
		tileM.draw(g2); // We're gonna core this draw method inside of the TileManager.
		
		// OBJECT
		for (int i = 0; i < obj.length; i++) { // Scan object array one by one.
			if (obj[i] != null) // Check if the slot is not empty to avoid NullPointer error...
				obj[i].draw(g2, this); // Then, draw it.
		}
		
		// PLAYER
		player.draw(g2); // Call player.draw from Player class passing the g2 to receive the Graphics 2D.
		
		// UI
		ui.draw(g2); // Call draw method. UI cannot be hidden by tiles or player, so it comes after player.draw.
		
		// DEBUG
		if (keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime(); // After drawing the components...
			long passed = drawEnd - drawStart; // show how many time takes to draw.
			g2.setColor(Color.WHITE); // Define the color font.
			g2.drawString("Draw Time: " + passed, 10, 400); // Show the time passed on the screen.
			System.out.println("Draw Time: " + passed); // Show the time passed on the console.
		} // Last test in my computer: 2076800 / 1 000 000 000 = ~0.002 seconds
		  // Optimized test in my computer: 673200 / 1 000 000 000 = 0.00067 seconds
		
		g2.dispose(); // dispose(): Dispose of this graphics context and release any system resources that it is using.
	}
}




