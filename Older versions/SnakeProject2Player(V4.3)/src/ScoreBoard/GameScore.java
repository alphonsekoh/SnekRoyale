package ScoreBoard;

import java.io.Serializable;

public class GameScore implements Serializable {

    private static final long serialVersionUID = 201809111959L;

    private final int MAX_SCORE_BOARD = 10;
    private final PlayerScore[] highScore;

    private long tempScore;

    GameScore(){

        highScore = new PlayerScore[MAX_SCORE_BOARD];
    }

    void init(){
        for (int i = 0; i < MAX_SCORE_BOARD; i++){
            highScore[i] =new PlayerScore("----", 0);
        }
    }

    public int getMAX_SCORE_BOARD() {
        return MAX_SCORE_BOARD;
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
        return score > highScore[MAX_SCORE_BOARD-1].getScore();
    }

    public void addHighScore(String name, long score){
        if (checkHighScore(score)){
            highScore[MAX_SCORE_BOARD-1].setScore(score);
            highScore[MAX_SCORE_BOARD-1].setName(name);
            sortHighScore();
        }
    }

    // Sort the score board in ascending order
    private void sortHighScore() {
        for (int i = 0; i < MAX_SCORE_BOARD; i++){
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
