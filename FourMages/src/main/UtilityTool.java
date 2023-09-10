package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool { // Tool box class to keep convenient functions in other classes and optimize the codes.
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType()); /* It
		* instantiate the BufferedImage and prepares the image to be scaled before the game loop to reduce 
		* the time of drawing images.*/
		Graphics2D g2 = scaledImage.createGraphics(); /* Creates a Graphics2D, 
		* which can be used to draw into this Buffered Image.*/
		g2.drawImage(original, 0, 0, width, height, null); // The g2 draws the image with this size.
		g2.dispose(); // Releases any system resources that is not using.
		
		return scaledImage; // Returns the image scaled.
	}
}
