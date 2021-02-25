package GameState;

import Entities.Powerup;
import Entities.Snake;
import Entities.Tail;
import ScoreBoard.GameFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlayState extends GameState implements KeyListener, ActionListener {

    private int width;
    private int height;

    private Snake snake;
    private Snake snake2;
    private List<Tail> snakeBody;
    private List<Tail> snake2Body;
    private Powerup pw;

    private static final int gridCell = 15;

    private float mTimer;
    private float mTime; //move every x second

    private List<Snake> live;
    private List<Snake> live2;

    private boolean pTime;
    private boolean exitMessage;

    private Timer timer;
    private int delay = 100;

    private int rows = 50;
    private int columns = 50;

    public static Random random = new Random();

    private ImageIcon titleImage;

    public PlayState(StateManager gameStateManager) {
        super(gameStateManager);

    }

    @Override
    protected void init() {
//
        timer = new Timer(delay, this);
        timer.start();

        //Timer
        mTimer = 0;
        mTime = 0.01f;

        snake = new Snake(gridCell);
        snake2 = new Snake(gridCell);
        live = new ArrayList<>();
        live2 = new ArrayList<>();
        for (int i = 0; i < snake.getLive(); i++){
            Snake newSnake = new Snake(15);
            live.add(newSnake);
        }

        for (int i = 0; i < snake2.getLive(); i++){
            Snake newSnake = new Snake(15);
            live2.add(newSnake);
        }

        updateRemainLives();

        resetSnake();


    }

    @Override
    public void update(KeyEvent e) {
        keyPressed(e);

    }

    private void resetSnake() {
        // player 1 configuratioin
        snake.resetPosition(5 * gridCell, 5 * gridCell, Snake.Facing.RIGHT);
        snakeBody = new ArrayList<>();

        int X = snake.getFacing() == Snake.Facing.UP ||
                snake.getFacing() == Snake.Facing.DOWN ? 0 :
                snake.getFacing() == Snake.Facing.LEFT ? -1 : 1;

        int Y = snake.getFacing() == Snake.Facing.LEFT ||
                snake.getFacing() == Snake.Facing.RIGHT ? 0 :
                snake.getFacing() == Snake.Facing.DOWN ? -1 : 1;

        snake2.resetPosition(5 * gridCell, 20 * gridCell, Snake.Facing.RIGHT);

// player 2 configuration
        snake2Body = new ArrayList<>();

        int X2 = snake2.getFacing() == Snake.Facing.UP ||
                snake2.getFacing() == Snake.Facing.DOWN ? 0 :
                snake2.getFacing() == Snake.Facing.LEFT ? -1 : 1;

        int Y2 = snake2.getFacing() == Snake.Facing.LEFT ||
                snake2.getFacing() == Snake.Facing.RIGHT ? 0 :
                snake2.getFacing() == Snake.Facing.DOWN ? -1 : 1;

        // Create snake with 2 body
        // Player 1 cofig
        snakeBody.add(new Tail(
                snake.getX() - gridCell * X,
                snake.getY() - gridCell * Y));

        snakeBody.add(new Tail(
                snakeBody.get(0).getX() - gridCell * X,
                snakeBody.get(0).getY() - gridCell * Y));

        snakeBody.add(new Tail(
                snakeBody.get(1).getX() - gridCell * X,
                snakeBody.get(1).getY() - gridCell * Y));

        // player 2 config
        snake2Body.add(new Tail(
                snake2.getX() - gridCell * X2,
                snake2.getY() - gridCell * Y2));

        snake2Body.add(new Tail(
                snake2Body.get(0).getX() - gridCell * X2,
                snake2Body.get(0).getY() - gridCell * Y2));

        snake2Body.add(new Tail(
                snake2Body.get(1).getX() - gridCell * X2,
                snake2Body.get(1).getY() - gridCell * Y2));




    }

    private boolean ispTime(){
        return pTime;
    }

    private void updateLive() {
        if (snake.isDead() || snake2.isDead()) {
            if (snake.getLive() < 0 || snake2.getLive()< 0) {
                if (snake.getLive()<0) {
                    GameFileManager.MANAGER.gameData.setTempScore((long) snake.getScore());
                }
                else{
                    GameFileManager.MANAGER.gameData.setTempScore((long) snake2.getScore());
                }
                gameStateManager.setState(StateManager.State.GAMEOVER);

            } else {
                resetSnake();
                pTime = !pTime;
            }
        }
    }

    private void updateRemainLives(){
        if (snake.isDead() && live.size() > 0) {
            live.remove(live.size() - 1);
        }
        if (snake2.isDead() && live2.size() > 0) {
            live2.remove(live2.size() - 1);
        }
        //updates the position whatever the board size
        int i = 0;
        for (Snake extraLive : live) {
            extraLive.setPosition(WIDTH - 15 - i++ * 20, HEIGHT + 5);
        }

        for (Snake extraLive : live2) {
            extraLive.setPosition(WIDTH - 15 - i++ * 20, HEIGHT + 5);
        }
    }

    @Override
    public void draw(Graphics g) {
        drawGrid(g);
        drawText(g);

        for (Snake live : live) {
            live.draw(g);
        }
        for (Snake live2 : live) {
            live2.draw(g);
        }

        snake.draw(g);
        snake2.draw(g);

        //draw body
        for (Tail body: snakeBody){
            body.draw(g);
        }
        for (Tail body2: snake2Body){
            body2.draw(g);
        }
//        body.forEach(bodyPart -> bodyPart.draw(sr));

        if (pw != null) {
            pw.draw(g);
        }

    }

    private void drawText(Graphics g) {
        //draw title image folder
        g.setColor(Color.white);
        g.drawRect(24, 10, 860, 55);

        //draw title image
        titleImage = new ImageIcon("bitmap.png");
        titleImage.paintIcon(null, g, 25, 11);

        //draw border for gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 860, 577);

        //draw background for the gameplay
//        g.setColor(Color.black);
//        g.fillRect(25, 75, 859, 575);

        //draw scores P1
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score(P1): "+ snake.getScore(), 780, 30);

        //draw scores P2
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score(P2): "+ snake2.getScore(), 780, 50);

        //draw lives P1
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Live(s)(P1): "+ snake.getScore(), 35, 30);

        //draw lives P2
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Live(s)(P2): "+ snake2.getScore(), 35, 50);

        float row = HEIGHT - 300;
        int x, y;
        Graphics2D g2d = (Graphics2D) g.create();

        String str = "Hit SPACE to start ... ";
        String str2 = "Are you sure to quit? (Hit Y to exit)";
        Font fnt = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(fnt);
        g2d.setColor(Color.white);
        FontMetrics fm = g2d.getFontMetrics();

        if (!pTime && !exitMessage) {
            x = ((WIDTH - fm.stringWidth(str))/2);
            g2d.drawString(str, x, row);
        }

        if (exitMessage) {
            y = ((WIDTH - fm.stringWidth(str2)) / 2);
            g2d.drawString(str2, y, row);
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 24; i <= 885; i += gridCell) {
            g.drawLine(i, 74, i, 650);
        }

        for (int i = 74; i <= 650; i += gridCell) {
            g.drawLine(24, i, 885, i);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void updateTime(float dt) {

        //actionPerformed(null);
        //timer.start();
        mTimer += dt;

        if (!ispTime()){
            return;
        }

        if (mTimer > mTime){
            mTimer = 0;

            checkCollision();

            updateBodyPosition();

            snake.update(dt);
            snake2.update(dt);
            snake.wrap();

            snake2.wrap();

            updateLive();
        }

        if (pw == null){
            pw = newPower();
        } else {
            pw.update(dt);
        }
    }

    private Powerup newPower() {
        float x;
        float y;
        boolean head, fruit = false;
        do {
            //Random x and y axis
            x = random.nextInt(columns - 1) * gridCell;
            y = random.nextInt(rows - 1) * gridCell;

            head = snake.checkCoordinate(x,y);
            head = snake2.checkCoordinate(x,y);

            for (Tail body: snakeBody){
                fruit = body.checkCoordinate(x, y);
                if (fruit){
                    //if contain exist from loop
                    break;
                }
            }
            for (Tail body2: snake2Body){
                fruit = body2.checkCoordinate(x, y);
                if (fruit){
                    //if contain exist from loop
                    break;
                }
            }
        } while (head || fruit);

        return new Powerup(x, y);
    }

    private void updateBodyPosition() {
        if (snakeBody.size() != 0){
            if (snake.ate()){
                snakeBody.add(new Tail(0,0));
            }
            for (int i = snakeBody.size() - 1; i >= 0; i--) {
                if (i == 0)
                    snakeBody.get(i).setPosition(snake.getX(), snake.getY());
                else
                    snakeBody.get(i).setPosition(snakeBody.get(i - 1).getX(), snakeBody.get(i - 1).getY());
            }
        }

        if (snake2Body.size() != 0){
            if (snake2.ate()){
                snake2Body.add(new Tail(0,0));
            }
            for (int i = snake2Body.size() - 1; i >= 0; i--) {
                if (i == 0)
                    snake2Body.get(i).setPosition(snake2.getX(), snake2.getY());
                else
                    snake2Body.get(i).setPosition(snake2Body.get(i - 1).getX(), snake2Body.get(i - 1).getY());
            }
        }
    }

    private void checkCollision() {
        for (Tail body : snakeBody) {
            if (body.checkCoordinate(snake.getX(), snake.getY())) {
                snake.collide();
            }
        }
        for (Tail body : snake2Body) {
            if (body.checkCoordinate(snake2.getX(), snake2.getY())) {
                snake2.collide();
            }
        }

        if (pw != null) {
            snake.eat(pw.checkCoordinate(snake.getX(), snake.getY()), pw.getScore());
            if (snake.isDead())
                pw = null;
        }

        if (pw != null) {
            snake2.eat(pw.checkCoordinate(snake2.getX(), snake2.getY()), pw.getScore());
            if (snake2.isDead())
                pw = null;
        }
        updateRemainLives();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (ispTime()){
            // player 1 key mappings
            if (e.getKeyCode() == KeyEvent.VK_UP){
                snake.setUp(e.getKeyCode() == KeyEvent.VK_UP);
                System.out.println("Up");
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                System.out.println("Down");
                snake.setDown(e.getKeyCode() == KeyEvent.VK_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                System.out.println("Right");
                snake.setLeft(e.getKeyCode() == KeyEvent.VK_RIGHT);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
                System.out.println("Left");
                snake.setRight(e.getKeyCode() == KeyEvent.VK_LEFT);
            }

            // player 2 key mapping
            if (e.getKeyCode() == KeyEvent.VK_W){
                snake2.setUp(e.getKeyCode() == KeyEvent.VK_W);
                System.out.println("W");
            }
            if (e.getKeyCode() == KeyEvent.VK_S){
                System.out.println("S");
                snake2.setDown(e.getKeyCode() == KeyEvent.VK_S);
            }
            if (e.getKeyCode() == KeyEvent.VK_D){
                System.out.println("D");
                snake2.setLeft(e.getKeyCode() == KeyEvent.VK_D);
            }
            if (e.getKeyCode() == KeyEvent.VK_A){
                System.out.println("A");
                snake2.setRight(e.getKeyCode() == KeyEvent.VK_A);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pTime = !pTime;
        }
        if ((e.getKeyCode() == KeyEvent.VK_ESCAPE) && pTime) {
            pTime = false;
            exitMessage = !exitMessage;
        }
        if (exitMessage && (e.getKeyCode() == KeyEvent.VK_Y)) {
            gameStateManager.setState(StateManager.State.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        timer.start();
//        if (!ispTime()){
//            return;
//        }
//
//            checkCollision();
//
//            updateBodyPosition();
//
//            snake.update(0);
//
//            snake.wrap();
//
//            updateLive();
//
//        if (pw == null){
//            pw = newPower();
//        } else {
//            pw.update(0);
//        }

    }
}
