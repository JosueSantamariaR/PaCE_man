package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class Ghost {
	private boolean is_on;
	int posX, posY;
	int type;
	Image ghost_image;

	int speed = 1;

	public Ghost(int type_aux, int speed_aux) {
		is_on = true;
		type = type_aux;
		speed = speed_aux;

		restart_position();
		try {
			load_image();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load_image() throws IOException{
		if(!is_on){
			this.ghost_image = new ImageIcon("Images/dead_ghost.gif").getImage().getScaledInstance(16,16,1);
		} else{
			switch(type){
				case 1:
					this.ghost_image = new ImageIcon("Images/blinky.gif").getImage().getScaledInstance(16,16,1);
					break;
				case 2:
					this.ghost_image = new ImageIcon("Images/pinky.gif").getImage().getScaledInstance(16,16,1);
					break;
				case 3:
					this.ghost_image = new ImageIcon("Images/inky.gif").getImage().getScaledInstance(16,16,1);
					break;
				case 4:
					this.ghost_image = new ImageIcon("Images/clyde.gif").getImage().getScaledInstance(16,16,1);
					break;
			}
		}
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void paint(Graphics2D g2d, String direccion){
		switch (direccion){
			case "R":
				posX -= speed;
				break;
			case "L":
				posX += speed;
				break;
			case "U":
				posY -= speed;
				break;
			case "D":
				posY += speed;
				break;
		}
		g2d.drawRect(posX, posY, 15, 15);
		g2d.drawImage(ghost_image, posX, posY, null);
	}

	public boolean collision(Rectangle pacman) {
		if(pacman.intersects(getBounds())) {
			return true;
		}
		return false;
	}

	public boolean get_is_on(){
		return is_on;
	}

	public void set_is_on(boolean is_on_aux){
		is_on = is_on_aux;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 25, 25);
	}

	
	public void restart_position() {
		switch(type){
			case 1:
				posY = (int) 12 * 25 + 5;
				posX = (int) 14 * 25 + 205;
				break;
			case 2:
				posY = (int) 12 * 25 + 5;
				posX = (int) 15 * 25 + 205;
				break;
			case 3:
				posY = (int) 12 * 25 + 5;
				posX = (int) 13 * 25 + 205;
				break;
			case 4:
				posY = (int) 11 * 25 + 5;
				posX = (int) 14 * 25 + 205;
				break;
		}
	}
}
