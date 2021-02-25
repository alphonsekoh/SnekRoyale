package ScoreBoard;

import java.io.Serializable;

public class PlayerScore implements Serializable {
    private static final long serialVersionUID = 201809111959L;

    private String name;
    private long score;

    public PlayerScore(String name, long score){
        this.name = name;
        this.score = score;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
