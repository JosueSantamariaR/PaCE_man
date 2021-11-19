package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

abstract class Fruits{
	/**
	 * Abstract method of the  getPosX
	 * @return
	 */

	public abstract  Integer getPosX();

	/**
	 * Abstract method of the  getPosY
	 * @return
	 */

	public abstract  Integer getPosY();

	/**
	 * Abstract method of the  state
	 * @return
	 */
	public abstract  boolean getState();

	/**
	 * Abstract method of the  getScore
	 * @return
	 */
	public abstract  Integer getScore();


	/**
	 * Abstract method to get the fruit type
	 * @return
	 */
	public abstract  Integer getType();

}


abstract class cherry extends Fruits{

	@Override
	public Integer getPosX() {
		return getPosX();
	}

	@Override
	public Integer getPosY() {
		return getPosX();
	}

	@Override
	public boolean getState() {
		return getState();
	}

	@Override
	public Integer getType() {
		return getType();
	}

	@Override
	public Integer getScore() {
		return getScore();
	}
}
abstract class orange extends Fruits{

	@Override
	public Integer getPosX() {
		return getPosX();
	}

	@Override
	public Integer getPosY() {
		return getPosX();
	}

	@Override
	public boolean getState() {
		return getState();
	}

	@Override
	public Integer getType() {
		return getType();
	}

	@Override
	public Integer getScore() {
		return getScore();
	}
}
abstract class strawberry extends Fruits{

	@Override
	public Integer getPosX() {
		return getPosX();
	}

	@Override
	public Integer getPosY() {
		return getPosX();
	}

	@Override
	public boolean getState() {
		return getState();
	}

	@Override
	public Integer getType() {
		return getType();
	}

	@Override
	public Integer getScore() {
		return getScore();
	}
}
abstract class grapes extends Fruits{

	@Override
	public Integer getPosX() {
		return getPosX();
	}

	@Override
	public Integer getPosY() {
		return getPosX();
	}

	@Override
	public boolean getState() {
		return getState();
	}

	@Override
	public Integer getType() {
		return getType();
	}

	@Override
	public Integer getScore() {
		return getScore();
	}
}


public class Fruit {
	private boolean is_on;
	Integer posX, posY;
	Integer type;
	Integer puntaje;
	Image fruit_image;

	/**
	 * Constructor of the fruit class with the fruit type,score,and the X and Y position in the map
	 * @param type_aux
	 * @param puntaje_aux
	 * @param cordx
	 * @param cordy
	 */
	public Fruit(Integer type_aux, Integer puntaje_aux, Integer cordx, Integer cordy){
		try {
			is_on = true;
			type = type_aux;
			puntaje = puntaje_aux;
			posX = cordx;
			posY = cordy;
			load_image();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method load the ghost image and throws Exception if the image doesn'texist.
	 * @throws IOException
	 */
	private void load_image() throws IOException {
		switch(type){
			case 1:
				fruit_image = new ImageIcon("Images/cherry.png").getImage().getScaledInstance(16,16,1);
				break;
			case 2:
				fruit_image = new ImageIcon("Images/straw.png").getImage().getScaledInstance(16,16,1);
				break;
			case 3:
				fruit_image = new ImageIcon("Images/orange.png").getImage().getScaledInstance(16,16,1);
				break;
			case 4:
				fruit_image = new ImageIcon("Images/grapes.png").getImage().getScaledInstance(16,16,1);
				break;
		}
	}

	/**
	 * Method in charge of the fruit draw in the board.
	 * @param g2d
	 */
	public void paint(Graphics2D g2d){
		if(is_on){
			g2d.drawRect((posX * 25) + 205, (posY * 25)+5, 15, 15);
			g2d.drawImage(fruit_image,(posX * 25) + 205, (posY * 25)+5, null);
		}
	}


	/**
	 * Check the collision beetween the fruit and pacman
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
	 * Method to get the image fruit positions in the map
	 * @return
	 */
	public Rectangle getBounds() {
		return new Rectangle((Integer ) (posX * 25) + 205, (Integer) (posY * 25)+5, 16, 16);
	}

	/**
	 * Method to get the score fruit
	 * @return
	 */
	public Integer getPuntaje() {
		return puntaje;
	}

	/**
	 * Check if the fruit is active
	 * @return
	 */
	public boolean get_is_on() {
		return is_on;
	}

	/**
	 * Setter of the X and Y fruit position
	 * @param cordx
	 * @param cordy
	 */
	public void setCords(Integer cordx, Integer cordy){
		posX = cordx;
		posY = cordy;
	}

	/**
	 * Setter of the active fruit method.
	 * @param is_on
	 */
	public void setIs_on(boolean is_on) {
		this.is_on = is_on;
	}
}
