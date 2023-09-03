package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager { // A manager tiles class.
	GamePanel gp; // Create a game panel to use the tile.
	Tile[] tile; // A group of tiles in the array.
	int mapTileNum[][]; // Initialize the mapTileNum to generate the values of the code tiles.
	
	public TileManager(GamePanel gp) { // Constructor.
		this.gp = gp; // Allow the TileManager instance to have access to the GamePanel.
		
		tile = new Tile[10]; // Size of this tile array. We create 10 kinds of tiles(grass, wall, stone, etc.).
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; // Array to put numbers to generate the maps.
		
		getTileImage(); // Call Method getTileImage.
		loadMap(); // Call the load Map to run it.
	}
	
	public void getTileImage() { // Load these tile images in this getTileImage Method
		try {
			tile[0] = new Tile(); // Instantiate this tile array.
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/tile.png")); // Ground tile.
			tile[1] = new Tile(); // Instantiate this tile array.
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png")); // Wall tile.
			tile[2] = new Tile(); // Instantiate this tile array.
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/stone.png")); // Stone tile.
			tile[3] = new Tile(); // Instantiate this tile array.
			tile[3].image = ImageIO.read(getClass().getResource("/tiles/grass.png")); // Grass tile.
			tile[4] = new Tile(); // Instantiate this tile array.
			tile[4].image = ImageIO.read(getClass().getResource("/tiles/floor.png")); // Grass tile.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt"); // Use InputStream to import the map01.
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Use BufferedReader to read the map01.
			
			int col = 0; // Initialize the column.
			int row = 0; // Initialize the row.
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) { // Limits the reading of text file map01.
				String line = br.readLine(); // .readLine(): Read a line of text.
				
				while (col < gp.maxScreenCol) { // Going to draw until the limit of the number of columns.
					String numbers[] = line.split(" "); /* String.split(string): Splits this string around matches
					* of the given regular expression. .split(" ") = Split the string at a space. */
					int num = Integer.parseInt(numbers[col]); // Convert string to integer.
					
					mapTileNum[col][row] = num; // Insert the code of tile in the mapTileNum array.
					col++; // Continue this until everything in the number[] is stores in the mapTileNum[][].
				}
				
				if (col == gp.maxScreenCol) { // If the number of columns reaches the maximum limit...
					col = 0; // Column number resets...
					row++; // Going to the next row.
				}
			}
			br.close(); // Close this BufferedReader, because we're not gonna use this.
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void draw(Graphics2D g2) { // Draw method to TileManager class.
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null); // Test for ground tile.
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null); // Test for wall tile.
//		g2.drawImage(tile[2].image, 96 , 0, gp.tileSize, gp.tileSize, null); // Test for stone tile.
//		g2.drawImage(tile[3].image, 96 , 0, gp.tileSize, gp.tileSize, null); // Test for grass tile.
		int col = 0; // Initialize the column.
		int row = 0; // Initialize the row.
		int x = 0; // Initialize the x axis.
		int y = 0; // Initialize the y axis.
		
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) { // Inside this loop will draw tiles.
			int tileNum = mapTileNum[col][row]; // Extract number from mapTileNum and put in the tileNum to draw images.
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null); // Drawing the tiles.
			col++; // Going to draw the next tile.
			x += gp.tileSize; // Sum x position to be drawn in the next tile.
			
			if (col == gp.maxScreenCol) { // When the number of columns reaches the maximum size...
				col = 0; // Index of column resets...
				x = 0; // x position resets...
				row++; // Going to draw the next row tile...
				y += gp.tileSize; // Sum y position to be drawn in the next tile.
			}
		}
	}
}








