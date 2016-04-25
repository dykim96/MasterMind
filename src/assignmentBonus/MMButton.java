/*  Assignment Bonus: Master Mind
 *  Create game, Master Mind, with Java
 *  Section: 16185
 *  Name1: Doyoung Kim
 *  UTEID1: dk24338
 *  Name2: Hyo-Jung Kim
 *  UTEID2: hk6336
 */
package assignmentBonus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class MMButton {
	private Rectangle button;
	private int mouseX, mouseY;
	private String text;
	private Font button_Font;
	public MMButton(int x, int y, int width, int height, String text){
		button = new Rectangle(x, y, width, height);
		this.text = text;
		this.button_Font = new Font("Arial", Font.BOLD, 18);
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
	
	public void SetText(String text){
		this.text = text;
	}
	
	public void Draw(Graphics g, boolean leftClick){
		//save original color
		Color temp = g.getColor();
		
		Font tempF = g.getFont();
    	Graphics2D g2 = (Graphics2D)g;
    	
		g.setFont(button_Font);
		FontRenderContext context = ((Graphics2D) g).getFontRenderContext();
		TextLayout layout = new TextLayout(text, button_Font, context);
		float width = layout.getAdvance();
		float height = layout.getAscent() + layout.getDescent();
		
		if(button.contains(new Point(mouseX, mouseY))){
			if(leftClick){
				g2.setColor(Color.DARK_GRAY);
			}
			else{
				g2.setColor(Color.GRAY);
			}
			g2.fill(button);
			g.setColor(Color.WHITE);
			g.drawString(text, (int)(button.x + button.width/2 - width/2), (int)(button.y + button.height/2 + height/2)-2);
		}
		else{
			g.setColor(Color.BLACK);
			g.drawString(text, (int)(button.x + button.width/2 - width/2), (int)(button.y + button.height/2 + height/2-2));
			//g.drawString(text, button.x + 2, button.y + 25);
		}
		g2.setColor(Color.BLACK);
		g2.draw(button);
    	
    	//load original color
    	g.setColor(temp);
    	g.setFont(tempF);
		
	}
}
