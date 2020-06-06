package indi.edward.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tankFrame extends Frame {

    int x = 200, y = 200;

    public tankFrame(){

        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("Tank Wars");
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        x += 10;
        y += 10;
    }
}
