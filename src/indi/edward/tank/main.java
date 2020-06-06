package indi.edward.tank;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class main {
    public static void main(String[] args) throws InterruptedException {
        tankFrame tf = new tankFrame();

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
