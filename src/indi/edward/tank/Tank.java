package indi.edward.tank;

import java.awt.*;


public class Tank {
    private int x;
    private int y;
    private Dir dir = Dir.DOWN;
    private TankFrame tf;
    private boolean moving = false;
    private static final int SPEED = 5;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.fillRect(x,y,50,50);
        g.setColor(color);
        move();

    }

    private void move() {
        if(!moving) return;
        else{
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
        }
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
    }
}

