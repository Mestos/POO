package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp; // Use the GamePanel class...
	KeyHandler keyH; // also the KeyHandler.
	
	public Player(GamePanel gp, KeyHandler keyH) { // Constructor.
		this.gp = gp; // Using it in the GamePanel class...
		this.keyH = keyH; // and Key Handler class.
		
		setDefaultValues(); // Use values of setDefaultValues Method in the player.
		getPlayerImage(); // Access getPlayerImage Method to show the player.
	}
	
	public void setDefaultValues() { // Set player`s default values.
		x = 100; // Set the initial position for the player in X axis.
		y = 100; // Set the initial position for the player in Y axis.
		speed = 4; // Set the initial speed for the player. 4 pixels moving.
		direction = "down"; // Initial image when starts the game.
	}
	
	public void getPlayerImage() { // A method to access the directory of the player's images.
		try {
			left1 = ImageIO.read(getClass().getResource("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/left2.png"));
			down1 = ImageIO.read(getClass().getResource("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/down2.png"));
			right1 = ImageIO.read(getClass().getResource("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/right2.png"));
			up1 = ImageIO.read(getClass().getResource("/player/down1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/down2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() { // Update information such as character positions.
	    /* The origin stays in the left corner (X: 0 and Y: 0). 
	     * X increases going to the right and Y increases going down.*/
		if (keyH.leftPressed == true || keyH.downPressed == true|| keyH.rightPressed == true || keyH.upPressed == true) {
			if (keyH.leftPressed == true) { // It calls keyPressed method when A button is pressed.
		        direction = "left"; // Increase
		        x -= speed; // Makes the player character go left.
		    }
		    if (keyH.downPressed == true) { // It calls keyPressed method when S button is pressed.
		        direction = "down";
		        y += speed; // Makes the player character go down.
		    }
		    if (keyH.rightPressed == true) { // It calls keyPressed method when D button is pressed.
		        direction = "right";
		        x += speed; // Makes the player character go right.
		    }
		    if (keyH.upPressed == true) { // It calls keyPressed method when W button is pressed.
		        direction = "up";
		        y -= speed; // Makes the player character go up.
		    }
		    
		    spriteCounter++; // A counter to mark the change of the sprite's speed-time.
			
			if (spriteCounter > 12) { // When spriteConter pass 12 frames...
				if (spriteNum == 1) { // it changes the image1 to image2...
					spriteNum = 2;
				} 
				else if (spriteNum == 2) { // or the image2 to image 1...
					spriteNum = 1;
				}
				spriteCounter = 0; // Then reset the spriteCounter.
			}
		}
	}
	
	public void draw(Graphics2D g2) { // Use the g2 inside the Player class.
//		g2.setColor(Color.yellow); // setColor(Color c): Sets a color to use for drawing objects.
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize); // fillRect: Draw a rectangle and fills  it with the specified color.
		
		BufferedImage image = null; // Initialize the image buffer.
		
		switch(direction) { // Switch the image according the move direction and the time of button pressed.
		case "left":
			if (spriteNum == 1)
				image = left1;
			if (spriteNum == 2)
				image = left2;
			break;
		case "down":
			if (spriteNum == 1)
				image = down1;
			if (spriteNum == 2)
				image = down2;
			break;
		case "right":
			if (spriteNum == 1)
				image = right1;
			if (spriteNum == 2)
				image = right2;
			break;
		case "up":
			if (spriteNum == 1)
				image = up1;
			if (spriteNum == 2)
				image = up2;
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // Draw the image based in the axis and resolution.
	}
}








