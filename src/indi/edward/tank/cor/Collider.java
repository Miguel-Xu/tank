package indi.edward.tank.cor;

import indi.edward.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
