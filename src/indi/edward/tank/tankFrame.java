package indi.edward.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tankFrame extends Frame {

    int x = 200, y = 200;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

    public tankFrame(){

        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("Tank Wars");
        this.setVisible(true);
        this.addKeyListener(new MykeyListener());

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
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default: break;

        }
    }

    class MykeyListener extends KeyAdapter{

        boolean BL = false;
        boolean BR = false;
        boolean BU = false;
        boolean BD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    BL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = true;
                    break;
                case KeyEvent.VK_UP:
                    BU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = true;
                    break;
                default: break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    BL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    BR = false;
                    break;
                case KeyEvent.VK_UP:
                    BU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    BD = false;
                    break;
                default: break;
            }

            setMainTankDir();
        }

        public void setMainTankDir(){
            if(BL) dir = Dir.LEFT;
            if(BR) dir = Dir.RIGHT;
            if(BU) dir = Dir.UP;
            if(BD) dir = Dir.DOWN;
        }
    }
}
