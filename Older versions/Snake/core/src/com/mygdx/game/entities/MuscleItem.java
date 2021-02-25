package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MuscleItem extends Items{

    public MuscleItem(float x, float y) {
        super(x, y);
    }

    @Override
    public boolean shouldRemove() {
        return super.shouldRemove();
    }

    @Override
    public boolean contains(float x, float y) {
        return super.contains(x, y);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public int getScore() {
        return 20;
    }

    @Override
    public void draw(SpriteBatch sb, Texture texture) {
        super.draw(sb, texture);
    }
}
