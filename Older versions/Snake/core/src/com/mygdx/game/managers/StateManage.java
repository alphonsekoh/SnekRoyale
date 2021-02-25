package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Main;
import com.mygdx.game.gamestates.*;


public class StateManage {

    private GameState gameState;
    private Winner player;

    // Set of different state of the snake game
    public enum State {
        MENU, PLAY, HIGHSCORES, CREDIT, GAMEOVER
    }

    public enum Winner {
        Player1, Player2
    }

    private static boolean newGame;

    public final SpriteBatch batch = new SpriteBatch();
    public final ShapeRenderer renderer = new ShapeRenderer();
    public final BitmapFont titleFont = new BitmapFont();
    public final BitmapFont font = new BitmapFont();


    public StateManage() {
        setState(State.MENU);
    }

    // set the current state of the game
    public void setState(State state) {
        if (gameState != null) {
            gameState.dispose();
            gameState = null;
            System.gc();

        }

        switch (state) {
            case MENU:
                Main.setCameraPosition();
                gameState = new MenuState(this);
                break;
            case PLAY:
                Main.setCameraPosition();
                    gameState = new PlayState(this);
                break;
            case HIGHSCORES:
                Main.setCameraPosition();
                gameState = new ScoreBoardState(this);
                break;
            case CREDIT:
                Main.setCameraPosition();
//                gameState = new OptionsState(this);

                break;
            case GAMEOVER:
                Main.setCameraPosition();
                gameState = new GameOverState(this);
                break;
        }
    }

    // set the current state of the game
    public void setWinner(Winner winner) {
            this.player = winner;

    }

    public Winner getWinner(){
        return this.player;
    }

    public static boolean getIsNewGame() {
        return newGame;
    }

    public static void setNewGame() {
        newGame=!newGame;
    }

    public void update(float time) {
        gameState.update(time);

    }

    public void draw() {
        gameState.paint();

    }
}
