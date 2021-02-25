package Entities;

import java.awt.*;

import static GameState.GameState.HEIGHT;
import static GameState.GameState.WIDTH;

public abstract class GameEntities {
    float x;
    float y;

    float directionX;
    float directionY;

    float radian;

    float speed;

    float width;
    float height;

    public float getX(){return x;}
    public float getY(){return y;}

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean checkCoordinate(float x, float y){
        return (getX()==x && getY()==y);
    }

    public abstract void draw(Graphics g);

    public abstract void update(float dt);

    public void wrap() {
        if (x < 0) {
            x = WIDTH - width;
        }
        if (x > WIDTH - width) {
            x = 0;
        }
        if (y < 0) {
            y = HEIGHT - height;
        }
        if (y > HEIGHT - height) {
            y = 0;
        }

    }
}
