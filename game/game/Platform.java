package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
public class Platform extends JComponent {
    public int x;
    public int y;
    public int width;
    public int height;
    public static Rectangle platform;
    
    public Platform(int xp, int yp, int w, int h){
    	width=w;
    	height=h;
    	platform = new Rectangle(xp,yp,w,h);
    	System.out.println(w);
    	this.setLocation(xp,yp);
    	move();
    }
    
    public void move(){
        x=this.getX();
        y=this.getY();
        this.setLocation(x, y);
        platform.setBounds(x,y,width,height);
    }
    
    public int getPlatformY(){
        return(y);
    }
    
    public int getPlatformX(){
        return(x);
    }
    
    public int getWidth(){
        return(width);
    }
    
    public int getHeight(){
        return(height);
    }
    
    public void paintComponent(Graphics g){
        move();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(50,200,50));
        g2.fill(platform);
        repaint();
    }
    
}