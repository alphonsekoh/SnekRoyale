import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

    //main snakeObejct creation
    snakeObject snake1 = new snakeObject(1);
    snakeObject snake2 = new snakeObject(2);

    //main powerup creation
    powerup apple = new apple();
    powerup muscle = new muscle();
    powerup poison = new poison();

    //controlClass controls = new controlClass();

    //required graphics variable creation
    private ImageIcon Logo;
    private ImageIcon titleImage;
    private ImageIcon appleImage;
    private ImageIcon snakeimage;
    private ImageIcon muscleImage;
    private ImageIcon poisonImage;


    private ImageIcon mouth;

    private Image mainMenuTitleImage;

    private Timer timer;
    private int delay = 100;
    private int gameState = 0;
    private boolean StartHovered = false;
    private boolean fPStartHovered = false;
    private boolean ExitHovered = false;
    private boolean ScoreboardHovered = false;

    private class MouseEvents extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

		if (gameState == 0) {
			// Start button hover.
			if (mouseX > 200 && mouseX < 400 && mouseY > 270 && mouseY < 325) {
                StartHovered = true;
			} else {
                StartHovered = false;
			}

			// 4P Start button hover.
            if (mouseX > 480 && mouseX < 680 && mouseY > 270 && mouseY < 325) {
                fPStartHovered = true;
            } else {
                fPStartHovered = false;
            }

			// Exit button hover.
			if (mouseX > 340 && mouseX < 540 && mouseY > 350 && mouseY < 405) {
                ExitHovered = true;
			} else {
                ExitHovered = false;
			}
            // Scoreboard button hover.
            if (mouseX > 340 && mouseX < 540 && mouseY > 430 && mouseY < 485) {
                ScoreboardHovered = true;
            } else {
                ScoreboardHovered = false;
            }
		} }

        @Override
        public void mousePressed(MouseEvent e) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            //Check if button start is click
            if (gameState == 0) {
                if (mouseX > 200 && mouseX < 400 && mouseY > 270 && mouseY < 325) {
                    gameState = 1;
                }
                if (mouseX > 480 && mouseX < 680 && mouseY > 270 && mouseY < 325) {
                    gameState = 4;
                }
            }

            if (gameState == 0) {
                // Check if button is on exit button.
                if (mouseX > 340 && mouseX < 540 && mouseY > 350 && mouseY < 405) {
                    gameState = 2;
                }

                if (gameState == 0) {
                    // Check if button is on scoreboard button.
                    if (mouseX > 340 && mouseX < 540 && mouseY > 430 && mouseY < 485) {
                        gameState = 3;
                    }
                }
            }
        }
    }

    public Gameplay()
    {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        snake1.setAllowInput(1);
        snake2.setAllowInput(1);

        snake1.setKeyTrueFalseValue("d", 1);
        snake2.setKeyTrueFalseValue("left", 1);

        mainMenuTitleImage = new ImageIcon("./img/Logo.png").getImage();

    }

    public void paint(Graphics g){
        super.paintComponent(g);


        if (gameState == 0) {
//			main menu
            addMouseListener(new MouseEvents());
            addMouseMotionListener(new MouseEvents());

            g.drawImage(mainMenuTitleImage, 130, 50, null);
            setBackground(Color.GREEN);

            g.setFont(new Font("arial", Font.PLAIN, 30));

            //Button and label for Start
            if (StartHovered == true){
                g.setColor(new Color(131, 131, 225));
            }
            else{
                g.setColor(new Color(56, 14, 112));
            }
            g.drawRect(200, 270, 200, 55);
            g.drawString("2P Start", 240, 310);


            // setting a four player option
            if (fPStartHovered == true){
                g.setColor(new Color(131, 131, 225));
            }
            else{
                g.setColor(new Color(56, 14, 112));
            }
            g.drawRect(480, 270, 200, 55);
            g.drawString("4P Start", 520, 310);


            //Button and label for Exit
            if (ExitHovered == true){
                g.setColor(new Color(131, 131, 225));
            }
            else{
                g.setColor(new Color(56, 14, 112));
            }
            g.drawRect(340, 350, 200, 55);
            g.drawString("Exit", 410, 385);

            //Button and label for Scoreboard
            if (ScoreboardHovered == true){
                g.setColor(new Color(131, 131, 225));
            }
            else{
                g.setColor(new Color(56, 14, 112));
            }
            g.drawRect(340, 430, 200, 55);
            g.drawString("Scoreboard", 360, 470);


    } else if (gameState == 1) {
        //set initial position of snake
        if(snake1.getMove() == 0 && snake2.getMove() == 0)
        {

            snake1.setInitialSnakePosition(550, 525, 500, 550, 550, 550);
            snake2.setInitialSnakePosition(50, 75, 100, 100, 100, 100);

        }

        //draw title image folder
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        //draw title image
        titleImage = new ImageIcon("./img/bitmap.png");
        titleImage.paintIcon(this, g, 25, 11);

        //draw border for gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        //draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        //draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score(P1): "+ snake1.getScore(), 780, 30);

        //draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score(P2): "+ snake2.getScore(), 780, 50);


        //create Snake 1
        //mouth = new ImageIcon("rightmouth.png");
        //mouth.paintIcon(this, g, snake1.getXYPosition("x",0), snake1.getXYPosition("y",0));
        snake1.getMouth().paintIcon(this, g, snake1.getXYPosition("x",0), snake1.getXYPosition("y",0));



        //create Snake 2
        mouth = new ImageIcon("./img/leftmouthP2.png");
        mouth.paintIcon(this, g, snake2.getXYPosition("x",0), snake2.getXYPosition("y",0));

        //animation for snake 1
        for(int x = 0; x < snake1.getSnakeLength(); x++)
        {
            //System.out.println("snake 1 animation running");


            int currentNodeX = snake1.getXYPosition("x",x);
            int currentNodeY = snake1.getXYPosition("y",x);

            //System.out.println("snake current x position " + currentNodeX + " snake current Y position " + currentNodeY);

            // if the node is the head of the snake
            if (x == 0){

                int nextNodeX = snake1.getXYPosition("x",x+1);
                int nextNodeY = snake1.getXYPosition("y",x+1);

                //System.out.println("snake head current x position " + currentNodeX + " snake head current Y position " + currentNodeY);
                //System.out.println("snake head next x position " + nextNodeX + " snake head next Y position " + nextNodeY);

                //when the position of the head is facing up
                if (currentNodeY < nextNodeY){

                    mouth = new ImageIcon("./img/upmouth.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //when the position of the head is facing right
                else if (currentNodeX > nextNodeX){

                    mouth = new ImageIcon("./img/rightmouth.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //when the position of the head is facing down
                else if (currentNodeY > nextNodeY){

                    mouth = new ImageIcon("./img/downmouth.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //when the position of the head is facing left
                else if (currentNodeX < nextNodeX){

                    mouth = new ImageIcon("./img/leftmouth.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }

            }
            // if the node is the tail of the snake
            else if (x == snake1.getSnakeLength() - 1){

                int previousNodeX = snake1.getXYPosition("x",x - 1);
                int previousNodeY = snake1.getXYPosition("y",x - 1);

                //System.out.println("snake current tail value Y is " + currentNodeY + " snake previous tail value Y is " + previousNodeY + " X in loop is " + x);

                //if tail facing up
                if (previousNodeY < currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketailup.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //if tail facing right
                else if (previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketailright.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //if facing down
                else if (previousNodeY > currentNodeY){

                    System.out.println("facing down now");
                    snakeimage = new ImageIcon("./img/snaketaildown.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //if facing left
                else if (previousNodeX < currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketailleft.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }


            }
            //if the node is not a head or tail
            else {

                int previousNodeX = snake1.getXYPosition("x",x - 1);
                int previousNodeY = snake1.getXYPosition("y",x - 1);

                int nextNodeX = snake1.getXYPosition("x",x+1);
                int nextNodeY = snake1.getXYPosition("y",x+1);

                //if going from left to right or right to left
                if (previousNodeX < currentNodeX && nextNodeX > currentNodeX || nextNodeX < currentNodeX && previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snakeimageLR.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);
                }

                // if going up down
                else if (previousNodeY < currentNodeY && nextNodeY > currentNodeY || nextNodeY < currentNodeY && previousNodeY > currentNodeY){

                    snakeimage = new ImageIcon("./img/snakeimageUD.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // upwards left turn
                else if (previousNodeX < currentNodeX && nextNodeY > currentNodeY || nextNodeX < currentNodeX && previousNodeY > currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketurnUpLeft.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // downwards left turn
                else if (previousNodeY < currentNodeY && nextNodeX < currentNodeX || nextNodeY < currentNodeY && previousNodeX < currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketurnDownLeft.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // downward right turn
                else if (previousNodeX > currentNodeX && nextNodeY < currentNodeY || nextNodeX > currentNodeX && previousNodeY < currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketurnDownRight.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // upward right turn
                else if (previousNodeY > currentNodeY && nextNodeX > currentNodeX || nextNodeY > currentNodeY && previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketurnUpRight.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

            }
        }

        //animation for snake 2
        for(int a = 0; a < snake2.getSnakeLength(); a++) {
            //System.out.println("snake 1 animation running");


            int currentNodeX = snake2.getXYPosition("x",a);
            int currentNodeY = snake2.getXYPosition("y",a);

            //System.out.println("snake current x position " + currentNodeX + " snake current Y position " + currentNodeY);

            // if the node is the head of the snake
            if (a == 0){

                int nextNodeX = snake2.getXYPosition("x",a + 1);
                int nextNodeY = snake2.getXYPosition("y",a + 1);

                //System.out.println("snake head current x position " + currentNodeX + " snake head current Y position " + currentNodeY);
                //System.out.println("snake head next x position " + nextNodeX + " snake head next Y position " + nextNodeY);
                //when the position of the head is facing up
                if (currentNodeY < nextNodeY){

                    mouth = new ImageIcon("./img/upmouthP2.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //when the position of the head is facing right
                else if (currentNodeX > nextNodeX){

                    mouth = new ImageIcon("./img/rightmouthP2.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //when the position of the head is facing down
                else if (currentNodeY > nextNodeY){

                    mouth = new ImageIcon("downmouthP2.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //when the position of the head is facing left
                else if (currentNodeX < nextNodeX){

                    mouth = new ImageIcon("./img/leftmouthP2.png");
                    mouth.paintIcon(this, g, currentNodeX, currentNodeY);

                }

            }
            // if the node is the tail of the snake
            else if (a == snake2.getSnakeLength() - 1){

                int previousNodeX = snake2.getXYPosition("x",a - 1);
                int previousNodeY = snake2.getXYPosition("y",a - 1);

                //if tail facing up
                if (previousNodeY < currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketailupP2.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }
                //if tail facing right
                else if (previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketailrightP2.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //if facing down
                else if (previousNodeY > currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketaildownP2.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }

                //if facing left
                else if (previousNodeX < currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketailleftP2.png");
                    snakeimage.paintIcon(this, g, currentNodeX, currentNodeY);

                }


            }
            //if the node is not a head or tail
            else {

                int previousNodeX = snake2.getXYPosition("x",a - 1);
                int previousNodeY = snake2.getXYPosition("y",a - 1);

                int nextNodeX = snake2.getXYPosition("x",a + 1);
                int nextNodeY = snake2.getXYPosition("y",a + 1);

                //if going from left to right or right to left
                if (previousNodeX < currentNodeX && nextNodeX > currentNodeX || nextNodeX < currentNodeX && previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snakeimageLRP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);
                }

                // if going up down
                else if (previousNodeY < currentNodeY && nextNodeY > currentNodeY || nextNodeY < currentNodeY && previousNodeY > currentNodeY){

                    snakeimage = new ImageIcon("./img/snakeimageUDP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // upwards left turn
                else if (previousNodeX < currentNodeX && nextNodeY > currentNodeY || nextNodeX < currentNodeX && previousNodeY > currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketurnUpLeftP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // downwards left turn
                else if (previousNodeY < currentNodeY && nextNodeX < currentNodeX || nextNodeY < currentNodeY && previousNodeX < currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketurnDownLeftP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // downward right turn
                else if (previousNodeX > currentNodeX && nextNodeY < currentNodeY || nextNodeX > currentNodeX && previousNodeY < currentNodeY){

                    snakeimage = new ImageIcon("./img/snaketurnDownRightP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

                // upward right turn
                else if (previousNodeY > currentNodeY && nextNodeX > currentNodeX || nextNodeY > currentNodeY && previousNodeX > currentNodeX){

                    snakeimage = new ImageIcon("./img/snaketurnUpRightP2.png");
                    snakeimage.paintIcon(this,g,currentNodeX,currentNodeY);

                }

            }
        }



        //creates the graphics for apple powerup,then gets the pic name and paints the graphics
        appleImage = new ImageIcon(apple.getPicName());
        appleImage.paintIcon(this, g, apple.getPosX(), apple.getPosY());

        //the logic to check for eaten powerup is in powerupEffect
        apple.powerupEffect(snake1);
        apple.powerupEffect(snake2);

        //creates the graphics for muscle powerup,then gets the pic name and paints the graphics
        System.out.println("the name file for muscle is " + muscle.getPicName());
        muscleImage = new ImageIcon(muscle.getPicName());	// set animation for muscle power up
        muscleImage.paintIcon(this, g, muscle.getPosX(), muscle.getPosY());

        //the logic to check for eaten powerup is in powerupEffect
        muscle.powerupEffect(snake1);
        muscle.powerupEffect(snake2);


        //creates the graphics for muscle powerup,then gets the pic name and paints the graphics
        poisonImage = new ImageIcon(poison.getPicName());	// set animation for poison power up
        poisonImage.paintIcon(this, g, poison.getPosX(), poison.getPosY());

        //the logic to check for eaten powerup is in powerupEffect
        poison.powerupEffect(snake1);
        poison.powerupEffect(snake2);




        //snake 1 lose condition
        for (int b = 1; b < snake1.getSnakeLength(); b++)
        {
            if(snake1.getXYPosition("x", b) == snake1.getXYPosition("x", 0) && snake1.getXYPosition("y", b) == snake1.getXYPosition("y", 0))
            {
				/*
				right = false;
				left = false;
				up = false;
				down = false;
				allowInput = false;
				*/

                //controls.blockControl();
                //controls.setAllowInput(0);
                snake1.blockControl();
                snake1.setAllowInput(0);


                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 60));
                g.drawString("P1 Out", 300, 300);
                g.dispose();

				/*g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART", 350, 340);*/
            }
        }

        //snake 2 lose condition
        for (int b = 1; b < snake2.getSnakeLength(); b++)
        {
            if(snake2.getXYPosition("x", b) == snake2.getXYPosition("x", 0) && snake2.getXYPosition("y", b) == snake2.getXYPosition("y", 0))
            {
				/*d = false;
				a = false;
				w = false;
				s = false;
				allowInput = false;
				*/

                //controls.blockControl();
                //controls.setAllowInput(0);
                snake2.blockControl();
                snake2.setAllowInput(0);


                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 60));
                g.drawString("P2 Out", 300, 300);
                g.dispose();

				/*g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Space to RESTART", 350, 340);*/
            }
        }


        // snake to snake collision detection

        for(int i = 0; i <snake1.getSnakeLength(); i++)	//checks every snake 1 cell,and if snake 2 head collide,game over.
        {
            if(snake2.getXYPosition("x", 0) == snake1.getXYPosition("x", i) && snake2.getXYPosition("y", 0) == snake1.getXYPosition("y", i)) {

				/*right = false;
				left = false;
				up = false;
				down = false;
				allowInput = false;
				*/

                //controls.blockControl();
                //controls.setAllowInput(0);

                snake2.blockControl();
                snake2.setAllowInput(0);

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 60));
                g.drawString("P2 Collide,Game Over", 150, 300);
                g.dispose();



            }


        }
        for(int d = 0; d <snake2.getSnakeLength(); d++)		//checks every snake 2 cell,and if snake 1 head collide,game over.
        {
            if(snake1.getXYPosition("x", 0) == snake2.getXYPosition("x", d) && snake1.getXYPosition("y", 0) == snake2.getXYPosition("y", d)) {

				/*right = false;
				left = false;
				up = false;
				down = false;
				allowInput = false;
				*/

                //controls.blockControl();
                //controls.setAllowInput(0);

                snake1.blockControl();
                snake1.setAllowInput(0);

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 60));
                g.drawString("P1 Collide,Game Over", 150, 300);
                g.dispose();

            }
        }



        g.dispose();
    }
		else if (gameState == 3){
        setBackground(Color.BLACK);
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 30));;
        //Button and label for Start
        g.drawRect(300, 50, 300,55);
        g.drawString("Scoreboard", 365, 85);

    }

		else {
        System.exit(0);
    }
}

    public void actionPerformed(ActionEvent e) {
        timer.start();
        //System.out.println("snake 1 allowinput : ");
        //System.out.println(snake1.getAllowInput());
        //System.out.println("snake 2 allowinput : ");
        //System.out.println(snake2.getAllowInput());


        if(snake1.getAllowInput() == 1)
        {
            //player 1 up,down,left,right input

            if(snake1.getKeyTrueFalseValue("d") == 1)
            {
                for (int r = snake1.getSnakeLength()-1; r >= 0; r--)
                {

                    snake1.setXYPosition("y",r + 1,snake1.getXYPosition("y", r));

                }
                for (int r = snake1.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake1.setXYPosition("x", r, snake1.getXYPosition("x", r) + 25);

                    }
                    else
                    {

                        snake1.setXYPosition("x", r, snake1.getXYPosition("x", r - 1));
                    }
                    if (snake1.getXYPosition("x", r) > 850) //out of border
                    {
                        //set position on other side
                        snake1.setXYPosition("x", r, 25);
                    }
                }
                repaint();

            }

            else if(snake1.getKeyTrueFalseValue("a") == 1)
            {
                for (int r = snake1.getSnakeLength()-1; r >= 0; r--)
                {

                    snake1.setXYPosition("y", r+1, snake1.getXYPosition("y", r));

                }
                for (int r = snake1.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake1.setXYPosition("x", r, snake1.getXYPosition("x", r) - 25);

                    }
                    else
                    {

                        snake1.setXYPosition("x", r, snake1.getXYPosition("x", r - 1));

                    }
                    if (snake1.getXYPosition("x", r) < 25) //out of border
                    {
                        //set position on other side
                        snake1.setXYPosition("x", r, 850);
                    }
                }
                repaint();
            }

            //if(controls.getKeyTrueFalseValue("w") == 1)
            else if(snake1.getKeyTrueFalseValue("w") == 1)
            {
                for (int r = snake1.getSnakeLength()-1; r >= 0; r--)
                {

                    snake1.setXYPosition("x", r+1, snake1.getXYPosition("x", r));

                }
                for (int r = snake1.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake1.setXYPosition("y", r, snake1.getXYPosition("y", r) - 25);
                    }
                    else
                    {

                        snake1.setXYPosition("y", r, snake1.getXYPosition("y", r - 1));
                    }
                    if (snake1.getXYPosition("y", r)< 75) //out of border
                    {
                        //set position on other side
                        snake1.setXYPosition("y", r, 625);
                    }
                }
                repaint();
            }

            else if(snake1.getKeyTrueFalseValue("s") == 1)
            {
                for (int r = snake1.getSnakeLength()-1; r >= 0; r--)
                {

                    snake1.setXYPosition("x", r+1, snake1.getXYPosition("x", r));

                }
                for (int r = snake1.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake1.setXYPosition("y", r, snake1.getXYPosition("y", r) + 25);

                    }
                    else
                    {

                        snake1.setXYPosition("y", r, snake1.getXYPosition("y", r - 1));

                    }
                    if (snake1.getXYPosition("y", r) > 625) //out of border
                    {
                        //set position on other side
                        snake1.setXYPosition("y", r, 75);
                    }
                }
                repaint();
            }


            //player 2 up,down,left,right input
            if(snake2.getKeyTrueFalseValue("right") == 1)
            {
                for (int r = snake2.getSnakeLength()-1; r >= 0; r--)
                {

                    snake2.setXYPosition("y",r + 1,snake2.getXYPosition("y", r));

                }
                for (int r = snake2.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake2.setXYPosition("x", r, snake2.getXYPosition("x", r) + 25);

                    }
                    else
                    {

                        snake2.setXYPosition("x", r, snake2.getXYPosition("x", r - 1));

                    }
                    if (snake2.getXYPosition("x", r)> 850) //out of border
                    {
                        //set position on other side

                        snake2.setXYPosition("x", r, 25);
                    }
                }
                repaint();

            }

            if(snake2.getKeyTrueFalseValue("left") == 1)
            {
                for (int r = snake2.getSnakeLength()-1; r >= 0; r--)
                {

                    snake2.setXYPosition("y", r+1, snake2.getXYPosition("y", r));

                }
                for (int r = snake2.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake2.setXYPosition("x", r, snake2.getXYPosition("x", r) - 25);

                    }
                    else
                    {

                        snake2.setXYPosition("x", r, snake2.getXYPosition("x", r - 1));

                    }
                    if (snake2.getXYPosition("x", r)< 25) //out of border
                    {
                        //set position on other side
                        snake2.setXYPosition("x", r, 850);
                    }
                }
                repaint();
            }

            if(snake2.getKeyTrueFalseValue("up") == 1)
            {
                for (int r = snake2.getSnakeLength()-1; r >= 0; r--)
                {

                    snake2.setXYPosition("x", r+1, snake2.getXYPosition("x", r));

                }
                for (int r = snake2.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake2.setXYPosition("y", r, snake2.getXYPosition("y", r) - 25);

                    }
                    else
                    {

                        snake2.setXYPosition("y", r, snake2.getXYPosition("y", r - 1));

                    }
                    if (snake2.getXYPosition("y", r)< 75) //out of border
                    {
                        //set position on other side
                        snake2.setXYPosition("y", r, 625);
                    }
                }
                repaint();
            }

            if(snake2.getKeyTrueFalseValue("down") == 1)
            {
                for (int r = snake2.getSnakeLength()-1; r >= 0; r--)
                {

                    snake2.setXYPosition("x", r+1, snake2.getXYPosition("x", r));

                }
                for (int r = snake2.getSnakeLength(); r >= 0; r--)
                {
                    if (r == 0)
                    {

                        snake2.setXYPosition("y", r, snake2.getXYPosition("y", r) + 25);

                    }
                    else
                    {

                        snake2.setXYPosition("y", r, snake2.getXYPosition("y", r - 1));

                    }
                    if (snake2.getXYPosition("y", r)> 625) //out of border
                    {
                        //set position on other side
                        snake2.setXYPosition("y", r, 75);
                    }
                }
                repaint();
            }
        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println("value checkers in keypressed before any checks  w a s d");
        //System.out.println(controls.getKeyTrueFalseValue("w"));
        //System.out.println(controls.getKeyTrueFalseValue("a"));
        //System.out.println(controls.getKeyTrueFalseValue("s"));
        //System.out.println(controls.getKeyTrueFalseValue("d"));
        System.out.println(snake1.getKeyTrueFalseValue("w"));
        System.out.println(snake1.getKeyTrueFalseValue("a"));
        System.out.println(snake1.getKeyTrueFalseValue("s"));
        System.out.println(snake1.getKeyTrueFalseValue("d"));

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)	//check if ESC is clicked
        {
            gameState = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) //reset game
        {
            //moves = 0;
            System.out.println("Entered space keyEvent");

            //controls.setMove(0);
            //controls.setAllowInput(1);

            snake1.setScore(0);
            snake1.setSnakeLength(3);

            snake1.setMove(0);
            snake1.setAllowInput(1);

            snake2.setScore(0);
            snake2.setSnakeLength(3);

            snake2.setMove(0);
            snake2.setAllowInput(1);

            snake1.setKeyTrueFalseValue("d", 1);
            snake2.setKeyTrueFalseValue("left", 1);
            //allowInput = true;
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_D)	//check if right is pressed for player 1
        {
            System.out.println("Entered d keyEvent");

            snake1.setMove(snake1.getMove()+1);
            snake1.setKeyTrueFalseValue("d", 1);

            if (snake1.getKeyTrueFalseValue("a") == 0)	//disable the use of opposite direction
            {

                snake1.setKeyTrueFalseValue("d", 1);

            }
            else
            {

                snake1.setKeyTrueFalseValue("d", 0);
                snake1.setKeyTrueFalseValue("a", 1);

            }

            snake1.setKeyTrueFalseValue("w", 0);
            snake1.setKeyTrueFalseValue("s", 0);



        }

        if (e.getKeyCode() == KeyEvent.VK_A)	//check if left is pressed for player 1
        {
            System.out.println("Entered a keyEvent");

            snake1.setMove(snake1.getMove()+1);
            snake1.setKeyTrueFalseValue("a",1);

            if (snake1.getKeyTrueFalseValue("d") == 0)	//disable the use of opposite direction
            {

                System.out.print("the move counter ");
                System.out.print(snake1.getMove());

                snake1.setKeyTrueFalseValue("a",1);
            }
            else
            {

                snake1.setKeyTrueFalseValue("a",0);
                snake1.setKeyTrueFalseValue("d",1);

            }

            snake1.setKeyTrueFalseValue("w",0);
            snake1.setKeyTrueFalseValue("s",0);
        }

        if (e.getKeyCode() == KeyEvent.VK_W)	//check if up is pressed for player 1
        {
            System.out.println("Entered w keyEvent");

            snake1.setMove(snake1.getMove()+1);
            snake1.setKeyTrueFalseValue("w", 1);

            if (snake1.getKeyTrueFalseValue("s") == 0)	//disable the use of opposite direction
            {

                snake1.setKeyTrueFalseValue("w", 1);

            }
            else
            {

                snake1.setKeyTrueFalseValue("w", 0);
                snake1.setKeyTrueFalseValue("s", 1);

            }

            snake1.setKeyTrueFalseValue("a", 0);
            snake1.setKeyTrueFalseValue("d", 0);

        }

        if (e.getKeyCode() == KeyEvent.VK_S)	//check if down is pressed for player 1
        {

            System.out.println("Entered s keyEvent");

            snake1.setMove(snake1.getMove() + 1);
            snake1.setKeyTrueFalseValue("s", 1);

            System.out.println("the down true value before being set by keyevent is ");
            System.out.println(snake1.getKeyTrueFalseValue("s"));

            System.out.println("the up true value before being set by keyevent is ");
            System.out.println(snake1.getKeyTrueFalseValue("w"));

            if (snake1.getKeyTrueFalseValue("w") == 0)	//disable the use of opposite direction
            {

                snake1.setKeyTrueFalseValue("s", 1);

                System.out.println("1:the down true value is ");
                System.out.println(snake1.getKeyTrueFalseValue("s"));

            }
            else
            {

                snake1.setKeyTrueFalseValue("s", 0);
                snake1.setKeyTrueFalseValue("w", 1);

            }

            snake1.setKeyTrueFalseValue("a", 0);
            snake1.setKeyTrueFalseValue("d", 0);


            System.out.println("the down true value set after keyevent is ");
            System.out.println(snake1.getKeyTrueFalseValue("s"));

            System.out.println("the up true value set after keyevent is ");
            System.out.println(snake1.getKeyTrueFalseValue("w"));
        }


        if (e.getKeyCode() == KeyEvent.VK_RIGHT) //check if right is pressed for player 2
        {
            System.out.println("Entered right keyEvent");

            snake2.setMove(snake2.getMove()+1);
            snake2.setKeyTrueFalseValue("right", 1);

            if (snake2.getKeyTrueFalseValue("left") == 0)	//disable the use of opposite direction
            {

                snake2.setKeyTrueFalseValue("right", 1);

            }
            else
            {

                snake2.setKeyTrueFalseValue("right", 0);
                snake2.setKeyTrueFalseValue("left", 1);

            }

            snake2.setKeyTrueFalseValue("up", 0);
            snake2.setKeyTrueFalseValue("down", 0);

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT)	//check if left is pressed for player 2
        {
            System.out.println("Entered left keyEvent");

            snake2.setMove(snake2.getMove()+1);
            snake2.setKeyTrueFalseValue("left", 0);

            if (snake2.getKeyTrueFalseValue("right") == 0)	//disable the use of opposite direction
            {

                snake2.setKeyTrueFalseValue("left", 1);

            }
            else
            {

                snake2.setKeyTrueFalseValue("left", 0);
                snake2.setKeyTrueFalseValue("right", 1);

            }

            snake2.setKeyTrueFalseValue("up", 0);
            snake2.setKeyTrueFalseValue("down", 0);

        }

        if (e.getKeyCode() == KeyEvent.VK_UP)	//check if up is pressed for player 2
        {
            System.out.println("Entered up keyEvent");

            snake2.setMove(snake2.getMove()+1);
            snake2.setKeyTrueFalseValue("up", 1);

            if (snake2.getKeyTrueFalseValue("down") == 0)	//disable the use of opposite direction
            {

                snake2.setKeyTrueFalseValue("up", 1);

            }
            else
            {

                snake2.setKeyTrueFalseValue("up", 0);
                snake2.setKeyTrueFalseValue("down", 1);

            }

            snake2.setKeyTrueFalseValue("left", 0);
            snake2.setKeyTrueFalseValue("right", 0);

        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN)	//check if down is pressed for player 2
        {

            System.out.println("Entered down keyEvent");

            snake2.setMove(snake2.getMove()+1);
            snake2.setKeyTrueFalseValue("down", 1);

            if (snake2.getKeyTrueFalseValue("up") == 0)	//disable the use of opposite direction
            {

                snake2.setKeyTrueFalseValue("down", 1);

            }
            else
            {

                snake2.setKeyTrueFalseValue("down", 0);
                snake2.setKeyTrueFalseValue("up", 1);

            }

            snake2.setKeyTrueFalseValue("left", 0);
            snake2.setKeyTrueFalseValue("right", 0);

        }


    }

    public void keyReleased(KeyEvent e) {

    }
}
