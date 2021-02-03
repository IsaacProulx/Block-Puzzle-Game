package game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
public class PushBlocks extends JComponent {
    public int x;
    public int y;
    int width=50;
    int height=50;
    public Rectangle block;
    int oneTime=1;
    boolean moveLeft=true;
    boolean moveRight=true;
    boolean moveUp=true;
    boolean moveDown=true;
    
    public PushBlocks(int xp, int yp){
    	block = new Rectangle(xp,yp,width,height);
    	this.setLocation(xp,yp);
    	move();
    }
    
    public void move(){
        x=this.getX();
        y=this.getY();
       
        moveLeft=true;
        moveRight=true;
        moveUp=true;
        moveDown=true;
        
        gui.blocks.stream().forEach(block->{
        	try {
        		if(x-block.getPlatformX()>0&&x-block.getPlatformX()<=25+25&&y-block.getPlatformY()<=25+24&&y-block.getPlatformY()>=-25-24){
                	moveLeft=false;
                }
                            
                if(x-block.getPlatformX()<0&&x-block.getPlatformX()>=-25-25&&y-block.getPlatformY()<=25+24&&y-block.getPlatformY()>=-25-24){
                	moveRight=false;
                }
                             
                if(y-block.getPlatformY()>0&&y-block.getPlatformY()<=25+25&&x-block.getPlatformX()<=49&&x-block.getPlatformX()>=-45){
                	moveUp=false;
                }
                            
                if(y-block.getPlatformY()<0&&y-block.getPlatformY()>=-25-25&&x-block.getPlatformX()<=49&&x-block.getPlatformX()>=-45){
                	moveDown=false;
                }
        	}catch(Exception ex) {
        		
        	}
        });
        
        gui.platforms.stream().forEach(platform->{
        	try {
        		if(x-platform.getPlatformX()>0&&x-platform.getPlatformX()<=platform.getWidth()&&y-platform.getPlatformY()<=platform.getHeight()-1&&y-platform.getPlatformY()>=-49){
                	moveLeft=false;
                }
                            
                if(x-platform.getPlatformX()<0&&x-platform.getPlatformX()>=-50&&y-platform.getPlatformY()<=platform.getHeight()-1&&y-platform.getPlatformY()>=-49){
                	moveRight=false;
                }
                             
                if(y-platform.getPlatformY()>0&&y-platform.getPlatformY()<=platform.getHeight()&&x-platform.getPlatformX()<=platform.getWidth()-1&&x-platform.getPlatformX()>=-45){
                	moveUp=false;
                }
                            
                if(y-platform.getPlatformY()<0&&y-platform.getPlatformY()>=-50&&x-platform.getPlatformX()<=platform.getWidth()-1&&x-platform.getPlatformX()>=-45){
                	moveDown=false;
                }
        	}catch(Exception ex) {
        		
        	}
        });
        
        if(moveLeft) {
        	if(gui.plr.x-getPlatformX()>0&&gui.plr.x-getPlatformX()<=25+25&&gui.plr.y-getPlatformY()<=25+24&&gui.plr.y-getPlatformY()>=-25-24){
        		x-=10;
        	}
        }        
        
        if(moveRight) {
        	if(gui.plr.x-getPlatformX()<0&&gui.plr.x-getPlatformX()>=-25-25&&gui.plr.y-getPlatformY()<=25+24&&gui.plr.y-getPlatformY()>=-25-24){
        		x+=10;
        	}
        }
        
        if(moveUp) {
        	if(gui.plr.y-getPlatformY()>0&&gui.plr.y-getPlatformY()<=25+25&&gui.plr.x-getPlatformX()<=49&&gui.plr.x-getPlatformX()>=-45){
        		y-=10;
        	}
        }
        
        if(moveDown) {
        	if(gui.plr.y-getPlatformY()<0&&gui.plr.y-getPlatformY()>=-25-25&&gui.plr.x-getPlatformX()<=49&&gui.plr.x-getPlatformX()>=-45){
        		y+=10;
        	}
        }
        
        this.setLocation(x,y);
        block.setLocation(x,y);    
    }
    
    public int getPlatformY(){
        return(y);
    }
    
    public int getPlatformX(){
        return(x);
    }
    
    public void paintComponent(Graphics g){
        move();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GRAY);
        g2.fill(block);
        repaint();
    }
    
}