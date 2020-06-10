package indi.edward.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.getValue("initTankCount"));

        for(int i = 0; i < initTankCount; i++){
            tf.enemies.add(tf.gf.CreateTank(50+i*100 ,100, Dir.DOWN, Group.BAD, tf));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint();

        }
    }
}
