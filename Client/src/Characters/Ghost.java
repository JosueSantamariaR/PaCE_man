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

	//int maze[][] = GhostBacktracking.fill_matriz(17, 13);
	
	private Vector<Rectangle> bounds;
	
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

		/*
	private boolean wall_collision(double x2, double y2) {
		for(int i=0; i<bounds.size(); i++) {
			if(bounds.get(i).intersects(new Rectangle((int) (x2), (int) (y2), 25, 25))) {
				return true;
			}
		}
		return false;
	}

	private boolean wall_collision() {
		for(int i=0; i<bounds.size(); i++) {
			if(bounds.get(i).intersects(getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int ) (x+xa), (int) (y+ya), 25, 25);
	}
	
	public Rectangle getBounds_without_moving() {
		return new Rectangle((int )x+5, (int)y, 25-10, 25-10);
	}
	
	public void paint(Graphics2D g){
		g.drawImage(ghost_image, (int ) x, (int) y, null);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth_ghost() {
		return width_ghost;
	}

	public int getHeigth_ghost() {
		return heigth_ghost;
	}
	
	public void restart_position() {
		x = 193;
		y = 224;
	}*/
}
