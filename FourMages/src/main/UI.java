package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.ObjHeart;
import object.SuperObject;

public class UI { // Handle all the on-screen UI (text message and item icons, etc.).
	GamePanel gp; // Initializes the game panel.
	Graphics2D g2; // Initializes the Graphics2D to draw.
	Font arial_40; // Declares the font.
	BufferedImage clockImage; // Prepare buffered image of the image of the clock.
	BufferedImage fullHeart, halfHeart, emptyHeart; // Prepare buffered image of the images of the hearts.
	
	double playTime; // Variable for time count.
	DecimalFormat dFormat = new DecimalFormat("#0.00"); // Adjust format of the time.
	
	public UI(GamePanel gp) { // Constructor.
		this.gp = gp; // Allow the UI class have access to GamePanel class.
		
		arial_40 = new Font("Arial", Font.PLAIN, 40); // Instantiate (Font name, font style, font size).
		
		try {
			clockImage = ImageIO.read(getClass().getResource("/tiles/stair.png")); // Load Image.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// CREATE HUD OBJECT
		SuperObject heart = new ObjHeart(gp); // Call SuperObject class and pass those heart.
		fullHeart = heart.image1; // Full heart receives the image.
		halfHeart = heart.image2; // Half heart receives the image. 
		emptyHeart = heart.image3; // Empty heart receives the image.
	}
	
	public void draw(Graphics2D g2) { // This draw method will be include in the game loop.
		// TIME
		g2.setFont(arial_40); // Don't create instance in game loop, because consume time and resource.
		g2.setColor(Color.white); // Set color of the font.
		g2.drawImage(clockImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null); // Draw time image.
		g2.drawString("" + dFormat.format(playTime), 75, 50); /* Show on screen the time number. 
		 * The drawImages's y is different of drawString's y.*/
		playTime += (double)1/60; // 60 times per second.
		drawPlayerLife(g2);
	}
	
	public void drawPlayerLife(Graphics2D g2) {
		int x = gp.tileSize / 2; // Position of heart on the x axis.
		int y = gp.tileSize; // Position of heart on the y axis.
		int i = 0; // Initializes number of hearts..
		
		// DRAW MAX LIFE (EMPTY HEART)
		while (i < gp.player.maxLife / 2) { // While it was not drawn the full empty hearts.
			g2.drawImage(emptyHeart, x, y, null); // Create numbers of empty hearts based on the life's value.
			i++; // Increasing the empty hearts.
			x += gp.tileSize / 2; // Empty hearts side by side going to the right.
		}
		
		// RESET
		x = gp.tileSize / 2; // Reset the x position to Redraw.
		y = gp.tileSize; // Reset the x position to Redraw.
		i = 0; // Reset the number i to Redraw the life's value.
		
		// FILL WITH RED THE CURRENT LIFE
		while (i < gp.player.life) { // While it was not drawn the actual value's life.
			g2.drawImage(halfHeart, x, y, null); // Draw the half heart.
			
			i++;
			
			if (i < gp.player.life) { // If the life's value was not reach. Then, draw a full heart instead.
				g2.drawImage(fullHeart, x, y, null); // Draw the full heart.
			}
			
			i++; // Go to the next heart.
			x += gp.tileSize / 2; // Draw a new heart on the right of the last heart drawn.
		}
	}
}





