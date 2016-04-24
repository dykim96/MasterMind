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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
public class Generator
{
	// x, y , num_Peg, rand, color array,, blue, green, orange, purple, red, and yellow.
	int ori_x = 0;
	int ori_y = 0;
	int num_Peg;
	boolean done = false;
	BoardRow frame;
	//ArrayList<Ellipse2D.Double> Dots;
	Color[] pick = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(255,0,255) };
	public Generator(int x, int y, int num_Peg)
	{
		this.done = false;
		ori_x = x;
		ori_y = y;
		this.num_Peg = num_Peg;
		frame = new BoardRow(x, y, num_Peg);
		Random rand = new Random();
		for (int i = 0 ; i < num_Peg ; i++){
			//adfasdfasdf
			//BoardRow.   pick[rand.nextInt(6)];
			//frame.
		}
		// TODO Auto-generated constructor stub
	}

}