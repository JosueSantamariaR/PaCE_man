package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Candy {

    private boolean is_on;
    Integer posX, posY;
    Integer score;
    Image candy_image;

    public Candy(Integer puntaje_aux, Integer cordx, Integer cordy) {
        try {
            is_on = true;
            score = puntaje_aux;
            posX = cordx;
            posY = cordy;
            load_image();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load_image() throws IOException {
        candy_image = new ImageIcon("Images/pastilla.png").getImage().getScaledInstance(25, 25, 1);
    }


    public void paint(Graphics2D g2d) {
        if (is_on) {
            g2d.drawRect((posX * 25) + 205, (posY * 25) + 5, 15, 15);
            g2d.drawImage(candy_image, (posX * 25) + 205, (posY * 25) + 5, null);
        }
    }

    public boolean get_is_on() {
        return is_on;
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

    public Integer getScore() {
        return score;
    }

    public void setCords(Integer cordx, Integer cordy) {
        posX = cordx;
        posY = cordy;
    }

    public void setIs_on(boolean is_on) {
        this.is_on = is_on;
    }
}



