package Entities;

import java.awt.*;
import java.util.Random;

public class Powerup extends GameEntities{

    private String type;
    private int posX;
    private int posY;
    private int[] posXarray;
    private int[] posYarray;
    private Random random;
    private String picName;
    private int score;

    public Powerup(){

        random = new Random();

        posXarray = new int[]{25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
                350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650,
                675, 700, 725, 750, 775, 800, 825, 850};
        posYarray = new int[]{75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350,
                375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
        posX = posXarray[random.nextInt(34)];
        posY = posYarray[random.nextInt(23)];
        type = "apple";

        //sets the score, if bonus fruit it adds 100 points, else 10 points.
        score = 1;

    }

    public Powerup(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void powerupEffect(SnakeObject affectedSnake){
        if(eatPowerup(affectedSnake)){

            int tempLength = affectedSnake.getSnakeLength();
            affectedSnake.setSnakeLength(tempLength+1);
            setPos();

        }
    }
    public void setType(String type){

        this.type = type;

    }
    public String getType(){

        return type;

    }
    public void setPos(){


        posX = posXarray[random.nextInt(34)];
        posY = posYarray[random.nextInt(23)];

    }
    public int getPosX(){

        return posX;

    }
    public int getPosY(){

        return posY;

    }
    public void setPicName(String picName){

        this.picName = picName;

    }
    public String getPicName(){

        return picName;

    }
    public boolean eatPowerup(SnakeObject affectedSnake){

        int tempX = getPosX();
        int tempY = getPosY();
        if(tempX == affectedSnake.getXYPosition("x",0) && tempY == affectedSnake.getXYPosition("y",0)){

            return true;
        }

        return false;

    }


    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update(float dt) {

    }

    public int getScore() {
        return score;
    }
}

class Apple extends Powerup{

    Apple(){

        setType("apple");
        setPicName("apple.png");


    }
    public void powerupEffect(SnakeObject affectedSnake){



        //this checks if the snake is eating the apple, and if it is ,length + 1
        if(eatPowerup(affectedSnake)){

            //adds snake score
            int tempScore = affectedSnake.getScore();
            affectedSnake.setScore(tempScore + 1);

            //adds snake length
            int tempLength = affectedSnake.getSnakeLength();
            affectedSnake.setSnakeLength(tempLength+1);

            //sets new position of powerup
            setPos();

        }


    }
}

class Muscle extends Powerup{

    Muscle(){

        setType("muscle");
        setPicName("muscle.png");


    }
    public void powerupEffect(SnakeObject affectedSnake){



        //this checks if the snake is eating the muscle, and if it is ,length + 2
        if(eatPowerup(affectedSnake)){

            //adds snake score
            int tempScore = affectedSnake.getScore();
            affectedSnake.setScore(tempScore + 2);

            //adds snake length
            int tempLength = affectedSnake.getSnakeLength();
            affectedSnake.setSnakeLength(tempLength+2);

            //sets new position of powerup
            setPos();

        }


    }
}

class Poison extends Powerup{

    Poison(){

        setType("poison");
        setPicName("poison.png");


    }
    public void powerupEffect(SnakeObject affectedSnake){

        //System.out.println("checking if the poison is eaten");


        //this checks if the snake is eating the poison, and if it is and the length is > 3, length --,score will deduct regardless of length
        if(eatPowerup(affectedSnake)){

            //System.out.println("the snake ate the poison");

            // if the snake length >3,deduct, else minus points
            if(affectedSnake.getSnakeLength() > 3){

                //deducts snake length
                int tempLength = affectedSnake.getSnakeLength();
                affectedSnake.setSnakeLength(tempLength-1);

            }
            // deduct snake score
            int tempScore = affectedSnake.getScore();
            affectedSnake.setScore(tempScore - 1);

            //sets new location of powerup
            setPos();
        }

    }
}