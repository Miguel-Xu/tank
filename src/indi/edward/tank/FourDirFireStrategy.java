package indi.edward.tank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        double bX = tank.getX() + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        double bY = tank.getY() + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;
        for(Dir dir:Dir.values()){
            tank.tf.gf.CreateBullet((int)bX, (int)bY, dir, tank.getGroup(), tank.getTf());
        }


    }
}
