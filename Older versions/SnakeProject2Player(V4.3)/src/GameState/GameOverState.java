package GameState;

import ScoreBoard.GameFileManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverState extends GameState implements KeyListener {

    private boolean newHighScore;
    private String name;
    private int currChar;


    public GameOverState(StateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    protected void init() {
        newHighScore = GameFileManager.MANAGER.gameData.checkHighScore(GameFileManager.MANAGER.gameData.getTempScore());

        if (newHighScore){
            name = "Player";
            currChar = 0;
        }
    }

    @Override
    public void update(KeyEvent e) {
        keyPressed(e);

    }

    @Override
    public void draw(Graphics g) {
        if (!newHighScore){
            return;
        }

        float row = HEIGHT - 500;
        int x, y;
        Graphics2D g2d = (Graphics2D) g.create();

        String str = "New High Score: " + GameFileManager.MANAGER.gameData.getTempScore();
        Font fnt = new Font("Arial", Font.BOLD, 32);
        g2d.setFont(fnt);
        g2d.setColor(Color.white);
        FontMetrics fm = g2d.getFontMetrics();
        x = ((WIDTH - fm.stringWidth(str))/2);
        g2d.drawString(str, x, row);

        y = ((WIDTH - fm.stringWidth(name))/2);
        g2d.drawString(name, y, row + 50);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void updateTime(float dt) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (newHighScore) {
                GameFileManager.MANAGER.gameData.addHighScore(new String(name),
                        GameFileManager.MANAGER.gameData.getTempScore());
                GameFileManager.MANAGER.save();
            }
            gameStateManager.setState(StateManager.State.MENU);
        }

//        if (newHighScore) {
//            if (name[currChar] == ' ') {
//                name[currChar] = 'Z';
//            } else {
//                name[currChar]--;
//                if (name[currChar] < 'A')
//                    name[currChar] = ' ';
//            }
//
//            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                if (name[currChar] == ' ') {
//                    name[currChar] = 'A';
//                } else {
//                    name[currChar]++;
//                    if (name[currChar] > 'Z')
//                        name[currChar] = ' ';
//                }
//            }
//
//            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                if (currChar < name.length - 1)
//                    currChar++;
//            }
//
//            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                if (currChar > 0)
//                    currChar--;
//            }
//
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
