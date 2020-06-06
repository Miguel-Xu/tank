package indi.edward.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tankFrame extends Frame {

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
        g.fillRect(200,200,50,50);
    }
}
