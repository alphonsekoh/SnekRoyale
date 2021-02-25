package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.gamestates.PlayState;

public class Items extends GameEntities {

    private float removeTimer;
    private final float removeTime;
    private boolean remove;
    private int score;


    public Items(float x, float y) {

        setPosition(x, y);
//        getRandomFruit();
        removeTimer = 0;
        removeTime = 10;

        //set WIDTH and HEIGHT
        width = height = PlayState.getGrid();
        score = 10;

    }

    /**
     * Check if the fruit i signed to be removed.
     *
     * @return - true or false.
     */
    public boolean shouldRemove() {
        return remove;
    }

    /**
     * Checks if a given coordinate is the same of the fruit. If it those, sets the fruit to be removed.
     *
     * @param x - point x
     * @param y - point y
     * @return - true or false.
     */
    public boolean contains(float x, float y) {
        remove = super.contains(x, y);
        return remove;
    }

    /**
     * Gets the score of the piece of fruit to sum to the player.
     *
     * @return - score.
     */
    public int getScore() {
        return score;
    }

    @Override
    public void update(float dt) {
        removeTimer += dt;
        if (removeTimer > removeTime) {
            removeTimer = 0;
            remove = true;
        }
    }

    @Override
    public void draw(ShapeRenderer sr) {

    }
    public void draw(SpriteBatch sb, Texture texture) {
        sb.begin();
        sb.draw(texture,x , y, width , height);
        sb.end();
    }
}
