package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
 * Clase para el manejo de la fruta que aparece en medio del mapa
 */
public class Fruit {
	private boolean is_on;
	Integer posX, posY;
	Integer type;
	Integer puntaje;
	Image fruit_image;
	
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
	
	public void paint(Graphics2D g2d){
		if(is_on){
			g2d.drawRect((posX * 25) + 205, (posY * 25)+5, 15, 15);
			g2d.drawImage(fruit_image,(posX * 25) + 205, (posY * 25)+5, null);
		}
	}


	public boolean collision(Rectangle pacman) {
		if(pacman.intersects(getBounds())) {
			return true;
		}
		return false;
	}

	public Rectangle getBounds() {
		return new Rectangle((Integer ) (posX * 25) + 205, (Integer) (posY * 25)+5, 16, 16);
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public boolean get_is_on() {
		return is_on;
	}

	public void setCords(Integer cordx, Integer cordy){
		posX = cordx;
		posY = cordy;
	}

	public void setIs_on(boolean is_on) {
		this.is_on = is_on;
	}
}
