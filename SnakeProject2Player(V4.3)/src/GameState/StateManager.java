package GameState;

import ScoreBoard.GameFileManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StateManager {

    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }


    //Constants list of Game State
    public enum State {
        MENU, PLAY, HIGHSCORES, GAMEOVER
    }

    private static boolean newGame;

    public StateManager() {


        setState(State.MENU);

    }

    //Setter to set Game State
    public void setState(State state) {
        if (gameState != null) {
            gameState.dispose();
            gameState = null;
            System.gc();

        }

        switch (state) {
            case MENU:
                gameState = new Menu(this);
                break;
            case PLAY:
                gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                gameState = new ScoreBoardState(this);
                break;
            case GAMEOVER:
                gameState = new GameOverState(this);
                break;
        }
    }
    public static boolean isNewGame() {
        return newGame;
    }

    public static void startNewGame() {

        newGame=!newGame;
    }

    public void update(KeyEvent e) {
        gameState.update(e);

    }

    public void draw(Graphics g) {
        gameState.draw(g);

    }

    public void updateTime(float dt) {
        gameState.updateTime(dt);
    }
}

