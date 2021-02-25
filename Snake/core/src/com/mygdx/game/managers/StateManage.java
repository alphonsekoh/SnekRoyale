package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Main;
import com.mygdx.game.gamestates.*;


public class StateManage {

    private GameState gameState;
    private Winner player;

    /**
     * List of state variable
     */
    public enum State {
        MENU, PLAY, HIGHSCORES, CREDIT, GAMEOVER
    }

    /**
     * set of players
     */
    public enum Winner {
        Player1, Player2
    }

    private static boolean newGame;

    public final SpriteBatch batch = new SpriteBatch();
    public final ShapeRenderer renderer = new ShapeRenderer();
    public final BitmapFont titleFont = new BitmapFont();
    public final BitmapFont font = new BitmapFont();

    /**
     * Constructor StateManage
     */
    public StateManage() {
        setState(State.MENU);
    }

    // set the current state of the game

    /**
     * Method to set the state of the game
     * @param state - Constants variable state of the game (MENU, PLAY, HIGHSCORES, CREDIT, GAMEOVER)
     */
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
                gameState = new CreditState(this);

                break;
            case GAMEOVER:
                Main.setCameraPosition();
                gameState = new GameOverState(this);
                break;
        }
    }

    /**
     * Method to set the player variable to be either Player1 or Player2
     * @param winner
     */
    public void setWinner(Winner winner) {
            this.player = winner;

    }

    /**
     * Method to return player variable
     * @return
     */
    public Winner getWinner(){
        return this.player;
    }

    /**
     * Toggle to set new start new game
     */
    public static void setNewGame() {
        newGame=!newGame;
    }

    /**
     * Method to update the game
     * @param time
     */
    public void update(float time) {
        gameState.update(time);

    }

    /**
     * Method to draw the render/draw the graphic of the game
     */
    public void draw() {
        gameState.paint();

    }
}
