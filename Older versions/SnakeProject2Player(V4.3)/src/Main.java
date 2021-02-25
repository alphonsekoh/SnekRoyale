import GameState.StateManager;

import java.awt.*;

import javax.swing.JFrame;

public class Main extends JFrame{

    public static int WIDTH = 905;
    public static int HEIGHT = 700;


    private static StateManager gameStateManager;

    public Main(){
        setTitle("Snake Royale");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1,0,0));
        start();
        setVisible(true);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        JFrame obj = new JFrame();
//        Gameplay gameplay = new Gameplay();
//        gameStateManager = new StateManager();
//
//        obj.setBounds(10, 10, WIDTH, HEIGHT);
//        obj.setBackground(Color.darkGray);
//        obj.setResizable(false);
//        obj.setVisible(true);
//        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        obj.add(gameplay);
        new Main();
    }

    public void start(){
        //Fill the new screen into the frame
        Screen sc = new Screen();
        add(sc);
        // show the screen from the sc object
        pack();
        // set location to be in the center
        setLocationRelativeTo(null);
    }


}
