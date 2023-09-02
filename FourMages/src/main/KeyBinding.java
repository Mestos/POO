package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinding implements KeyListener{ /* KeyListener: The listener 
interface for receiving keyboard events(keystrokes).*/
	public boolean leftPressed, downPressed, rightPressed, upPressed;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event. KeyBidingCodes.
		
		if (code == KeyEvent.VK_A) // Bind the button A.
			leftPressed = true; // Button A was pressed!
		if (code == KeyEvent.VK_S) // Bind the button S.
			downPressed = true; // Button S was pressed!
		if (code == KeyEvent.VK_D) // Bind the button D.
			rightPressed = true; // Button D was pressed!
		if (code == KeyEvent.VK_W) // Bind the button W.
			upPressed = true; // Button W was pressed!
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_A) // Bind the button A.
			leftPressed = false; // Button A was released!
		if (code == KeyEvent.VK_S) // Bind the button S.
			downPressed = false; // Button S was released!
		if (code == KeyEvent.VK_D) // Bind the button D.
			rightPressed = false; // Button D was released!
		if (code == KeyEvent.VK_W) // Bind the button W.
			upPressed = false; // Button W was released!
	} 

}
