package Entities;

import javax.swing.*;
import java.awt.*;

public class Snake extends GameEntities{

    private boolean isRotateLeft;
    private boolean isRotateRight;

    private boolean isDead;
    private boolean isEat;

    private double score;
    private int live;

    private ImageIcon mouth;

    public enum Facing {
        UP, DOWN, LEFT, RIGHT
    }

    private Facing facing;

    public Facing getFacing() {
        return facing;
    }

    public Snake(int size) {
        width = height = size;
        speed = 1;
        live = 3;

        reset();
    }

    public void setLeft(boolean b) {
        switch (facing) {
            case UP:
                if (setRotateLeft(b) && b)
                    facing = Facing.LEFT;
                break;
            case DOWN:
                if (setRotateRight(b) && b)
                    facing = Facing.LEFT;
                break;
        }
    }

    private boolean setRotateRight(boolean b) {
        if (!this.isRotateRight && !isRotateLeft) {
            this.isRotateRight = b;
            return true;
        }
        return false;
    }

    private boolean setRotateLeft(boolean b) {
        if (!this.isRotateLeft && !isRotateRight) {
            this.isRotateLeft = b;
            return true;
        }
        return false;
    }

    public void setRight(boolean b) {
        switch (facing) {
            case DOWN:
                if (setRotateLeft(b) && b)
                    facing = Facing.RIGHT;
                break;
            case UP:
                if (setRotateRight(b) && b)
                    facing = Facing.RIGHT;
        }
    }

    public void setDown(boolean b) {
        switch (facing) {
            case LEFT:
                if (setRotateLeft(b) && b)
                    facing = Facing.DOWN;
                break;
            case RIGHT:
                if (setRotateRight(b) && b)
                    facing = Facing.DOWN;
                break;
        }
    }

    public void setUp(boolean b) {
        switch (facing) {
            case RIGHT:
                if (setRotateLeft(b) && b)
                    facing = Facing.UP;
                break;
            case LEFT:
                if (setRotateRight(b) && b)
                    facing = Facing.UP;
                break;
        }
    }

    private void reset() {
        radian = (float)Math.PI / 2;
        facing = Facing.RIGHT;

        isDead = false;
    }

    public int getLive() {

        return live;
    }

    public double getScore() {

        return score;
    }

    public void collide() {
        isDead = !isDead;
        live -= 1;
    }

    public boolean isDead(){
        return isDead;
    }

    public boolean ate(){
        return isEat;
    }

    public boolean eat(boolean b, int score) {
        if (b) {
            this.score += score;
            isEat = true;
        }
        return b;
    }

    public void resetPosition(float x, float y, Facing facing) {
    //reset the rotation to prevent start with rotating direction
        isRotateLeft = false;
        isRotateRight = false;

        setPosition(x,y);
        this.facing = facing;
        switch (facing){
            case UP:
                radian =(float) Math.PI / 2;
                break;
            case DOWN:
                radian =(float) Math.PI * 3 / 2;
                break;
            case LEFT:
                radian =(float) Math.PI * 2;
                break;
            case RIGHT:
                radian =(float) Math.PI;
                break;
        }

        isDead = false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0.15f, 0, 1));
        g.drawRect((int)x, (int)y, (int)width, (int)height);
        g.fillRect((int)x, (int)y, (int)(width), (int)(height));

        g.setColor(new Color(0, 1f, 0, 0.5f));
        g.drawRect((int)x, (int)y, (int)width, (int)height);

        g.setColor(new Color(0, 0.45f, 0, 1));
        g.drawRect((int)x+4, (int)y+4, (int)width-8, (int)height-8);
        g.fillRect((int)x+4, (int)y+4, (int)width-8, (int)height-8);

        //create Snake 2
//        mouth = new ImageIcon("rightmouth.png");
//        mouth.paintIcon(null, g, (int)x, (int)y);
    }

    @Override
    public void update(float dt) {
        isEat = false;

        if (isRotateLeft){
            radian += (float) Math.PI / 2;
            isRotateLeft = false;
        }
        if (isRotateRight) {
            radian -= (float) Math.PI / 2;
            isRotateRight = false;
        }

        //calculate direction
        directionX = Math.round(Math.cos(radian)) * speed;
        directionY = Math.round(Math.sin(radian)) * speed;

        //set new position
        x += directionX;
        y += directionY;
    }
}
