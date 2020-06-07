package indi.edward.tank;

import java.awt.*;


public class Tank {
    private int x;
    private int y;
    private Dir dir = Dir.DOWN;
    private TankFrame tf;
    private boolean isAlive = true;
    private boolean moving = false;
    private static final int SPEED = 5;
    public static final int TANK_HEIGHT = ResourceMgr.tankL.getHeight();
    public static final int TANK_WIDTH = ResourceMgr.tankL.getWidth();

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.enemies.remove(this);

        }
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
        }

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
        double bX = this.x + TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        double bY = this.y + TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;
        tf.bullets.add(new Bullet((int)bX, (int)bY, this.dir, this.tf));
    }

    public void die() {
        isAlive = false;
    }
}

