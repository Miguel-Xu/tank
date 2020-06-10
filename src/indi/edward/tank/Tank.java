package indi.edward.tank;

import indi.edward.tank.abstractfactory.BaseTank;

import java.awt.*;
import java.util.Random;


public class Tank extends BaseTank {

    private int x;
    private int y;
    private Dir dir ;
    public TankFrame tf;
    private boolean isAlive = true;
    private Group group;
    private boolean moving = true;
    private Random random = new Random();
    private static final int SPEED = 3;
    public static final int TANK_HEIGHT = ResourceMgr.goodTankL.getHeight();
    public static final int TANK_WIDTH = ResourceMgr.goodTankL.getWidth();

    FireStrategy fs ;

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

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
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


    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
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
        if(this.group == Group.GOOD) {
            try {
                fs = (FireStrategy) Class.forName((String) PropertyMgr.getValue("goodFireStrategy")).getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else{
            try {
                fs = (FireStrategy) Class.forName((String) PropertyMgr.getValue("badFireStrategy")).getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fs.fire(this);
    }

    @Override
    public void die() {
        isAlive = false;
    }
}

