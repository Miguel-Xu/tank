package indi.edward.tank;

import java.awt.*;
import java.util.Random;


public class Tank {
    private int x;
    private int y;
    private Dir dir = Dir.DOWN;
    private TankFrame tf;
    private boolean isAlive = true;
    private boolean moving = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    private static final int SPEED = 3;
    public static final int TANK_HEIGHT = ResourceMgr.goodTankL.getHeight();
    public static final int TANK_WIDTH = ResourceMgr.goodTankL.getWidth();

    Rectangle rect = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = TANK_WIDTH;
        rect.height = TANK_HEIGHT;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if(!isAlive){
            tf.enemies.remove(this);

        }
        switch(dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
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

            if(group == Group.BAD && random.nextInt(100) > 95) fire();
            if(group == Group.BAD && random.nextInt(100) > 90) randomDir();
            boundsCheck();

            rect.x = this.x;
            rect.y = this.y;
        }
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- Tank.TANK_WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.TANK_HEIGHT -2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        double bX = this.x + TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        double bY = this.y + TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;
        tf.bullets.add(new Bullet((int)bX, (int)bY, this.dir, this.group, this.tf));
    }

    public void die() {
        isAlive = false;
    }
}

