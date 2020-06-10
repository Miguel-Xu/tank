package indi.edward.tank;

import indi.edward.tank.abstractfactory.BaseExplosion;

import java.awt.*;

public class Explosion extends BaseExplosion {
    public static final int EXPLOSION_HEIGHT = ResourceMgr.explosions[0].getHeight();
    public static final int EXPLOSION_WIDTH = ResourceMgr.explosions[0].getWidth();
    private int x;
    private int y;
    private int step = 0;
    private TankFrame tf = null;


    public Explosion(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explosions[step++], x, y, null);
        if(step >= ResourceMgr.explosions.length){
            tf.explosions.remove(this);
        }
    }

}
