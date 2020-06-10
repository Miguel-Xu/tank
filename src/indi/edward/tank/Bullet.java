package indi.edward.tank;

import indi.edward.tank.abstractfactory.BaseBullet;
import indi.edward.tank.abstractfactory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {
    public static final int BULLET_HEIGHT = ResourceMgr.bulletL.getHeight();
    public static final int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    private static final int SPEED = 10;
    private boolean isAlive = true;
    private int x;
    private int y;
    private Dir dir;
    private Group group = Group.BAD;
    private TankFrame tf;

    Rectangle rect = new Rectangle();


    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = BULLET_WIDTH;
        rect.height = BULLET_HEIGHT;

        tf.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
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

        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) isAlive = false;
    }

    @Override
    public void collideWith(BaseTank tank) {
        if(this.group == tank.getGroup()) return;

        if(this.rect.intersects(tank.rect)){
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.TANK_WIDTH/2 - Explosion.EXPLOSION_WIDTH/2;
            int eY = tank.getY() + Tank.TANK_HEIGHT/2 - Explosion.EXPLOSION_HEIGHT/2;
            tf.explosions.add(tf.gf.CreateExplosion(eX ,eY, tf));
        }
    }

    private void die() {
        isAlive = false;
    }
}
