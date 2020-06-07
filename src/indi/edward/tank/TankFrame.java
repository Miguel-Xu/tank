package indi.edward.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200,200,Dir.DOWN);
    Bullet bullet = new Bullet(30,30, Dir.DOWN);


    public TankFrame(){

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

        myTank.paint(g);
        bullet.paint(g);

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
            if(!BL && !BR && !BU && !BD) myTank.setMoving(false);
            else{
                myTank.setMoving(true);

                if(BL) myTank.setDir(Dir.LEFT);
                if(BR) myTank.setDir(Dir.RIGHT);
                if(BU) myTank.setDir(Dir.UP);
                if(BD) myTank.setDir(Dir.DOWN);
            }

        }
    }
}
