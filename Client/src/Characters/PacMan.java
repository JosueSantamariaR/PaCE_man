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

    public Image getPacmanLives() {
        return pacmanLives;
    }

    Image pacmanLives = new ImageIcon("Images/vidas.png").getImage().getScaledInstance(30, 30, 1);

    Integer velX = 0;
    Integer velY = 0;

    Rectangle colisionZone;
    Boolean notColliding = true;

    Integer option = 0;
    Integer score = 0;
    Integer currentLevel = 1;

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getNotColliding() {
        return notColliding;
    }

    public void resetPacman(){
        posX = 460;
        posY = 300;
    }

    public void setNotColliding(Boolean notColliding) {
        this.notColliding = notColliding;
    }

    public Rectangle getColisionZone() {
        return colisionZone;
    }

    public void setColisionZone(Rectangle colisionZone) {
        this.colisionZone = colisionZone;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }


    public Image getpacmanImg() {
        return pacmanImg;
    }

    public void setpacmanImg(Image pacmanImg) {
        this.pacmanImg = pacmanImg;

    }

    public void addLives(Integer lives) { this.lives += lives; }

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

    public Integer getOption() {

        return this.option;
    }

    public Integer getVelX() {
        return velX;
    }

    public void setVelX(Integer velX) {
        this.velX = velX;
    }

    public Integer getVelY() {
        return velY;
    }

    public void setVelY(Integer velY) {
        this.velY = velY;
    }

}
