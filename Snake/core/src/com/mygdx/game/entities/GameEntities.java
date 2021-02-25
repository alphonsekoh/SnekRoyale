package com.mygdx.game.entities;

import com.mygdx.game.Main;

public abstract class GameEntities implements Entities{
    //postition
    float x;
    float y;

    //vector
    float dx;
    float dy;

    //angle direction in radians
    float radians;

    float speed;

    //size
    float width;
    float height;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Sets coordinates for center of a body part.
     *
     * @param x - WIDTH coordinate
     * @param y - eight coordinate
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return true if coordinates x and y are the same,
     *
     * @param x  - point x
     * @param y- point y
     * @return true or false
     */
    public boolean contains(float x, float y) {
        return (getX() == x && getY() == y);
    }


    /**
     * When the snake go into the border, will warp the snake to appear on the other side.
     */
    public void wrap() {
        if (x < 0) {
            x = Main.WIDTH - width;
        }
        if (x > Main.WIDTH - width) {
            x = 0;
        }
        if (y < 0) {
            y = Main.HEIGHT - height;
        }
        if (y > Main.HEIGHT - height) {
            y = 0;
        }

    }

}
