package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjHeart extends SuperObject{
	GamePanel gp; // Initializes the game panel.
	
	public ObjHeart(GamePanel gp) { // COnstructor.
		this.gp = gp; // Allow the ObjHeart class have access to the GamePanel class.
		
		name = "Heart"; // Put a name into it.
		
		try {
			image1 = ImageIO.read(getClass().getResource("/objects/fullHeart.png")); // Read the image.
			image2 = ImageIO.read(getClass().getResource("/objects/halfHeart.png")); // Read the image.
			image3 = ImageIO.read(getClass().getResource("/objects/emptyHeart.png")); // Read the image.
			image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize); // Scale the image.
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize); // Scale the image.
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize); // Scale the image.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
