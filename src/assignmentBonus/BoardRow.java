/*  Assignment Bonus: Master Mind
 *  Create game, Master Mind, with Java
 *  Section: 16185
 *  Name1: Doyoung Kim
 *  UTEID1: dk24338
 *  Name2: Hyo-Jung Kim
 *  UTEID2: hk6336
 */
package assignmentBonus;

import java.awt.*;
import java.awt.geom.*;

public class BoardRow {
	private Rectangle row;
	private Ellipse2D[] pegColumns;
	private Color[] pegColors;
	private final Color BROWN = new Color(165, 42, 42);
	public BoardRow(int x, int y, int size){
		row = new Rectangle(x, y, 36 * size, 36);
		pegColumns = new Ellipse2D[size];
		pegColors = new Color[size];
		for(int i = 0; i < size; i++){
			Ellipse2D peg = new Ellipse2D.Double(x + 3 + 36*i, y + 3, 30, 30);
			pegColumns[i] = peg;
			pegColors[i] = Color.BLACK;
		}
	}
	
	public boolean Click(int x, int y, Color c){
		for(int i = 0; i < pegColumns.length; i++){
			if(pegColumns[i].contains(new Point(x, y))){
				pegColors[i] = c;
				return true;
			}
		}
		return false;
	}
	
	public void Draw(Graphics g){
		//save original color
		Color temp = g.getColor();
		
    	Graphics2D g2 = (Graphics2D)g;
    	//fill
    	g2.setColor(BROWN);
    	g2.fill(row);
    	for(int i = 0; i < pegColumns.length; i++){
    		g2.setColor(pegColors[i]);
    		g2.fill(pegColumns[i]);
    	}
    	//draw outlines
    	g2.setColor(Color.black);
    	g2.draw(row);
    	for(int i = 0; i < pegColumns.length; i++){
    		g2.draw(pegColumns[i]);
    	}
    	
    	//load original color
    	g.setColor(temp);
	}
}
