package GameState;

import ScoreBoard.GameFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.module.FindException;

public class Menu extends GameState implements KeyListener {

    private int current;
    private String[] itemList;
//    private static int WIDTH = 900;
//    private static int HEIGHT = 700;
    private Image mainMenuTitleImage;

    public Menu(StateManager gameStateManager) {
        super(gameStateManager);

    }

    @Override
    protected void init() {
        itemList = new String[]{"PLAY", "SCOREBOARD", "EXIT"};
        GameFileManager.MANAGER.load();
    }

    @Override
    public void update(KeyEvent e) {
        keyPressed(e);

    }

    @Override
    public void draw(Graphics g) {
//        try {
//            mainMenuTitleImage = new ImageIcon("Logo.png").getImage();
//        } catch (FindException e){
//            System.out.println("File not found, could not load image");
//            JOptionPane.showMessageDialog(null,"File not found, could not load image");
//        }
        float row = HEIGHT - 500;
        int[] x = new int[5];
        Graphics2D g2d = (Graphics2D) g.create();

        //g.drawImage(mainMenuTitleImage, WIDTH/8 + 20, 20, null);

        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        for (int i = 0; i < itemList.length; i++) {
            x[i] = ((WIDTH - fm.stringWidth(itemList[i]))/2);
        }

        Font fnt = new Font("Arial", Font.BOLD, 50);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("SNAKE ROYALE", WIDTH/4 + 40, (int)row - 50);

        for (int i = 0; i < itemList.length; i++){
            row += 80;

            g2d.setColor(current == i ? Color.RED: Color.WHITE);
            g2d.drawString(itemList[i], x[i], row);
        }

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

        if (e.getKeyCode() == KeyEvent.VK_UP && current > 0){
            current-=1;
            System.out.println("Key Press UP");

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && current < itemList.length - 1){
            current+=1;
            System.out.println("Key Press DOWN");

        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            select();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void select() {
        // play
        switch (current) {
            case 0:
                StateManager.startNewGame();
                gameStateManager.setState(StateManager.State.PLAY);
                break;
            case 1:
                gameStateManager.setState(StateManager.State.HIGHSCORES);
                break;
            case 2:
                System.exit(0);;
                break;
        }

    }
}
