package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

public class button extends JComponent {
	
	    public int x;
	    public int y;
	    int width=50;
	    int height=50;
	    public static Rectangle button;
	    int oneTime=1;
	    private boolean pressed =false;
	    private boolean colour =true;
	    
	    public button(int xp, int yp){
	    	button = new Rectangle(xp,yp,width,height);
	    	this.setLocation(xp,yp);
	    	move();
	    }
	    
	    public void move(){
	        x=this.getX();
	        y=this.getY();
	        pressed=false;
	        gui.blocks.stream().forEach(block->{
	        	try{
                    if(block.block.intersects(button)) {
                    	pressed=true;
                    }
        	    }catch(Exception e){
         			e.printStackTrace();
         		}
         		
         	});  
	        button.setLocation(x, y);
	        this.setLocation(x,y);
	    }
	    
	    public int getPlatformY(){
	        return(y);
	    }
	    
	    public int getPlatformX(){
	        return(x);
	    }
	    
	    public boolean getPress(){
	        return(pressed);
	    }
	    
	    public void paintComponent(Graphics g){
	        move();
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;
	        if(pressed) {
	        	g2.setColor(Color.ORANGE);
	        }else {
	        	g2.setColor(Color.YELLOW);
	        }
	        
	        g2.fill(button);
	        repaint();
	    }
	 
}
