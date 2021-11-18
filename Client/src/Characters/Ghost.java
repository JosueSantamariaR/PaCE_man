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

	private int count_control_move_var;
	private int control_move_var=2;
	private double speed=1;

	public Ghost(int type_aux) {
		is_on = true;
		type = type_aux;

		try {
			load_image();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load_image() throws IOException {
		switch(type){
			case 1:
				ghost_image = new ImageIcon("Images/blinky.gif").getImage().getScaledInstance(16,16,1);
				posY = 12;
				posX = 14;
				break;
			case 2:
				ghost_image = new ImageIcon("Images/pinky.gif").getImage().getScaledInstance(16,16,1);
				posY = 12;
				posX = 15;
				break;
			case 3:
				ghost_image = new ImageIcon("Images/inky.gif").getImage().getScaledInstance(16,16,1);
				posY = 12;
				posX = 13;
				break;
			case 4:
				ghost_image = new ImageIcon("Images/clyde.gif").getImage().getScaledInstance(16,16,1);
				posY = 11;
				posX = 14;
				break;
		}
	}

	public void paint(Graphics2D g2d){
		if(is_on){
			g2d.drawRect((posX * 25) + 205, (posY * 25)+5, 15, 15);
			g2d.drawImage(ghost_image,(posX * 25) + 205, (posY * 25)+5, null);
		}
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

	public Rectangle getBounds() {
		return new Rectangle((int ) (posX * 25) + 205, (int) (posY * 25)+5, 25, 25);
	}
	
	public Rectangle getBounds_without_moving() {
		return new Rectangle((int )posX+5, (int)posY, 25-10, 25-10);
	}

	
	public void restart_position() {
		switch(type){
			case 1:
				posY = 12;
				posX = 14;
				break;
			case 2:
				posY = 12;
				posX = 15;
				break;
			case 3:
				posY = 12;
				posX = 13;
				break;
			case 4:
				posY = 11;
				posX = 14;
				break;
		}
	}
}
