package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Main;
import com.mygdx.game.managers.Font;
import com.mygdx.game.managers.FileManager;
import com.mygdx.game.managers.StateManage;
import com.mygdx.game.managers.Jukebox;

public class MenuState extends GameState {

    private Texture bg;

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont titleFont;
    private BitmapFont font;

    static final String title = "snek royale game";

    private int currentItem;
    private String[] menuItems;


    public MenuState(StateManage stateManager) {
        super(stateManager);
    }


    @Override
    public void setup() {
        batch = stateManager.batch;
        renderer = stateManager.renderer;

        // set font
        titleFont = Font.MANAGER.set(36);
        titleFont.setColor(Color.WHITE);

        font = Font.MANAGER.set(24);

        menuItems = new String[]{"Play", "Highscores", "Credit", "Quit"};

        bg = new Texture("image/bg.png");

        FileManager.MANAGER.load();

    }

    @Override
    public void update(float dt) {
        inputKeyHandle();

    }

    @Override
    public void paint() {
        batch.setProjectionMatrix(Main.orgCamera.combined);
        renderer.setProjectionMatrix(Main.orgCamera.combined);

        batch.begin();
        batch.draw(bg, 0,0, Main.WIDTH, Main.HEIGHT);
        batch.end();
        Font.MANAGER.centered(batch, titleFont, title, Main.WIDTH / 2, Main.HEIGHT - 50);

        float row = Main.HEIGHT - 100;

        for (int i = 0; i < menuItems.length; i++) {
            row -= 50;

            font.setColor(currentItem == i ? Color.RED : Color.WHITE);

            Font.MANAGER.centered(batch, font, menuItems[i], Main.WIDTH / 2, row);
        }

    }

    @Override
    public void inputKeyHandle() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentItem > 0) {
            currentItem--;
            Jukebox.MANAGER.play("select");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentItem < menuItems.length - 1) {
            currentItem++;
            Jukebox.MANAGER.play("select");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            select();
            Jukebox.MANAGER.play("accept");
        }
    }

    @Override
    public void dispose() {
        //dispose of objects is manipulated by the Game class
    }

    /**
     * Selection options of the game menu.
     */
    private void select() {
        // play
        switch (currentItem) {
            case 0:
                StateManage.setNewGame();
                stateManager.setState(StateManage.State.PLAY);
                break;
            case 1:
                stateManager.setState(StateManage.State.HIGHSCORES);
                break;
            case 2:
                stateManager.setState(StateManage.State.CREDIT);
                break;
            case 3:
                Gdx.app.exit();
                break;
        }

    }
}
