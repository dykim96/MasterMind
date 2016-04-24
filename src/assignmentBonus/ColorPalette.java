package assignmentBonus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class ColorPalette {
	private final Color BROWN = new Color(165, 42, 42);
	private Rectangle row;
	private Ellipse2D[] pegColumns;
	private Color[] pegColors;
	private int selected;
	
	public ColorPalette(int x, int y){
		row = new Rectangle(x, y, 36 * 6, 36);
		pegColumns = new Ellipse2D[6];
		pegColors = new Color[6];
		for(int i = 0; i < 6; i++){
			Ellipse2D peg = new Ellipse2D.Double(x + 3 + 36*i, y + 3, 30, 30);
			pegColumns[i] = peg;
		}
		pegColors[0] = Color.RED;
		pegColors[1] = Color.ORANGE;
		pegColors[2] = Color.YELLOW;
		pegColors[3] = Color.GREEN;
		pegColors[4] = Color.BLUE;
		pegColors[5] = new Color(255, 0, 255);//violet
	}
	
	public Color GetColor(int index){
		if(index < pegColors.length){
			selected = index;
			return pegColors[index];
		}
		return Color.BLACK;
	}
	
	//Check if mouse is inside any of pegs and change color
	public Color Click(int x, int y){
		for(int i = 0; i < pegColumns.length; i++){
			if(pegColumns[i].contains(new Point(x, y))){
				selected = i;
				return pegColors[i];
			}
		}
		return Color.BLACK;
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
    	//draw square to indicate which color's selected
    	g2.setColor(Color.ORANGE);
    	g2.draw(new Rectangle(row.x + 1 + 36*selected, row.y + 1, 34, 34));
    	//load original color
    	g.setColor(temp);
	}
}
