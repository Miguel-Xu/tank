package indi.edward.tank;

import indi.edward.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1080;
    static final int GAME_HEIGHT = 960;
    Tank myTank = new Tank(200,400,Dir.UP, Group.GOOD, this);
    List<BaseBullet> bullets = new ArrayList<>();
    List<BaseTank> enemies = new ArrayList<>();
    List<BaseExplosion> explosions = new ArrayList<>();

    public AbstractGameFactory gf = new DefaultFactory();

    public TankFrame(){

        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("The Number of Bullet(s):" +bullets.size(),10, 50 );
        g.drawString("The Number of enemies:" + enemies.size(), 160, 50);
        g.setColor(color);
        myTank.paint(g);
        for(int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).paint(g);
        }
        for(int i = 0; i < bullets.size(); i++){
            for(int j = 0; j < enemies.size(); j++){
                bullets.get(i).collideWith(enemies.get(j));
            }
        }
        for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).paint(g);
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
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
