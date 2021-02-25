package com.mygdx.game.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Main;
import com.mygdx.game.entities.*;
import com.mygdx.game.managers.Font;
import com.mygdx.game.managers.FileManager;
import com.mygdx.game.managers.StateManage;
import com.mygdx.game.managers.Jukebox;

import java.util.ArrayList;
import java.util.List;


public class PlayState extends GameState{

    private SpriteBatch sb;
    private ShapeRenderer sr;
    private BitmapFont titleFont;
    private BitmapFont font;

    private float tempGameWidth;
    private float tempGameHeight;

    private Texture appleTT, muscleTT, poisonTT;

    private Player p1;
    private Player p2;
    private List<Tail> b1;
    private List<Tail> b2;
    private Items poison;
    private Items apple;
    private Items muscle;

    //remaning lives and apples
    private List<Player> liveP1;
    private List<Player> liveP2;

    private static final int gridCell = 15;
    private final int row = 20;
    private final int col = 20;

    private float moveTimer;
    private float moveTime; //move every x second

    private boolean playTime;
    private boolean exitMessage;

    private boolean beat1;


    public PlayState(StateManage stateManager) {
        super(stateManager);
    }

    public static float getGrid() {
        return (float) gridCell;
    }

    @Override
    public void setup() {
        sr = stateManager.renderer;
        sb = stateManager.batch;
        titleFont = stateManager.titleFont;


        appleTT = new Texture("image/apple.png");
        muscleTT = new Texture("image/muscle.png");
        poisonTT = new Texture("image/poison.png");

        //removed from draw, causes a huge consumption of memory in there
        Color color = new Color(0, 1, 1, 1);
        titleFont = Font.MANAGER.set(30, color);
        font = Font.MANAGER.set(15);

        tempGameWidth = Main.WIDTH;
        tempGameHeight = Main.HEIGHT;

        Main.WIDTH = col * gridCell;
        Main.HEIGHT = row * gridCell;

        Main.orgCamera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);
        Main.orgCamera.update();

        //Timers
        moveTimer = 0;
        moveTime = 0.25f;

        p1 = new Player(gridCell);
        p2 = new Player(gridCell);

        liveP1 = new ArrayList<>();
        liveP2 = new ArrayList<>();


        for (int i = 0; i < p1.getLives(); i++) {
            Player newLive = new Player(15);
            liveP1.add(newLive);
        }

        for (int i = 0; i < p2.getLives(); i++) {
            Player newLive = new Player(15);
            liveP2.add(newLive);
        }

        updateExtraLives();

        resetBody();

    }

    /**
     * Resets the snake position considering level start position and orientation.
     */
    private void resetBody() {
        p1.resetToPosition(
                5 * gridCell,
                5 * gridCell,
                Player.Facing.UP);
        p2.resetToPosition(
                15 * gridCell,
                15 * gridCell,
                Player.Facing.UP);

        b1 = new ArrayList<>();
        b2 = new ArrayList<>();

        //factor variables for initialize tail considering orientation
        int factorX = 0;

        int factorY = 1;

        //add 2 body
        b1.add(new Tail(
                p1.getX() - gridCell * factorX,
                p1.getY() - gridCell * factorY));

        b1.add(new Tail(
                b1.get(0).getX() - gridCell * factorX,
                b1.get(0).getY() - gridCell * factorY));

        b1.add(new Tail(
                b1.get(1).getX() - gridCell * factorX,
                b1.get(1).getY() - gridCell * factorY));

        //add 2 body
        b2.add(new Tail(
                p2.getX() - gridCell * factorX,
                p2.getY() - gridCell * factorY));

        b2.add(new Tail(
                b2.get(0).getX() - gridCell * factorX,
                b2.get(0).getY() - gridCell * factorY));

        b2.add(new Tail(
                b2.get(1).getX() - gridCell * factorX,
                b2.get(1).getY() - gridCell * factorY));

    }


    private boolean isPlayTime() {
        return playTime;
    }

    @Override
    public void update(float dt) {
        //get user input
        inputKeyHandle();

        moveTimer += dt;

        //if is paused doesn't update the rest
        if (!isPlayTime()) {
            return;
        }

        //only moves every time defined by moveTime
        if (moveTimer > moveTime) {
            moveTimer = 0; //reset timer

            checkCollision();

            //update body position
            updateBodyPosition();

            //update p1
            p1.update(dt);

            p1.wrap();

            //update p2
            p2.update(dt);

            p2.wrap();

            if (beat1)
                Jukebox.MANAGER.play("slide1", 0.1f);
            else
                Jukebox.MANAGER.play("slide2", 0.10f);
            beat1 = !beat1;

            updatesLives();
        }
        //create apple

        if (apple == null ) {
            apple = newApple();
        } else {
            apple.update(dt);
            if (apple.shouldRemove())
                apple = null;
        }
        //create poison
        if (poison == null) {
            poison = newPoison();
        } else {
            poison.update(dt);
            if (poison.shouldRemove())
                poison = null;
        }
        //create muscle
        if (muscle == null) {
            muscle = newMuscle();
        } else {
            muscle.update(dt);
            if (muscle.shouldRemove())
                muscle = null;
        }
    }

    /**
     * Update p1's lives.
     */
    private void updatesLives() {
        if (p1.isDead() || p2.isDead()) {
            if (p1.getLives() < 0) {
                FileManager.MANAGER.gameScore.setTempScore((long) p1.getScore() + (long)p2.getScore());
                stateManager.setWinner(StateManage.Winner.Player2);
                stateManager.setState(StateManage.State.GAMEOVER);
            }
            else if (p2.getLives() < 0) {
                stateManager.setWinner(StateManage.Winner.Player1);
                FileManager.MANAGER.gameScore.setTempScore((long) p1.getScore() + (long)p2.getScore());
                stateManager.setState(StateManage.State.GAMEOVER);
            }  else {
                resetBody();
                playTime = !playTime;
            }
        }

    }

    /**
     * Updates tail parts position.
     */
    private void updateBodyPosition() {
        if (b1.size() != 0) {
            //if p1 has eat a apple add a body part
            if (p1.hasEat())
                b1.add(new Tail(0, 0));
            //sets new position of body parts
            for (int i = b1.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    b1.get(i).setPosition(p1.getX(), p1.getY());
                }
                else
                    b1.get(i).setPosition(b1.get(i - 1).getX(), b1.get(i - 1).getY());
            }
        }
        if (b2.size() != 0) {
            //if p2 has eat a apple add a body part
            if (p2.hasEat())
                b2.add(new Tail(0, 0));
            //sets new position of body parts
            for (int i = b2.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    b2.get(i).setPosition(p2.getX(), p2.getY());
                }
                else
                    b2.get(i).setPosition(b2.get(i - 1).getX(), b2.get(i - 1).getY());
            }
        }
    }

    /**
     * Creates a new Apple. Check if space is free to create.
     *
     * @return new Apple
     */
    private Items newApple() {
        float x;
        float y;
        boolean containsHeadP1, containsHeadP2, containsFruit = false;
        do {
            // first get the position random
            x = MathUtils.random(col - 1) * gridCell;
            y = MathUtils.random(row - 1) * gridCell;

            // check if space is free
            containsHeadP1 = p1.contains(x, y);
            containsHeadP2 = p2.contains(x, y);

            for (Tail bodyPart : b1) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }
            for (Tail bodyPart : b2) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }

        } while (containsHeadP1 || containsHeadP2 || containsFruit );

        return new AppleItem(x, y);
    }
    private Items newMuscle() {
        float x;
        float y;
        boolean containsHeadP1, containsHeadP2, containsFruit = false;
        do {
            // first get the position random
            x = MathUtils.random(col - 1) * gridCell;
            y = MathUtils.random(row - 1) * gridCell;

            // check if space is free
            containsHeadP1 = p1.contains(x, y);
            containsHeadP2 = p2.contains(x, y);

            for (Tail bodyPart : b1) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }
            for (Tail bodyPart : b2) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }

        } while (containsHeadP1 || containsHeadP2 || containsFruit );

        return new MuscleItem(x, y);
    }

    private Items newPoison() {
        float x;
        float y;
        boolean containsHeadP1, containsHeadP2, containsFruit = false;
        do {
            // first get the position random
            x = MathUtils.random(col - 1) * gridCell;
            y = MathUtils.random(row - 1) * gridCell;

            // check if space is free
            containsHeadP1 = p1.contains(x, y);
            containsHeadP2 = p2.contains(x, y);

            for (Tail bodyPart : b1) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }
            for (Tail bodyPart : b2) {
                containsFruit = bodyPart.contains(x, y);
                if (containsFruit) {
                    break; //if contains exit for loop
                }
            }

        } while (containsHeadP1 || containsHeadP2 || containsFruit );

        return new PoisonItem(x, y);
    }

    /**
     * Check collision point to point intersection
     */
    private void checkCollision() {
        if (p1.contains(p2.getX(), p2.getY())){
            p2.hit();
        }
        if (p2.contains(p1.getX(), p1.getY())){
            p1.hit();
        }
        //p1 to tail
        for (Tail bodyPart : b1) {
            if (bodyPart.contains(p1.getX(), p1.getY())) {
                p1.hit();
            }
            if (bodyPart.contains(p2.getX(), p2.getY())) {
                p2.hit();
            }
        }
        //p2 to tail
        for (Tail bodyPart : b2) {
            if (bodyPart.contains(p2.getX(), p2.getY())) {
                p2.hit();
            }
            if (bodyPart.contains(p1.getX(), p1.getY())) {
                p1.hit();
            }
        }


        //p1 to apple
        if (apple != null && muscle != null) {
            if (p1.eat(apple.contains(p1.getX(), p1.getY()), apple.getScore()) ||
                    p1.eat(muscle.contains(p1.getX(), p1.getY()), muscle.getScore()) ||
                    p1.eat(poison.contains(p1.getX(), p1.getY()), poison.getScore()))
            {

                // each 5 decrease update time, increasing speed.
                if (p1.fruitsAte() % 5 == 0) {
                    moveTime += moveTime * -0.05f;
                }
                Jukebox.MANAGER.play("hiss");
            }else if (p2.eat(apple.contains(p2.getX(), p2.getY()), apple.getScore()) ||
                    p2.eat(muscle.contains(p2.getX(), p2.getY()), muscle.getScore()) ||
                    p2.eat(poison.contains(p2.getX(), p2.getY()), poison.getScore())) {

                // each 5 decrease update time, increasing speed.
                if (p2.fruitsAte() % 5 == 0) {
                    moveTime += moveTime * -0.05f;
                }
                Jukebox.MANAGER.play("hiss");
            }

            if (apple.shouldRemove() || p1.isDead() || p2.isDead()){
                apple = null;
            }
            if (muscle.shouldRemove() || p1.isDead() || p2.isDead()){
                muscle = null;
            }
            if (poison.shouldRemove() || p1.isDead() || p2.isDead()){
                poison = null;
            }

        }

        updateExtraLives();

    }


    /**
     * Updates the extra lives to the display
     */
    private void updateExtraLives() {
        if (p1.isDead() && liveP1.size() > 0) {
            liveP1.remove(liveP1.size() - 1);
        }
        if (p2.isDead() && liveP2.size() > 0) {
            liveP2.remove(liveP2.size() - 1);
        }

        //updates the position whatever the board size
        int i = 0;
        int j = 0;
        for (Player extraLive : liveP1) {
            extraLive.setPosition(Main.WIDTH - 15 - i++ * 20, Main.HEIGHT + 25);
        }
        for (Player extraLive : liveP2) {
            extraLive.setPosition(Main.WIDTH - 15 - j++ * 20, Main.HEIGHT + 5);
        }
    }



    @Override
    public void paint() {
        sr.setProjectionMatrix(Main.orgCamera.combined);
        sb.setProjectionMatrix(Main.orgCamera.combined);

        drawGrid();


        drawText();

        for (Player live : liveP1) {
            live.draw(sr);
        }
        for (Player live : liveP2) {
            live.draw(sr,true);
        }

        p1.draw(sr);
        p2.draw(sr, true);

        //draw body
        for (Tail bodyPart: b1){
            bodyPart.draw(sr);
        }
        for (Tail bodyPart: b2){
            bodyPart.draw(sr, true);
        }

        if (apple != null) {
            apple.draw(sb, appleTT);
        }
        if (muscle != null) {
            muscle.draw(sb, muscleTT);
        }
        if (poison != null) {
            poison.draw(sb, poisonTT);
        }

    }

    /**
     * Draws text information
     */
    private void drawText() {
        Font.MANAGER.centered(sb, titleFont,
                MenuState.title,
                Main.WIDTH / 2,
                Main.HEIGHT + 80);

        Font.MANAGER.left(sb, font,
                "P1 Score: " + (int) p1.getScore(),
                0,
                Main.HEIGHT + 40);

        Font.MANAGER.left(sb, font,
                "P2 Score: " + (int) p2.getScore(),
                0,
                Main.HEIGHT + 20);

        if (!playTime && !exitMessage) {
            Font.MANAGER.centered(sb, font,
                    "Hit space to continue ...",
                    Main.WIDTH / 2,
                    Main.HEIGHT / 2);
        }

        if (exitMessage) {
            Font.MANAGER.centered(sb, font,
                    "Are you sure you want",
                    Main.WIDTH / 2,
                    Main.HEIGHT / 2 + 20);

            Font.MANAGER.centered(sb, font,
                    "to quit the game?",
                    Main.WIDTH / 2,
                    Main.HEIGHT / 2);

            Font.MANAGER.centered(sb, font,
                    "(Y to exit)",
                    Main.WIDTH / 2,
                    Main.HEIGHT / 2 - 20);
        }

        Font.MANAGER.right(sb, font,
                "P1: " ,
                Main.WIDTH - 55, Main.HEIGHT + 38);
        Font.MANAGER.right(sb, font,
                "P2: " ,
                Main.WIDTH - 55, Main.HEIGHT + 18);
        Font.MANAGER.centered(sb, font,
                "Press SPACEBAR to PAUSE the game",
                Main.WIDTH / 2,
                Main.HEIGHT / 4 - 100);
        Font.MANAGER.centered(sb, font,
                "Press ESC to QUIT the game",
                Main.WIDTH / 2,
                Main.HEIGHT / 4 - 130);
    }


    /**
     * Draws a grid of the board.
     */
    private void drawGrid() {
        sr.setProjectionMatrix(Main.orgCamera.combined);

        sr.setColor(0, 0, 0.35f, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0; i <= Main.WIDTH; i += gridCell) {
            sr.line(i, 0, i, Main.HEIGHT);
        }

        for (int i = 0; i <= Main.HEIGHT; i += gridCell) {
            sr.line(0, i, Main.WIDTH, i);
        }

        sr.end();
    }

    @Override
    public void inputKeyHandle() {
        if (isPlayTime()) {
            //user preferences input keys
            p1.setLeft(Gdx.input.isKeyJustPressed(Input.Keys.LEFT));
            p1.setRight(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT));
            p1.setUp(Gdx.input.isKeyJustPressed(Input.Keys.UP));
            p1.setDown(Gdx.input.isKeyJustPressed(Input.Keys.DOWN));
            
            //Player 2
            p2.setLeft(Gdx.input.isKeyJustPressed(Input.Keys.A));
            p2.setRight(Gdx.input.isKeyJustPressed(Input.Keys.D));
            p2.setUp(Gdx.input.isKeyJustPressed(Input.Keys.W));
            p2.setDown(Gdx.input.isKeyJustPressed(Input.Keys.S));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            playTime = !playTime;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && playTime) {
            playTime = false;
            exitMessage = !exitMessage;
        }
        if (exitMessage && Gdx.input.isKeyJustPressed(Input.Keys.Y))
            stateManager.setState(StateManage.State.MENU);

    }

    public int getGridCell() {
        return gridCell;
    }

    @Override
    public void dispose() {
        Main.WIDTH = tempGameWidth;
        Main.HEIGHT = tempGameHeight;
        Main.setCameraPosition();

        //dispose of objects is manipulated by the Game class
    }
}
