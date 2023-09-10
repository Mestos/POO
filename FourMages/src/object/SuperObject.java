package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject { // Declare some classes and objects variables.
	public BufferedImage image1, image2, image3; // Initializes the load of image buffer.
	public String name; // Initializes name.
	public boolean collision = false; // Initializes of collision.
	public int worldX, worldY; // Initializes the coordinates.
	UtilityTool uTool = new UtilityTool(); // Instantiate UtilityTool.
	
	public void draw(Graphics2D g2, GamePanel gp) { // Constructor.
		int screenX = worldX - gp.player.worldX + gp.player.screenX; // Define the screen position on the x axis.
		int screenY = worldY - gp.player.worldY + gp.player.screenY; // Define the screen position on the y axis.
		/* Explanation: The worldX is the position on the map. The screenX is where we draw it.
		 * So, we subtract the distance of the travel's player on the worldX and sum with the middle screen position.*/
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			// Center of the screen - player screen x axis. +1 tile in the +x axis (+ gp.tileSize).
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			// Center of the screen + player screen x axis. +1 tile in the -x axis (+ gp.tileSize).
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
			// Center of the screen - player screen y axis. +1 tile in the +y axis (+ gp.tileSize).
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { 
			// Center of the screen + player screen y axis. +1 tile in the -y axis (+ gp.tileSize).
			g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null); // Drawing the object image.
		}
	}
}
