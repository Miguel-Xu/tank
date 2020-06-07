package indi.edward.tank;

import java.awt.*;
import indi.edward.tank.TankFrame;

public class Bullet {
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;
    private static final int SPEED = 10;
    private boolean live = true;
    private int x;
    private int y;
    private Dir dir;
    private TankFrame tf = null;


    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!live){
            tf.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,HEIGHT,WIDTH);
        g.setColor(color);
        move();

    }

    private void move() {

        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default: break;
        }
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;
    }
}
