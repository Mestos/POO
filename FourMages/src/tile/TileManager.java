package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager { // A manager tiles class.
	GamePanel gp; // Instantiate the game panel to use the tile.
	public Tile[] tile; // A group of tiles in the array.
	public int mapTileNum[][]; // Initialize the mapTileNum to generate the values of the code tiles.
	
	public TileManager(GamePanel gp) { // Constructor.
		this.gp = gp; // Allow the TileManager instance to have access to the GamePanel.
		
		tile = new Tile[10]; // Size of this tile array. We create 10 kinds of tiles(grass, wall, stone, etc.).
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Array to put numbers to generate the world map.
		
		getTileImage(); // Call Method getTileImage.
		loadMap("/maps/world01.txt"); // Call the load Map to run it.
	}
	
	public void getTileImage() { // Load these tile images in this getTileImage Method
		setup(0, "walkway", false); // Pass to setup (arrays's code, png's name, collision).
		setup(1, "wall", true); // Pass to setup (arrays's code, png's name, collision).
		setup(2, "stone", true); // Pass to setup (arrays's code, png's name, collision).
		setup(3, "grass", false); // Pass to setup (arrays's code, png's name, collision).
		setup(4, "floor", false); // Pass to setup (arrays's code, png's name, collision).
	}
	
	public void setup(int index, String imageName, boolean collision) { // Constructor.
		UtilityTool uTool = new UtilityTool(); // Instantiate this new UnityTool.
		
		try {
			tile[index] = new Tile(); // Instantiate the new tile array.
			tile[index].image = ImageIO.read(getClass().getResource("/tiles/" + imageName +".png")); // Read the image.
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize); // Get scaled image from utility tool.
			tile[index].collision = collision; // Set the collision.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) { // Load the map based in the directory of it from a file .txt
		try {
			InputStream is = getClass().getResourceAsStream(filePath); // Use InputStream to import the map.
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Use BufferedReader to read the map.
			
			int col = 0; // Initialize the column.
			int row = 0; // Initialize the row.
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) { // Limits the reading of text file map.
				String line = br.readLine(); // .readLine(): Read a line of text.
				
				while (col < gp.maxWorldCol) { // Going to draw until the limit of the number of columns.
					String numbers[] = line.split(" "); /* String.split(string): Splits this string around matches
					* of the given regular expression. .split(" ") = Split the string at a space. */
					int num = Integer.parseInt(numbers[col]); // Convert string to integer.
					
					mapTileNum[col][row] = num; // Insert the code of tile in the mapTileNum array.
					col++; // Continue this until everything in the number[] is stores in the mapTileNum[][].
				}
				
				if (col == gp.maxWorldCol) { // If the number of columns reaches the maximum limit...
					col = 0; // Column number resets...
					row++; // Going to the next row.
				}
			}
			br.close(); // Close this BufferedReader, because we're not gonna use this.
		} catch (Exception e) {
		}
	}
	
	public void draw(Graphics2D g2) { // Draw method to TileManager class.
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null); // Test for ground tile.
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null); // Test for wall tile.
//		g2.drawImage(tile[2].image, 96 , 0, gp.tileSize, gp.tileSize, null); // Test for stone tile.
//		g2.drawImage(tile[3].image, 96 , 0, gp.tileSize, gp.tileSize, null); // Test for grass tile.
		int worldCol = 0; // Initialize the column.
		int worldRow = 0; // Initialize the row.
//		int x = 0; // Initialize the x axis.
//		int y = 0; // Initialize the y axis.
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) { // Inside this loop will draw tiles.
			int tileNum = mapTileNum[worldCol][worldRow]; // Extract number from mapTileNum and put in the tileNum to draw images.
			
			int worldX = worldCol * gp.tileSize; // Define the size of tile worldX based on the array of map.
			int worldY = worldRow * gp.tileSize; // Define the size of tile worldY based on the array of map.
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
				g2.drawImage(tile[tileNum].image, screenX, screenY, null); // Drawing the tiles without scaling it.
			}
			
			worldCol++; // Going to draw the next tile.
//			x += gp.tileSize; // Sum x position to be drawn in the next tile.
			
			if (worldCol == gp.maxWorldCol) { // When the number of columns reaches the maximum size...
				worldCol = 0; // Index of column resets...
//				x = 0; // x position resets...
				worldRow++; // Going to draw the next row tile...
//				y += gp.tileSize; // Sum y position to be drawn in the next tile.
			}
		}
	}
}








