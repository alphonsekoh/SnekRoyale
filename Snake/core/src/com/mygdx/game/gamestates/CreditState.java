package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Main;
import com.mygdx.game.managers.Font;
import com.mygdx.game.managers.Jukebox;
import com.mygdx.game.managers.StateManage;

public class CreditState extends GameState{

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont titleFont;
    private BitmapFont font;

    public CreditState(StateManage stateManager) {
        super(stateManager);
    }

    @Override
    protected void setup() {
        batch = stateManager.batch;
        renderer = stateManager.renderer;
        titleFont = Font.MANAGER.set(36);
        font = Font.MANAGER.set(20);
    }

    @Override
    public void update(float time) {
        inputKeyHandle();
    }

    @Override
    public void paint() {
        batch.setProjectionMatrix(Main.orgCamera.combined);

        Font.MANAGER.centered(batch, titleFont, "Credit", Main.WIDTH / 2, 425);

        Font.MANAGER.centered(batch, font, "Jessica Tan", Main.WIDTH / 2, 350);
        Font.MANAGER.centered(batch, font, "George Lee", Main.WIDTH / 2, 300);
        Font.MANAGER.centered(batch, font, "Zheng Jie", Main.WIDTH / 2, 250);
        Font.MANAGER.centered(batch, font, "Han Zheng", Main.WIDTH / 2, 200);
        Font.MANAGER.centered(batch, font, "Huan Yin", Main.WIDTH / 2, 150);
        Font.MANAGER.centered(batch, font, "Wei Kai", Main.WIDTH / 2, 100);
        Font.MANAGER.centered(batch, font, "Press ENTER back to MENU", Main.WIDTH/2, 50);


    }

    @Override
    public void inputKeyHandle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Jukebox.MANAGER.play("accept");
            stateManager.setState(StateManage.State.MENU);
        }
    }

    @Override
    public void dispose() {

    }
}
