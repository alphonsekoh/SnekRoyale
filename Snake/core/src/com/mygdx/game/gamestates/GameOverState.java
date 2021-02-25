package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Main;
import com.mygdx.game.managers.Font;
import com.mygdx.game.managers.FileManager;
import com.mygdx.game.managers.StateManage;

public class GameOverState extends GameState {

    private SpriteBatch sB;
    private ShapeRenderer sR;

    private BitmapFont fntGameOver;
    private BitmapFont fnt;

    private boolean highScore;
    private char[] name;
    private int currChar;

    public GameOverState(StateManage stateManager) {
        super(stateManager);

    }

    @Override
    public void setup() {
        sB = stateManager.batch;
        sR = stateManager.renderer;

        highScore = FileManager.MANAGER.gameScore.checkHighScore(FileManager.MANAGER.gameScore.getTempScore());

        if (highScore) {
            name = new char[]{'A', 'A', 'A', 'A'};
            currChar = 0;
        }
        fntGameOver = Font.MANAGER.set(32);
        fnt = Font.MANAGER.set(20);

    }

    @Override
    public void update(float time) {
        inputKeyHandle();

    }

    @Override
    public void paint() {
        sB.setProjectionMatrix(Main.orgCamera.combined);

        Font.MANAGER.centered(sB, fntGameOver, "Game Over", Main.WIDTH / 2, 300);

        Font.MANAGER.centered(sB, fnt, stateManager.getWinner() + " WIN", Main.WIDTH / 2, 250);

        if (!highScore) return;

        String str = "New High Score: " + FileManager.MANAGER.gameScore.getTempScore();
        Font.MANAGER.centered(sB, fnt, str, Main.WIDTH / 2, 200);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(fnt, "AAAA");

        sB.begin();
        for (int i = 0; i < name.length; i++) {
            fnt.draw(sB, Character.toString(name[i]),
                    (Main.WIDTH - layout.width) / 2 + 14 * i,
                    150);
        }
        sB.end();


    }

    @Override
    public void inputKeyHandle() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            if (highScore) {
                FileManager.MANAGER.gameScore.addHighScore(new String(name),
                        FileManager.MANAGER.gameScore.getTempScore());
                FileManager.MANAGER.save();
            }
            stateManager.setState(StateManage.State.MENU);
        }

        // prevents accessing keys if isn't new high score
        if (highScore) {

            if (Gdx.input.isKeyJustPressed(Keys.UP)) {
                if (name[currChar] == ' ') {
                    name[currChar] = 'Z';
                } else {
                    name[currChar]--;
                    if (name[currChar] < 'A')
                        name[currChar] = ' ';
                }
            }

            if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
                if (name[currChar] == ' ') {
                    name[currChar] = 'A';
                } else {
                    name[currChar]++;
                    if (name[currChar] > 'Z')
                        name[currChar] = ' ';
                }
            }

            if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
                if (currChar < name.length - 1)
                    currChar++;
            }

            if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
                if (currChar > 0)
                    currChar--;
            }
        }

    }

    @Override
    public void dispose() {
    }

}
