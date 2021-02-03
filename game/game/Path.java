package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
public class Path extends JComponent {
    public int x;
    public int y;
    int width=50;
    int height=50;
    public static Rectangle path;
    int oneTime=1;
    
    public Path(int xp, int yp){
    	path = new Rectangle(xp,yp,width,height);
    	this.setLocation(xp,yp);
    	move();
    }
    
    public void move(){
        x=this.getX();
        y=this.getY();
        this.setLocation(x,y);
        path.setLocation(x,y);
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
        g2.setColor(new Color(200,150,0));
        g2.fill(path);
        repaint();
    }
    
}