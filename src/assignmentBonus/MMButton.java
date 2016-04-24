package assignmentBonus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class MMButton {
	private Rectangle button;
	private int mouseX, mouseY;
	public MMButton(int x, int y, int width, int height){
		button = new Rectangle(x, y, width, height);
	}
	
	public void SetXY(int x, int y){
		button.x = x;
		button.y = y;
	}
	
	public void SetMouse(int x, int y){
		mouseX = x;
		mouseY = y;
	}
	
	public boolean Contains(int x, int y){
		return button.contains(new Point(x, y));
	}
	
	public void Draw(Graphics g, boolean leftClick){
		//save original color
		Color temp = g.getColor();
		
    	Graphics2D g2 = (Graphics2D)g;
    	
		g.setFont(new Font("Arial", Font.BOLD, 20));
		if(button.contains(new Point(mouseX, mouseY))){
			if(leftClick){
				g2.setColor(Color.DARK_GRAY);
			}
			else{
				g2.setColor(Color.GRAY);
			}
			g2.fill(button);
			g.setColor(Color.WHITE);
			g.drawString("GO", button.x + 2, button.y + 25);
		}
		else{
			g.setColor(Color.BLACK);
			g.drawString("GO", button.x + 2, button.y + 25);
		}
		g2.setColor(Color.BLACK);
		g2.draw(button);
    	
    	//load original color
    	g.setColor(temp);
	}
}
