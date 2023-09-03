package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ // Import the JPanel in the GamePanel; Runnable = gameThread.
	//	SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile. 
	final int scale = 3; // Increase the size by 3 of images.
	public final int tileSize = originalTileSize * scale; // 48x48 tile. Put it as public to Player class access it.
	public final int maxScreenCol = 16; // 16 "tiles" of images. Put it as public to TileManager class access it.
	public final int maxScreenRow = 12; // 12 "tiles" of images. Put it as public to TileManager class access it.
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels. Put it as public to TileManager class access it.
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels. Put it as public to TileManager class access it.
	
	//FPS
	int FPS = 60; // Use 60 FPS to run the game.
	
	TileManager tileM = new TileManager(this); // Instantiate TileManager and pass this GamePanel class.
	KeyHandler keyH = new KeyHandler(); // Instantiate the KeyHandler.
	Thread gameThread; // Run a repetition of frame sets.
	Player player = new Player(this, keyH); // Instantiate the Player class with GamePanel and Key Handler.
	
	// SET PLAYER'S DEFAULT POSITION
//	int playerX = 100; // Set the initial position for the player in X axis.
//	int playerY = 100; // Set the initial position for the player in Y axis.
//	int playerSpeed = 4; // Set the initial speed for the player. 4 pixels moving.
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the dimensions of window.
		this.setBackground(Color.black); // Define black as background.
		this.setDoubleBuffered(true); // Reduce flickering when the content is updated in GamePanel.
		this.addKeyListener(keyH); // Add the KeyHandler to GamePanel to recognise the key inputs.
		this.setFocusable(true); // GamePanel is able to receive inputs now!
	}

	public void startGameThread() {
		gameThread = new Thread(this); // Pass GamePanel to this Thread. It instantiate a Thread.
		gameThread.start(); // Start the thread. It cores the run method.
	}
	
//	//SLEEP METHOD
//	// Declaring a method in sub class, which is already present in parent class. 
//	@Override // With override line that child class can give its own implementation.
//	public void run() { // The gameThread automatically requires the run method. 
//		while(gameThread != null) { // Here we create a game loop that is a core of our game.
//			// System.out.println("The game loop is running!"); // A test to print a message when the game is running.
//			// The currentTime bellow is good to prevent the character move too fast(millions pixel/second).
//			// long currentTime = System.nanoTime(); 
//			/* System.nanoTime(): Returns the current value of the running
//			 * Java Virtual Machine's high-resolution time source, in nanoseconds.*/
//			// We can use "long currentTime2 = System.currentTimeMillis()". But, "nanoTime" is more precise.
//			// System.out.println("Current Time: " + currentTime); // A test to print the current time.
//			
//			double drawInterval = 1000000000 / FPS; // ~0.017 seconds
//			double nextDrawTime = System.nanoTime() + drawInterval; // The allocated time for single loop is 0.017s.
//			
//			update(); // Call update.
//			
//			repaint(); // Call painComponent.
//			
//			try { // Prevents potential interruptions that can occur during the use of "Thread.sleep().
//				double remainingTime = nextDrawTime - System.nanoTime(); // Check the remaining time to the next draw.
//				remainingTime /= 1000000; Convert nanoseconds to milliseconds to use sleep.
//				
//				if (remainingTime < 0) // If passes the draw interval, no time is left.
//					remainingTime = 0; // Prevents a problem happens if it is a negative time in remainingTime.
//				
//				Thread.sleep((long) remainingTime); /* Thread.sleep only accept long type, sleep accept milliseconds.
//				* Sleep pause the game loop. Nothing happens until the sleep time is over. */
//				
//				nextDrawTime += drawInterval; /* When sleep time is over, the thread awakened now. 
//				* A new interval to the nextDrawTime is added(~0.017s).*/
//				
//			} catch (InterruptedException e) { // It is an exception that can thrown when in sleeping state.
//				e.printStackTrace(); // It helps identify a problem occurred with the exception providing details.
//			}
//		}
//	}
	
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
		
		tileM.draw(g2); // We're gonna core this draw method inside of the TileManager.
		
		player.draw(g2); // Call player.draw from Player class passing the g2 to receive the Graphics 2D.
		
		g2.dispose(); // dispose(): Dispose of this graphics context and release any system resources that it is using.
	}
}




