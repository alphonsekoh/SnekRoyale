package com.mygdx.game.managers;

import com.mygdx.game.entities.PlayerScore;

import java.io.Serializable;

public class GameScore implements Serializable {

    private static final long serialVersionUID = 20210210L;

    private final PlayerScore[] highScore;
    private final int MAX_SCOREBOARD_LIST = 10;

    private long tempScore;

    /**
     * Constructor GameScore
     */
    GameScore(){
        highScore = new PlayerScore[MAX_SCOREBOARD_LIST];
    }

    /**
     * init method to initialise the game score
     */
    void init(){
        for (int i = 0; i < MAX_SCOREBOARD_LIST; i++){
            highScore[i] =new PlayerScore("-NIL-", 0);
        }
    }

    /**
     *
     * @return List of high score
     */
    public PlayerScore[] getHighScore() {
        return highScore;
    }

    /**
     * Method to get tentative score
     * @return
     */
    public long getTempScore() {
        return tempScore;
    }

    /**
     * Method to set the tentative score to variable tempScore
     * @param tempScore
     */
    public void setTempScore(long tempScore) {
        this.tempScore = tempScore;
    }

    /**
     * Method to check if the score is higher than the score in the list
     * @param score
     * @return boolean true or false
     */
    public boolean checkHighScore(long score){
        return score > highScore[MAX_SCOREBOARD_LIST-1].getScore();
    }

    /**
     * Method to adding and sorting high score into the list
     * if the score is higher than the score in the list
     * @param name
     * @param score
     */
    public void addHighScore(String name, long score){
        if (checkHighScore(score)){
            highScore[MAX_SCOREBOARD_LIST-1].setScore(score);
            highScore[MAX_SCOREBOARD_LIST-1].setName(name);
            sortHighScore();
        }
    }

    /**
     * Sort the score in an ascending order
     */
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
