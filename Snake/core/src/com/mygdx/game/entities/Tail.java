package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.gamestates.PlayState;

public class Tail extends GameEntities {


    public Tail(float x, float y) {

        //set WIDTH and HEIGHT
        width = height = PlayState.getGrid();
        setPosition(x, y);
    }

    @Override
    public void update(float dt) {

    }

    /**
     * Method to draw player 1
     * @param sr
     */
    @Override
    public void draw(ShapeRenderer sr) {

        sr.setColor(0, 0.25f, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(x, y, width, height);
        sr.end();
        sr.setColor(0, 2, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x, y, width, height);
        sr.end();

    }

    /**
     * Method to draw player 2
     * @param sr
     * @param isP2
     */
    public void draw(ShapeRenderer sr, boolean isP2) {
        if (isP2) {
            sr.setColor(0.25f, 0, 0, 1);
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(x, y, width, height);
            sr.end();
            sr.setColor(2, 0, 0, 1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.rect(x, y, width, height);
            sr.end();
        }

    }
}
