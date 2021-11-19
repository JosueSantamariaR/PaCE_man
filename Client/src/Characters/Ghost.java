package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class Ghost implements GhostBuilder{
	private boolean is_on;
	Integer posX, posY;
	Integer type;
	Image ghost_image;
	Integer speed = 1;

	/**
	 * This method is in charge of the ghost creations with type and speed.
	 * @param type_aux
	 * @param speed_aux
	 */
	public Ghost(Integer type_aux, Integer speed_aux) {
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

	/**
	 * This method load the ghost image and throws Exception if the image don't exist.
	 * @throws IOException
	 */
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

	/**
	 * Getter of the ghostX position in the board
	 * @return ghostX position
	 */
	public Integer getPosX() {
		return posX;
	}

	/**
	 * Getter of the ghostY position in the board
	 * @return ghostY position
	 */
	public Integer getPosY() {
		return posY;
	}

	/**
	 * Method in charge of the ghost draw and move in the board.
	 * @param g2d
	 * @param direccion
	 */
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

	/**
	 * Check the collision beetween the ghost and pacman
	 * @param pacman
	 * @return
	 */
	public boolean collision(Rectangle pacman) {
		if(pacman.intersects(getBounds())) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check if the ghost is active
	 * @return
	 */
	public boolean get_is_on(){
		return is_on;
	}

	/**
	 * Setter method to change if the ghost is active
	 * @param is_on_aux
	 */
	public void set_is_on(boolean is_on_aux){
		is_on = is_on_aux;
	}

	/**
	 * Method to get the image ghost positions
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 25, 25);
	}

	/**
	 * Method that return the ghost type
	 * @return
	 */
	public Integer getType() {
		return type;
	}

	@Override
	public void setIsOn(boolean is_on) {
		this.is_on = true ;
	}

	@Override
	public void setPosX(Integer posX) {
		this.posX=posX;
	}

	@Override
	public void setPosY(Integer posY) {
		this.posY=posY;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Setter of the ghost speed
	 * @param speed
	 */
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	/**
	 *Method that restar the ghost position
	 */
	public void restart_position() {
		switch(type){
			case 1:
				posY = (Integer) 12 * 25 + 5;
				posX = (Integer) 14 * 25 + 205;
				break;
			case 2:
				posY = (Integer) 12 * 25 + 5;
				posX = (Integer) 15 * 25 + 205;
				break;
			case 3:
				posY = (Integer) 12 * 25 + 5;
				posX = (Integer) 13 * 25 + 205;
				break;
			case 4:
				posY = (Integer) 11 * 25 + 5;
				posX = (Integer) 14 * 25 + 205;
				break;
		}
	}
}
