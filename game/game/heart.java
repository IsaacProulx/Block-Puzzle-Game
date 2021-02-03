package game;
import java.awt.*;

import javax.swing.*;

public class heart extends JComponent {
public Rectangle heart;
public int currentHeart=gui.hearts.size()+1;
int hY=50;
int hX=(currentHeart*50)+10;
	
public void removeHearts() {
	currentHeart=10;
}

public void init() {
		if(currentHeart>=6) {
			hY+=20;
			hX=(currentHeart-6)*50+60;
		}
		
		heart = new Rectangle(hX,hY,10,10);
	}
	
	public heart() {
		init();
	}
	
	public void reset(){
		currentHeart=gui.heartNum;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		if(currentHeart<=20) {
			if(gui.plrHealth>0) {
				if(gui.plrHealth>=currentHeart) {
					g2.fill(heart);
				}else {
					g2.draw(heart);
				}
			}
		}
		repaint();
		
	}
	
}