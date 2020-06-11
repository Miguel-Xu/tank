package indi.edward.tank;

import org.ietf.jgss.GSSManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200,400,Dir.UP, Group.GOOD, this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> enemies = new ArrayList<>();
    List<Explosion> explosions = new ArrayList<>();

    public GameModel(){
        int initTankCounts = Integer.parseInt((String)PropertyMgr.getValue("initTankCounts"));
        for(int i = 0; i < initTankCounts; i++){
            enemies.add(new Tank(50+i*100 ,100, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("The Number of Bullet(s):" +bullets.size(),10, 50 );
        g.drawString("The Number of enemies:" + enemies.size(), 160, 50);
        g.setColor(color);
        myTank.paint(g);
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).paint(g);
        }
        for(int i = 0; i < bullets.size(); i++){
            for(int j = 0; j < enemies.size(); j++){
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).paint(g);
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

}
