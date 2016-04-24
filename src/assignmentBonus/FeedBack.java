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
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class FeedBack
{
	int num_Peg;
	Rectangle Frame;
	//Ellipse2D.Double Dots;
	ArrayList<Ellipse2D.Double> Dots;
	Color[] answer;
	Color[] guess;
	int color_match;
	int all_match;
		// input guess array, input array)
		// output void , but should have draw method that will used by the main
		// 
	public FeedBack(int x, int y, int num, Color[] answer, Color[] guess)
	{
		this.Frame = new Rectangle(x, y, 36, 36); 
		num_Peg = num;
		Dots = new ArrayList<Ellipse2D.Double>();
		this.answer = answer;
		this.guess = guess;
		color_match = 0;
		all_match = 0;
//		for (int i = 0 ;  i < num ; i ++){
//			
//			Ellipse2D.Double Dot = new Ellipse2D.Double
//			Dots.add()
//			
//		}
		
	}
	
	public void Draw(){
		
		
		
		
		
	}
	
	public void ans_chk(){
		for (int i= 0; i < num_Peg ; i++){
			if (answer[0].equals(guess[i])){
				all_match += 1;
			}
			
			
		}
		
		
		
	}
	
}
