package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.entities.PlayerScore;
import com.mygdx.game.managers.Font;
import com.mygdx.game.managers.StateManage;
import com.mygdx.game.managers.FileManager;
import com.mygdx.game.managers.Jukebox;

public class ScoreBoardState extends GameState {

    private SpriteBatch batch;
    private PlayerScore[] highScores;
    private BitmapFont font, headerFnt;

    public ScoreBoardState(StateManage stateManager) {
        super(stateManager);
    }

    @Override
    public void setup() {
        batch = stateManager.batch;
        font = Font.MANAGER.set(20);
        headerFnt = Font.MANAGER.set(30);

        FileManager.MANAGER.load();
        highScores = FileManager.MANAGER.gameScore.getHighScore();
    }

    @Override
    public void update(float time) {

        inputKeyHandle();
    }

    @Override
    public void paint() {
        batch.setProjectionMatrix(Main.orgCamera.combined);

        Font.MANAGER.centered(batch, headerFnt, "High Scores", Main.WIDTH / 2, Main.HEIGHT - 30);

        float row = Main.HEIGHT - 85;

        String s = String.format("%10s %6s", "NAME", "SCORE");
        Font.MANAGER.centered(batch, font, s, Main.WIDTH / 2, row);
        for (int i = 0; i < highScores.length; i++) {
            row -= 30;
            String s1 = String.format("%2d. %s %4s", i+1, highScores[i].getName(), highScores[i].getScore());
            Font.MANAGER.centered(batch, font, s1, Main.WIDTH / 2, row);
        }
        Font.MANAGER.centered(batch, font, "Press ENTER back to MENU", Main.WIDTH/2, row - 50);
    }

    @Override
    public void inputKeyHandle() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Jukebox.MANAGER.play("accept");
            stateManager.setState(StateManage.State.MENU);
        }
    }

    @Override
    public void dispose() {

    }


}
