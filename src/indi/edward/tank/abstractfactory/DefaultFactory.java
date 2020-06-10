package indi.edward.tank.abstractfactory;

import indi.edward.tank.*;

public class DefaultFactory extends AbstractGameFactory{

    @Override
    public BaseTank CreateTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseBullet CreateBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x,y,dir,group,tf);
    }

    @Override
    public BaseExplosion CreateExplosion(int x, int y, TankFrame tf) {
        return new Explosion(x,y,tf);
    }
}
