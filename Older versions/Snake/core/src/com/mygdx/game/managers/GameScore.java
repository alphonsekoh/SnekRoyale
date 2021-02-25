package com.mygdx.game.managers;

import com.mygdx.game.entities.PlayerScore;

import java.io.Serializable;

public class GameScore implements Serializable {

    private static final long serialVersionUID = 20210210L;

    private final PlayerScore[] highScore;
    private final int MAX_SCOREBOARD_LIST = 10;

    private long tempScore;

    GameScore(){

        highScore = new PlayerScore[MAX_SCOREBOARD_LIST];
    }

    void init(){
        for (int i = 0; i < MAX_SCOREBOARD_LIST; i++){
            highScore[i] =new PlayerScore("-NIL-", 0);
        }
    }

    public int getMAX_SCOREBOARD_LIST() {
        return MAX_SCOREBOARD_LIST;
    }

    public PlayerScore[] getHighScore() {
        return highScore;
    }

    public long getTempScore() {
        return tempScore;
    }

    public void setTempScore(long tempScore) {
        this.tempScore = tempScore;
    }


    public boolean checkHighScore(long score){
        return score > highScore[MAX_SCOREBOARD_LIST-1].getScore();
    }

    public void addHighScore(String name, long score){
        if (checkHighScore(score)){
            highScore[MAX_SCOREBOARD_LIST-1].setScore(score);
            highScore[MAX_SCOREBOARD_LIST-1].setName(name);
            sortHighScore();
        }
    }

    // Sort the score board in ascending order
    private void sortHighScore() {
        for (int i = 0; i < MAX_SCOREBOARD_LIST; i++){
            long score = highScore[i].getScore();
            String name = highScore[i].getName();
            int x;
            for (x = i - 1; x >= 0 && highScore[x].getScore() < score; x--){
                highScore[x + 1].setScore(highScore[x].getScore());
                highScore[x + 1].setName(highScore[x].getName());
            }
            highScore[x + 1].setName(name);
            highScore[x + 1].setScore(score);
        }
    }
}
