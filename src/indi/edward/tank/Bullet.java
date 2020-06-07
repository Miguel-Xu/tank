package indi.edward.tank;

import java.awt.*;

public class Bullet {
    public static final int BULLET_HEIGHT = ResourceMgr.bulletL.getHeight();
    public static final int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    private static final int SPEED = 10;
    private boolean isAlive = true;
    private int x;
    private int y;
    private Dir dir;
    private Group group = Group.BAD;
    private TankFrame tf = null;


    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.bullets.remove(this);
        }
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) isAlive = false;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;
        Rectangle bullet_rect = new Rectangle(this.x, this.y, BULLET_WIDTH, BULLET_HEIGHT);
        Rectangle tank_rect = new Rectangle(tank.getX(), tank.getY(),Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
        if(bullet_rect.intersects(tank_rect)){
            this.die();
            tank.die();
        }
    }

    private void die() {
        isAlive = false;
    }
}
