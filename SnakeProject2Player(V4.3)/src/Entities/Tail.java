package Entities;

import javax.swing.*;
import java.awt.*;

public class Tail extends GameEntities {
    private ImageIcon snakeimage;

    public Tail(float x, float y) {
        width = height = 15;
        setPosition(x,y);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(new Color(0, 0.25f, 0, 1));
        g.drawRect((int)x, (int)y, (int)width, (int)height);
        g.fillRect((int)x, (int)y, (int)(width), (int)(height));

        g.setColor(new Color(0, 1f, 0, 0.5f));
        g.drawRect((int)x, (int)y, (int)width, (int)height);


//        snakeimage = new ImageIcon("snakeimageLR.png");
//        snakeimage.paintIcon(null,g,(int)x, (int)y);
//        snakeimage = new ImageIcon("snaketailright1.png");
//        snakeimage.paintIcon(null,g,(int)x, (int)y);
    }

    @Override
    public void update(float dt) {

    }
}
