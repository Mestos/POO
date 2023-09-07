package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity { // Entity class: This stores variable that will be used in player, monster and NPC classes.
	public int worldX, worldY, speed; // Attributes of the entity.
	public BufferedImage left1, left2, down1, down2, right1, right2, up1, up2; /* BufferedImage describes
	* an image with an accessible buffer of image data. (We use this to store our image files).*/
	public String direction; // A direction that makes the entity go.
	public int spriteCounter = 0; // The number of images passed.
	public int spriteNum = 1; // The code of the sprite image to be shown.
	public Rectangle solidArea; // Can create an invisible rectangle with this class and store data such as width, height...
	public boolean collisionOn = false; // Create a collision for entity.
}
