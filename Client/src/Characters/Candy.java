package Characters;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Candy {

    private boolean is_on;
    Integer posX, posY;
    Integer score;
    Image candy_image;

    /**
     * Constructor of the Candy class with candy Score, Xposition and Yposition.
     * @param puntaje_aux
     * @param cordx
     * @param cordy
     */
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

    /**
     * This method load the candy image and throws Exception if the image doesn'texist.
     * @throws IOException
     */
    private void load_image() throws IOException {
        candy_image = new ImageIcon("Images/pastilla.png").getImage().getScaledInstance(25, 25, 1);
    }


    /**
     * Method in charge of the fruit draw in the board.
     * @param g2d
     */
    public void paint(Graphics2D g2d) {
        if (is_on) {
            g2d.drawRect((posX * 25) + 205, (posY * 25) + 5, 15, 15);
            g2d.drawImage(candy_image, (posX * 25) + 205, (posY * 25) + 5, null);
        }
    }

    /**
     * Check if the fruit is active
     * @return
     */
    public boolean get_is_on() {
        return is_on;
    }

    /**
     * Check the collision beetween the candy and pacman
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
     * Getter of the candy score
     * @return
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Setter of the candy x and y position
     * @param cordx
     * @param cordy
     */
    public void setCords(Integer cordx, Integer cordy) {
        posX = cordx;
        posY = cordy;
    }

    /**
     * Check if the candy is active
     * @param is_on
     */
    public void setIs_on(boolean is_on) {
        this.is_on = is_on;
    }
}



