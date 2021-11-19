package Characters;

public interface GhostBuilder {
    /**
     * Setter of the active ghost
     * @param x
     */
    void setIsOn(boolean x);

    /**
     * Setter of the X position
     * @param x
     */
    void setPosX(Integer x);

    /**
     * Setter of the Y position
     * @param x
     */
    void setPosY(Integer x);

    /**
     * Setter of the ghost type
     * @param x
     */
    void setType(Integer x);

    /**
     *  Setter of the ghost speed
     * @param x
     */
    void setSpeed(Integer x);

}
