package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{ // Import the JPanel in the GamePanel; Runnable = gameThread.
	//	SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile.
	final int scale = 3; // Increase the size by 3 of images.
	
	final int tileSize = originalTileSize * scale; // 48x48 tile.
	final int maxScreenCol = 16; // 16 "tiles" of images.
	final int maxScreenRow = 12; // 12 "tiles" of images.
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels.
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels.
	
	Thread gameThread; // Run a repetition of frame sets.
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the dimensions of window.
		this.setBackground(Color.black); // Define black as background.
		this.setDoubleBuffered(true); // Reduce flickering when the content is updated in GamePanel.
	}

	public void startGameThread() {
		gameThread = new Thread(this); // Pass GamePanel to this Thread. It instantiate a Thread.
		gameThread.start(); // Start the thread. It cores the run method.
	}
	
	// Declaring a method in sub class, which is already present in parent class. 
	@Override // With override line that child class can give its own implementation.
	public void run() { // The gameThread automatically requires the run method. 
		while(gameThread != null) { // Here we create a game loop that is a core of our game.
			/* System.out.println("The game loop is running!"); */ // A test to print a message when the game is running.
			update(); // Call update.
			repaint(); // Call painComponent.
		}
	}
	
	public void update() { // 1 - UPDATE: update information such as character positions.
		
	}
	
	public void paintComponent(Graphics g) { // 2 - DRAW: draw the screen with the updated information.
		super.paintComponent(g); // It needs to use painComponent; "super" means the parent class of the JPanel in this case.
		
	}
}




