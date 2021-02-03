package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JComponent;
import org.w3c.dom.css.Rect;

public class graphics extends JComponent {
    public int bulX = gui.plrX;
    public int bulY = gui.plrY;
    int i=0;
    public Rectangle bul;
    int bulFacing = gui.plrFacing;
    
    public void reset(){
        bulX = 50;
        bulY = 50;
        i=0;
        bulFacing = gui.plrFacing;
    }
    
   
    
    public void init(){
        i++;
        bulX=gui.plrX;
        bulY=gui.plrY;
        this.setLocation(bulX, bulY);
        bul = new Rectangle(bulX,bulY,50,50);
    }
    
    public graphics(){
    	init();
    }
    
    public void rectangle(){
    	bulX=this.getX();
    	bulY=this.getY();             
        
    	if(bulFacing==1) {
    		bulY-=10;
    	}
    	
    	if(bulFacing==2) {
    		bulY-=10;
    		bulX+=10;
    	}
    	
    	if(bulFacing==3) {
    		bulX+=10;
    	}
    	
    	if(bulFacing==4) {
    		bulY+=10;
    		bulX+=10;
    	}
    	
    	if(bulFacing==5) {
    		bulY+=10;
    	}
    	
    	if(bulFacing==6) {
    		bulY+=10;
    		bulX-=10;
    	}
    	
    	if(bulFacing==7) {
    		bulX-=10;
    	}
    	
    	if(bulFacing==8) {
    		bulY-=10;
    		bulX-=10;
    	}
    	
    	this.setLocation(bulX, bulY);
        bul.setLocation(bulX,bulY);
    }
    
    
    
    public void paintComponent(Graphics g) {
    	rectangle();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.RED);
        //g.drawString(""+i,500,500);
        g2.fill(bul);
        repaint();
    }
    
}