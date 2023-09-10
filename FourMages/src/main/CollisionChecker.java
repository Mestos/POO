package main;

import characters.Entity;

public class CollisionChecker {
	GamePanel gp; // Create game panel for this class.
	
	public CollisionChecker(GamePanel gp) { // Constructor.
		this.gp = gp; // Receive game panel.
	}
	
	public void checkTile(Entity entity) { // Define monster and player collision.
		int entityLeftWorldX = entity.worldX + entity.solidArea.x; // Entity's solid area on the initial x axis of Rectangle.
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; // Final Entity's solid area...
		int entityTopWorldY = entity.worldY + entity.solidArea.y; // Entity's solid area on the initial y axis of Rectangle.
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; // Final Entity's solid area...

		int entityLeftCol = entityLeftWorldX / gp.tileSize; // Value on left side from collision of the Column in pixels.
		int entityRightCol = entityRightWorldX / gp.tileSize; // Value on right side from collision of the Column in pixels.
		int entityTopRow = entityTopWorldY / gp.tileSize; // Value on top side from collision of the Column in pixels.
		int entityBottomRow = entityBottomWorldY / gp.tileSize; // Value on bottom side from collision of the Column in pixels.
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize; // Prediction to move left.
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // Point of the entity on the left superior.
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; // Point of the entity on the left inferior.
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { /* If the
			* collision of tileNum1 or tileNum2 is true. The Entity is hitting a tile and cannot move in this direction.*/
				entity.collisionOn = true; // Activating collision.
			}
			break;
		case "down":
			entityBottomRow= (entityBottomWorldY + entity.speed) / gp.tileSize; // Prediction to move down.
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; // Point of the entity on the left inferior.
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; // Point of the entity on the right inferior.
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { /* If the
			* collision of tileNum1 or tileNum2 is true. The Entity is hitting a tile and cannot move in this direction.*/
				entity.collisionOn = true; // Activating collision.
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize; // Prediction to move right.
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; // Point of the entity on the right superior.
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow]; // Point of the entity on the right inferior.
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { /* If the
			* collision of tileNum1 or tileNum2 is true. The Entity is hitting a tile and cannot move in this direction.*/
				entity.collisionOn = true; // Activating collision.
			}
			break;
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; // Prediction to move up.
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // Point of the entity on the left superior.
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; // Point of the entity on the right superior.
			
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) { /* If the
			* collision of tileNum1 or tileNum2 is true. The Entity is hitting a tile and cannot move in this direction.*/
				entity.collisionOn = true; // Activating collision.
			}
			break;
		}
	}
}
