package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;

public class enemy extends JComponent {
	int eHealth=5;
	boolean onPlatform=false;
	public Rectangle enemy;
	private graphics graphics;
	private heartContainer heartContainer;
        public ArrayList<Platform> platforms = new ArrayList<Platform>();
	public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
	int i2=0;
	int eX;
	int eY;
	static int eFacing=0;
	public static boolean eFire=false;
	int waitTime=0;
        static double a=9.8;
        static int m = 2;
	boolean moveRight=true;
            boolean moveLeft=true;
            boolean moveUp=true;
            boolean moveDown=true;
        
	public enemy(int x, int y){
		init(x, y);
		eHealth=5;
	}
	
	
	public void init(int x,int y){
		eX=x;
		eY=y;
            this.setLocation(250,250);
            platforms=gui.platforms;
            this.setLocation(eX, eY);
            enemy = new Rectangle(250,250,50,50);
	}
	
	public void reset() {
		eX=250;
		eY=250;
        this.setLocation(eX, eY);
		enemy.setLocation(eX, eY);
	}
	
	public void move() {
            collides();
	if(!gui.firstTime) {
            platforms=gui.platforms;
            eX=this.getX();
            eY=this.getY();
            int speed=5;
            int xOver=0;
            int yOver=0;
            int dX=gui.plr.x-enemy.x;
            int dY=gui.plr.y-enemy.y;
            if(!gui.paused) {
            eX+=speed*(Math.cos(Math.atan2(dY, dX)));
            eY+=speed*(Math.sin(Math.atan2(dY, dX)));
            if(!moveLeft){
                eX+=10;
            }
            if(!moveRight){
                eX-=10;
            }
            if(!moveUp){
                eY+=10;
            }
            if(!moveDown){
                eY-=10;
            }
            }
            this.setLocation(eX,eY);
            enemy.setLocation(eX,eY);
	}
		 
	}
        
        public void collides(){
            moveRight=true;
            moveLeft=true;
            moveUp=true;
            moveDown=true;
            gui.platforms.stream().forEach(platform -> {
          	try{
                    platform.move();
                    if(enemy.x-platform.getPlatformX()>0&&enemy.x-platform.getPlatformX()<=25+25&&enemy.y-platform.getPlatformY()<=25+24&&enemy.y-platform.getPlatformY()>=-25-24){
                        moveLeft=false;
                    }
                    
                    if(enemy.x-platform.getPlatformX()<0&&enemy.x-platform.getPlatformX()>=-25-25&&enemy.y-platform.getPlatformY()<=25+24&&enemy.y-platform.getPlatformY()>=-25-24){
                        moveRight=false;
                    }
                     
                    if(enemy.y-platform.getPlatformY()>0&&enemy.y-platform.getPlatformY()<=25+25&&enemy.x-platform.getPlatformX()<=49&&enemy.x-platform.getPlatformX()>=-45){
                        moveUp=false;
                    }
                    
                    if(enemy.y-platform.getPlatformY()<0&&enemy.y-platform.getPlatformY()>=-25-25&&enemy.x-platform.getPlatformX()<=49&&enemy.x-platform.getPlatformX()>=-45){
                        moveDown=false;
                    }
         	    }catch(Exception e){
                        e.printStackTrace();
                    }
          	});
         }
	
	public void paintComponent(Graphics g) {
		move();
       if(gui.bullets.size()>0&&eHealth>0){
    	   gui.bullets.stream().forEach(bullet->{
            try{
            if(bullet.bul.intersects(enemy)){
                gui.bRemove=true;
                gui.enemyTookDamage=true;
                eHealth--;
            }
            }catch(Exception e){
            }    
        });
        };
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLUE);
        	if(gui.enemyTookDamage==true) {
        		g2.setColor(Color.ORANGE);
        		gui.enemyTookDamage=false;
        	}
        	if(eHealth>0) {
        		g2.fill(enemy);
        	}
        repaint();
	}

}