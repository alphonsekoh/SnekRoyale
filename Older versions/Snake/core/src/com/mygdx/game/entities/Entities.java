package com.mygdx.game.entities;

public interface Entities {

    void setPosition(float x, float y);
    boolean contains(float x, float y);
    void wrap();

}
