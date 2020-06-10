package indi.edward.tank.abstractfactory;

import indi.edward.tank.Dir;
import indi.edward.tank.Group;
import indi.edward.tank.TankFrame;

public abstract class AbstractGameFactory {
    public abstract BaseTank CreateTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseBullet CreateBullet(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplosion CreateExplosion(int x, int y, TankFrame tf);
}
