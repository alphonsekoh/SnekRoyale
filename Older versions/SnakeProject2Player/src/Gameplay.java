import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

class snakeObject extends controlClass{
	private int life;
	private int lengthOfSnake;
	private int[] snakeXlength;
	private int[] snakeYlength;
	
	public snakeObject(){
		life = 3;
		lengthOfSnake = 3;
		snakeXlength = new int[750];
		snakeYlength = new int[750];
		
		
		
	}
	public int getLife() {
		
		return life;
		
	}
	public void setLife(int newLife) {
		
		life = newLife;
		
	}
	
	public int getSnakeLength() {
		
		return lengthOfSnake;
	}
	public void setSnakeLength(int length) {
		
		lengthOfSnake = length;
	}
	public int[] getSnakeArrayX() {
		
		return snakeXlength;
		
	}
	
	public int[] getSnakeArrayY() {
		
		return snakeYlength;
		
	}
	
	public void setInitialSnakePosition(int x0,int x1,int x2,int y0,int y1,int y2) { //this sets the first 3 body dots of snake( X 0,1,2 and Y 0,1,2 ) position for the snake body
		
		snakeXlength[0] = x0;
		snakeXlength[1] = x1;
		snakeXlength[2] = x2;
		
		snakeYlength[0] = y0;
		snakeYlength[1] = y1;
		snakeYlength[2] = y2;
		
		
		
	}
	public int getXYPosition(String axis,int index) {
		
		int tempPosition = 0;
		if(axis == "x") {
			
			if(index<snakeXlength.length) {
				
				tempPosition =  snakeXlength[index];
				
			}
		}
		else if(axis == "y") {
			
			if(index<snakeYlength.length) {
				
				tempPosition =  snakeYlength[index];
				
			}
		}
		
		return tempPosition;
		
	}
	public void setXYPosition(String axis,int index,int position) {
		
		if(axis == "x") {
			
			if(index<snakeXlength.length) {
				
				snakeXlength[index] = position;
				
			}
		}
		else if(axis == "y") {
			
			if(index<snakeYlength.length) {
				
				snakeYlength[index] = position;
				
			}
		}
		
		
	}
}
class controlClass{
	
	private int move;
	
	private int allowInput;
	private int left;
	private int right;
	private int up;
	private int down;
	
	private int a;
	private int d;
	private int w;
	private int s;
	
	public controlClass() {
		move = 0;
		
		allowInput = 0;
		left = 0;
		right = 0;
		up = 0;
		down = 0;
		
		a = 0;
		d = 0;
		w = 0;
		s = 0;
		
	}
	public void setMove(int newMove) {
		
		move = newMove;
		
	}
	public int getMove() {
		
		return move;
		
	}
	public void setAllowInput(int newInput) {
		
		allowInput = newInput;
		
	}
	public int getAllowInput() {
		
		return allowInput;
		
	}
	public void setKeyTrueFalseValue(String key, int newValue) {
		System.out.println("inside set method set value: ");
		System.out.println(newValue);
		System.out.println("inside set method set target: ");
		System.out.println(key);
		
		// 0  represents off,1 represent on
		
		if (key == "w") {
			w = newValue;
		}
		else if (key == "a") {
			a = newValue;
		}
		else if (key == "s") {
			s = newValue;
		}
		else if (key == "d") {
			d = newValue;
		}
		else if (key == "left") {
			left = newValue;
		}
		else if (key == "up") {
			up = newValue;
		}
		else if (key == "down") {
			down = newValue;
		}
		else if (key == "right") {
			right = newValue;
		}
		System.out.println("inside set trueFalseValue before exiting check w a s d");
		System.out.println(w);
		System.out.println(a);
		System.out.println(s);
		System.out.println(d);
		
	}
	public int getKeyTrueFalseValue(String key) {
		
		int tempInt = 0;
		switch(key) {
		case "left":
			tempInt = left;
			break;
			
		case "right":
			tempInt = right;	
			break;
			
		case "up":
			tempInt = up;
			break;
			
		case "down":
			tempInt = down;
			break;
			
		case "a":
			tempInt = a;
			break;
			
		case "w":
			tempInt = w;
			break;
			
		case "s":
			tempInt = s;
			break;
		
		case "d":
			tempInt = d;
			break;
		
		}
		
		return tempInt;
	}
	
	public void blockControl() {
		
		//if(p == 1) {  //if player 1 calls the blockControl
			
			a = 0;
			d = 0;
			w = 0;
			s = 0;
			
		//}
		//else if(p == 2) { //if player 2 calls the blockcontrol
			
			left = 0;
			right = 0;
			up = 0;
			down = 0;
			
		//}
		
	}
	
}
class gameBoardUI extends JPanel{
	
	private ImageIcon titleImage;
	private ImageIcon appleImage;
	private ImageIcon snakeimage;
	
	
	
	
}
class gameStart{
	
}
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	
	snakeObject snake1 = new snakeObject();
	snakeObject snake2 = new snakeObject();
	
	//controlClass controls = new controlClass();
	
	private ImageIcon titleImage;
	private ImageIcon appleImage;
	private ImageIcon snakeimage;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int[] appleXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325,
			350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 
			675, 700, 725, 750, 775, 800, 825, 850};
	private int[] appleYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 
			375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private Random random = new Random();	
	private int xPos = random.nextInt(34);
	private int yPos = random.nextInt(23);
	
	private int score_1 = 0;
	private int score_2 = 0;
	
	private Timer timer;
	private int delay = 100;
	
	
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
		
	}
	
	public void paint(Graphics g)
	{
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
		titleImage = new ImageIcon("snaketitle.jpg");
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
		g.drawString("Score(P1): "+ score_1, 780, 30);
		
		//draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score(P2): "+ score_2, 780, 50);
		
		
		//create Snake 1
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snake1.getXYPosition("x",0), snake1.getXYPosition("y",0));
		
		
		//create Snake 2
		leftmouth = new ImageIcon("leftmouthP2.png");
		leftmouth.paintIcon(this, g, snake2.getXYPosition("x",0), snake2.getXYPosition("y",0));
		
		for(int x = 0; x < snake1.getSnakeLength(); x++) //animation for snake 1
		{
			System.out.println("snake 1 animation running");
			
			if(x == 0 && snake1.getKeyTrueFalseValue("d") == 1)
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snake1.getXYPosition("x", x), snake1.getXYPosition("y", x));
			}
			
			if(x == 0 && snake1.getKeyTrueFalseValue("a") == 1)
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snake1.getXYPosition("x", x), snake1.getXYPosition("y", x));
			}
			
			if(x == 0 && snake1.getKeyTrueFalseValue("w") == 1)
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snake1.getXYPosition("x", x), snake1.getXYPosition("y", x));
			}
			
			if(x == 0 && snake1.getKeyTrueFalseValue("s") == 1)
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snake1.getXYPosition("x", x), snake1.getXYPosition("y", x));
			}
			
			if(x != 0)
			{
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snake1.getXYPosition("x", x), snake1.getXYPosition("y", x));
			}
		}
		
		//animations for snake 2
		for(int a = 0; a < snake2.getSnakeLength(); a++)
		{
			System.out.println("snake 2 animation running");
			if(a == 0 && snake2.getKeyTrueFalseValue("right") == 1)
			{
				rightmouth = new ImageIcon("rightmouthP2.png");
				rightmouth.paintIcon(this, g,snake2.getXYPosition("x", a),snake2.getXYPosition("y", a));
			}
			
			if(a == 0 && snake2.getKeyTrueFalseValue("left") == 1)
			{
				leftmouth = new ImageIcon("leftmouthP2.png");
				leftmouth.paintIcon(this, g, snake2.getXYPosition("x", a), snake2.getXYPosition("y", a));
			}
			
			if(a == 0 && snake2.getKeyTrueFalseValue("up") == 1)
			{
				upmouth = new ImageIcon("upmouthP2.png");
				upmouth.paintIcon(this, g, snake2.getXYPosition("x", a), snake2.getXYPosition("y", a));
			}

			if(a == 0 && snake2.getKeyTrueFalseValue("down") == 1)
			{
				downmouth = new ImageIcon("downmouthP2.png");
				downmouth.paintIcon(this, g, snake2.getXYPosition("x", a), snake2.getXYPosition("y", a));
			}
			
			if(a != 0)
			{
				snakeimage = new ImageIcon("snakeimageP2.png");
				snakeimage.paintIcon(this, g, snake2.getXYPosition("x", a), snake2.getXYPosition("y", a));
				
			}
		}
		
		
		
		appleImage = new ImageIcon("apple.png");	//set animation for apple
		appleImage.paintIcon(this, g, appleXpos[xPos], appleYpos[yPos]);
		
		//after eating the apple for snake 1
		if (appleXpos[xPos] == snake1.getXYPosition("x",0) && appleYpos[yPos] == snake1.getXYPosition("y",0))
		{
			score_1++;
			int tempLength = snake1.getSnakeLength()+1;
			snake1.setSnakeLength(tempLength);
			
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}
		
		//after eating the apple for snake 2
		if (appleXpos[xPos] == snake2.getXYPosition("x",0) && appleYpos[yPos] == snake2.getXYPosition("y",0))
		{
			score_2++;
			int tempLength = snake2.getSnakeLength()+1;
			snake2.setSnakeLength(tempLength);
			
			xPos = random.nextInt(34);
			yPos = random.nextInt(23);
		}
		
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
				g.drawString("P2 Collide,Game Over", 300, 300);
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
				g.drawString("P1 Collide,Game Over", 300, 300);
				g.dispose();
				
			}
		}
		
		
		
		g.dispose();
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
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) //reset game
		{
			//moves = 0;
			System.out.println("Entered space keyEvent");
			
			//controls.setMove(0);
			//controls.setAllowInput(1);
			
			score_1 = 0;
			snake1.setSnakeLength(3);
			
			snake1.setMove(0);
			snake1.setAllowInput(1);
			
			score_2 = 0;
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
