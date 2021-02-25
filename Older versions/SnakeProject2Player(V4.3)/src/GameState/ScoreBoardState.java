package GameState;

import ScoreBoard.GameFileManager;
import ScoreBoard.PlayerScore;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ScoreBoardState extends GameState implements KeyListener {

    private PlayerScore[] highScores;

    public ScoreBoardState(StateManager gameStateManager) {

        super(gameStateManager);
    }

    @Override
    protected void init() {
        GameFileManager.MANAGER.load();
        highScores = GameFileManager.MANAGER.gameData.getHighScore();
    }

    @Override
    public void update(KeyEvent e) {
        keyPressed(e);

    }

    @Override
    public void draw(Graphics g) {
        float row = HEIGHT - 550;
        int[] x = new int[11];
        int x1,x2;
        Graphics2D g2d = (Graphics2D) g.create();
        Graphics2D g2d2 = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        Font font2 = new Font("Arial", Font.BOLD, 50);
        g2d2.setFont(font2);
        FontMetrics fm2 = g2d2.getFontMetrics();

        //HEADER
        g2d2.setColor(Color.white);
        x1 = ((WIDTH - fm2.stringWidth("SCOREBOARD")) / 2);
        g2d2.drawString("SCOREBOARD", x1 , row-50);

        for (int i = 0; i < highScores.length; i++) {
            row += 40;
            String s = String.format("%2d. %5s %s", i + 1, highScores[i].getScore(), highScores[i].getName());
            x[i] = ((WIDTH - fm.stringWidth(s))/2);
            g2d.setColor(Color.WHITE);
            g2d.drawString(s, x[i], row);
        }
        x2 = ((WIDTH - fm.stringWidth("Press ENTER back to Menu")) / 2);
        g2d.drawString("Press ENTER back to Menu", x2 , row+60);
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
        if ((e.getKeyCode() == KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_ESCAPE)){
            gameStateManager.setState(StateManager.State.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
