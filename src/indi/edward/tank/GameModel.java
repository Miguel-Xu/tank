package indi.edward.tank;

import indi.edward.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.UP, Group.GOOD, this);

    ColliderChain chain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public GameModel() {
        int initTankCounts = Integer.parseInt((String) PropertyMgr.getValue("initTankCounts"));
        for (int i = 0; i < initTankCounts; i++) {
            add(new Tank(50 + i * 100, 100, Dir.DOWN, Group.BAD, this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(color);
        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }

}
