package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Entities {

    /**
     *Abstract method to update the game
     * @param dt
     */
    void update(float dt);


    /**
     *Abstract method to draw the shape of the entities
     * @param sr
     */
    void draw(ShapeRenderer sr);

}
