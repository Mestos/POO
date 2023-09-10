package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {		
		JFrame window = new JFrame(); // Create a window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Let the window close when click in the close button.
		window.setResizable(false); // With it we cannot resize this window.
		window.setTitle("Four Mages"); // Title of our game.
		
		GamePanel gamePanel = new GamePanel(); // Create a GamePanel.
		window.add(gamePanel); // Add the gamePanel to this window.
		
		window.pack(); /* Causes this window to be sized to fit the preferred size 
		* and layouts of its subcomponents(=GamePanel).*/
		
		window.setLocationRelativeTo(null); // Display the window at the center of the screen.
		window.setVisible(true); // It allows us to see this window.
		
		gamePanel.startGameThread(); // Initializes the game loop in gameThread.
	}

}



