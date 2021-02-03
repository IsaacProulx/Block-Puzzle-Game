package game;
import java.awt.*;
import javax.swing.*;

public class heartContainer extends JComponent {
	public Rectangle heartC;
	int hX = gui.plrX;
	int hY = gui.plrY;
	
	public void init() {
		this.setLocation(hX, hY);
		heartC = new Rectangle(hX,hY,30,30);
	}
	
	public heartContainer() {
		init();
		//System.out.println("added heart");
	}
	
	public void setLocation() {
		hX=this.getX();
		hY=this.getY();
		heartC.setLocation(hX,hY);
		this.setLocation(hX, hY);
	}
	
	public void paintComponent(Graphics g) {
		setLocation();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		g2.fill(heartC);
		repaint();
	}
	
}