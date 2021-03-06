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

public class FeedBack
{
	int ori_x = 0;
	int ori_y = 0;
	int num_Peg;
	int between = 2;
	Rectangle frame;
	ArrayList<Ellipse2D.Double> dots;
	int color_Match;
	int all_Match;
	int total_Match;
	int ball_Size = 14;
	int pushdown = 0;
	ArrayList<Color> answer_Array;
	ArrayList<Color> guess_Array;
	int num_Color;
	int[] num_Match;	//if all match = 2, color match = 1, none match = 0;
		// input guess array, input array)
		// output void , but should have draw method that will used by the main
		// 
	public FeedBack(int x, int y, int num, Color[] answer, Color[] guess)
	{
		ori_x = x;
		ori_y = y;
		this.pushdown = 0;
		this.frame = new Rectangle(x, y, 36, 36); 
		this.num_Peg = num;
		this.dots = new ArrayList<Ellipse2D.Double>();
		this.color_Match = 0;
		this.all_Match = 0;
		this.answer_Array = new ArrayList<Color>();
		this.guess_Array = new ArrayList<Color>();
		this.total_Match = 0;
		for (int i = 0; i < num_Peg ; i ++){
			answer_Array.add(answer[i]);
			guess_Array.add(guess[i]);
		}
		this.Ans_chk();			// check the answer
		
		if (num_Peg != 4){		// if number of peg is 4, peg size is 12, other wise different
			ball_Size = 8;
			between = 2;
			pushdown = 6;
		}
		for (int i = 0 ;  i < total_Match ; i ++){	//make dots and put into the arraylist
			Ellipse2D.Double dot = new Ellipse2D.Double(ori_x + between + (between*2 + ball_Size)*(i/2), // location
																	  ori_y + pushdown + between + (between*2 + ball_Size)*(i%2), ball_Size, ball_Size);
			dots.add(dot);
		}
		
	}
	
	public void Draw(Graphics2D g){
		
		g.setColor(Color.black);
		g.draw(frame);
		for (int i  = 0 ; i < all_Match ; i++){
			g.fill(dots.get(i));
			g.draw(dots.get(i));
		}
		g.setColor(Color.GRAY);
		for (int i  = all_Match ; i < total_Match ; i++){
			g.fill(dots.get(i));
			g.draw(dots.get(i));
		}
	}
	
	public void Ans_chk(){
		
		for (int i = 0; i < answer_Array.size() ; i++){				// find all match and get rid off all those are matched
			if( answer_Array.get(i).equals(guess_Array.get(i))) {
				all_Match +=1;
				answer_Array.remove(i);
				guess_Array.remove(i);
				i = i -1;
			}
		}
		
		for (int i = 0; i < answer_Array.size() ; i++){
			for (int j = 0; j < answer_Array.size() ; j ++){
				if (answer_Array.get(i).equals(guess_Array.get(j))){
					color_Match +=1;
					answer_Array.remove(i);
					guess_Array.remove(j);
					i = i -1;
					break;
				}
			}
		}
		
		total_Match = color_Match + all_Match;
	}
	
	public boolean Chk_all() {
		if (num_Peg == all_Match) return true;
		return false;
	}
}