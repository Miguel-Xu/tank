package indi.edward.tank.cor;

import indi.edward.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public void add(Collider collider){
        colliders.add(collider);
    }

    public ColliderChain(){
        colliders.add(new BulletTankCollider());
    }


    public boolean collide(GameObject o1, GameObject o2) {
        for(int i = 0; i < colliders.size(); i++){
            if(!colliders.get(i).collide(o1, o2)) return false;
        }
        return true;
    }
}
