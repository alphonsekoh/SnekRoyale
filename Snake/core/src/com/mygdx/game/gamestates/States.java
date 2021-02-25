package com.mygdx.game.gamestates;

public interface States {
    /**
     *Abstract method to update the logic
     * @param time
     */
    void update(float time);

    /**
     *Abstract method to paint the graphic
     */
    void paint();

    /**
     *Abstract method to handle key input from user
     */
    void inputKeyHandle();

    /**
     *Abstract method to dispose of unused material
     */
    void dispose();
}
