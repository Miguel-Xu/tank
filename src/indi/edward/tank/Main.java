package indi.edward.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for(int i = 0; i < 5; i++){
            tf.enemies.add(new Tank(50+i*100 ,100, Dir.DOWN, Group.BAD, tf));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
