import GameState.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements Runnable, KeyListener {

//    protected final TimeTaken timeFrame ;
    public static int WIDTH = 900;
    public static int HEIGHT = 700;

    private Thread thread;
    private boolean runState = false;

    private static StateManager gameStateManager;

    float dt;
    long lastTime;

    public Screen(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        gameStateManager = new StateManager();
        thread = new Thread(this);
        thread.start();
        lastTime = System.nanoTime();
//        timeFrame = new TimeTaken(System.nanoTime());
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void run() {
        long time;
//        time = System.nanoTime();
        runState = true;
        while (runState){
            time = System.nanoTime();
            dt = (time - lastTime) / 1000000000.0f;
            lastTime = time;
            if (gameStateManager.getGameState() != null) {
                gameStateManager.updateTime(dt);
            }
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        if (gameStateManager.getGameState() != null) {
            gameStateManager.draw(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameStateManager.getGameState() != null){
            gameStateManager.update(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
