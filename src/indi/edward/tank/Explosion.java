package indi.edward.tank;

import java.awt.*;

public class Explosion extends GameObject {
    public static final int EXPLOSION_HEIGHT = ResourceMgr.explosions[0].getHeight();
    public static final int EXPLOSION_WIDTH = ResourceMgr.explosions[0].getWidth();
    private int x;
    private int y;
    private int step = 0;
    private GameModel gm;


    public Explosion(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explosions[step++], x, y, null);
        if (step >= ResourceMgr.explosions.length) {
            gm.remove(this);
        }
    }

}
