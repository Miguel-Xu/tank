package indi.edward.tank.cor;

import indi.edward.tank.Bullet;
import indi.edward.tank.GameObject;
import indi.edward.tank.Tank;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            bullet.collideWith(tank);
            return false;
        }
        else if(o2 instanceof Bullet && o1 instanceof Tank) collide(o2, o1);
        return true;
    }
}
