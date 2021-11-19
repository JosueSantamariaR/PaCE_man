package Characters;

import javax.swing.*;
import java.awt.*;

public class PacMan {

    Integer posX = 460;
    Integer posY = 300;
    Integer lives = 3;
    Image pacmanImg;
    Image pacmanRightImage = new ImageIcon("Images/PacmanR.gif").getImage().getScaledInstance(16, 16, 1);
    Image pacmanLeftImage = new ImageIcon("Images/PacmanL.gif").getImage().getScaledInstance(16, 16, 1);
    Image pacmanDownImage = new ImageIcon("Images/PacmanD.gif").getImage().getScaledInstance(16, 16, 1);
    Image pacmanUpImage = new ImageIcon("Images/PacmanU.gif").getImage().getScaledInstance(16, 16, 1);
    Image pacmanLives = new ImageIcon("Images/vidas.png").getImage().getScaledInstance(30, 30, 1);
    Integer velX = 0;
    Integer velY = 0;
    Rectangle colisionZone;
    Boolean notColliding = true;
    Integer option = 0;
    Integer score = 0;
    Integer currentLevel = 1;


    /**
     * Getter of the pacman lives
     * @return
     */
    public Image getPacmanLives() {
        return pacmanLives;
    }

    /**
     * Getter of the pacman current level
     * @return
     */
    public Integer getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Setter of the pacman level
     * @param currentLevel
     */
    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Getter of the pacman current score
     * @return
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Setter of the pacman score
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Method to check if the pacman is collinding with objects
     * @return
     */
    public Boolean getNotColliding() {
        return notColliding;
    }

    /**
     * Method to use it when pacman lose one live. Reset the position
     */
    public void resetPacman(){
        posX = 460;
        posY = 300;
    }

    /**
     * Method to set if the pacman is collinding with object
     * @param notColliding
     */
    public void setNotColliding(Boolean notColliding) {
        this.notColliding = notColliding;
    }


    /**
     * Method to check the rectangule colision zone of the pacman to compare with the objects
     * @return
     */
    public Rectangle getColisionZone() {
        return colisionZone;
    }

    /**
     * Method to setter the rectangule colision zone of the pacman to compare with the objects
     * @param colisionZone
     */
    public void setColisionZone(Rectangle colisionZone) {
        this.colisionZone = colisionZone;
    }

    /**
     * Getter of the pacman X position
     * @return
     */
    public Integer getPosX() {
        return posX;
    }

    /**
     * Setter of the pacman X position
     * @param posX
     */
    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    /**
     * Getter of the pacman Y position
     * @return
     */
    public Integer getPosY() {
        return posY;
    }

    /**
     * Setter of the pacman Y position
     * @param posY
     */
    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    /**
     * Getter of the pacman lives
     * @return
     */
    public Integer getLives() {
        return lives;
    }

    /**
     * Setter of the pacman lives
     * @param lives
     */
    public void setLives(Integer lives) {
        this.lives = lives;
    }

    /**
     * Getter of the pacman image
     * @return
     */

    public Image getpacmanImg() {
        return pacmanImg;
    }

    /**
     * Setter of the pacman image
     * @param pacmanImg
     */
    public void setpacmanImg(Image pacmanImg) {
        this.pacmanImg = pacmanImg;

    }

    /**
     * Method to add pacman lives.
     * @param lives
     */
    public void addLives(Integer lives) { this.lives += lives; }

    /**
     * Method in charge of the image in differents moves.
     * @param option1
     */
    public void setDir(Integer option1) {
        switch (option1) {
            case 0:
                this.setpacmanImg(pacmanRightImage);
                break;
            case 1:
                this.setpacmanImg(pacmanLeftImage);
                break;
            case 2:
                this.setpacmanImg(pacmanDownImage);
                break;
            case 3:
                this.setpacmanImg(pacmanUpImage);
                break;
        }
        this.option = option1;
    }

    /**
     * Getter of the option
     * @return
     */
    public Integer getOption() {

        return this.option;
    }

    /**
     * Getter of the pacman velocity in X
     * @return
     */
    public Integer getVelX() {
        return velX;
    }

    /**
     * Setter of the pacman velocity in X
     * @param velX
     */
    public void setVelX(Integer velX) {
        this.velX = velX;
    }

    /**
     * Getter of the pacman velocity in Y
     * @return
     */
    public Integer getVelY() {
        return velY;
    }

    /**
     * Setter of the pacman velocity in Y
     * @param velY
     */
    public void setVelY(Integer velY) {
        this.velY = velY;
    }

}
