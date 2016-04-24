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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
public class Generator
{
	// x, y , num_Peg, rand, color array,, blue, green, orange, purple, red, and yellow.
	private int ori_x = 0;
	private int ori_y = 0;
	private int num_Peg;
	private boolean done = false;
	private BoardRow frame;
	//int font_Size = 20;  /// need to be changed later depending on the size of the num_Peg
	private Font q_Font;
	private String question = "?";
	

	private Color[] ans_Col;
	private Color[] pick = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(255,0,255) };
	public Generator(int x, int y, int num_Peg)
	{
		q_Font = new Font("Arial", Font.BOLD, 20);
		this.done = true;
		ori_x = x;
		ori_y = y;
		this.num_Peg = num_Peg;
		frame = new BoardRow(x, y, num_Peg);
		Random rand = new Random();
		ans_Col = new Color[num_Peg];
		for (int i = 0 ; i < num_Peg ; i++){
			frame.ColorPeg(i, Color.white);
			ans_Col[i] = pick[rand.nextInt(6)];
		}

	}
	
	
	public void Draw(Graphics2D g){
		
		
		if (done){
			for (int i = 0 ; i < num_Peg ; i++){
				frame.ColorPeg(i, ans_Col[i]);
			}
		}
		else {
			g.setFont(q_Font);
			g.setColor(Color.black);
			FontRenderContext context = g.getFontRenderContext();
			TextLayout layout = new TextLayout(question, q_Font, context);
			float width = layout.getAdvance();
			float height = layout.getAscent() + layout.getDescent();
			
			for (int i = 0; i < num_Peg ; i++){
				g.drawString(question, ori_x + (18*(i+1)-width/2), ori_y+(18-height/2));
			}
			
		}
		frame.Draw(g);
		
	}
	
	public void Done(){
		this.done = true;
	}
	
	public Color[] get_answer(){
		return ans_Col;
	}
	
}